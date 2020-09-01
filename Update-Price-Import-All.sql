-- Update invoice price From ATD PriceBook
UPDATE [import_Price_All]
SET InvoicePrice = [Total]
From [Reporting].[dbo].[Pricebook-ATD], [import_Price_All] 
WHERE [import_Price_All].[Brand] = [Pricebook-ATD].[Brand] COLLATE DATABASE_DEFAULT AND 
	  Partno = [Supplier #] COLLATE DATABASE_DEFAULT;

-- Update invoice price From NTW PriceBook
UPDATE [import_Price_All]
SET InvoicePrice = [List price]
From [Reporting].[dbo].[Pricebook-NTW], [import_Price_All] 
WHERE [import_Price_All].[Brand] = SUBSTRING([Description],0,CHARINDEX(' ',[Description])) COLLATE DATABASE_DEFAULT AND
	  Partno = [Article] COLLATE DATABASE_DEFAULT;

-- Update invoice price From TireHub PriceBook
UPDATE [import_Price_All]
SET InvoicePrice = [DWW]
From [Reporting].[dbo].[Pricebook-TireHub], [import_Price_All] 
WHERE [import_Price_All].[Brand] = [Pricebook-TireHub].[Price Family] COLLATE DATABASE_DEFAULT AND 
	  [import_Price_All].Partno = [Pricebook-TireHub].[Item#] COLLATE DATABASE_DEFAULT;

--SUBSTRING([Description],CHARINDEX('R',[Description])+1, 2) AS Size  // pricebook-ATD