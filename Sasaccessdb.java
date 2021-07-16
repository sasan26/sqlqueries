
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
import java.text.SimpleDateFormat;
import java.util.Date;
 
public class Sasaccessdb {

    Connection connection = null;
    Statement statement = null;
    ResultSet rs = null;
    PreparedStatement updateData = null;
    public static String accdb = "jdbc:ucanaccess://C:/Users/it/Desktop/accjava/db2.accdb";   // access database
    public static String jdbcdriver = "net.ucanaccess.jdbc.UcanaccessDriver";  // jdbc driver

    public static String B = System.getProperty("line.separator");
    public static String[] files = {"sasan"};
    public static String[] selectedDb = {"sasan"};
    public static String numbatch, idBatch, datebatch, statebatch, checkbatch, bbatch, statusbatch, sapbatch;  // vars for db query
    public static String batchNum, batchId, sentDate, responseDate, state,t1, t2, check, piece, sap, status, batch, res, running, nowSas;  // for Mailers db table
    public static String taxId, taxMail, taxState, taxAlert, taxDate, taxBalance, taxUser, taxPass, taxPin, taxLn, taxNote, nextFiling;   // for List db table (tax)
    public static String stateName, stateLink, stateId;  // for weblink db table
    public static String loginid, sasuser, saspass, saslastlogin, allid, alluser, allpass, alllastlogin, userLastLogin;  // for Credentials and log db tables
    public static String passid, passweb, passuser, passpass, passacc;  // for passwords db
    public static String mapid, pc, tel, mapip, mapname, mapwin, domain, serial, pin, puk, mkey, duo;    // for map db
    public static String  meterDate, meterTime, meterA, meterB, meterBatch, IDdateB, IDdate, mBatch, mailer_Batch, extra;  // for Meter db
    public static Integer ID1, ID2, IDall, ID1B, ID2B, ID_2B, ID_2, IDallB, totm = 0;  // for Meter db
    public static String newdate;   // for newFormatDate function
    
    public static void testdbdriver(){  //jdbdc driver
        try { Class.forName(jdbcdriver); }
        catch(ClassNotFoundException cnfex) { 
            System.out.println("ERROR: \t MS Access JDBC driver");
            cnfex.printStackTrace();
        }
    }

    public void closeConnection(){  // close connections
        try {
            if(null != connection) { connection.close(); }
            if(null != rs) { rs.close(); }
            if(null != statement) { statement.close(); }
        }
        catch (SQLException sqlex) { sqlex.printStackTrace(); }
    }

    // website urls
    public static void webLink(String urlString) {
        try {
            Desktop.getDesktop().browse(new URL(urlString).toURI());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stateWebsite(String stateId, String stateName, String stateLink){ //System.out.println("\t\tid: "+ stateId +"\t\tname: "+ stateName+"\t\tlink: " + stateLink);
        if(stateId.length() == 1  ){ stateId = "0" + stateId; }
        if(files[0].equals("LINK>"+stateId)){ 
            webLink(stateLink);
            System.out.println("\n\t\t[" + stateId + "] " + stateName);
            System.out.println("\t\t" + stateLink);
        }
    }

    public static void changeDateFormat(){
        Date date = new Date();
        SimpleDateFormat newformat = new SimpleDateFormat("MM/dd/yyyy");
        newdate= newformat.format(date);
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
            if(!nextFiling.equals("") ){ if( taxAlert.equals("error:") ){
                if( taxState.length() < 19  ){ System.out.println( "\t" + nextFiling + "\t\t[" + tId + "] " + taxState.substring(8, taxState.length()) + "\t\t\t" + taxDate ); }
                else if( taxState.length() > 26  ){ System.out.println( "\t" + nextFiling + "\t\t[" + tId + "] " + taxState.substring(8, taxState.length()) + "\t" + taxDate ); }
                else{
                    System.out.println( "\t" + nextFiling + "\t\t[" + tId + "] " + taxState.substring(8, taxState.length()) + "\t\t" + taxDate );
                }}
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
            String devider = "\n\t-------------------------------------------\n\t";
            System.out.println( 
                    "\n\t[" + tId + "] " + taxState.substring(8, taxState.length()) + "\n" + 
                    devider + taxDate + devider + nextFiling + devider + taxBalance + devider + taxUser + devider + 
                    taxPass + devider + taxPin + devider + taxLn + devider + taxAlert + devider + taxNote + 
                    "\n\t-------------------------------------------" 
                    );
            System.out.print(B);
        }
    }

    // ============================================================================================================================================================
    //                                                                        Map Print
    // ============================================================================================================================================================
    public void printMap(String id, String pc, String tel, String ip, String name, String win, String domain, String serial, String pin, String puk, String mkey, String duo){
        String location = ip.substring(0, 7);
        String loc = ""; 
        if(location.equals("10.10.2")){loc = "Lasvegas  ";} else if(location.equals("10.10.1")){ loc = "Chatsworth";} else if(location.equals("10.10.0")){ loc = "Fullerton  ";} else if(location.equals("10.10.5")){ loc = "India      ";} 
        if(pc.contains("remote")){ loc = "Remote    "; }
        if(id.length() == 1  ){ id = "0" + id; } 
        if(name.equals("Empty") || name.equals("empty")){name="";}
        if(tel.equals("***")){tel = "   ";}
        if(win.equals("pro")){win = "Pro ";}
        if(domain.equals("dww")){ domain = "dww.dc"; }
        if(files[0].equals("ALL")){
            System.out.println( "\t[DESKTOP-ST" + id + "] " + ip + "\t" + tel + "\t" + loc + "\t" + win  + "\t" + domain + "\t" + name);
            System.out.println( "\t~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" );
        }
        else if(files[0].equals("V>ALL")){
            if( loc == "Lasvegas  "){
                System.out.println( "\t[DESKTOP-ST" + id + "] " + ip + "\t" + tel + "\t" + pc + "\t" + win  + "\t" + domain + "\t" + name);
                System.out.println( "\t~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" );
            }
        }
        else if(files[0].equals("C>ALL")){
            if( loc == "Chatsworth"){
                System.out.println( "\t[DESKTOP-ST" + id + "] " + ip + "\t" + tel + "\t" + win  + "\t" + domain +"\t" + name);
                System.out.println( "\t~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" );
            }
        }
        else if(files[0].equals("F>ALL")){
            if( loc == "Fullerton  "){
                System.out.println( "\t[DESKTOP-ST" + id + "] " + ip + "\t" + tel + "\t" + win  + "\t" + domain +"\t" + name);
                System.out.println( "\t~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" );
            }
        }
        else if(files[0].equals("I>ALL")){
            if( loc == "India      "){
                System.out.println( "\t[DESKTOP-ST" + id + "] " + ip + "\t" + tel + "\t" + win  + "\t" + domain +"\t" + name);
                System.out.println( "\t~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" );
            }
        }
        else if(files[0].equals("R>ALL")){
            if( loc == "Remote    "){
                System.out.println( "\t[DESKTOP-ST" + id + "] " + ip + "\t" + tel + "\t" + win  + "\t" + domain +"\t" + name);
                System.out.println( "\t~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~" );
            }
        }
        else if(files[0].contains("WIN>")){
                System.out.println( "\t[DESKTOP-ST" + id + "] " + "\t" + ip + "\t" + win + "\t" + domain);
        }
        else{
            String devider = "\n\t---------------------------------------------------------------------\n\t";
            System.out.println( 
                "\n\t[DESKTOP-ST"+ id +"] " + pc + "\t\t" + loc + B +
                devider + "name:    \t" +
                name + 
                devider + "ext:     \t" +
                tel +
                devider + "ip:      \t" +
                ip + 
                devider + "windows: \t" +
                win + 
                devider + "domain:  \t" +
                domain + 
                devider + "serial:  \t" +
                serial + 
                devider + "pin:     \t" +
                pin + 
                devider + "puk:     \t" +
                puk + 
                devider + "m-key:   \t" +
                mkey + 
                devider + "duo:     \t" +
                duo + 
                devider
                );
        System.out.print(B);
        }
    }
    // ============================================================================================================================================================
    //                                                                        Meter Print
    // ============================================================================================================================================================
    public void printMeter(String id, String date, String time, String a, String b, String mb){
       
        System.out.print( 
            "\n\t" + 
            id + "\t" +                                        
            date + "\t" +
            time + "\t" +
            a + "\t\t" +
            b + "\t\t" +
            mb
            );
    }

    // ============================================================================================================================================================
    //                                                                        Mailer Print
    // ============================================================================================================================================================

    public void printRes(String batchNum, String batchId, String sentDate, String state, String t1, String t2, String check, String piece, String sap, String status, String batch){
        if(batchNum.length() == 1){ batchNum = "00" + batchNum;} else if(batchNum.length() == 2){ batchNum = "0" + batchNum;} 
        if(sentDate.equals(newdate)){nowSas = "now\t";} else{nowSas="\t";}
        if(files[0].equals("ALL")){
            System.out.println( "\t" + batchNum + "\t" + batchId );
        }
        else if(files[0].contains("STATUS>")){                
            switch (status){
                case "ready": status = " [ r e a d y . . . . . ]"; break;
                case "printed": status = " [ . . P R I N T E D . ]"; break;
                case "shipped": status =  " [ . . . S h i p p e d ]"; break;
                case "hold": status =  " [ H O L D . . . . . . ]"; break;
                case "": status =  " [ . . . . . . . . . . ]"; break;
                default: status = " [ . r u n n i n g . . ]";
            }
            
            if(status.equals(" [ . r u n n i n g . . ]")){running = "\t>>>" + nowSas;}else{running="\t" +nowSas;}
            if(sap.equals("+")){ sap = running + "[s]";} else{sap = running + "[ ]";}
            res = sap +  status + " [" + batchNum +"] "+ batchId;           
            System.out.println(res);
        }
        else if(files[0].equals("RUNNING>") || files[0].equals("PRINTED>")){                
            res = "\t\t[" + batchNum +"] "+ batchId;           
            System.out.println(res);
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
                    batch + "\t\t\t[" +
                    batchNum + "] " + 
                    batchId 
                    );
            } 
            else{
                System.out.print( 
                    "\n\t" +                                         
                    batch + "\t" +
                    sentDate + "\t[" +
                    batchNum + "] " +
                    batchId 
                    );
            }
        }
        else if(files[0].contains("BATCH>")){
            if(status.equals(" [ . r u n n i n g . . ]") || status.equals("running")){running = ">>>";}else{running="";}
            if(sentDate.length() < 8){
                System.out.print( "\n\t\t\t\t\t\t[" + batchNum + "] " + batchId );
            } else{
                System.out.print( 
                    "\n\t" + running +  
                    nowSas +                                      
                    sentDate + "\t" +
                    t1 + "\t" +
                    t2 + "\t" +
                    piece + "\t[" + 
                    batchNum + "] " +
                    batchId 
                    );
            }
        }
        else if(files[0].contains("CHECK>")){ 
                System.out.print( "\n\tCheck# " + check);
                System.out.print( "\n\t------------\n");
                System.out.println( "\n\t[" + batchNum + "] " + batchId + "\t\tBatch-" + batch + "\t\t" + sentDate +"\n" ); 
        }
        else if(files[0].contains("DATE>") || files[0].contains("TODAY>") || files[0].equals("ACTIVE>")){             
            System.out.println( "\n\t[" + batchNum + "] " + batchId );
            String devider = "\t~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n";
            System.out.println( 
                devider +
                "\t" + sentDate +  
                "\t" + piece + 
                "\t" + t1 +
                "\t" + t2 + 
                "\t" + status + " [" + sap + "]" + B +
                devider    
                );

            System.out.print(B); 

        }
        else{
            System.out.println( "\n\t[" + batchNum + "] " + batchId );
            String devider = "\n\t.===============================.===============================.\n\t";
            String deviderend = "\n\t'==============================='==============================='\n\t";
            String devider_2 = "\n\t";
            String tt = "\t\t\t|";
            String ttt = "\t\t\t\t|";
            String ttsp = "\t\t\t|   ";
            if(state.length() < 3){state = state + "   ";}
            if(check.length() < 4){check = "    ";}
            if(piece.length()<3){piece = "     ";}
            if(status.length()<3){status = "     ";}
            System.out.println( 
                devider +
                "|  Batch" + ttsp + batch + ttt +
                devider_2 + 
                "|  Date\t" + ttsp + sentDate + tt + 
                devider_2 +
                "|  State" + ttsp + state + "\t\t\t|" + 
                deviderend + devider + 
                "|  Pieces" + ttsp + piece + tt + 
                devider_2 +
                "|  Tray-1" + ttsp + t1 + ttt + 
                devider_2 +
                "|  Tray-2" + ttsp + t2 + ttt + 
                deviderend + devider +
                "|  Check" + ttsp + check + tt + 
                devider_2 +
                "|  SAP\t" + ttsp + sap + ttt + 
                devider_2 +
                "|  Status" + ttsp + status + tt + 
                deviderend 
                );

            System.out.print(B);
        }
    }

    // ============================================================================================================================================================
    //                                                                        Password
    // ============================================================================================================================================================

    public void printPass(String id, String user, String pass, String acc, String link){
        if(id.length()==1){ id = "0"+id;}
        if(files[0].equals("ALL")){
            System.out.println( "\t[" + id + "] " + acc );
        }
        else if(files[0].contains("LINK>")){
            stateWebsite(id, acc, link);
        }
        else{
            String devider = "\n\t--------------------------------------------------------------------------------------\n\t";
            System.out.println( 
                "\n\t[" + id + "] " + acc + B +
                devider + "user:\t" +
                user +
                devider + "pass:\t" +
                pass + 
                devider + "url: \t" +
                link + 
                devider
                );
        System.out.print(B);
        }
    }

    // ============================================================================================================================================================
    //                                                                        Access VARS
    // ============================================================================================================================================================

    public void vars(String sasDb, String sas){

        testdbdriver();  
        try { 
            connection = DriverManager.getConnection(accdb); 
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

                    if(rs.getString(2) == null){stateName = "";} else{stateName = rs.getString(2);}
                    if(rs.getString(1) == null){stateId = "";} else{stateId = rs.getString(1);}
                    if(rs.getString(3) == null){stateLink = "";} else{stateLink = rs.getString(3);}
                    
                    stateWebsite(stateId, stateName, stateLink);
                }
            } 
            else if(sasDb.equals("Pass")){
                while(rs.next()) {    
                            if(rs.getString(1) == null){passid = "";} else{passid = rs.getString(1);}                    
                            if(rs.getString(2) == null){passweb = "";} else{passweb = rs.getString(2);}
                            if(rs.getString(3) == null){passuser = "";} else{passuser = rs.getString(3);}
                            if(rs.getString(4) == null){passpass = "";} else{passpass = rs.getString(4);}
                            if(rs.getString(5) == null){passacc = "";} else{passacc = rs.getString(5);}
                                                    
                            printPass( passid, passuser, passpass, passacc, passweb);                   
                } 
            }
            else if(sasDb.equals("Map")){
                while(rs.next()) {    
                            if(rs.getString(1) == null){mapid = "";} else{mapid = rs.getString(1);}                    
                            if(rs.getString(2) == null){pc = "";} else{pc = rs.getString(2);}
                            if(rs.getString(3) == null){tel = "";} else{tel = rs.getString(3);}
                            if(rs.getString(4) == null){mapip = "";} else{mapip = rs.getString(4);}
                            if(rs.getString(5) == null){mapname = "";} else{mapname = rs.getString(5);}
                            if(rs.getString(6) == null){mapwin = "";} else{mapwin = rs.getString(6);}
                            if(rs.getString(7) == null){domain = "";} else{domain = rs.getString(7);}
                            if(rs.getString(8) == null){serial = "";} else{serial = rs.getString(8);}
                            if(rs.getString(9) == null){pin = "";} else{pin = rs.getString(9);}
                            if(rs.getString(10) == null){puk = "";} else{puk = rs.getString(10);}
                            if(rs.getString(11) == null){mkey = "";} else{mkey = rs.getString(11);}
                            if(rs.getString(12) == null){duo = "";} else{duo = rs.getString(12);}
                                                   
                            printMap( mapid, pc, tel, mapip, mapname, mapwin, domain, serial, pin, puk, mkey, duo);                   
                } 
            }
            else if(sasDb.equals("Meter")){                
                if(files[0].contains("CAL>")){ 
                    totm = 0;
                    String ids = files[0].substring(4,files[0].length());
                    String id1 = ids.substring(0,2);
                    String id2 = ids.substring(3,5);
                    Integer id_1 = Integer.parseInt(id1);
                    Integer id_2 = Integer.parseInt(id2) + 1;
                    Integer dif, tm;

                    rs = statement.executeQuery(" SELECT MeterBatch From Meter WHERE ID = " + id1 );                                 
                    while(rs.next()) { mBatch = rs.getString("MeterBatch"); }

                    rs = statement.executeQuery(" SELECT [# Mailer] From Mailers WHERE ID = " + mBatch );                                 
                    while(rs.next()) { mailer_Batch = rs.getString("# Mailer"); }

                    rs = statement.executeQuery(" SELECT MeterDate From Meter WHERE ID = " + id1 );                                 
                    while(rs.next()) { IDdate = rs.getString("MeterDate"); }

                    rs = statement.executeQuery(" SELECT MeterDate From Meter WHERE ID = " + id_2 );                                 
                    while(rs.next()) { IDdateB = rs.getString("MeterDate"); }

                    rs = statement.executeQuery(" SELECT MeterA From Meter WHERE ID = " + id1 );                                 
                    while(rs.next()) { ID1 = Integer.parseInt(rs.getString("MeterA")); }
                    
                    rs = statement.executeQuery(" SELECT MeterA From Meter WHERE ID = " + id2 );                             
                    while(rs.next()) { ID2 = Integer.parseInt(rs.getString("MeterA")); }  
                    IDall = ID1 - ID2;  

                    rs = statement.executeQuery(" SELECT MeterB From Meter WHERE ID = " + id1 );                                 
                    while(rs.next()) { ID1B = Integer.parseInt(rs.getString("MeterB")); }
                    
                    rs = statement.executeQuery(" SELECT MeterB From Meter WHERE ID = " + id2 );                             
                    while(rs.next()) { ID2B = Integer.parseInt(rs.getString("MeterB")); }  
                    IDallB = ID1B - ID2B;  
                    String devider = "\t\t~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
                    System.out.println( B + "\n\t\t[ " + IDdateB + " to " + IDdate +" ]\n" );                   
                    System.out.println( devider + "\n\t\t(PRINTER)\n");

                    rs = statement.executeQuery(" SELECT MeterA From Meter WHERE ID = " + id_2 );                             
                    while(rs.next()) { ID_2 = Integer.parseInt(rs.getString("MeterA")); }  
                    rs = statement.executeQuery(" SELECT MeterB From Meter WHERE ID = " + id_2 );                             
                    while(rs.next()) { ID_2B = Integer.parseInt(rs.getString("MeterB")); }  

                    if(ID_2 - ID1 > 1){
                        System.out.println( 
                                            "\t\tMeter A:  " + ID_2 +" to " + ID1 +  
                                            "\n\t\tMeter B:  " + ID_2B +" to " + ID1B 
                                          );
                    } else{
                        System.out.println( 
                                            "\t\tMeter A:  " + ID2 +" to " + ID1 +  
                                            "\n\t\tMeter B:  " + ID2B +" to " + ID1B 
                                          );
                      }

                    System.out.println( devider + "\n\t\t(MAILERS)\n");
                    rs = statement.executeQuery("SELECT ID, [# Mailer] AS Total, BatchID From Mailers INNER JOIN Meter ON Mailers.ID = MeterBatch WHERE Meter.ID BETWEEN " + id_2 + " AND " + id_1 + " GROUP BY ID" );
                    while(rs.next()) { 
                        tm = Integer.parseInt(rs.getString("Total")) *2;
                        totm += tm;
                        System.out.println("\t\t\t" + tm + "  > [" + rs.getString("ID") + "] " + rs.getString("BatchID") + "\t"); 
                    }
                    dif = IDall - totm;
                    if( IDallB > totm ){ extra = " (EXTRA)"; } else{extra = "";}

                    System.out.println( devider +
                                        "\n\t\t(RESULT)\n" +
                                        "\n\t\tPrinted:  " + IDall +
                                        "\n\t\tSent:\t  " + totm +
                                        "\n\n\t\tGAP:\t  " + dif + extra 
                                      );
                    
                    
                }
                else{
                    while(rs.next()) {    
                        if(rs.getString(2) == null){meterDate = "";} else{meterDate = rs.getString(2);}                    
                        if(rs.getString(3) == null){meterTime = "";} else{meterTime = rs.getString(3);}
                        if(rs.getString(4) == null){meterA = "";} else{meterA = rs.getString(4);}
                        if(rs.getString(5) == null){meterB = "";} else{meterB = rs.getString(5);}
                        if(rs.getString(6) == null){meterBatch = "";} else{meterBatch = rs.getString(6);}
                       
                        printMeter( rs.getString(1), meterDate, meterTime, meterA, meterB, meterBatch);   
            
                    } 
                }

            }
                          
        }
        catch(SQLException sqlex){ sqlex.printStackTrace(); }
        finally { closeConnection(); } 
    }

    // ============================================================================================================================================================
    //                                                                        update data
    // ============================================================================================================================================================

    public void updateRecord(String sasDb){

        String newlog = null;        
        testdbdriver(); 
        try {
 
            String sasId = null; String dodor = null;
            String threeD = sasDb.substring(0,3);
            if(!threeD.contains("/")){
                sasId = sasDb.substring(0,3);
                dodor =  sasDb.substring(4,sasDb.length());
            }
            else{
                sasId = sasDb.substring(0,2);
                dodor =  sasDb.substring(3,sasDb.length());
            }
            
            Integer pos = null;

            for(Integer i = 0; i< dodor.length(); i++){
                char devider = dodor.charAt(i);
                String deviderSas = Character.toString(devider);
                
                if(deviderSas.equals("/") ){ pos = i; break; }
                
            }
            
            String clSas = dodor.substring(0,pos);
            String dataSas = dodor.substring(pos + 1, dodor.length());

            connection = DriverManager.getConnection(accdb); 

            LocalDateTime now = LocalDateTime.now();                    
            DateTimeFormatter format = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss");  
            String llogin = now.format(format);

            String sas = "UPDATE " + selectedDb[0] + " SET [" + clSas + "] = '" + dataSas.toLowerCase() + "' WHERE ID = " + sasId;
            if(selectedDb[0].equals("Mailers")){
                newlog ="INSERT INTO Log (loguser, loglast, logcl, logchange, logtable) VALUES ('" + SasPass.User + "','" + llogin + "','" + clSas + "','[" + sasId + "]=> " + dataSas.toLowerCase() + "','Mailers')";
            }
            else if(selectedDb[0].equals("Map")){
                newlog ="INSERT INTO Log (loguser, loglast, logcl, logchange, logtable) VALUES ('" + SasPass.User + "','" + llogin + "','" + clSas + "','[" + sasId + "]=> " + dataSas.toLowerCase() + "','Map')";
            }
            else{
                newlog ="INSERT INTO Log (loguser, loglast, logcl, logchange, logtable) VALUES ('" + SasPass.User + "','" + llogin + "','" + clSas + "','[" + sasId + "]=> " + dataSas.toLowerCase() + "','List')";
            }
            updateData = connection.prepareStatement(sas);
            updateData.executeUpdate();
            updateData = connection.prepareStatement(newlog);
            updateData.executeUpdate();
            System.out.println("\t\t\t\tNum " + sasId + "\t[ UPDATED ]");            
                          
        }
        catch(SQLException sqlex){ sqlex.printStackTrace(); }
        finally { closeConnection(); } 
    }

    // ============================================================================================================================================================
    //                                                                        update url
    // ============================================================================================================================================================

    public void updateLink(String sasDb){
       
        testdbdriver();
  
        try {
            String sasId = sasDb.substring(0,2);
            String dataSas =  sasDb.substring(3,sasDb.length());

            connection = DriverManager.getConnection(accdb); 
            String sas = "UPDATE webLinks SET [links] = '" + dataSas.toLowerCase() + "' WHERE ID = " + sasId;
            String newlog ="INSERT INTO Log (loguser, loglast, logcl, logchange, logtable) VALUES ('" + SasPass.User + "','" + userLastLogin + "','links','[" + sasId + "]=> " + dataSas.toLowerCase() + "','Weblinks')";
            updateData = connection.prepareStatement(sas);
            updateData.executeUpdate();
            updateData = connection.prepareStatement(newlog);
            updateData.executeUpdate();
            System.out.println("\t\t\t\tNum " + sasId + "\t[ UPDATED ]");            
                          
        }
        catch(SQLException sqlex){ sqlex.printStackTrace(); }
        finally { closeConnection(); } 
    }

    // ============================================================================================================================================================
    //                                                                      Login
    // ============================================================================================================================================================

    public void login(String sasDb, String sasUpdate, String userog){        

        testdbdriver();
  
        try {          
                connection = DriverManager.getConnection(accdb); 
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
                        String newlog ="INSERT INTO Log (loguser, loglast, logchange) VALUES ('" + SasPass.User + "','" + userLastLogin + "','Logged IN')";
                        updateData = connection.prepareStatement(sas);
                        updateData.executeUpdate();
                        updateData = connection.prepareStatement(newlog);
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
                            String newlog ="INSERT INTO Log (loguser, loglast, logcl, logchange, logtable) VALUES ('" + SasPass.User + "','" + userLastLogin + "','sasUser','["+ oldu + "]=> " + newu + "','Credentials')";
                            updateData = connection.prepareStatement(sas);
                            System.out.println("\n\n\t\tyour username: (" + oldu + ") changed to [ " + newu + " ]"); 
                            // sasuser = newu;
                            // SasPass.User = newu;
                            updateData.executeUpdate();
                            updateData = connection.prepareStatement(newlog);
                            updateData.executeUpdate();
                        }
                        else if(cl.equals("PASS")){
                            String sas = "UPDATE Credentials SET sasPass = '" + newu + "' WHERE sasUser = '" + oldu + "'";
                            String newlog ="INSERT INTO Log (loguser, loglast, logcl, logchange, logtable) VALUES ('" + SasPass.User + "','" + userLastLogin + "','sasPass','["+ oldu + "]=> " + newu + "','Credentials')";
                            updateData = connection.prepareStatement(sas);
                            System.out.println("\n\n\t\tyour password for this username: (" + oldu + ") has been changed"); 
                            // saspass = newu;
                            // SasPass.Pass = newu;
                            updateData.executeUpdate();
                            updateData = connection.prepareStatement(newlog);
                            updateData.executeUpdate();
                        }
                    }
                    else{ System.out.println("\n\t\tAccess denied!"); }                   
                }
        }
        catch(SQLException sqlex){ sqlex.printStackTrace(); }
        finally { closeConnection(); }    
    }

    // ============================================================================================================================================================
    //                                                                      All Users
    // ============================================================================================================================================================
    public void allUsers(String sasDb){

        testdbdriver();
  
        try {          
                connection = DriverManager.getConnection(accdb); 
                statement = connection.createStatement();
                rs = statement.executeQuery("SELECT * FROM Credentials");

                if(SasPass.User.equals("sasan")){
                    System.out.println("\n\t\tid" + "\tlast login" + "\t\t\tlogin credential"  );
                    System.out.println("\t\t----------------------------------------------------------------------------------");
                }
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
                        
                        if(SasPass.User.equals("sasan")){
                            if(alluser.equals("sasan")){
                                System.out.println("\t\t[" + allid + "]\t" + saslastlogin + "\t\t" + alluser + "(" + allpass + ")" );
                            }
                            else{
                                System.out.println("\t\t[" + allid + "]\t" + alllastlogin + "\t\t" + alluser + "(" + allpass + ")" );
                            }
                        }
                        else if(alluser.equals(SasPass.User)){
                            System.out.println("\n\t\tusername:\t" +  SasPass.User + "\n\t\tpassword:\t" +  SasPass.Pass  + "\n\t\tlast login:\t" + saslastlogin);
                        }
                    }    
                }                           
        }
        catch(SQLException sqlex){ sqlex.printStackTrace(); }
        finally { closeConnection(); }       
    }

    // ============================================================================================================================================================
    //                                                                      Add Users
    // ============================================================================================================================================================
    public void adduser(String sasDb, String chor){ 

        String sas = null, newlog = null;        

        try { Class.forName(jdbcdriver);}
        catch(ClassNotFoundException cnfex) { 
            System.out.println("ERROR: \t MS Access JDBC driver");
            cnfex.printStackTrace();
        }
  
        try {        
                connection = DriverManager.getConnection(accdb); 
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
                    newlog ="INSERT INTO Log (loguser, loglast, logcl, logchange, logtable) VALUES ('" + SasPass.User + "','" + userLastLogin + "','+++','" + newuser + "','Credentials')";
                    System.out.println("\t\tnew user has been added successfully!");
                }
                else if(chor.equals("del")){
                    sas = "DELETE FROM Credentials WHERE sasUser ='" + newuser + "'";
                    newlog ="INSERT INTO Log (loguser, loglast, logcl, logchange, logtable) VALUES ('" + SasPass.User + "','" + userLastLogin + "','xxx','" + newuser + "','Credentials')";
                    System.out.println("\t\tusername has been deleted successfully!");
                }
                updateData = connection.prepareStatement(sas);
                updateData.executeUpdate();
                updateData = connection.prepareStatement(newlog);
                updateData.executeUpdate();
                                              
        }
        catch(SQLException sqlex){ sqlex.printStackTrace(); }
        finally { closeConnection(); }         
    }

    // ============================================================================================================================================================
    //                                                                      Log
    // ============================================================================================================================================================
    public void viewLog(){
        
        testdbdriver();
  
        try {          
                connection = DriverManager.getConnection(accdb); 
                statement = connection.createStatement();
                rs = statement.executeQuery("SELECT * FROM Log");

                System.out.println("\n  last modified" + "\t\tuser" + "\t\ttable [column]" + "\t\tchanges"  );
                System.out.println("  ------------------------------------------------------------------------------------------------------------");

                while(rs.next()) { 
                    String logid = rs.getString(1);
                    String loguser = rs.getString(2);
                    String loglast = rs.getString(3);
                    String logcl = rs.getString(4);
                    String logchange = rs.getString(5);
                    String logtable = rs.getString(6);
                    String gogol = logtable + "[" + logcl;
                    if(loguser.length() > 7){                           
                        if(gogol.length() > 14){
                            System.out.println("  " + loglast + "\t" + loguser + "\t" + logtable + "[" + logcl + "]\t" + logchange );
                        }
                        else{ System.out.println("  " + loglast + "\t" + loguser + "\t" + logtable + "[" + logcl + "]\t\t" + logchange ); }
                    }
                    else{  
                        if(gogol.length() > 14){
                            System.out.println("  " + loglast + "\t" + loguser + "\t\t" + logtable + "[" + logcl + "]\t" + logchange ); 
                        }
                        else{ System.out.println("  " + loglast + "\t" + loguser + "\t\t" + logtable + "[" + logcl + "]\t\t" + logchange ); }
                    }                        
                }    
                            
        }
        catch(SQLException sqlex){ sqlex.printStackTrace(); }
        finally { closeConnection(); }        
    }

    // ============================================================================================================================================================
    //                                                                        update Passwords
    // ============================================================================================================================================================

    public void updatePass(String sasDb){

        String sas = null;
       
        testdbdriver();
  
        try {
            String cl = sasDb.substring(0,4); 
            String sasId = sasDb.substring(5,7); 
            String dataSas =  sasDb.substring(8,sasDb.length());   

            connection = DriverManager.getConnection(accdb); 
            if(cl.equals("USER")){
                sas = "UPDATE Pass SET [username] = '" + dataSas.toLowerCase() + "' WHERE ID = " + sasId;
            }
            else if(cl.equals("PASS")){
                sas = "UPDATE Pass SET [password] = '" + dataSas.toLowerCase() + "' WHERE ID = " + sasId;
            }
            else if(cl.equals("LINK")){
                sas = "UPDATE Pass SET [website] = '" + dataSas.toLowerCase() + "' WHERE ID = " + sasId;
            }
            else if(cl.equals("NAME")){
                sas = "UPDATE Pass SET [acc] = '" + dataSas.toLowerCase() + "' WHERE ID = " + sasId;
            }
            updateData = connection.prepareStatement(sas);
            updateData.executeUpdate();
            System.out.println("\t\t\t\tNum " + sasId + "\t[ UPDATED ]");            
                          
        }
        catch(SQLException sqlex){ sqlex.printStackTrace(); }
        finally { closeConnection(); } 
    }

    // ============================================================================================================================================================
    //                                                                      Add Meter
    // ============================================================================================================================================================
    public void addmeter(String sasDb, String chor){ 

        String sas = null;        
        testdbdriver();
  
        try {        
                connection = DriverManager.getConnection(accdb); 
                statement = connection.createStatement();
                
                if(chor.equals("add")){
                    sas = "INSERT INTO Meter (meterDate) VALUES('" + sasDb + "')";
                    System.out.println("\t\tnew Meter data has been added successfully!");
                }
                
                updateData = connection.prepareStatement(sas);
                updateData.executeUpdate();
                                              
        }
        catch(SQLException sqlex){ sqlex.printStackTrace(); }
        finally { closeConnection(); }      
    }


    // ============================================================================================================================================================
    //                                                                      Add Password
    // ============================================================================================================================================================
    public void addpass(String sasDb, String chor){ 

        String sas = null;        

        testdbdriver();
  
        try {        
                connection = DriverManager.getConnection(accdb); 
                statement = connection.createStatement();
                
                String newuser = sasDb.substring(0,sasDb.length()).toLowerCase();

                if(chor.equals("add")){
                    sas = "INSERT INTO Pass (acc) VALUES('" + newuser + "')";
                    System.out.println("\t\tnew password has been added successfully!");
                }
                else if(chor.equals("del")){
                    sas = "DELETE FROM Pass WHERE acc ='" + newuser + "'";
                    System.out.println("\t\tpassword has been deleted successfully!");
                }
                updateData = connection.prepareStatement(sas);
                updateData.executeUpdate();
                                              
        }
        catch(SQLException sqlex){ sqlex.printStackTrace(); }
        finally { closeConnection(); }      
    }

    // ============================================================================================================================================================
    public void vName(String vname) {
        files[0] = vname;
        if(vname.equals("HELP")){
            
            JFrame f= new JFrame("Help | SasanApp");  
            JLabel label1, label2, label3, label4, label5, label6; 
            label1=new JLabel("Help");
            label2=new JLabel("Mailers Database Help");  
            label3=new JLabel("Tax Database Help");  
            label4=new JLabel("Update Data Help"); 
            label5=new JLabel("User Help");
            label6=new JLabel("Meter Help");      
            label1.setBounds(60, 20, 100, 30); 
            label2.setBounds(60, 140, 300, 30);
            label3.setBounds(60, 356, 300, 30);
            label4.setBounds(60, 505, 300, 30);
            label5.setBounds(60, 625, 300, 30);
            label6.setBounds(60, 785, 300, 30);
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
                              {"update>url>[number]/[new data]","for update a URL."}                             
                            };    
            String column4[]={"Command","Details"};         
            JTable jt4=new JTable(data4,column4);    
            JScrollPane sp4=new JScrollPane(jt4);
            panel4.add(sp4); 

            // ----------------- panel 5 ------------------
            JPanel panel5=new JPanel(new FlowLayout(FlowLayout.LEFT));  
            panel5.setBounds(50,650,600,122);    
            
            String data5[][]={ {"log>","to show the log"},    
                              {"user>","to show all the users and passwords"},    
                              {"change>user/[old user name]/[new user name]","for update a username."},
                              {"change>pass/[user name]/[new password]","for update a password."},
                              {"user>add>[username]/[password]","for add a new user."},
                              {"user>del>[username]/x","for delete a user."}                                
                            };    
            String column5[]={"Command","Details"};         
            JTable jt5=new JTable(data5,column5);     
            JScrollPane sp5=new JScrollPane(jt5);
            panel5.add(sp5); 

            // ----------------- panel 6 ------------------
            JPanel panel6=new JPanel(new FlowLayout(FlowLayout.LEFT));  
            panel6.setBounds(50,810,600,74);    
            
            String data6[][]={ {"all","to show all the meter data"},    
                              {"cal>[num]/[num]","to show meter data in a specified date range"},
                              {"meter+>[date]","to add a new data"}                                
                            };    
            String column6[]={"Command","Details"};         
            JTable jt6=new JTable(data6,column6);     
            JScrollPane sp6=new JScrollPane(jt6);
            panel6.add(sp6);
            
            // ----------------- close button -----------------
            JButton b=new JButton("Close");  
            b.setBounds(411,900,95,30); 
            b.addActionListener(new ActionListener(){  
                public void actionPerformed(ActionEvent e){  
                    f.setVisible(false); 
                    f.dispose();  
                        }  
                    });   
            f.add(b);  

            f.add(label1); f.add(label2); f.add(label3); f.add(label4); f.add(label5); f.add(label6);
            f.add(panel); f.add(panel2); f.add(panel3); f.add(panel4); f.add(panel5); f.add(panel6);
            f.setSize(600,1000);  
            f.setLayout(null);  
            f.setVisible(true);

        }

        if(vname.equals("DB>")){
            System.out.println("\tmailers\n\ttax\n\tpassword\n\tmap\n\tmeter");
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
        else if(vname.equals("DB>PASSWORD")){
            selectedDb[0] = "Pass";
            System.out.println("\n\t\t>> Conected to the Password database");
            System.out.println("\t\t=================================");
        }
        else if(vname.equals("DB>MAP")){
            selectedDb[0] = "Map";
            System.out.println("\n\t\t>> Conected to the Map database");
            System.out.println("\t\t=================================");
        }
        else if(vname.equals("DB>METER")){
            selectedDb[0] = "Meter";
            System.out.println("\n\t\t>> Conected to the Meter database");
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
                System.out.print(B);
                datebatch = vname.substring(5,vname.length()); 
                vars("Mailers", "SELECT * FROM MAILERS WHERE [Sent Date]='" + datebatch + "'");
            }
            else if(vname.contains("TODAY>")){
                System.out.print(B);
                changeDateFormat();
                vars("Mailers", "SELECT * FROM MAILERS WHERE [Sent Date]='" + newdate + "'");
            }
            else if(vname.contains("STATE>")){
                System.out.print("\n\tBatch \tSent Date \tNum  Batch ID \n" );
                statebatch = vname.substring(6,vname.length());
                vars("Mailers", "SELECT * FROM MAILERS WHERE [State] LIKE'%" + statebatch + "%'");
                System.out.print(B);
            }
            else if(vname.contains("CHECK>")){
                checkbatch = vname.substring(6,vname.length());
                vars("Mailers", "SELECT * FROM MAILERS WHERE [Check #]='" + checkbatch + "'");
            }
            else if(vname.contains("BATCH>")){
                changeDateFormat();
                System.out.print("\n\t\tSent Date \tTray-1 \tTray-2 \tPieces \tBatch ID \n" );
                bbatch = vname.substring(6,vname.length());  
                vars("Mailers", "SELECT * FROM MAILERS WHERE [Batch]='" + bbatch + "'");
                System.out.print(B);
            }
            else if(vname.contains("STATUS>")){
                changeDateFormat();
                statusbatch = vname.substring(7,vname.length());
                vars("Mailers", "SELECT * FROM MAILERS WHERE [Batch]='" + statusbatch + "'ORDER BY Status IS NULL, Status DESC,sap");
                System.out.print(B);
            }
            else if(vname.equals("RUNNING>")){
                vars("Mailers", "SELECT * FROM MAILERS WHERE [Status]='running' ORDER BY [sent Date] ASC");
                System.out.print(B);
            }
            else if(vname.equals("PRINTED>")){
                vars("Mailers", "SELECT * FROM MAILERS WHERE [Status]='printed' ORDER BY [sent Date] ASC");
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
        else if(selectedDb[0] == "Pass"){
            if(vname.equals("ALL")){
                vars("Pass", "SELECT * FROM " + selectedDb[0]);  
                System.out.print(B);             
            }
            else if(vname.contains("NUM>")){
                String numPass = vname.substring(4,vname.length());
                vars("Pass", "SELECT * FROM Pass WHERE ID='" + numPass + "'");
            }
            else if(vname.contains("ACC>")){
                String numPass = vname.substring(4,vname.length());
                vars("Pass", "SELECT * FROM Pass WHERE acc='" + numPass + "'");
            }
            else if(vname.contains("UPDATE>")){
                updatePass(vname.substring(7, vname.length()));  
                System.out.print(B); 
            } 
            else if(vname.contains("PASS>ADD>")){ 
                if(SasPass.User.equals("sasan")){ 
                    addpass(vname.substring(9,vname.length()), "add"); 
                } else{System.out.println("\t\tAccess denied!");}
                System.out.print(B); 
            }
            else if(vname.contains("PASS>DEL>")){ 
                if(SasPass.User.equals("sasan")){ 
                    addpass(vname.substring(9,vname.length()), "del"); 
                } else{System.out.println("\t\tAccess denied!");}
                System.out.print(B); 
            }
            if(vname.contains("LINK>")){
                String numTax = vname.substring(5,vname.length());
                vars("Pass", "SELECT * FROM Pass WHERE ID='" + numTax + "'");
            }
        }
        else if(selectedDb[0] == "Map"){
            if(vname.equals("ALL") || vname.equals("V>ALL") || vname.equals("C>ALL") || vname.equals("F>ALL")  || vname.equals("I>ALL") || vname.equals("R>ALL")  ){
                if(vname.equals("V>ALL")){
                    System.out.println( B + "\t" + "VEGAS\n\t--------------\n" );
                }
                if(vname.equals("C>ALL")){
                    System.out.println( B + "\t" + "CHATSWORTH\n\t--------------\n" );
                }
                if(vname.equals("F>ALL")){
                    System.out.println( B + "\t" + "FULERTON\n\t--------------\n" );
                }
                if(vname.equals("I>ALL")){
                    System.out.println( B + "\t" + "INDIA\n\t--------------\n" );
                }
                if(vname.equals("R>ALL")){
                    System.out.println( B + "\t" + "REMOTE\n\t--------------\n" );
                }
                vars("Map", "SELECT * FROM " + selectedDb[0]);  
                System.out.print(B);             
            }
            else if((vname.contains("WIN>")) ){
                vars("Map", "SELECT * FROM map ORDER BY [windows] DESC");  
                System.out.print(B);             
            }
            else if(vname.contains("NUM>")){
                String numPass = vname.substring(4,vname.length());
                vars("Map", "SELECT * FROM map WHERE ID='" + numPass + "'");
            }
            else if(vname.contains("EXT>")){
                String numPass = vname.substring(4,vname.length());
                vars("Map", "SELECT * FROM map WHERE tel='" + numPass + "'");
            }
            else if(vname.contains("IP>")){
                String numPass = vname.substring(3,vname.length());
                vars("Map", "SELECT * FROM map WHERE ip='" + numPass + "'");
            }
            else if(vname.contains("NAME>")){
                String numPass = vname.substring(5,vname.length());
                vars("Map", "SELECT * FROM map WHERE name='" + numPass + "'");
            }

        }
        else if(selectedDb[0] == "Meter"){
            if(vname.equals("ALL") ){
                System.out.println( B + "\tNum" + "\tDate" + "\t\tTime" + "\tMeter A" + "\t\tMeter B" + "\t\tBatch-ID" );
                vars("Meter", "SELECT * FROM " + selectedDb[0]);  
                System.out.print(B);             
            } 
            else if(vname.contains("METER+>")){ 
                addmeter(vname.substring(7,vname.length()), "add"); 
                System.out.print(B); 
            }
            else if(vname.contains("CAL>")){ 
                vars("Meter", "SELECT * From Meter");
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
            allUsers("users"); 
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
        if(vname.equals("LOG>")){           
            viewLog();            
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
                        new ProcessBuilder("cmd", "/c", "color 0C").inheritIO().start().waitFor(); 
                    }
                    else if(name.equals("color")){ 
                        new ProcessBuilder("cmd", "/c", "color F0").inheritIO().start().waitFor(); 
                    }   
                    else{
                          new ProcessBuilder("cmd", "/c", "color 07").inheritIO().start().waitFor();
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


