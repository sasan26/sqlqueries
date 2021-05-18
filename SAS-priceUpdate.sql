/*
	HOW TO UPDATE TIRES/WHEELS PRICES:
		1. DELETE FROM [SAS-Raw-Import-Price];
		2. DELETE FROM [SAS-Web-Import-Tire]; DELETE FROM [SAS-Web-Import-Wheel];

		3. Import raw data into [SAS-Raw-Import-Price] table . ( vendor partno, invoice price, MAP price, NET price and Dropshipper )
		4. Import website tire & wheel export data into [SAS-Web-Import-Tire] and [SAS-Web-Import-Wheel] tables (data type: varchar).
		5. Set @vendor value to NTW, Tirehub, ATD or other
		6. Set @OUTPUT  ( Display table(s) on Result window [SQL, Website or both] )
		7. Set @FINALSAP or @WHEELMAP ( Update final_SAP or WheelMap table )
		8. Set @PRODTYPE ( tires or wheels )
			- TO update tires prices: change @PRODTYPE value to 'TIRE'
			- TO update wheels prices: change @PRODTYPE value to 'WHEEL' then after step 9 execute 'PriceUpdateWheels.sql' query!
		9. Execute.
*/


-- ======================================================== Variables ================================================================ --

DECLARE 
		-- values: YES, NO
		@FINALSAP VARCHAR(3) = 'NO',   
		@WHEELMAP VARCHAR(3) = 'NO', 
		
		-- values: SQL, WEB, SQLWEB
		@OUTPUT VARCHAR(6) = 'SQLWEB',       

		-- values: TIRE, WHEEL
		@PRODTYPE NVARCHAR(5) = 'WHEEL',      

		-- values: NTW, Tirehub, ATD, other
		@vendor NVARCHAR(20) = 'other',		 

		-- Sale-start & Sale-expire variables
		@SALEST NVARCHAR(MAX) = '{ts ''2016-01-01 00:00:00''}',
		@SALEXP NVARCHAR(MAX) = '{ts ''2025-08-01 00:00:00''}'

-- =================================================================================================================================== --




-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--                                                                                                                                                                                                         --
--                                                                                                 TIRES                                                                                                   --
--                                                                                                                                                                                                         --
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

IF @PRODTYPE = 'TIRE'
BEGIN
    CREATE TABLE tempSQL (	
        vendorPartno NVARCHAR(255),
        PARTNO NVARCHAR(255),
		Price2 DECIMAL(10,2),
		SalesStart NVARCHAR(50),
		SalesExp NVARCHAR(50),
        VENDERCOST DECIMAL(10,2),
        SALEPRICE DECIMAL(10,2),
        VENMARKEDUPPRICE DECIMAL(10,2),
        MAP DECIMAL(10,2),
        SALETYPE NVARCHAR(1),
		Dropshipper NVARCHAR(255),
    );

  

    -- Insert all the required data into a temporary table 
    INSERT INTO tempSQL ( PARTNO, vendorPartno, SALETYPE, Price2, SalesStart, SalesExp, Dropshipper, MAP )	-- Insert all the existing partno in our website
    SELECT  [SAS-Web-Import-Tire].[Partno], 
			[SAS-Web-Import-Tire].VENDORPARTNO, 
			[SAS-Web-Import-Tire].[SALETYPE],
			[SAS-Web-Import-Tire].[Price2],
			[SAS-Web-Import-Tire].[SALESTART],
			[SAS-Web-Import-Tire].[SALEEXP],
			[SAS-Web-Import-Tire].[DROPSHIPPER],
			[SAS-Web-Import-Tire].[PRICE2]
			
			FROM [SAS-Web-Import-Tire] INNER JOIN [SAS-Raw-Import-Price] ON [SAS-Raw-Import-Price].[VendorPartno] = [SAS-Web-Import-Tire].[VENDORPARTNO];


    UPDATE tempSQL  -- Update invoice price and MAP price
    SET tempSQL.MAP = CASE
							WHEN [SAS-Raw-Import-Price].[Map] IS NOT NULL THEN [SAS-Raw-Import-Price].[Map]
							WHEN [SAS-Raw-Import-Price].[Map] IS NULL THEN tempSQL.MAP		
					  END,
        tempSQL.VENDERCOST = [SAS-Raw-Import-Price].Price
    FROM tempSQL, [SAS-Raw-Import-Price], [SAS-Web-Import-Tire]
    WHERE tempSQL.vendorPartno =  [SAS-Raw-Import-Price].VendorPartno COLLATE DATABASE_DEFAULT;


	-- ******************************************************************************  UPDATE NET PRICE  ******************************************************************************


	-- update net price
	UPDATE tempSQL  	
	SET tempSQL.SALEPRICE = [SAS-Raw-Import-Price].[NET] 
    FROM tempSQL, [SAS-Raw-Import-Price]
    WHERE tempSQL.vendorPartno =  [SAS-Raw-Import-Price].VendorPartno COLLATE DATABASE_DEFAULT;


	----------------------------------------------------------------------
	--                          NTW NET Price                           --
	----------------------------------------------------------------------

	IF @vendor = 'NTW'
	BEGIN
		DECLARE 
		-- BFGoodrich discounts
		@BFG_RCB DECIMAL(10,2) = 9.50,		-- RCB (BFGoodrich)
		@BFG_BDF DECIMAL(10,2) = 3.00,		-- BDF (BFGoodrich)
		@BFG_rew DECIMAL(10,2) = 2.00,		-- NTW Total Reward (BFGoodrich)

		-- Hankook discounts
		@HAN_reb DECIMAL(10,2) = 8.00,		-- Volume Rebate (Hankook)
		@HAN_rew DECIMAL(10,2) = 2.00,		-- NTW Total Reward (Hankook)

		-- Michelin discounts
		@MIC_RCB DECIMAL(10,2) = 9.50,		-- RCB (Michelin)
		@MIC_BDF DECIMAL(10,2) = 3.00,		-- BDF (Michelin)
		@MIC_rew DECIMAL(10,2) = 2.00,		-- NTW Total Reward (Michelin)

		-- Uniroyal discounts 
		@UNI_RCB DECIMAL(10,2) = 9.50,		-- RCB (Uniroyal)
		@UNI_BDF DECIMAL(10,2) = 3.00,		-- BDF (Uniroyal)
		@UNI_rew DECIMAL(10,2) = 2.00,		-- NTW Total Reward (Uniroyal)

		-- Nexen discounts
		@NEX_bon DECIMAL(10,2) = 5.00,		-- Volume Bonus (Nexen)
		@NEX_rew DECIMAL(10,2) = 2.00,		-- NTW Total Reward (Nexen)

		-- Toyo discounts
		@TOY_rew DECIMAL(10,2) = 2.00		-- NTW Total Reward (Toyo)


		-- Calculate invoice price
		UPDATE [SAS-NET-NTW]
		SET invoice = tempSQL.VENDERCOST
		FROM [SAS-NET-NTW], tempSQL
		WHERE [SAS-NET-NTW].partno = tempSQL.PARTNO COLLATE DATABASE_DEFAULT;

		-- Calculate discounts
		UPDATE [SAS-NET-NTW]
		SET discount1 = CASE WHEN brand IN ('BFG', 'bfgoodrich') THEN invoice * (@BFG_RCB / 100) 
							 WHEN brand IN ('Hankook') THEN @HAN_reb
							 WHEN brand IN ('Michelin') THEN invoice * (@MIC_RCB/100)
							 WHEN brand IN ('Uniroyal') THEN invoice * (@UNI_RCB / 100) 
							 WHEN brand IN ('Nexen') THEN @NEX_bon
							 WHEN brand IN ('Toyo') THEN invoice * (@TOY_rew/100)
							 END,

			discount2 = CASE WHEN brand IN ('BFG', 'bfgoodrich') THEN invoice * (@BFG_BDF / 100) 
							 WHEN brand IN ('Hankook') THEN invoice * (@HAN_rew / 100)
							 WHEN brand IN ('Michelin') THEN invoice * (@MIC_BDF/100)
							 WHEN brand IN ('Uniroyal') THEN invoice * (@UNI_BDF / 100) 
							 WHEN brand IN ('Nexen') THEN invoice * (@NEX_rew/100)
							 WHEN brand IN ('Toyo') THEN 0
							 END,

			discount3 = CASE WHEN brand IN ('BFG', 'bfgoodrich') THEN invoice * (@BFG_rew / 100) 
							 WHEN brand IN ('Hankook', 'Nexen') THEN 0
							 WHEN brand IN ('Michelin') THEN invoice * (@MIC_rew/100)
							 WHEN brand IN ('Uniroyal') THEN invoice * (@UNI_rew / 100) 
							 WHEN brand IN ('Toyo') THEN 0
							 END

		FROM [SAS-NET-NTW], tempSQL
		WHERE [SAS-NET-NTW].partno = tempSQL.PARTNO COLLATE DATABASE_DEFAULT;

		-- Calculate total-discount & NET price
		UPDATE [SAS-NET-NTW]
		SET totaldiscount = discount1 + discount2 + discount3 + discount4 + discount5,
			net = invoice - (discount1 + discount2 + discount3 + discount4 + discount5)
		FROM [SAS-NET-NTW]

		UPDATE tempSQL  	
		SET tempSQL.SALEPRICE = [SAS-NET-NTW].[NET] 
		FROM tempSQL, [SAS-NET-NTW]
		WHERE tempSQL.PARTNO =  [SAS-NET-NTW].partno COLLATE DATABASE_DEFAULT;

	END

	----------------------------------------------------------------------
	--                       Tire Hub NET Price                          -
	----------------------------------------------------------------------
	ELSE IF @vendor = 'Tirehub'
	BEGIN
		-- Calculate invoice price
		UPDATE [SAS-NET-Tirehub]
		SET invoice = tempSQL.VENDERCOST
		FROM [SAS-NET-Tirehub], tempSQL
		WHERE [SAS-NET-Tirehub].partno = tempSQL.PARTNO COLLATE DATABASE_DEFAULT;

		-- Calculate NET price
		UPDATE [SAS-NET-Tirehub]
		SET net = invoice - totaldiscount
		FROM [SAS-NET-Tirehub], tempSQL
		WHERE [SAS-NET-Tirehub].partno = tempSQL.PARTNO COLLATE DATABASE_DEFAULT;

		UPDATE tempSQL  	
		SET tempSQL.SALEPRICE = [SAS-NET-Tirehub].[NET] 
		FROM tempSQL, [SAS-NET-Tirehub]
		WHERE tempSQL.PARTNO =  [SAS-NET-Tirehub].partno COLLATE DATABASE_DEFAULT;

	END

	----------------------------------------------------------------------
	--                          ATD NET Price                            -
	----------------------------------------------------------------------

	ELSE IF @vendor = 'ATD'
	BEGIN
		-- Calculate invoice price
		UPDATE [SAS-NET-ATD]
		SET invoice = tempSQL.VENDERCOST
		FROM [SAS-NET-ATD], tempSQL
		WHERE [SAS-NET-ATD].partno = tempSQL.PARTNO COLLATE DATABASE_DEFAULT;

		-- Calculate NET price
		UPDATE [SAS-NET-ATD]
		SET net = invoice - totaldiscount
		FROM [SAS-NET-ATD], tempSQL
		WHERE [SAS-NET-ATD].partno = tempSQL.PARTNO COLLATE DATABASE_DEFAULT;

		UPDATE tempSQL  	
		SET tempSQL.SALEPRICE = [SAS-NET-ATD].[NET] 
		FROM tempSQL, [SAS-NET-ATD]
		WHERE tempSQL.PARTNO =  [SAS-NET-ATD].partno COLLATE DATABASE_DEFAULT;

	END
	-- **********************************************************************************************************************************************************************************


    UPDATE tempSQL	-- calculate the Markedup Price
    SET VENMARKEDUPPRICE = CASE	
                                WHEN SALEPRICE IS NOT NULL AND SALEPRICE > 200 THEN SALEPRICE * 1.1
                                WHEN SALEPRICE IS NOT NULL AND SALEPRICE <= 100 THEN SALEPRICE * 1.2
                                WHEN SALEPRICE IS NOT NULL AND SALEPRICE > 100 AND SALEPRICE <= 200 THEN SALEPRICE * 1.15
                                WHEN SALEPRICE IS NULL AND VENDERCOST > 200 THEN VENDERCOST * 1.1
                                WHEN SALEPRICE IS NULL AND VENDERCOST <= 100 THEN VENDERCOST * 1.2
                                WHEN SALEPRICE IS NULL AND VENDERCOST > 100 AND VENDERCOST <= 200 THEN VENDERCOST * 1.15
							END
    FROM tempSQL;


    UPDATE tempSQL
    SET MAP = NULL , SALETYPE = 0
    FROM tempSQL
    WHERE VENMARKEDUPPRICE > MAP; 

	-- update finalSAP table
    IF @FINALSAP = 'YES'
    BEGIN
        UPDATE Final_Sap
        SET 
        [Final_SAP].[VENDERCOST] = tempSQL.[VENDERCOST],
        [Final_SAP].[DSVendorCost] = tempSQL.[VENDERCOST],
        [Final_Sap].[SalePrice] = tempSQL.[SALEPRICE],
        [Final_Sap].[VENMARKEDUPPRICE] = tempSQL.[VENMARKEDUPPRICE],
        [Final_Sap].[DSVendorMarkedUpPrice] = tempSQL.[VENMARKEDUPPRICE]

        FROM [Final_Sap],tempSQL
        WHERE tempSQL.PARTNO=Final_Sap.PARTNO;
    END

    CREATE TABLE tempWEB (	
        PARTNO VARCHAR(MAX),
        PRICE DECIMAL(10,2),
        PRICE2 DECIMAL(10,2),
        SALEPRICE DECIMAL(10,2),
        SALETYPE INT, 
        SALESTART VARCHAR(MAX),
        SALEEXP VARCHAR(MAX),
        map DECIMAL(10,2),
        markedup DECIMAL(10,2),
    );

    INSERT INTO tempWEB (PARTNO, PRICE, PRICE2, SALEPRICE, SALETYPE, map, markedup)
    SELECT PARTNO, VENMARKEDUPPRICE, VENMARKEDUPPRICE, SALEPRICE, SALETYPE, MAP, VENMARKEDUPPRICE
    FROM tempSQL;

    UPDATE tempWEB
    SET SALESTART = CASE WHEN SALETYPE > 1 THEN @SALEST ELSE '' END,
        SALEEXP = CASE WHEN SALETYPE > 1 THEN @SALEXP ELSE '' END,
        PRICE = CASE WHEN SALETYPE > 1 THEN map ELSE markedup END,
        PRICE2 = CASE WHEN SALETYPE > 1	THEN map ELSE markedup END,
        SALEPRICE = CASE WHEN SALETYPE > 1	THEN markedup ELSE NULL END
    FROM tempWEB;

    IF @OUTPUT = 'WEB'
    BEGIN
        SELECT PARTNO, PRICE, PRICE2, SALEPRICE, SALETYPE, SALESTART, SALEEXP FROM tempWEB ORDER BY SALETYPE;
    END
    ELSE IF @OUTPUT = 'SQL'
    BEGIN
        SELECT PARTNO, VENDERCOST, SALEPRICE, VENMARKEDUPPRICE, MAP FROM tempSQL;
    END
    ELSE
    BEGIN
        SELECT PARTNO, PRICE, PRICE2, SALEPRICE, SALETYPE, SALESTART, SALEEXP FROM tempWEB ORDER BY SALETYPE;
        SELECT PARTNO, VENDERCOST, SALEPRICE, VENMARKEDUPPRICE, MAP FROM tempSQL;
    END

    DROP TABLE tempSQL, tempWEB;
END




-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
--                                                                                                                                                                                                         --
--                                                                                                 WHEELS                                                                                                  --
--                                                                                                                                                                                                         --
-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

 IF @PRODTYPE = 'WHEEL'
BEGIN
	CREATE TABLE tempSQL (	
        vendorPartno NVARCHAR(255),
        PARTNO NVARCHAR(255),
		VENDOR NVARCHAR(255),
		WHEELDIAMETER DECIMAL(10,2),
		WHEELWIDTH DECIMAL(10,2),
        OURCOST DECIMAL(10,2),
        PRICE DECIMAL(10,2),
        PRICE2 DECIMAL(10,2),
		SALETYPE INT,
		dropshipper NVARCHAR(255),
		map DECIMAL(10,2)
    );


	-- Insert all the required data into a temporary table.
    INSERT INTO tempSQL ( PARTNO, vendorPartno, VENDOR, WHEELDIAMETER, WHEELWIDTH, dropshipper, SALETYPE, map )	
    SELECT [SAS-Web-Import-Wheel].[Partno], 
	[SAS-Web-Import-Wheel].VENDORPARTNO, 
	[SAS-Web-Import-Wheel].[DROPSHIPPER], 
	[SAS-Web-Import-Wheel].WHEELDIAMETER, 
	[SAS-Web-Import-Wheel].WHEELWIDTH, 
	[SAS-Web-Import-Wheel].DROPSHIPPER,
	[SAS-Web-Import-Wheel].SALETYPE,
	[SAS-Web-Import-Wheel].[PRICE2] 
	FROM [SAS-Web-Import-Wheel] INNER JOIN [SAS-Raw-Import-Price] ON [SAS-Raw-Import-Price].[VendorPartno] = [SAS-Web-Import-Wheel].[VENDORPARTNO];

	UPDATE tempSQL  -- Update invoice price and MAP price
    SET tempSQL.MAP = CASE
							WHEN [SAS-Raw-Import-Price].[Map] IS NOT NULL THEN [SAS-Raw-Import-Price].[Map]
							ELSE tempSQL.MAP			
					  END,
        OURCOST = [SAS-Raw-Import-Price].Price
    FROM tempSQL, [SAS-Raw-Import-Price], [SAS-Web-Import-Wheel]
    WHERE tempSQL.vendorPartno =  [SAS-Raw-Import-Price].VendorPartno COLLATE DATABASE_DEFAULT; 


	CREATE TABLE tempWEB (	
        PARTNO VARCHAR(MAX),
        PRICE DECIMAL(10,2),
        PRICE2 DECIMAL(10,2),
        SALEPRICE VARCHAR(MAX),
        SALETYPE INT, 
        SALESTART VARCHAR(MAX),
        SALEEXP VARCHAR(MAX),
        map DECIMAL(10,2),
        markedup DECIMAL(10,2),
		wheeldiameter INT,
		dropshipper VARCHAR(MAX)
    );


	INSERT INTO tempWEB ( PARTNO, PRICE, PRICE2, wheeldiameter, dropshipper, map, SALETYPE )	
    SELECT  PARTNO, OURCOST, OURCOST, WHEELDIAMETER, dropshipper, map, SALETYPE
	FROM tempSQL;

	

	-- Calculate Markedup price
	UPDATE tempWEB
    SET  markedup = CASE WHEN wheeldiameter <= 16 THEN 30 + PRICE
						 WHEN wheeldiameter = 17 THEN 35 + PRICE
						 WHEN wheeldiameter IN (18, 19) THEN 40 + PRICE
						 WHEN wheeldiameter IN (20, 21) THEN 50 + PRICE
						 WHEN wheeldiameter = 22 THEN 60 + PRICE
						 WHEN wheeldiameter = 24 THEN 70 + PRICE
						 WHEN wheeldiameter = 26 THEN 80 + PRICE
						 WHEN wheeldiameter = 28 THEN 100 + PRICE
						 WHEN wheeldiameter = 30 THEN 120 + PRICE
						 WHEN wheeldiameter = 32 THEN 140 + PRICE
						 END
	
	FROM tempWEB;

	UPDATE tempWEB
    SET SALETYPE = 4 
    FROM tempWEB
    WHERE MAP > 0;

	UPDATE tempWEB
    SET MAP = NULL , SALETYPE = 0
    FROM tempWEB
    WHERE markedup > MAP;

	UPDATE tempWEB
    SET SALESTART = CASE WHEN  SALETYPE > 1 THEN @SALEST ELSE '' END,
        SALEEXP = CASE WHEN SALETYPE > 1 THEN @SALEXP ELSE '' END,
		SALEPRICE = CASE WHEN SALETYPE > 1 THEN markedup ELSE null END,
		markedup = CASE 
						WHEN dropshipper IN ('WheelGroup','WheelPros','Vision','MHT','TSW','Prestige') AND wheeldiameter <= 20 THEN markedup + 8
						WHEN dropshipper IN ('WheelGroup','WheelPros','Vision','MHT','TSW','Prestige') AND wheeldiameter > 20 THEN markedup + 12
						ELSE markedup END
        FROM tempWEB;

		UPDATE tempWEB
		SET 
			SALESTART = CASE WHEN SALETYPE < 2 THEN '' ELSE SALESTART END,
			SALEEXP = CASE WHEN SALETYPE < 2 THEN '' ELSE SALEEXP END,
			PRICE = CASE WHEN SALETYPE < 2 THEN markedup ELSE map END,
			PRICE2 = CASE WHEN SALETYPE < 2 THEN markedup ELSE map END
		FROM tempWEB;


		-- Update WheelMAP table MAP price
		IF @WHEELMAP = 'YES'
		BEGIN
			UPDATE [WheelMAP]
			SET 
				[WheelMAP].[PRICE] = tempWEB.map,
				[WheelMAP].[PRICE2] = tempWEB.map,
				[WheelMAP].[SALEPRICE] = tempWEB.markedup,
				[WheelMAP].[SALETYPE] = tempWEB.SALETYPE,
				[WheelMAP].[SALESTART] = @SALEST,
				[WheelMAP].[SALEEXP] = @SALEXP

			FROM [WheelMAP],tempWEB
			WHERE tempWEB.PARTNO=[WheelMAP].[PARTNO] COLLATE DATABASE_DEFAULT
		
			-- Update [WheelsCostUpdate] table 
			DELETE FROM [WheelsCostUpdate];
			INSERT INTO [WheelsCostUpdate] ( [PARTNO], [VENDOR], [WHEELDIAMETER], [WHEELWIDTH], [OURCOST], [Price], [Price2] )
			SELECT PARTNO, VENDOR, WHEELDIAMETER, WHEELWIDTH, OURCOST, PRICE, PRICE2 FROM tempSQL;
		END


	IF @OUTPUT = 'WEB'
    BEGIN
        SELECT PARTNO, PRICE, PRICE2, SALEPRICE, SALETYPE, SALESTART, SALEEXP FROM tempWEB ORDER BY SALETYPE;
    END
    ELSE IF @OUTPUT = 'SQL'
    BEGIN
        SELECT PARTNO, VENDOR, WHEELDIAMETER, WHEELWIDTH, OURCOST, PRICE, PRICE2 FROM tempSQL;
    END
    ELSE
    BEGIN
        SELECT PARTNO, PRICE, PRICE2, SALEPRICE, SALETYPE, SALESTART, SALEEXP FROM tempWEB ORDER BY SALETYPE;
        SELECT PARTNO, VENDOR, WHEELDIAMETER, WHEELWIDTH, OURCOST, PRICE, PRICE2 FROM tempSQL;
    END


	DROP TABLE tempSQL, tempWEB;
END


