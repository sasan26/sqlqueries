CREATE PROCEDURE [dbo].[makeMapTable] @FinalSAP nvarchar(MAX), @MapTable char(255), @vend varchar(MAX)
AS
DECLARE @MAPPrice VARCHAR(MAX) = CASE
											WHEN @vend = 'NTW' THEN '[IUMAP Single Tire]'
											WHEN @vend = 'TireHub' THEN '[MAP EFFECTIVE 4-1-20]'
											WHEN @vend = 'Falken' THEN '[MAP - 3/1/2020]'
											WHEN @vend = 'Sumitomo' THEN '[MAP Price]'
											ELSE '[MAP]' END
DECLARE @MPN VARCHAR(MAX) = CASE
									WHEN @vend = 'NTW' THEN '[MSPN]'
									WHEN @vend = 'ATD' THEN '[Article]'
									WHEN @vend = 'TireHub' THEN '[Product Code]'
									WHEN @vend = 'Falken' THEN '[PRODUCT]'
									WHEN @vend = 'Hankook' THEN '[S-Code]'
									WHEN @vend = 'Sumitomo' THEN '[Product_Code]'
									WHEN @vend = 'Yokohama' THEN '[SAP Material]'
									WHEN @vend = 'AMP' THEN '(SELECT REPLACE([MAP-RAW-AMP-TWG].[SKU/Size], ''-'', ''''))'
									END
DECLARE @Brand VARCHAR(MAX) = CASE
									WHEN @vend IN ('NTW','TireHub','ATD') THEN @MapTable + '.Brand'
									WHEN @vend IN ('Falken') THEN 'Falken'
									END
IF( @vend IN ('NTW','TireHub','ATD') )
	BEGIN
	EXEC ( 'INSERT INTO [MAP-ALL] (PARTNO, MPN, DSPartno, MAP, Brand) SELECT ' + @FinalSAP + '.PARTNO, ' + @FinalSAP + '.MPN, ' + @FinalSAP + '.DSPartNo, '  + @MAPPrice +', ' + @Brand +'
	FROM ' + @MapTable + '
	INNER JOIN ' + @FinalSAP + ' ON ' + @MPN + ' = ' + @FinalSAP + '.MPN  COLLATE DATABASE_DEFAULT');
	END
ELSE
	BEGIN
	IF( @vend != 'AMP' )
		BEGIN
		EXEC ( 'INSERT INTO [MAP-ALL] (PARTNO, MPN, DSPartno, MAP) SELECT ' + @FinalSAP + '.PARTNO, ' + @FinalSAP + '.MPN, ' + @FinalSAP + '.DSPartNo, '  + @MAPPrice +'
		FROM ' + @MapTable + '
		INNER JOIN ' + @FinalSAP + ' ON ' + @MPN + ' = ' + @FinalSAP + '.MPN  COLLATE DATABASE_DEFAULT');
		END
	ELSE
		BEGIN
		EXEC ( 'INSERT INTO [MAP-ALL] (PARTNO, MPN, DSPartno, MAP) SELECT ' + @FinalSAP + '.PARTNO, ' + @FinalSAP + '.MPN, ' + @FinalSAP + '.DSPartNo, '  + @MAPPrice +'
		FROM ' + @MapTable + '
		INNER JOIN ' + @FinalSAP + ' ON ' + @MPN + ' = (SELECT REPLACE([Final_Sap-TEST].MPN, ''-'', '''')) COLLATE DATABASE_DEFAULT');
		 --ON (SELECT REPLACE([MAP-RAW-AMP-TWG].[SKU/Size], '-', '') ) = (SELECT REPLACE([Final_Sap-TEST].MPN, '-', '') )   COLLATE DATABASE_DEFAULT;
		END
	END


UPDATE [MAP-ALL] SET Brand = 'Hankook' WHERE LEFT([MAP-ALL].PARTNO,2) = 'HT'
UPDATE [MAP-ALL] SET Brand = 'Falken' WHERE LEFT([MAP-ALL].PARTNO,2) = 'FT'
UPDATE [MAP-ALL] SET Brand = 'Yokohama' WHERE LEFT([MAP-ALL].PARTNO,2) = 'YT'
UPDATE [MAP-ALL] SET Brand = 'Uniroyal' WHERE LEFT([MAP-ALL].PARTNO,8) = 'UNIROYAL'
UPDATE [MAP-ALL] SET Brand = 'AMP' WHERE LEFT([MAP-ALL].PARTNO,3) = 'AMP'
UPDATE [MAP-ALL] SET Brand = 'Samitomo' WHERE LEFT([MAP-ALL].PARTNO,8) = 'SUMITOMO'

GO

