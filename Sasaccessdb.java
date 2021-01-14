
import java.sql.*;
import java.io.*;
import java.awt.event.*; 
import java.awt.*; 
import javax.swing.*;
import java.util.*;
import java.net.*;
import javax.swing.*;
import javax.swing.table.*;
import java.time.LocalDateTime;  
import java.time.format.DateTimeFormatter;  
 
public class Sasaccessdb {

    public static String B = System.getProperty("line.separator");
    public static String[] files = {"sasan"};
    public static String[] selectedDb = {"sasan"};
    public static String numbatch;
    public static String idBatch;
    public static String datebatch;
    public static String statebatch;
    public static String checkbatch;
    public static String bbatch;
    public static String statusbatch;
    public static String sapbatch;

    public static String batchNum;
    public static String batchId;
    public static String sentDate; 
    public static String responseDate;
    public static String state;
    public static String t1;
    public static String t2;
    public static String check;
    public static String piece;
    public static String sap;
    public static String status;
    public static String batch;

    public static String taxId;
    public static String taxMail;
    public static String taxState;
    public static String taxAlert;
    public static String taxDate;
    public static String taxBalance;
    public static String taxUser;
    public static String taxPass;
    public static String taxPin;
    public static String taxLn;
    public static String taxNote;
    public static String nextFiling;

    public static String stateName;
    public static String stateLink;
    public static String stateId;

    public static String loginid, sasuser, saspass, saslastlogin, allid, alluser, allpass, alllastlogin, userLastLogin;

    // website urls
    public static void webLink(String urlString) {
        try {
            Desktop.getDesktop().browse(new URL(urlString).toURI());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stateWebsite(String stateId, String stateName, String stateLink){
        if(stateId.length() == 1  ){ stateId = "0" + stateId; }
        if(files[0].equals("LINK>"+stateId)){
            webLink(stateLink);
            System.out.println("\n\t\t[" + stateId + "] " + stateName);
            System.out.println("\t\t" + stateLink);
        }
    }

    // ============================================================================================================================================================
    //                                                                        Tax Print
    // ============================================================================================================================================================   

    public void printTaxRs(String taxId, String taxState, String taxAlert, String taxDate, String taxBalance, String taxUser, String taxPass, String taxPin, String taxLn, String taxNote, String nextFiling){
        String tId = taxId.substring(5, taxId.length());
        if(taxId.length() == 6  ){ tId = "0" + tId; } 
        if(files[0].equals("ALL")){
            if(taxDate.equals("")){ taxDate = "";}else{ taxDate = taxDate.substring(8, taxDate.length());}
            if(nextFiling.equals("next:")){ nextFiling = "";}else{ nextFiling = nextFiling.substring(7, nextFiling.length());}
            if( taxState.length() < 19  ){ System.out.println( "\t[" + tId + "] " + taxState.substring(8, taxState.length()) + "\t\t\t" + taxDate  ); }
            else if( taxState.length() > 26  ){ System.out.println( "\t[" + tId + "] " + taxState.substring(8, taxState.length()) + "\t" + taxDate  ); }
            else{
                System.out.println( "\t[" + tId + "] " + taxState.substring(8, taxState.length()) + "\t\t" + taxDate );
            }
        }
        else if(files[0].equals("DATE")){
            if(taxDate.equals("")){ taxDate = "";}else{ taxDate = taxDate.substring(8, taxDate.length());}
            if(nextFiling.equals("next:")){ nextFiling = "";}else{ nextFiling = nextFiling.substring(7, nextFiling.length());}
            if(!taxDate.equals("") ){
                if( taxState.length() < 19  ){ System.out.println( "\t" + taxDate + "\t[" + tId + "] " + taxState.substring(8, taxState.length()) + "\t\t\t" + nextFiling ); }
                else if( taxState.length() > 26  ){ System.out.println( "\t" + taxDate + "\t[" + tId + "] " + taxState.substring(8, taxState.length()) + "\t" + nextFiling ); }
                else{
                    System.out.println( "\t" + taxDate + "\t[" + tId + "] " + taxState.substring(8, taxState.length()) + "\t\t" + nextFiling );
                }
            }
        }
        else if(files[0].equals("DUE")){
            if(taxDate.equals("")){ taxDate = "";}else{ taxDate = taxDate.substring(8, taxDate.length());}
            if(nextFiling.equals("next:")){ nextFiling = "";}else{ nextFiling = nextFiling.substring(7, nextFiling.length());}
            if(!nextFiling.equals("") ){
                if( taxState.length() < 19  ){ System.out.println( "\t" + nextFiling + "\t\t[" + tId + "] " + taxState.substring(8, taxState.length()) + "\t\t\t" + taxDate ); }
                else if( taxState.length() > 26  ){ System.out.println( "\t" + nextFiling + "\t\t[" + tId + "] " + taxState.substring(8, taxState.length()) + "\t" + taxDate ); }
                else{
                    System.out.println( "\t" + nextFiling + "\t\t[" + tId + "] " + taxState.substring(8, taxState.length()) + "\t\t" + taxDate );
                }
            }
        }
        else if(files[0].equals("BALANCE")){
            if( taxState.length() < 19  ){ System.out.println( "\t[" + tId + "] " + taxState.substring(8, taxState.length()) + "\t\t\t" + taxBalance.substring(9, taxBalance.length()) + "\t\t" + taxNote.substring(5, taxNote.length())); }
            else if( taxState.length() > 26  ){ System.out.println( "\t[" + tId + "] " + taxState.substring(8, taxState.length()) + "\t" + taxBalance.substring(9, taxBalance.length()) + "\t\t" + taxNote.substring(5, taxNote.length()) ); }
            else{
                System.out.println( "\t[" + tId + "] " + taxState.substring(8, taxState.length()) + "\t\t" + taxBalance.substring(9, taxBalance.length()) + "\t\t" + taxNote.substring(5, taxNote.length()) );
            }
        }
        else if(files[0].equals("ERROR")){
            if( taxState.length() < 19  ){ System.out.println( "\t[" + tId + "] " + taxState.substring(8, taxState.length()) + "\t\t\t" + taxAlert.substring(7, taxAlert.length()) ); }
            else if( taxState.length() > 26  ){ System.out.println( "\t[" + tId + "] " + taxState.substring(8, taxState.length()) + "\t" + taxAlert.substring(7, taxAlert.length()) ); }
            else{
                System.out.println( "\t[" + tId + "] " + taxState.substring(8, taxState.length()) + "\t\t" + taxAlert.substring(7, taxAlert.length()) );
            }
        }
        
        else{
            System.out.println( 
                    "\n\t[" + tId + "] " + 
                    taxState.substring(8, taxState.length()) + "\n" + 
                    "\n\t-------------------------------------------\n\t" +
                    taxDate + 
                    "\n\t-------------------------------------------\n\t" +
                    nextFiling +  
                    "\n\t-------------------------------------------\n\t" +
                    taxBalance + 
                    "\n\t-------------------------------------------\n\t" +
                    taxUser + 
                    "\n\t-------------------------------------------\n\t" +
                    taxPass + 
                    "\n\t-------------------------------------------\n\t" +
                    taxPin +  
                    "\n\t-------------------------------------------\n\t" +
                    taxLn + 
                    "\n\t-------------------------------------------\n\t" +
                    taxAlert + 
                    "\n\t-------------------------------------------\n\t" +
                    taxNote + 
                    "\n\t-------------------------------------------" 
                    );
            System.out.print(B);
        }
    }

    // ============================================================================================================================================================
    //                                                                        Mailer Print
    // ============================================================================================================================================================

    public void printRes(String batchNum, String batchId, String sentDate, String state, String t1, String t2, String check, String piece, String sap, String status, String batch){
        if(files[0].equals("ALL")){
            System.out.println( "\t" + batchNum + "\t" + batchId );
        }
        else if(files[0].contains("STATUS>")){
            if(status == ""){
                System.out.println("\t\t[ ] |         | => " + batchId );
            }
            else{
                if(sap.equals("+")){
                    switch (status){
                        case "ready": System.out.println("\t\t[s] | ready   | => " + batchId ); break;
                        case "printed": System.out.println("\t\t[s] | PRINTED | => " + batchId ); break;
                        case "shipped": System.out.println("\t\t[s] | Shipped | => " + batchId ); break;
                        case "hold": System.out.println("\t\t[s] | HOLD    | => " + batchId ); break;
                        default: System.out.println("\t\t[s] | " + status + " | => " + batchId );
                        } 
                } 
                else{
                    switch (status){
                        case "ready": System.out.println("\t\t[ ] | ready   | => " + batchId ); break;
                        case "printed": System.out.println("\t\t[ ] | PRINTED | => " + batchId ); break;
                        case "shipped": System.out.println("\t\t[ ] | Shipped | => " + batchId ); break;
                        case "hold": System.out.println("\t\t[ ] | HOLD    | => " + batchId ); break;
                        default: System.out.println("\t\t[ ] | " + status + " | => " + batchId );
                        }
                }
            }
        }
        else if(files[0].contains("SAP>")){
            if(sap.equals("+")){
                System.out.println("\t\t [+] ADDED => "+batchId);
            }
            else{
                System.out.println("\t\t [ ]       => " + batchId); 
            }
        }
        else if(files[0].equals("DATE>ALL")){
            if (!sentDate.equals("")){
                System.out.println( "\t" + sentDate + "\t" + batchId );
            }
        }
        else if(files[0].equals("CHECK>ALL")){
            if( !check.equals("")){
                System.out.println( "\t" + check + "\t" + batchId  );
            }
        }
        else if(files[0].contains("STATE>")){
            if( sentDate.length() < 8  ){
                System.out.print( 
                    "\n\t" +
                    batchNum + "\t" +                      
                    batch + "\t\t\t" +
                    batchId 
                    );
            } 
            else{
                System.out.print( 
                    "\n\t" +
                    batchNum + "\t" +                      
                    batch + "\t" +
                    sentDate + "\t" +
                    batchId 
                    );
            }
        }
        else if(files[0].contains("BATCH>")){
            if(sentDate.length() < 8){
                System.out.print( "\n\t" + batchNum + "\t\t\t\t\t\t" + batchId );
            } else{
                System.out.print( 
                    "\n\t" +
                    batchNum + "\t" +                      
                    sentDate + "\t" +
                    t1 + "\t" +
                    t2 + "\t" +
                    piece + "\t" + 
                    batchId 
                    );
            }
        }
        else if(files[0].contains("CHECK>")){ 
                System.out.print( "\n\tCheck# " + check);
                System.out.print( "\n\t------------\n");
                System.out.println( "\n\t[" + batchNum + "] " + batchId + "\t\tBatch-" + batch + "\t\t" + sentDate +"\n" ); 
        }
        else if(files[0].contains("DATE>")){ 
            
            System.out.print( "\n\t[" + batchNum + "] " + batchId + "\n\n\tBatch-" + batch + "\t\t" + sentDate + "\t\t" + status ); 
            System.out.print( "\n\t--------------------------------------------------------\n");
        }
        else{

            System.out.println( "\n\t[" + batchNum + "] " + batchId);
            System.out.println( 
                "\n\t----------------------------" +
                "\n\tBatch\t\t" + batch +
                "\n\t----------------------------" + 
                "\n\tDate\t\t" + sentDate + 
                "\n\t----------------------------" +
                "\n\tState\t\t" + state + 
                "\n\t----------------------------" +
                "\n\tCheck\t\t" + check + 
                "\n\t----------------------------" + 
                "\n\tPieces\t\t" + piece + 
                "\n\t----------------------------" +
                "\n\tTray-1\t\t" + t1 + 
                "\n\t----------------------------" +
                "\n\tTray-2\t\t" + t2 + 
                "\n\t----------------------------" + 
                "\n\tSAP\t\t" + sap + 
                "\n\t----------------------------" +
                "\n\tStatus\t\t" + status + 
                "\n\t----------------------------" 
                );
    
            System.out.print(B);
        }
    }

    // ============================================================================================================================================================
    //                                                                        Access VARS
    // ============================================================================================================================================================

    public void vars(String sasDb, String sas){

        // variables
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;

        try { 
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        }
        catch(ClassNotFoundException cnfex) { 
            System.out.println("ERROR: \t MS Access JDBC driver");
            cnfex.printStackTrace();
        }
  
        try {
 
            connection = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/it/Desktop/accjava/db2.accdb"); 
            statement = connection.createStatement();

            rs = statement.executeQuery(sas); 
            if(sasDb.equals("Mailers")){
                while(rs.next()) {                        
                            if(rs.getString(1) == null){batchNum = "";} else{batchNum = rs.getString(1);}
                            if(rs.getString(2) == null){batchId = "";} else{batchId = rs.getString(2);}
                            if(rs.getString(3) == null){sentDate = "";} else{sentDate = rs.getString(3);}
                            if(rs.getString(4) == null){responseDate = "";} else{responseDate = rs.getString(4);}
                            if(rs.getString(5) == null){state = "";} else{state = rs.getString(5);}
                            if(rs.getString(6) == null){t1 = "";} else{t1 =  rs.getString(6);}
                            if(rs.getString(7) == null){t2 = "";} else{t2 =  rs.getString(7);}
                            if(rs.getString(8) == null){check = "";} else{check = rs.getString(8);}
                            if(rs.getString(9) == null){piece = "";} else{piece = rs.getString(9);}
                            if(rs.getString(10) == null){sap = "";} else{sap = rs.getString(10);}
                            if(rs.getString(11) == null){status = "";} else{status = rs.getString(11);}
                            if(rs.getString(12) == null){batch = "";} else{batch = rs.getString(12);}
                                                    
                            printRes( batchNum,  batchId,  sentDate,  state,  t1,  t2,  check,  piece,  sap,  status,  batch);
                    
                } 
            }
            else if(sasDb.equals("Tax")){
                while(rs.next()) { 

                    if(rs.getString(1) == null){taxId = "";} else{taxId = "id:\t\t" + rs.getString(1);}
                    // if(rs.getString(2) == null){taxMail = "";} else{taxMail = rs.getString(2);}
                    if(rs.getString(2) == null){taxState = "";} else{taxState = "state:\t\t" + rs.getString(2);}
                    if(rs.getString(4) == null){taxAlert = "error:";} else{taxAlert = "error:\t\t" + rs.getString(4);}
                    if(rs.getString(5) == null){taxDate = "";} else{taxDate = "filed:\t\t" + rs.getString(5);}
                    if(rs.getString(6) == null){taxBalance = "";} else{taxBalance = "balance:\t" + rs.getString(6);}
                    if(rs.getString(7) == null){taxUser = "";} else{taxUser = "user:\t\t" + rs.getString(7);}
                    if(rs.getString(8) == null){taxPass = "";} else{taxPass = "pass:\t\t" + rs.getString(8);}
                    if(rs.getString(9) == null){taxPin = "";} else{taxPin = "pin:\t\t" + rs.getString(9);}
                    if(rs.getString(10) == null){taxLn = "";} else{taxLn = "license:\t" + rs.getString(10);}
                    if(rs.getString(11) == null){taxNote = "";} else{taxNote = "note:\t\t" + rs.getString(11);}
                    if(rs.getString(12) == null){nextFiling = "next:";} else{nextFiling = "next:\t\t" + rs.getString(12);}

                    printTaxRs(taxId, taxState, taxAlert, taxDate, taxBalance, taxUser, taxPass, taxPin, taxLn, taxNote, nextFiling);
                }
            } 
            else if(sasDb.equals("Link")){
                while(rs.next()) { 

                    if(rs.getString(1) == null){stateName = "";} else{stateName = rs.getString(1);}
                    if(rs.getString(3) == null){stateId = "";} else{stateId = rs.getString(3);}
                    if(rs.getString(2) == null){stateLink = "";} else{stateLink = rs.getString(2);}
                    
                    stateWebsite(stateId, stateName, stateLink);
                }
            } 
                          
        }
        catch(SQLException sqlex){ sqlex.printStackTrace(); }

        finally {

            try {
                if(null != connection) {
                    rs.close();
                    statement.close();
                    connection.close();
                }
            }
            catch (SQLException sqlex) {
                sqlex.printStackTrace();
            }
        }

    }

    // ============================================================================================================================================================
    //                                                                        update data
    // ============================================================================================================================================================

    public void updateRecord(String sasDb){

        // variables
        Connection connection = null;
        PreparedStatement updateData = null;
        Statement statement = null;
        ResultSet rs = null;


        try { 
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        }
        catch(ClassNotFoundException cnfex) { 
            System.out.println("ERROR: \t MS Access JDBC driver");
            cnfex.printStackTrace();
        }
  
        try {
 
            // rs = statement.executeQuery(sas); 
            String sasId = sasDb.substring(0,2);
            String dodor =  sasDb.substring(3,sasDb.length());
            Integer pos = null;

            for(Integer i = 0; i< dodor.length(); i++){
                char devider = dodor.charAt(i);
                String deviderSas = Character.toString(devider);
                
                if(deviderSas.equals("/") ){ pos = i; break;}
                
            }
            
            String clSas = dodor.substring(0,pos);
            String dataSas = dodor.substring(pos + 1, dodor.length());

            connection = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/it/Desktop/accjava/db2.accdb"); 
            String sas = "UPDATE " + selectedDb[0] + " SET [" + clSas + "] = '" + dataSas.toLowerCase() + "' WHERE ID = " + sasId;
            updateData = connection.prepareStatement(sas);

            updateData.executeUpdate();
            System.out.println("\t\trow " + sasId + "\t[ UPDATED ]");            
                          
        }
        catch(SQLException sqlex){ sqlex.printStackTrace(); }

        finally {

            try {
                if(null != connection) {
                    connection.close();
                }
            }
            catch (SQLException sqlex) {
                sqlex.printStackTrace();
            }
        }

    }

    // ============================================================================================================================================================
    //                                                                        update url
    // ============================================================================================================================================================

    public void updateLink(String sasDb){

        Connection connection = null;
        PreparedStatement updateData = null;
       
        try { 
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        }
        catch(ClassNotFoundException cnfex) { 
            System.out.println("ERROR: \t MS Access JDBC driver");
            cnfex.printStackTrace();
        }
  
        try {
 
            String sasId = sasDb.substring(0,2);
            String dataSas =  sasDb.substring(3,sasDb.length());
            
            System.out.println(sasId + "\t\t" + dataSas);

            connection = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/it/Desktop/accjava/db2.accdb"); 
            String sas = "UPDATE webLinks SET [links] = '" + dataSas.toLowerCase() + "' WHERE ID = " + sasId;
            updateData = connection.prepareStatement(sas);

            updateData.executeUpdate();
            System.out.println("\t\trow " + sasId + "\t[ UPDATED ]");            
                          
        }
        catch(SQLException sqlex){ sqlex.printStackTrace(); }

        finally {

            try {
                if(null != connection) {
                    connection.close();
                }
            }
            catch (SQLException sqlex) {
                sqlex.printStackTrace();
            }
        }

    }

    // user
    public void user(String u, String p){ 
        // login("LOGIN", null, sasuser);
        System.out.println("\n\t\tusername:\t" +  u + "\n\t\tpassword:\t" +  p  + "\n\t\tlast login:\t" + saslastlogin);
    };

    // ============================================================================================================================================================
    //                                                                      Login
    // ============================================================================================================================================================

    public void login(String sasDb, String sasUpdate, String userog){

        // variables
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        PreparedStatement updateData = null;
        

        try { 
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        }
        catch(ClassNotFoundException cnfex) { 
            System.out.println("ERROR: \t MS Access JDBC driver");
            cnfex.printStackTrace();
        }
  
        try {          
                connection = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/it/Desktop/accjava/db2.accdb"); 
                statement = connection.createStatement();
                rs = statement.executeQuery("SELECT * FROM Credentials WHERE SasUser ='" + userog + "'");
           
            if(sasDb.equals("LOGIN")){ 
                while(rs.next()) { 

                    loginid = rs.getString(1);
                    sasuser = rs.getString(2);
                    saspass = rs.getString(3);
                    saslastlogin = rs.getString(4);
                    
                    LocalDateTime now = LocalDateTime.now();                    
                    DateTimeFormatter format = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss");  
                    userLastLogin = now.format(format);   

                    String sas = "UPDATE Credentials SET last = '" + userLastLogin + "' WHERE sasUser = '" + sasuser + "'";
                    updateData = connection.prepareStatement(sas);
                    updateData.executeUpdate();
                }
            }
            else if(sasDb.equals("UPDATE")){

                String cl = sasUpdate.substring(0,4);
                String dodor =  sasUpdate.substring(5,sasUpdate.length());              
                Integer pos = null;
    
                for(Integer i = 0; i< dodor.length(); i++){
                    char devider = dodor.charAt(i);
                    String deviderSas = Character.toString(devider);
                    
                    if(deviderSas.equals("/") ){ pos = i; break;}
                    
                }
                
                String oldu = dodor.substring(0,pos).toLowerCase();
                String newu = dodor.substring(pos + 1, dodor.length()).toLowerCase(); 
                if(SasPass.User.equals(oldu) || SasPass.User.equals("sasan")){
                    if(cl.equals("USER")){
                        String sas = "UPDATE Credentials SET sasUser = '" + newu + "' WHERE sasUser = '" + oldu + "'";
                        updateData = connection.prepareStatement(sas);
                        System.out.println("\n\n\t\tyour username: (" + oldu + ") changed to [ " + newu + " ]"); 
                        sasuser = newu;
                        SasPass.User = newu;
                        updateData.executeUpdate();
                    }
                    else if(cl.equals("PASS")){
                        String sas = "UPDATE Credentials SET sasPass = '" + newu + "' WHERE sasUser = '" + oldu + "'";
                        updateData = connection.prepareStatement(sas);
                        System.out.println("\n\n\t\tyour password for this username: (" + oldu + ") has been changed"); 
                        saspass = newu;
                        SasPass.Pass = newu;
                        updateData.executeUpdate();
                    }
                }
                else{ System.out.println("\n\t\tAccess denied!"); }
                
            }
                          
        }
        catch(SQLException sqlex){ sqlex.printStackTrace(); }

        finally {

            try {
                if(null != connection) {
                    rs.close();
                    statement.close();
                    connection.close();
                }
            }
            catch (SQLException sqlex) {
                sqlex.printStackTrace();
            }
        }
      
    }

    // ============================================================================================================================================================
    //                                                                      All Users
    // ============================================================================================================================================================
    public void allUsers(String sasDb){

        // variables
        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;
        PreparedStatement updateData = null;
        

        try { 
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        }
        catch(ClassNotFoundException cnfex) { 
            System.out.println("ERROR: \t MS Access JDBC driver");
            cnfex.printStackTrace();
        }
  
        try {          
                connection = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/it/Desktop/accjava/db2.accdb"); 
                statement = connection.createStatement();
                rs = statement.executeQuery("SELECT * FROM Credentials");

                System.out.println("\n\t\tid" + "\tlast login" + "\t\t\tlogin credential"  );
                System.out.println("\t\t----------------------------------------------------------------------------------");
                if(sasDb.equals("users")){ 
                    while(rs.next()) { 

                        allid = rs.getString(1);
                        alluser = rs.getString(2);
                        allpass = rs.getString(3);
                        alllastlogin = rs.getString(4);
                        
                        LocalDateTime now = LocalDateTime.now();                    
                        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss");  
                        userLastLogin = now.format(format);   

                        String sas = "UPDATE Credentials SET last = '" + userLastLogin + "' WHERE sasUser = '" + sasuser + "'";
                        updateData = connection.prepareStatement(sas);
                        updateData.executeUpdate();
                        
                        if(alluser.equals("sasan")){
                            System.out.println("\n\t\t[" + allid + "]\t" + saslastlogin + "\t\t" + alluser + "(" + allpass + ")\n" );
                            // System.out.println("\n\t\t["+ allid +"]\n\t\tusername:\t"+ alluser +"\n\t\tpassword:\t"+ allpass +"\n\t\tlast login:\t"+ saslastlogin);
                        }
                        else{
                            System.out.println("\n\t\t[" + allid + "]\t" + alllastlogin + "\t\t" + alluser + "(" + allpass + ")\n" );
                            // System.out.println("\n\t\t["+ allid +"]\n\t\tusername:\t"+ alluser +"\n\t\tpassword:\t"+ allpass +"\n\t\tlast login:\t"+ alllastlogin);
                        }
                    }    
                }
                            
        }
        catch(SQLException sqlex){ sqlex.printStackTrace(); }

        finally {

            try {
                if(null != connection) {
                    rs.close();
                    statement.close();
                    connection.close();
                }
            }
            catch (SQLException sqlex) {
                sqlex.printStackTrace();
            }
        }        
    }

    // ============================================================================================================================================================
    //                                                                      Add Users
    // ============================================================================================================================================================
    public void adduser(String sasDb, String chor){ 
        // variables
        Connection connection = null;
        Statement statement = null;
        PreparedStatement updateData = null;
        String sas = null;
        

        try { 
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        }
        catch(ClassNotFoundException cnfex) { 
            System.out.println("ERROR: \t MS Access JDBC driver");
            cnfex.printStackTrace();
        }
  
        try {        
                connection = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/it/Desktop/accjava/db2.accdb"); 
                statement = connection.createStatement();
             
                Integer pos = null;    
                for(Integer i = 0; i< sasDb.length(); i++){
                    char devider = sasDb.charAt(i);
                    String deviderSas = Character.toString(devider);
                    
                    if(deviderSas.equals("/") ){ pos = i; break;}
                    
                }
                
                String newuser = sasDb.substring(0,pos).toLowerCase();
                String newpass = sasDb.substring(pos + 1, sasDb.length()).toLowerCase(); 

                if(chor.equals("add")){
                    sas = "INSERT INTO Credentials (last, sasUser, sasPass) VALUES('00-00-0000 00:00:00', '" + newuser + "', '" + newpass + "')";
                    System.out.println("\t\tnew user has been added successfully!");
                }
                else if(chor.equals("del")){
                    sas = "DELETE FROM Credentials WHERE sasUser ='" + newuser + "'";
                    System.out.println("\t\tusername has been deleted successfully!");
                }
                updateData = connection.prepareStatement(sas);
                updateData.executeUpdate();
                                              
        }
        catch(SQLException sqlex){ sqlex.printStackTrace(); }

        finally {
            try {
                if(null != connection) {
                    statement.close();
                    connection.close();
                }
            }
            catch (SQLException sqlex) {
                sqlex.printStackTrace();
            }
        }        
    }

    // ============================================================================================================================================================
    public void vName(String vname) {
        files[0] = vname;
        if(vname.equals("HELP")){
            
            JFrame f= new JFrame("Help | SasanApp");  
            JLabel label1, label2, label3, label4, label5; 
            label1=new JLabel("Help");
            label2=new JLabel("Mailers Database Help");  
            label3=new JLabel("Tax Database Help");  
            label4=new JLabel("Update Data Help"); 
            label5=new JLabel("User Help");      
            label1.setBounds(60, 20, 100, 30); 
            label2.setBounds(60, 140, 300, 30);
            label3.setBounds(60, 356, 300, 30);
            label4.setBounds(60, 505, 300, 30);
            label5.setBounds(60, 625, 300, 30);
            // ----------- panel 1 ------------
            JPanel panel=new JPanel(new FlowLayout(FlowLayout.LEFT));  
            panel.setBounds(50,50,600,74);    
            panel.setBackground(new Color(0,0,0,0)); 

            String data[][]={ {"db>[database name]","for connect to a data base"},    
                              {"help>","for more information"},    
                              {"exit","for quit from app"}
                            };    
            String column[]={"Command","Details"};         
            JTable jt=new JTable(data,column);    
            // jt.setBounds(10,10,450,250);    
            JScrollPane sp=new JScrollPane(jt);    
            panel.add(sp); 

            // ----------------- panel 2 ------------------
            JPanel panel2=new JPanel(new FlowLayout(FlowLayout.LEFT));  
            panel2.setBounds(50,170,600,170);    

            String data2[][]={ {"all","for whole list of batch Ids"},    
                              {"num>[number]","show details of a batch id search by number"},    
                              {"batch>[number]","show full details of a batch"},
                              {"date>[date]","show details of batch id(s) search by date"},
                              {"state>[state]", "details of batch id(s) search by state abbreviation"},
                              {"check>[number]", "show details of a batch id search by check number"},
                              {"date>all", "show whole list of dates and batch ids"},
                              {"check>all", "show whole list of check numbers and batch ids"},
                              {"status>[number]", "show status details of a whole batch"}
                            };    
            String column2[]={"Command","Details"};                     
            JTable jt2=new JTable(data2,column2);               
            // jt2.setBounds(10,10,500,170);   
            jt2.getColumnModel().getColumn(1).setPreferredWidth(200);
            JScrollPane sp2=new JScrollPane(jt2);
            panel2.add(sp2); 
            
            // ----------------- panel 3 ------------------
            JPanel panel3=new JPanel(new FlowLayout(FlowLayout.LEFT));  
            panel3.setBounds(50,386,600,106);    
            
            String data3[][]={ {"all","for whole list of states"},    
                              {"num>[number]","show details of a state search by number"},    
                              {"state>[state]","show details of a state search by state name"},
                              {"date","whole list of states sort by last filing date"},
                              {"due", "whole list of states sort by next filing due date"}
                            };    
            String column3[]={"Command","Details"};         
            JTable jt3=new JTable(data3,column3);    
            jt3.getColumnModel().getColumn(1).setPreferredWidth(200);   
            JScrollPane sp3=new JScrollPane(jt3);
            panel3.add(sp3);   

            // ----------------- panel 4 ------------------
            JPanel panel4=new JPanel(new FlowLayout(FlowLayout.LEFT));  
            panel4.setBounds(50,535,600,74);    
            
            String data4[][]={ {"all","for whole list of states"},    
                              {"set>[number]/[column]/[new data]","for update a record."},    
                              {"update>link>[number]/[new data]","for update a URL."}                             
                            };    
            String column4[]={"Command","Details"};         
            JTable jt4=new JTable(data4,column4);    
            JScrollPane sp4=new JScrollPane(jt4);
            panel4.add(sp4); 

            // ----------------- panel 5 ------------------
            JPanel panel5=new JPanel(new FlowLayout(FlowLayout.LEFT));  
            panel5.setBounds(50,650,600,122);    
            
            String data5[][]={ {"user>","to show current user credentials"},    
                              {"user>all","to show all the users and passwords"},    
                              {"change>user/[old user name]/[new user name]","for update a username."},
                              {"change>pass/[user name]/[new password]","for update a password."},
                              {"user>add>[username]/[password]","for add a new user."},
                              {"user>del>[username]/x","for delete a user."}                                
                            };    
            String column5[]={"Command","Details"};         
            JTable jt5=new JTable(data5,column5);     
            JScrollPane sp5=new JScrollPane(jt5);
            panel5.add(sp5); 
            
            // ----------------- close button -----------------
            JButton b=new JButton("Close");  
            b.setBounds(411,782,95,30); 
            b.addActionListener(new ActionListener(){  
                public void actionPerformed(ActionEvent e){  
                    f.setVisible(false); 
                    f.dispose();  
                        }  
                    });   
            f.add(b);  

            f.add(label1); f.add(label2); f.add(label3); f.add(label4); f.add(label5);
            f.add(panel); f.add(panel2); f.add(panel3); f.add(panel4); f.add(panel5);
            f.setSize(600,900);  
            f.setLayout(null);  
            f.setVisible(true);

        }

        if(vname.equals("DB>MAILERS")){
            selectedDb[0] = "Mailers"; 
            System.out.println("\n\t\t>> Conected to the Mailers database");
            System.out.println("\t\t=====================================");
        }
        else if(vname.equals("DB>TAX")){
            selectedDb[0] = "List";
            System.out.println("\n\t\t>> Conected to the Tax database");
            System.out.println("\t\t=================================");
        }
        if(selectedDb[0] == "Mailers"){ 
            if(vname.equals("ALL") || vname.equals("DATE>ALL") || vname.equals("CHECK>ALL")){
                vars("Mailers", "SELECT * FROM " + selectedDb[0]);  
                System.out.print(B);             
            }            
            else if(vname.contains("NUM>")){
                numbatch = vname.substring(4,vname.length());
                vars("Mailers", "SELECT * FROM MAILERS WHERE ID='" + numbatch + "'");
            }
            else if(vname.contains("ID>")){
                idBatch = vname.substring(3,vname.length());
                vars("Mailers", "SELECT * FROM MAILERS WHERE BatchID='" + idBatch + "'");
            }
            else if(vname.contains("DATE>")){
                datebatch = vname.substring(5,vname.length()); 
                vars("Mailers", "SELECT * FROM MAILERS WHERE [Sent Date]='" + datebatch + "'");
            }
            else if(vname.contains("STATE>")){
                System.out.print("\n\tNum \tBatch \tSent Date \tBatch ID \n" );
                statebatch = vname.substring(6,vname.length());
                vars("Mailers", "SELECT * FROM MAILERS WHERE [State] LIKE'%" + statebatch + "%'");
                System.out.print(B);
            }
            else if(vname.contains("CHECK>")){
                checkbatch = vname.substring(6,vname.length());
                vars("Mailers", "SELECT * FROM MAILERS WHERE [Check #]='" + checkbatch + "'");
            }
            else if(vname.contains("BATCH>")){
                System.out.print("\n\tNum \tSent Date \tTray-1 \tTray-2 \tPieces \tBatch ID \n" );
                bbatch = vname.substring(6,vname.length());  
                vars("Mailers", "SELECT * FROM MAILERS WHERE [Batch]='" + bbatch + "'");
                System.out.print(B);
            }
            else if(vname.contains("STATUS>")){
                statusbatch = vname.substring(7,vname.length());
                vars("Mailers", "SELECT * FROM MAILERS WHERE [Batch]='" + statusbatch + "'");
                System.out.print(B);
            }
            else if(vname.contains("SAP>")){
                sapbatch = vname.substring(4,vname.length());
                vars("Mailers", "SELECT * FROM MAILERS WHERE [Batch]='" + sapbatch + "'");
                System.out.print(B);
            }
        }
        else if(selectedDb[0] == "List"){  
            if(vname.contains("NUM>")){
                String numTax = vname.substring(4,vname.length());
                vars("Tax", "SELECT * FROM List WHERE ID='" + numTax + "'");
            }
            if(vname.contains("LINK>")){
                String numTax = vname.substring(5,vname.length());
                vars("Link", "SELECT * FROM webLinks WHERE ID='" + numTax + "'");
            }
            else if(vname.contains("STATE>")){
                String numState = vname.substring(6,vname.length());
                vars("Tax", "SELECT * FROM List WHERE STATE='" + numState + "'");
            }
            else if(vname.equals("ALL")){
                vars("Tax", "SELECT * FROM " + selectedDb[0] + " ORDER BY [State]");  
                System.out.print(B);             

            }
            else if(vname.equals("DATE")){
                vars("Tax", "SELECT * FROM " + selectedDb[0] + " ORDER BY [Last Filing Date]");  
                System.out.print(B);             

            }
            else if(vname.equals("DUE")){
                vars("Tax", "SELECT * FROM " + selectedDb[0] + " ORDER BY [nextFiling] DESC");  
                System.out.print(B);             

            }
            else if(vname.equals("BALANCE")){
                vars("Tax", "SELECT * FROM List WHERE NOT BALANCE IN ('No balance due', '') ORDER BY BALANCE" );  
                System.out.print(B); 
            }
            else if(vname.equals("ERROR")){
                vars("Tax", "SELECT * FROM List WHERE ALERT <> '' " );  
                System.out.print(B); 
            }            
        }
        if(vname.contains("SET>")){  
            if(vname.contains("/")){  
                updateRecord(vname.substring(4, vname.length())); 
            }
            else{
                System.out.println("\t\t Invalid Syntax!");
            } 
            System.out.print(B); 
        } 
        if(vname.contains("UPDATE>URL>")){
            updateLink(vname.substring(11, vname.length()));  
            System.out.print(B); 
        }  
        if(vname.contains("CHANGE>")){ 
            login("UPDATE", vname.substring(7, vname.length()), SasPass.User);  
            System.out.print(B); 
        } 
        if(vname.equals("USER>")){
            user(SasPass.User, SasPass.Pass);  
            System.out.print(B); 
        }        
        if(vname.equals("USER>ALL")){
            if(SasPass.User.equals("sasan")){
                allUsers("users"); 
            } else{System.out.println("\t\tAccess denied!");}
            System.out.print(B); 
        }
        if(vname.contains("USER>ADD>")){ 
            if(SasPass.User.equals("sasan")){ 
                adduser(vname.substring(9,vname.length()), "add"); 
            } else{System.out.println("\t\tAccess denied!");}
            System.out.print(B); 
        }
        if(vname.contains("USER>DEL>")){ 
            if(SasPass.User.equals("sasan")){ 
                adduser(vname.substring(9,vname.length()), "del"); 
            } else{System.out.println("\t\tAccess denied!");}
            System.out.print(B); 
        }
        System.out.print("$ ");         
    }

   
    public static void main(String[] args) {        
        Sasaccessdb mailersData = new Sasaccessdb();  
        SasPass pass = new SasPass();  
        pass.passpopup();
        pass.checkPassword();
        mailersData.login("LOGIN", null, SasPass.User);
        Scanner in = new Scanner(System.in);    
 
        System.out.println("===============================================================================================================");
        System.out.println("\t\t Name: \t\tSasanApp");
        System.out.println("\t\t Version: \t3.2.0");
        System.out.println("\t\t Author: \tSasan Bazade");
        System.out.println("===============================================================================================================");
        System.out.println("\t\t\t\t\t\t\t\t\t\ttype help for more information");
            
        if(SasPass.Pass.equals(saspass) && SasPass.User.equals(sasuser)) {
            while ( SasPass.Pass.equals(saspass) ) {
                try {
                    String name = in.nextLine(); 

                    if(name.equals("exit")){ 
                        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor(); 
                        System.exit(0);

                    }
                    if(name.equals("error")){ 
                        new ProcessBuilder("cmd", "/c", "color fc").inheritIO().start().waitFor(); 
                    } else{
                        new ProcessBuilder("cmd", "/c", "color f0").inheritIO().start().waitFor();
                    }
                    mailersData.vName(name.toUpperCase());
                } 
                catch (Exception e) { 
                    System.out.print(e + "\t\tERROR:  wrong input\n\n"); break;
                }
            }    
        } 
        else { 
            System.err.println("\n\n\n\t\t\t\t\tInvalid username or password!\n\n\n\n");
            System.exit(1);
        }
        in.close();         
    }
}


// ======================================================================================================================
//                                                       Password
// ======================================================================================================================
public class SasPass {

    public static String B = System.getProperty("line.separator");
    public static char[] userString = System.console().readPassword("%s", "SasanApp> Enter username");
    public static String User = new String(userString);
    public static String Pass = "";

    public void checkPassword(){
        System.out.println("SasanApp> username: " + User);
        System.out.print("SasanApp> Password: ");
        for(int i=0; i< Pass.length(); i++){ 
          System.out.print("*");
        } 
        System.out.print(B);
    }

    // Password JFRame

    public void passpopup(){
        final JPasswordField pf = new JPasswordField();

        JOptionPane pane = new JOptionPane(pf, JOptionPane.QUESTION_MESSAGE, JOptionPane.DEFAULT_OPTION);
        JDialog dialog = pane.createDialog("ENTER PASSWORD ( user: " + User + " )");
        Pass = "";
       
        dialog.addComponentListener(new ComponentListener(){

            @Override
            public void componentShown(ComponentEvent e) {
                pf.requestFocusInWindow();
            }
            @Override public void componentHidden(ComponentEvent e) {}
            @Override public void componentResized(ComponentEvent e) {}
            @Override public void componentMoved(ComponentEvent e) {}
            });

        dialog.setVisible(true);

        char[] password2 = pf.getPassword();
        Pass = new String(password2);

    }
}


