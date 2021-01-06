
import java.sql.*;
import java.io.*;
import java.awt.event.*; 
import java.awt.*; 
import javax.swing.*;
import java.util.*;
 
public class Sasaccessdb {

    public static char[] userString = System.console().readPassword("%s", "SasanApp> Enter username");
    public static String User = new String(userString);
    public static String B = System.getProperty("line.separator");
    public static String[] files = {"sasan"};
    public static String[] selectedDb = {"sasan"};
    public static String Pass = "";
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

    public void checkPassword(){
        System.out.println("SasanApp> username: " + User);
        System.out.print("SasanApp> Password: ");
        for(int i=0; i< Pass.length(); i++){ 
          System.out.print("*");
        } 
        System.out.print(B);
    }

    public void passpopup(){
        final JPasswordField pf = new JPasswordField();

        JOptionPane pane = new JOptionPane(pf, JOptionPane.PLAIN_MESSAGE, JOptionPane.OK_CANCEL_OPTION);
        JDialog dialog = pane.createDialog("ENTER SUPERUSER PASSWORD");
       
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

    public void printTaxRs(String taxId, String taxState, String taxAlert, String taxDate, String taxBalance, String taxUser, String taxPass, String taxPin, String taxLn, String taxNote, String nextFiling){
        String tId = taxId.substring(5, taxId.length());
        if(taxId.length() == 6  ){ tId = "0" + tId; } 
        if(files[0].equals("ALL")){
            if(taxDate.equals("")){ taxDate = "";}else{ taxDate = taxDate.substring(8, taxDate.length());}
            if(nextFiling.equals("")){ nextFiling = "";}else{ nextFiling = nextFiling.substring(7, nextFiling.length());}
            if( taxState.length() < 19  ){ System.out.println( "\t[" + tId + "] " + taxState.substring(8, taxState.length()) + "\t\t\t" + taxDate  ); }
            else if( taxState.length() > 26  ){ System.out.println( "\t[" + tId + "] " + taxState.substring(8, taxState.length()) + "\t" + taxDate  ); }
            else{
                System.out.println( "\t[" + tId + "] " + taxState.substring(8, taxState.length()) + "\t\t" + taxDate );
            }
        }
        else if(files[0].equals("DATE")){
            if(taxDate.equals("")){ taxDate = "";}else{ taxDate = taxDate.substring(8, taxDate.length());}
            if(nextFiling.equals("")){ nextFiling = "";}else{ nextFiling = nextFiling.substring(7, nextFiling.length());}
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
            if(nextFiling.equals("")){ nextFiling = "";}else{ nextFiling = nextFiling.substring(7, nextFiling.length());}
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
                    "\n\t[" +
                    tId + "] " + 
                    taxState.substring(8, taxState.length()) + "\n\n\t" +  
                    taxDate + "\n\t" +
                    nextFiling + "\n\t" +  
                    taxBalance + "\n\t" + 
                    taxUser + "\n\t" + 
                    taxPass + "\n\t" + 
                    taxPin + "\n\t" + 
                    taxLn + "\n\t" + 
                    taxAlert + "\n\t" +
                    taxNote + "\n\t" 
                    );
            System.out.print(B);
        }
    }

    public void printRes(String batchNum, String batchId, String sentDate, String state, String t1, String t2, String check, String piece, String sap, String status, String batch){
        if(files[0].equals("ALL")){
            System.out.println( "\t" + batchNum + "\t" + batchId );
        }
        else if(files[0].contains("STATUS>")){
            if(status == ""){
                System.out.println("\t\t|         | => " + batchId );
            }
            else{
                switch (status){
                    case "ready": System.out.println("\t\t| ready   | => " + batchId ); break;
                    case "printed": System.out.println("\t\t| PRINTED | => " + batchId ); break;
                    case "shipped": System.out.println("\t\t| SHIPPED | => " + batchId ); break;
                    case "hold": System.out.println("\t\t| HOLD    | => " + batchId ); break;
                    default: System.out.println("\t\t| " + status + " | => " + batchId );
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
            } else{
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
                    "\n\tBatch\t\t" + batch + "\n\tDate\t\t" + sentDate + "\n\tState\t\t" + state + "\n\tCheck\t\t" + check + "\n\t----------------------------" + "\n\tPieces\t\t" +
                    piece + "\n\tTray-1\t\t" + t1 + "\n\tTray-2\t\t" + t2 + "\n\t----------------------------" + "\n\tSAP\t\t" + 
                    sap + "\n\tStatus\t\t" + status + "\n\t----------------------------" 
                    );
    
            System.out.print(B);
        }
    }

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
                    if(rs.getString(4) == null){taxAlert = "";} else{taxAlert = "error:\t\t" + rs.getString(4);}
                    if(rs.getString(5) == null){taxDate = "";} else{taxDate = "filed:\t\t" + rs.getString(5);}
                    if(rs.getString(6) == null){taxBalance = "";} else{taxBalance = "balance:\t" + rs.getString(6);}
                    if(rs.getString(7) == null){taxUser = "";} else{taxUser = "user:\t\t" + rs.getString(7);}
                    if(rs.getString(8) == null){taxPass = "";} else{taxPass = "pass:\t\t" + rs.getString(8);}
                    if(rs.getString(9) == null){taxPin = "";} else{taxPin = "pin:\t\t" + rs.getString(9);}
                    if(rs.getString(10) == null){taxLn = "";} else{taxLn = "license:\t" + rs.getString(10);}
                    if(rs.getString(11) == null){taxNote = "";} else{taxNote = "note:\t\t" + rs.getString(11);}
                    if(rs.getString(12) == null){nextFiling = "";} else{nextFiling = "next:\t\t" + rs.getString(12);}

                    printTaxRs(taxId, taxState, taxAlert, taxDate, taxBalance, taxUser, taxPass, taxPin, taxLn, taxNote, nextFiling);
                }
            } 
            else if(sasDb.equals("update")){
                
                System.out.println("begin:\n\n");

                PreparedStatement updateSales = connection.prepareStatement(sas);

                updateSales.executeUpdate();

                // statement.executeUpdate(sas); 
                System.out.println("UPDATED");
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






    public void updateRecord(String sasDb){

        // variables
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







    public void vName(String vname) {
        files[0] = vname;
        if(vname.equals("HELP>")){
            System.out.println("\n\n\t\tdb>[database name]\t\t\tfor connect to a data base");
            System.out.println("\t\thelp>\t\t\t\t\tfor more information");
            System.out.println("\t\texit\t\t\t\t\tfor quit from app");
            System.out.println("\t\t-----------------------------------------------------------------------------------------------");
            System.out.println("\n\t\t[ Mailers data base ]\n");
            System.out.println("\t\tall\t\t\t\t\tfor whole list of batch Ids");
            System.out.println("\t\tnum>[number]\t\t\t\tshow details of a batch id search by number");
            System.out.println("\t\tbatch>[number]\t\t\t\tshow full details of a batch");
            System.out.println("\t\tdate>[date]\t\t\t\tshow details of batch id(s) search by date");
            System.out.println("\t\tstate>[state]\t\t\t\tdetails of batch id(s) search by state abbreviation");
            System.out.println("\t\tcheck>[number]\t\t\t\tshow details of a batch id search by check number");
            System.out.println("\t\tdate>all\t\t\t\tshow whole list of dates and batch ids");
            System.out.println("\t\tcheck>all\t\t\t\tshow whole list of check numbers and batch ids");
            System.out.println("\t\tstatus>[number]\t\t\t\tshow status details of a whole batch");
            System.out.println("\t\tsap>[number]\t\t\t\tshow sap details of a whole batch");
            System.out.println("\t\t-----------------------------------------------------------------------------------------------");
            System.out.println("\n\t\t[ Tax data base ]\n");
            System.out.println("\t\tall\t\t\t\t\tfor whole list of states");
            System.out.println("\t\tnum>[number]\t\t\t\tshow details of a state search by number");
            System.out.println("\t\tstate>[state]\t\t\t\tshow details of a state search by state name");
            System.out.println("\t\tdate\t\t\t\t\twhole list of states sort by last filing date");
            System.out.println("\t\tdue\t\t\t\t\twhole list of states sort by next filing due date");
            System.out.println("\t\t-----------------------------------------------------------------------------------------------");
            System.out.println("\n\t\t[ Update Data ]\n");
            System.out.println("\t\tset>[number]/[column]/[new data]\tfor update a record.");
        }

        if(vname.equals("DB>MAILERS")){
            selectedDb[0] = "Mailers"; 
            System.out.println("\n\t\t>> Conected to the Mailers database {M}");
            System.out.println("\t\t=======================================");
        }
        else if(vname.equals("DB>TAX")){
            selectedDb[0] = "List";
            System.out.println("\n\t\t>> Conected to the Tax database {T}");
            System.out.println("\t\t===================================");
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
            updateRecord(vname.substring(4, vname.length()));  
            System.out.print(B); 
        }  
        System.out.print("$ ");         
    }


    public static void main(String[] args) {        
        Sasaccessdb mailersData = new Sasaccessdb();    
        mailersData.passpopup();
        mailersData.checkPassword();
        Scanner in = new Scanner(System.in);    
 
        System.out.println("===============================================================================================================");
        System.out.println("\t\t Name: \t\tSasanApp");
        System.out.println("\t\t Version: \t3.2.0");
        System.out.println("\t\t Author: \tSasan Bazade");
        System.out.println("===============================================================================================================");
        System.out.println("\t\t\t\t\t\t\t\t\t\ttype help> for more information");
            
        if(Pass.equals("sasan") && User.equals("sasan")){ 
            while ( Pass.equals("sasan") ) {
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
                } catch (Exception e) { 
                    System.out.print("ERROR:  wrong input"); break;
                }
            }    
        } 
        else { System.out.print("\t\t\tInvalid username or password!"); }
        in.close();         
    }
}


