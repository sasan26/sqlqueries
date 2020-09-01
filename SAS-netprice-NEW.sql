
--------------------------------------------------------------------------------------------------------------------------
/*    ___________________
	 | 					 |
	 |		  ATD		 |
	 |___________________|

*/

-- NITTO   (PROII - 200 units)
DECLARE @Circuit_Quarterly DECIMAL(18,2) = 6 
DECLARE @ATD_Retai_Reward DECIMAL(18,2) = 0
DECLARE @Nitto_ATD_STH DECIMAL(18,2) = 1.75

-- Kumho   (Level3 - 200 units)
DECLARE @ATD_Retail_Rewards DECIMAL(18,2) = 0 
DECLARE @Quarterly_Volume_Bonus DECIMAL(18,2) = 1
DECLARE @FUEL_Quarterly_Line_Rebate DECIMAL(18,2) = 0
DECLARE @FUEL_Hi_Octane_Bonus_18 DECIMAL(18,2) = 0
DECLARE @Kumho_ATD_STH DECIMAL(18,2) = 1.75

--Falken	(400 units)
DECLARE @Fanatic_Rebate DECIMAL(18,2) = 6
DECLARE @AT_Trail_Promo DECIMAL(18,2) = 0
DECLARE @Q4_Promo DECIMAL(18,2) = 0
DECLARE @Falken_ATD_STH DECIMAL(18,2) = 1.75

-- Continental, General (Premier - 3800 units)
DECLARE @Continental_ATD_STH DECIMAL(18,2) = 2.50
DECLARE @GOLD_Reward DECIMAL(18,2) = 0
DECLARE @Annual_Volume_Bonus DECIMAL(18,2) = 5
DECLARE @Q4_Gold_Promo DECIMAL(18,2) = 0
DECLARE @Max_Retail_Rewards DECIMAL(18,2) = 0


---------------------------------------------------------------------------------------------------------------------------
/*    _______________________
	 | 						 |
	 |		  TieHub		 |
	 |_______________________|

*/
-- Bridgeston   (Plus:Level4 - 750 units, Annual:Level3 - 3000 unit)
DECLARE @Bridgestone_Rebate DECIMAL(18,2) = 6
DECLARE @Bridgestone_Plus DECIMAL(18,2) = 5
DECLARE @Bridgestone_Annual DECIMAL(18,2) = 0
DECLARE @Bridgestone_G3xpress DECIMAL(18,2) = 0

-- Firestone   (Plus:Level2 - 300 units, Annual:Level3 - 3000 units)
DECLARE @Firestone_Rebate DECIMAL(18,2) = 4
DECLARE @Firestone_Plus DECIMAL(18,2) = 3
DECLARE @Firestone_Annual DECIMAL(18,2) = 0
DECLARE @Firestone_G3xpress DECIMAL(18,2) = 0

-- Fuzion   (Annual:Level1 - 1200 units)
DECLARE @Fuzion_Rebate DECIMAL(18,2) = 2
DECLARE @Fuzion_Plus DECIMAL(18,2) = 2
DECLARE @Fuzion_Annual DECIMAL(18,2) = 0
DECLARE @Fuzion_G3xpress DECIMAL(18,2) = 0

-- Goodyear   (Tire4 - 800 unit)
DECLARE @Goodyear_Rebate DECIMAL(18,2) = 0
DECLARE @Goodyear_Plus DECIMAL(18,2) = 5
DECLARE @Goodyear_Annual DECIMAL(18,2) = 0
DECLARE @Goodyear_G3xpress DECIMAL(18,2) = 13

-- Kelley   (Tire2 - 200 units)
DECLARE @Kelley_Rebate DECIMAL(18,2) = 0
DECLARE @Kelley_Plus DECIMAL(18,2) = 0
DECLARE @Kelley_Annual DECIMAL(18,2) = 0
DECLARE @Kelley_G3xpress DECIMAL(18,2) = 5

-- Dunlop, GDY(NT)  (Tire2 - 200 units)
DECLARE @Dunlop_Rebate DECIMAL(18,2) = 0
DECLARE @Dunlop_Plus DECIMAL(18,2) = 3
DECLARE @Dunlop_Annual DECIMAL(18,2) = 0
DECLARE @Dunlop_G3xpress DECIMAL(18,2) = 3

----------------------------------------------------------------------------------------------------------------------------

/*    ___________________
	 | 					 |
	 |		  NTW		 |
	 |___________________|

*/

--BFGoodrich
DECLARE @BFG_NTW_Total_Rewards DECIMAL(18,2) = 2
DECLARE @BFG_RCB DECIMAL(18,2) = 9.50
DECLARE @BFG_BDF DECIMAL(18,2) = 3
DECLARE @BFG_NTW_ATF_Credit DECIMAL(18,2) = 0

--Hankook
DECLARE @Hankook_NTW_Total_Rewards DECIMAL(18,2) = 2.00
DECLARE @Volume_Rebate DECIMAL(18,2) = 8.00
DECLARE @Premium_Rewards DECIMAL(18,2) = 0

--Michelin
DECLARE @Mich_NTW_Total_Rewards DECIMAL(18,2) = 2.00
DECLARE @Mich_RCB DECIMAL(18,2) = 9.50
DECLARE @Mich_BDF DECIMAL(18,2) = 3.00
DECLARE @Mich_NTW_ATF_Credit DECIMAL(18,2) = 0

-- Nexen
DECLARE @Nexen_NTW_Total_Rewards DECIMAL(18,2) = 2.00
DECLARE @Volume_Bonus DECIMAL(18,2) = 5.00
DECLARE @Base_Payout DECIMAL(18,2) = 3.00

-- Toyo
DECLARE @Passenger_LTRebate DECIMAL(18,2) = 0
DECLARE @Comm_Truck_Rebate DECIMAL(18,2) = 0
DECLARE @Q3_Rewards DECIMAL(18,2) = 0
DECLARE @Toyo_NTW_Total_Rewards DECIMAL(18,2) = 2.00

-- Uniroyal
DECLARE @Uni_NTW_Total_Rewards DECIMAL(18,2) = 2.00
DECLARE @Uni_RCB DECIMAL(18,2) = 9.50
DECLARE @Uni_BDF DECIMAL(18,2) = 3.00
DECLARE @Uni_NTW_ATF_Credit DECIMAL(18,2) = 0








EXEC [CalculateNetPrice] @Bridgestone_Rebate, @Bridgestone_Plus, @Bridgestone_Annual, @Bridgestone_G3xpress, @Firestone_Rebate, @Firestone_Plus, @Firestone_Annual, @Firestone_G3xpress,
									 @Fuzion_Rebate, @Fuzion_Plus, @Fuzion_Annual, @Fuzion_G3xpress,@Goodyear_G3xpress, @Goodyear_Plus, @Goodyear_Annual, @Goodyear_Rebate, @Kelley_G3xpress, 
									 @Kelley_Plus, @Kelley_Annual, @Kelley_Rebate, @Dunlop_G3xpress, @Dunlop_Plus, @Dunlop_Annual, @Dunlop_Rebate, @Nitto_ATD_STH, @Circuit_Quarterly, 
									 @ATD_Retai_Reward, @Continental_ATD_STH, @Annual_Volume_Bonus, @Q4_Gold_Promo, @Max_Retail_Rewards, @FUEL_Hi_Octane_Bonus_18, @FUEL_Quarterly_Line_Rebate, 
									 @Quarterly_Volume_Bonus, @Kumho_ATD_STH, @ATD_Retail_Rewards, @Falken_ATD_STH, @Q4_Promo, @AT_Trail_Promo, @Fanatic_Rebate,
									 @BFG_NTW_Total_Rewards, @BFG_RCB, @BFG_BDF, @BFG_NTW_ATF_Credit, @Hankook_NTW_Total_Rewards, @Volume_Rebate, @Premium_Rewards, 
									 @Mich_NTW_Total_Rewards, @Mich_RCB, @Mich_BDF, @Mich_NTW_ATF_Credit, @Nexen_NTW_Total_Rewards, @Volume_Bonus, @Base_Payout, 
									 @Passenger_LTRebate, @Comm_Truck_Rebate, @Q3_Rewards, @Toyo_NTW_Total_Rewards, @Uni_NTW_Total_Rewards, @Uni_RCB, @Uni_BDF, @Uni_NTW_ATF_Credit

