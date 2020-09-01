DECLARE @FinalSAP VARCHAR(MAX) = '[Tire Database].[dbo].[Final_Sap-TEST]'  -- Final_MAP-TEST Table
DECLARE @Map_NTW VARCHAR(MAX) = '[MAP-RAW-BFG-Mich-Uni-NTW]' -- BFGoodrich, Michelin and Uniroyal MAP Table (NTW)
DECLARE @Map_ATD VARCHAR(MAX) = '[MAP-RAW-Conti-Gen-ATD]' -- Continental and General MAP Table (ATD)
DECLARE @Map_TireHub VARCHAR(MAX) = '[MAP-RAW-Goodyear-Dunlop-Kelly-TireHub]' -- Goodyear, Dunlop and Kelly MAP Table (TireHub)
DECLARE @Map_Falken VARCHAR(MAX) = '[MAP-RAW-Falken-ATD]'  -- Falken MAP Table (ATD)
DECLARE @Map_Hankook VARCHAR(MAX) = '[MAP-RAW-Hankook-NTW]'  -- Hankook MAP Table (NTW)
DECLARE @Map_Sumitomo VARCHAR(MAX) = '[MAP-RAW-Sumitomo-TW]'  -- Sumitomo MAP Table (TW)
DECLARE @Map_Yokohama VARCHAR(MAX) = '[MAP-RAW-Yokohama-TW]'  -- Yokohama MAP Table (TW)
DECLARE @Map_AMP VARCHAR(MAX) = '[MAP-RAW-AMP-TWG]'  -- AMP MAP Table (TWG)
DELETE FROM [MAP-ALL]

-- BFG-Mich-Uni
EXEC [makeMapTable] @FinalSAP, @MapTable = @Map_NTW, @vend = 'NTW'

-- Conti - Gen
EXEC [makeMapTable] @FinalSAP, @MapTable = @Map_ATD, @vend = 'ATD'

--Goodyear-Dunlop-Kelly
EXEC [makeMapTable] @FinalSAP, @MapTable = @Map_TireHub, @vend = 'TireHub'

--Falken
EXEC [makeMapTable] @FinalSAP, @MapTable = @Map_Falken, @vend = 'Falken'

-- Hankook
EXEC [makeMapTable] @FinalSAP, @MapTable = @Map_Hankook, @vend = 'Hankook'

-- Sumitomo
EXEC [makeMapTable] @FinalSAP, @MapTable = @Map_Sumitomo, @vend = 'Sumitomo'

--Yokohama
EXEC [makeMapTable] @FinalSAP, @MapTable = @Map_Yokohama, @vend = 'Yokohama'

-- AMP
EXEC [makeMapTable] @FinalSAP, @MapTable = @Map_AMP, @vend = 'AMP'

