	
--	1. Import website tire and wheel export data into [SAS-Web-Import-Tire] and [SAS-Web-Import-Wheel] tables.		

--	DELETE FROM [SAS-Web-Import-Wheel];	DELETE FROM [SAS-Web-Import-Tire];

-- Update Quantity
EXEC [FULLInventoryFTP];  -- execute Full-Inventory-FTP query

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

