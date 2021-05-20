-- Club SAP
SELECT
REPLACE((SUBSTRING(T2.NumAtCard, 1, CHARINDEX('-', T2.NumAtCard))),'-','') as 'Invoice #',
T0.DocNum as 'PO',
T2.DocNum as 'AP',
T1.ItemCode,
T1.Quantity,
T1.PricebefDi as 'Unit Price',
T0.DocTotal as 'Total Price',
T0.DocDate as 'PO Date',
T2.DocDate as 'AP Date',
REPLACE((REPLACE(T0.CardCode, 'v17', 'NTW')), 'VU1577', 'Tire Hub') as 'Vendor',
T0.CardCode as 'Vendor Code'
FROM dbo.OPOR T0
INNER JOIN dbo.POR1 T1 ON T0.DocEntry = T1.DocEntry
Left JOIN dbo.PCH1 T3 ON T1.Docentry = T3.Baseentry and T1.LineNum=T3.Baseline
left JOIN dbo.OPCH T2 ON T3.Docentry = T2.Docentry
WHERE T0.[DocDate] BETWEEN [%0] AND [%1] AND T0.CardCode IN('VU1577', 'V17')
FOR BROWSE



-- Retail SAP
SELECT
REPLACE((SUBSTRING(T2.NumAtCard, 1, CHARINDEX('-', T2.NumAtCard))),'-','') as 'Invoice #',
T0.DocNum as 'PO',
T2.DocNum as 'AP',
T1.ItemCode,
T1.Quantity,
T1.PricebefDi as 'Unit Price',
T0.DocTotal as 'Total Price',
T0.DocDate as 'PO Date',
T2.DocDate as 'AP Date',
REPLACE((REPLACE(T0.CardCode, 'v17', 'NTW')), 'VU850', 'Tire Hub') as 'Vendor',
T0.CardCode as 'Vendor Code'
FROM dbo.OPOR T0
INNER JOIN dbo.POR1 T1 ON T0.DocEntry = T1.DocEntry
Left JOIN dbo.PCH1 T3 ON T1.Docentry = T3.Baseentry and T1.LineNum=T3.Baseline
left JOIN dbo.OPCH T2 ON T3.Docentry = T2.Docentry
WHERE T0.[DocDate] BETWEEN [%0] AND [%1] AND T0.CardCode IN('VU850', 'V17')
FOR BROWSE


-- Global SAP
SELECT
REPLACE((SUBSTRING(T2.NumAtCard, 1, CHARINDEX('-', T2.NumAtCard))),'-','') as 'Invoice #',
T0.DocNum as 'PO',
T2.DocNum as 'AP',
T1.ItemCode,
T1.Quantity,
T1.PricebefDi as 'Unit Price',
T0.DocTotal as 'Total Price',
T0.DocDate as 'PO Date',
T2.DocDate as 'AP Date',
REPLACE((REPLACE(T0.CardCode, 'v00031', 'NTW')), 'VN101825', 'Tire Hub') as 'Vendor',
T0.CardCode as 'Vendor Code'
FROM dbo.OPOR T0
INNER JOIN dbo.POR1 T1 ON T0.DocEntry = T1.DocEntry
Left JOIN dbo.PCH1 T3 ON T1.Docentry = T3.Baseentry and T1.LineNum=T3.Baseline
left JOIN dbo.OPCH T2 ON T3.Docentry = T2.Docentry
WHERE T0.[DocDate] BETWEEN [%0] AND [%1] AND T0.CardCode IN('VN101825', 'v00031')
FOR BROWSE