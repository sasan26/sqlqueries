/*
	HOW TO UPDATE TIRES/WHEELS PRICES and QUANTITIES:
		0. Update the [netPrice_All] table first.
		1. Import raw data into [SAS-Raw-Import-Price] table . ( vendor partno, invooice price, MAP price )
		2. Import website tire export data into [SAS-Web-Import-Tire] table.
		3. Update @SALETYPE
		4. Update @OUTPUT  ( Display table(s) on Result window [SQL, Website or both] )
		5. Update @FINALSAP or @WHEELMAP ( Update final_SAP or WheelMap table )
		6. Update @PRODTYPE ( tires, wheels or qty )
			- To update products quantity :  first run 'FULL-Inventory-FTP-Update(NEW)' Query then change @PRODTYPE value to 'QTY'
			- TO update tires prices: change @PRODTYPE value to 'TIRE'
			- TO update wheels prices: change @PRODTYPE value to 'WHEEL' then run this query and finally run 'PriceUpdateWheels.sql' query
		7. Run this query.
*/


-- =========================================================================================================== --

DECLARE @SALETYPE INT = 4,
		@FINALSAP VARCHAR(3) = 'NO',       --  values: YES, NO
		@WHEELMAP VARCHAR(3) = 'YES',      --  values: YES, NO
		@OUTPUT VARCHAR(6) = 'SQLWEB',     --  values: SQL, WEB, SQLWEB
		@PRODTYPE NVARCHAR(5) = 'QTY'      --  values: TIRE, WHEEL, QTY

-- =========================================================================================================== --

-- TIRES
IF @PRODTYPE = 'TIRE'
BEGIN
    CREATE TABLE tempSQL (	
        vendorPartno VARCHAR(MAX),
        PARTNO VARCHAR(MAX),
        VENDERCOST DECIMAL(10,2),
        SALEPRICE DECIMAL(10,2),
        VENMARKEDUPPRICE DECIMAL(10,2),
        MAP DECIMAL(10,2),
        SALETYPE VARCHAR(1)
    );

    -- create a temporary table, convert all the vendors partno to our website. GEt the data from net price table.
    SELECT Partno, Brand, InvoicePrice, NetPrice
    INTO  tempPartnoCreator FROM [Reporting].[dbo].[netPrice_All];


    UPDATE tempPartnoCreator
    SET [Partno] = CASE	WHEN [Brand] = 'Nexen' THEN REPLACE([Partno],'XK','')
    ELSE [Partno]
    END

    UPDATE tempPartnoCreator
    SET [Partno] = CASE	WHEN [Brand] IN ('BFG','Michelin','Toyo','Continental') THEN [Partno]
                        WHEN [Brand] = 'Uniroyal' THEN REPLACE([Partno],'UNI-','UR-U')
                        WHEN [Brand] = 'Bridgestone' THEN REPLACE([Partno],'BR ','BT-')
                        WHEN [Brand] = 'Firestone' THEN REPLACE([Partno],'FS ','FIRESTONE-')
                        WHEN [Brand] = 'Kelly' THEN REPLACE([Partno],'KY ','KELLY-')
                        WHEN [Brand] = 'Hankook' THEN REPLACE([Partno],'HANK-','HT-')
                        WHEN [Brand] = 'Fuzion' THEN REPLACE([Partno],'FZ ','FUZION-')
                        WHEN [Brand] = 'Goodyear' THEN REPLACE([Partno],'GY ','GOODYEAR-')
                        WHEN [Brand] = 'Dunlop' THEN REPLACE([Partno],'DU ','DUNLOP-')
                        WHEN [Brand] = 'General' THEN REPLACE([Partno],[Partno],  CONCAT('GENERAL-', [Partno]  ) )
                        WHEN [Brand] = 'Falken' THEN REPLACE([Partno],[Partno],  CONCAT('FT-', [Partno]  ) )
                        WHEN [Brand] = 'Kumho' THEN REPLACE([Partno],[Partno],  CONCAT('KUMHO-', [Partno]  ) )
                        WHEN [Brand] = 'Nitto' THEN REPLACE([Partno],[Partno],  CONCAT('NIT-', [Partno]  ) )
                        WHEN [Brand] = 'Nexen' THEN REPLACE([Partno],'NEXN-','')
    END

    -- Insert all the required data into a temporary table to update the Final_SAP table.
    INSERT INTO tempSQL ( PARTNO, vendorPartno, SALETYPE )	-- Insert all the existing partno in our website
    SELECT [SAS-Web-Import-Tire].[Partno], [SAS-Web-Import-Tire].VENDORPARTNO, [SAS-Web-Import-Tire].[SALETYPE] FROM [SAS-Web-Import-Tire] INNER JOIN [SAS-Raw-Import-Price] ON [SAS-Raw-Import-Price].[VendorPartno] = [SAS-Web-Import-Tire].[VENDORPARTNO];

    UPDATE tempSQL  -- Update saleprice
    SET tempSQL.SALEPRICE =  [tempPartnoCreator].NetPrice
    FROM tempSQL, [tempPartnoCreator]
    WHERE tempSQL.PARTNO =  [tempPartnoCreator].Partno COLLATE DATABASE_DEFAULT;

    UPDATE tempSQL  -- Update invoice price and MAP price
    SET tempSQL.MAP = [SAS-Raw-Import-Price].[Map],
        tempSQL.VENDERCOST = [SAS-Raw-Import-Price].Price
    FROM tempSQL, [SAS-Raw-Import-Price]
    WHERE tempSQL.vendorPartno =  [SAS-Raw-Import-Price].VendorPartno COLLATE DATABASE_DEFAULT;


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
    SET  SALETYPE = @SALETYPE FROM tempSQL WHERE MAP IS NOT NULL; 

    UPDATE tempSQL
    SET MAP = NULL , SALETYPE = 0
    FROM tempSQL
    WHERE VENMARKEDUPPRICE > MAP; 

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
    SET SALESTART = CASE
                        WHEN  SALETYPE > 1 THEN '{ts ''2016-08-18 00:00:00''}'
                        ELSE ''
                    END,
        SALEEXP = CASE
                        WHEN  SALETYPE > 1 THEN '{ts ''2016-08-18 00:00:00''}'
                        ELSE ''
                    END,
        PRICE = CASE
                        WHEN SALETYPE > 1	THEN map
                        ELSE markedup
                    END,
        PRICE2 = CASE
                        WHEN SALETYPE > 1	THEN map
                        ELSE markedup
                    END,
        SALEPRICE = CASE
                        WHEN SALETYPE > 1	THEN markedup
                        ELSE NULL
                    END
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

    DROP TABLE tempSQL, tempWEB, tempPartnoCreator;
    --DELETE FROM [SAS-Raw-Import-Price];
    --DELETE FROM [SAS-Web-Import-Tire];
END

--  WHEELS 
 IF @PRODTYPE = 'WHEEL'
BEGIN
	CREATE TABLE tempSQL (	
        vendorPartno VARCHAR(MAX),
        PARTNO VARCHAR(MAX),
		VENDOR VARCHAR(MAX),
		WHEELDIAMETER INT,
		WHEELWIDTH DECIMAL (4,1),
        OURCOST DECIMAL(10,2),
        PRICE DECIMAL(10,2),
        PRICE2 DECIMAL(10,2),
		SALETYPE VARCHAR(1),
		dropshipper VARCHAR(MAX)
    );


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



	-- Insert all the required data into a temporary table.
    INSERT INTO tempSQL ( PARTNO, vendorPartno, VENDOR, WHEELDIAMETER, WHEELWIDTH, dropshipper )	
    SELECT [SAS-Web-Import-Wheel].[Partno], [SAS-Web-Import-Wheel].VENDORPARTNO, [SAS-Web-Import-Wheel].[DROPSHIPPER], [SAS-Web-Import-Wheel].WHEELDIAMETER, [SAS-Web-Import-Wheel].WHEELWIDTH, [SAS-Web-Import-Wheel].DROPSHIPPER  FROM [SAS-Web-Import-Wheel] INNER JOIN [SAS-Raw-Import-Price] ON [SAS-Raw-Import-Price].[VendorPartno] = [SAS-Web-Import-Wheel].[VENDORPARTNO];


	UPDATE tempSQL
	SET OURCOST = [SAS-Raw-Import-Price].Price
	FROM [SAS-Raw-Import-Price], tempSQL
	WHERE [SAS-Raw-Import-Price].VendorPartno = tempSQL.PARTNO;


	INSERT INTO tempWEB ( PARTNO, PRICE, PRICE2, wheeldiameter, dropshipper )	
    SELECT  PARTNO, OURCOST, OURCOST, WHEELDIAMETER, dropshipper
	FROM tempSQL;

	UPDATE tempWEB 
	SET [tempWEB].map = [SAS-Raw-Import-Price].[MAP] 
	FROM tempWEB, [SAS-Raw-Import-Price]
	WHERE [SAS-Raw-Import-Price].VendorPartno = tempWEB.PARTNO;

	-- Calculate Markedup price
	UPDATE tempWEB
    SET  SALETYPE = CASE WHEN MAP IS NOT NULL THEN @SALETYPE ELSE 0 END,
		 markedup = CASE WHEN wheeldiameter <= 16 THEN 30 + PRICE
						 WHEN wheeldiameter = 17 THEN 35 + PRICE
						 WHEN wheeldiameter = 18 THEN 40 + PRICE
						 WHEN wheeldiameter = 19 THEN 40 + PRICE
						 WHEN wheeldiameter = 20 THEN 50 + PRICE
						 WHEN wheeldiameter = 21 THEN 50 + PRICE
						 WHEN wheeldiameter = 22 THEN 60 + PRICE
						 WHEN wheeldiameter = 24 THEN 70 + PRICE
						 WHEN wheeldiameter = 26 THEN 80 + PRICE
						 WHEN wheeldiameter = 28 THEN 100 + PRICE
						 WHEN wheeldiameter = 30 THEN 120 + PRICE
						 WHEN wheeldiameter = 32 THEN 140 + PRICE
						 END
	
	FROM tempWEB;

	UPDATE tempWEB
    SET SALESTART = CASE
                        WHEN  SALETYPE > 1 THEN '{ts ''2016-08-18 00:00:00''}'
                        ELSE ''
                    END,
        SALEEXP = CASE
                        WHEN  SALETYPE > 1 THEN '{ts ''2016-08-18 00:00:00''}'
                        ELSE ''
                    END,
		SALEPRICE = CASE
						WHEN SALETYPE > 1 THEN markedup ELSE null END,

		markedup = CASE 
						WHEN dropshipper IN ('WheelGroup','WheelPros','Vision','MHT','TSW','Prestige') AND wheeldiameter <= 20 THEN markedup + 8
						WHEN dropshipper IN ('WheelGroup','WheelPros','Vision','MHT','TSW','Prestige') AND wheeldiameter > 20 THEN markedup + 12
						ELSE markedup
						END

        FROM tempWEB;

		UPDATE tempWEB
		SET map = CASE WHEN markedup > map THEN NULL ELSE map END,
			SALESTART = CASE WHEN markedup > map THEN '' ELSE SALESTART END,
			SALEEXP = CASE WHEN markedup > map THEN '' ELSE SALEEXP END,
			PRICE = CASE WHEN markedup > map THEN markedup ELSE map END,
			PRICE2 = CASE WHEN markedup > map THEN markedup ELSE map END
		FROM tempWEB;


		-- Update WheelMAP table MAP price
		IF @WHEELMAP = 'YES'
		BEGIN
			UPDATE [WheelMAP]
			SET 
				[WheelMAP].[PRICE] = tempWEB.map,
				[WheelMAP].[PRICE2] = tempWEB.map,
				[WheelMAP].[SALEPRICE] = tempWEB.markedup,
				[WheelMAP].[SALETYPE] = @SALETYPE,
				[WheelMAP].[SALESTART] = '{ts ''2016-08-18 00:00:00''}',
				[WheelMAP].[SALEEXP] = '{ts ''2025-08-01 00:00:00''}'

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
    --DELETE FROM [SAS-Raw-Import-Price];
    --DELETE FROM [SAS-Web-Import-Wheel];


END

-- Update Quantity
IF @PRODTYPE = 'QTY'
BEGIN

DECLARE @min INT = 8

SELECT SUM(Qty) AS TotalQuantity, Partno, Fetcher INTO temptable
  FROM [Dropshipper_Inventory]
  GROUP BY Fetcher, Partno
  ORDER BY Partno;

INSERT INTO [InventoryQuantity] ( [PARTNO], [VENDORPARTNO], [DROPSHIPPER], [QTYAVAIL], [PRODUCTTYPE] )
SELECT [PARTNO] ,[VENDORPARTNO] ,[DROPSHIPPER] ,[QTYAVAIL] ,'WHEEL'  
FROM [SAS-Web-Import-Wheel];

INSERT INTO [InventoryQuantity] ( [PARTNO], [VENDORPARTNO], [DROPSHIPPER], [QTYAVAIL], [PRODUCTTYPE] )
SELECT [PARTNO] ,[VENDORPARTNO] ,[DROPSHIPPER] ,[QTYAVAIL] ,'TIRE'  
FROM [SAS-Web-Import-Tire];

UPDATE [InventoryQuantity]
SET INVENTORY =  [TotalQuantity]					 
				
FROM [InventoryQuantity], [temptable]
WHERE [InventoryQuantity].[PARTNO] = [temptable].[Partno] COLLATE DATABASE_DEFAULT AND 
	  [InventoryQuantity].[DROPSHIPPER] = [temptable].[Fetcher] COLLATE DATABASE_DEFAULT;

UPDATE [InventoryQuantity]
SET QTYAVAIL =  CASE 
						WHEN INVENTORY >= @min THEN 1
						ELSE 0
				END

FROM [InventoryQuantity], [temptable];
SELECT [PARTNO],[QTYAVAIL] FROM [Tire Database].[dbo].[InventoryQuantity] ORDER BY PARTNO ;


DROP TABLE temptable;
DELETE FROM [InventoryQuantity];

END