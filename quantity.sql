
DECLARE @min INT = 8

SELECT SUM(Qty) AS TotalQuantity, Partno, Fetcher INTO temptable
  FROM [Dropshipper_Inventory]
  GROUP BY Fetcher, Partno
  ORDER BY Partno;

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