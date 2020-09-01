
DECLARE @Yokohama VARCHAR(10) = 'Yokohama'
DECLARE @AMP VARCHAR(10) = 'AMP'
DECLARE @Falken VARCHAR(10) = 'Falken'
DECLARE @Hankook VARCHAR(10) = 'Hankook'
DECLARE @Sumitomo VARCHAR(10) = 'Sumitomo'

DELETE FROM [MAP-ALL]

-- BFG-Mich-Uni
INSERT INTO [MAP-ALL] (PARTNO, MPN, DSPartno, MAP, Brand)
SELECT [Final_Sap-TEST].PARTNO, [Final_Sap-TEST].MPN, [Final_Sap-TEST].DSPartNo, [MAP-RAW-BFG-Mich-Uni-NTW].[IUMAP Single Tire],  [MAP-RAW-BFG-Mich-Uni-NTW].Brand
FROM [MAP-RAW-BFG-Mich-Uni-NTW]
INNER JOIN [Tire Database].[dbo].[Final_Sap-TEST] ON [MAP-RAW-BFG-Mich-Uni-NTW].MSPN = [Final_Sap-TEST].MPN  COLLATE DATABASE_DEFAULT;

-- Conti - Gen
INSERT INTO [MAP-ALL] (PARTNO, MPN, DSPartno, MAP, Brand)
SELECT [Final_Sap-TEST].PARTNO, [Final_Sap-TEST].MPN, [Final_Sap-TEST].DSPartNo, [MAP-RAW-Conti-Gen-ATD].[MAP],  [MAP-RAW-Conti-Gen-ATD].Brand
FROM [MAP-RAW-Conti-Gen-ATD]
INNER JOIN [Tire Database].[dbo].[Final_Sap-TEST] ON [MAP-RAW-Conti-Gen-ATD].[Article ] = [Final_Sap-TEST].MPN  COLLATE DATABASE_DEFAULT;

-- Falken
INSERT INTO [MAP-ALL] (PARTNO, MPN, DSPartno, MAP, Brand)
SELECT [Final_Sap-TEST].PARTNO, [Final_Sap-TEST].MPN, [Final_Sap-TEST].DSPartNo, [MAP-RAW-Falken-ATD].[MAP - 3/1/2020], @Falken
FROM [MAP-RAW-Falken-ATD]
INNER JOIN [Tire Database].[dbo].[Final_Sap-TEST] ON [MAP-RAW-Falken-ATD].PRODUCT = [Final_Sap-TEST].MPN  COLLATE DATABASE_DEFAULT;

--Goodyear-Dunlop-Kelly
INSERT INTO [MAP-ALL] (PARTNO, MPN, DSPartno, MAP, Brand)
SELECT [Final_Sap-TEST].PARTNO, [Final_Sap-TEST].MPN, [Final_Sap-TEST].DSPartNo, [MAP-RAW-Goodyear-Dunlop-Kelly-TireHub].[MAP EFFECTIVE 4-1-20],  [MAP-RAW-Goodyear-Dunlop-Kelly-TireHub].Brand
FROM [MAP-RAW-Goodyear-Dunlop-Kelly-TireHub]
INNER JOIN [Tire Database].[dbo].[Final_Sap-TEST] ON [MAP-RAW-Goodyear-Dunlop-Kelly-TireHub].[Product Code] = [Final_Sap-TEST].MPN  COLLATE DATABASE_DEFAULT;

-- Hankook
INSERT INTO [MAP-ALL] (PARTNO, MPN, DSPartno, MAP, Brand)
SELECT [Final_Sap-TEST].PARTNO, [Final_Sap-TEST].MPN, [Final_Sap-TEST].DSPartNo, [MAP-RAW-Hankook-NTW].MAP, @Hankook
FROM [MAP-RAW-Hankook-NTW]
INNER JOIN [Tire Database].[dbo].[Final_Sap-TEST] ON [MAP-RAW-Hankook-NTW].[S-Code] = [Final_Sap-TEST].MPN  COLLATE DATABASE_DEFAULT;

-- Sumitomo
INSERT INTO [MAP-ALL] (PARTNO, MPN, DSPartno, MAP, Brand)
SELECT [Final_Sap-TEST].PARTNO, [Final_Sap-TEST].MPN, [Final_Sap-TEST].DSPartNo, [MAP-RAW-Sumitomo-TW].[MAP Price], @Sumitomo
FROM [MAP-RAW-Sumitomo-TW]
INNER JOIN [Tire Database].[dbo].[Final_Sap-TEST] ON [MAP-RAW-Sumitomo-TW].[Product_Code] = [Final_Sap-TEST].MPN  COLLATE DATABASE_DEFAULT;

--Yokohama
INSERT INTO [MAP-ALL] (PARTNO, MPN, DSPartno, MAP, Brand)
SELECT [Final_Sap-TEST].PARTNO, [Final_Sap-TEST].MPN, [Final_Sap-TEST].DSPartNo, [MAP-RAW-Yokohama-TW].[MAP], @Yokohama
FROM [MAP-RAW-Yokohama-TW]
INNER JOIN [Tire Database].[dbo].[Final_Sap-TEST] ON [MAP-RAW-Yokohama-TW].[SAP Material] = [Final_Sap-TEST].MPN  COLLATE DATABASE_DEFAULT;

-- AMP
INSERT INTO [MAP-ALL] (PARTNO, MPN, DSPartno, MAP, Brand)
SELECT [Final_Sap-TEST].PARTNO, [Final_Sap-TEST].MPN, [Final_Sap-TEST].DSPartNo, [MAP-RAW-AMP-TWG].[MAP], @AMP
FROM [MAP-RAW-AMP-TWG]
INNER JOIN [Tire Database].[dbo].[Final_Sap-TEST] ON (SELECT REPLACE([MAP-RAW-AMP-TWG].[SKU/Size], '-', '') ) = (SELECT REPLACE([Final_Sap-TEST].MPN, '-', '') )   COLLATE DATABASE_DEFAULT;






