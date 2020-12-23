
import java.sql.*;
import java.io.*;
import java.awt.event.*; 
import java.awt.*; 
import javax.swing.*;
import java.util.*;
 
public class Sasaccessdb {

    public static String B = System.getProperty("line.separator");
    public static String[] files = {"sasan"};
    public static String Pass = "sasan";
    public static String numbatch;
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

    public void printRes(String batchNum, String batchId, String sentDate, String state, String t1, String t2, String check, String piece, String sap, String status, String batch){
        if(files[0].equals("ALL")){
            System.out.println( "\t" + batchNum + "\t" + batchId );
        }
        else if(files[0].contains("STATUS>")){
            if(status == "Status: "){
                System.out.println("\t\t|       | => " + batchId );
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
            if(sap == "Sap: "){
                System.out.println("\t\t [ ]       => " + batchId);
            }
            else{
                System.out.println("\t\t [+] ADDED => "+batchId);  
            }
        }
        else{
            if(files[0].contains("CHECK>")){ System.out.println( "\n\t" + check + "\n\t--------------"); }
            if(files[0].contains("STATE>")){ System.out.println( "\n\t" + state + "\n\t--------------"); }
            if(files[0].contains("DATE>")){ System.out.println( "\n\t" + sentDate + "\n\t--------------"); }
            if(files[0].contains("BATCH>")){ System.out.println( "\n\t" + batch + "\n\t--------------"); }
            System.out.println( 
                    "\n\t" +
                    batchNum + "\n\t" + 
                    batchId + "\n\t" + 
                    sentDate + "\n\t" + 
                    state + "\n\t" + 
                    t1 + "\n\t" + 
                    t2 + "\n\t" + 
                    check + "\n\t" + 
                    piece + "\n\t" + 
                    sap + "\n\t" + 
                    status + "\n\t" +
                    batch + "\n\t" 
                    );
            System.out.print(B);
        }
    }

    public void vars(String sas){

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
 
            connection = DriverManager.getConnection("jdbc:ucanaccess://C:/Users/it/Desktop/accjava/db.accdb"); 
            statement = connection.createStatement();

            rs = statement.executeQuery(sas); 
            while(rs.next()) {                        
                        if(rs.getString(1) == null){batchNum = "";} else{batchNum = rs.getString(1);}
                        if(rs.getString(2) == null){batchId = "";} else{batchId = rs.getString(2);}
                        if(rs.getString(3) == null){sentDate = "Date: ";} else{sentDate = "Date: " + rs.getString(3);}
                        if(rs.getString(4) == null){responseDate = "";} else{responseDate = rs.getString(4);}
                        if(rs.getString(5) == null){state = "State: ";} else{state = "State: " + rs.getString(5);}
                        if(rs.getString(6) == null){t1 = "Tray1: ";} else{t1 = "Tray1: " + rs.getString(6);}
                        if(rs.getString(7) == null){t2 = "Tray2: ";} else{t2 = "Tray2: " + rs.getString(7);}
                        if(rs.getString(8) == null){check = "Check: ";} else{check ="Check: " + rs.getString(8);}
                        if(rs.getString(9) == null){piece = "Pieces: ";} else{piece = "Pieces: " + rs.getString(9);}
                        if(rs.getString(10) == null){sap = "Sap: ";} else{sap = "Sap: " + rs.getString(10);}
                        if(rs.getString(11) == null){status = "Status: ";} else{status = "Status: " + rs.getString(11);}
                        if(rs.getString(12) == null){batch = "Batch: ";} else{batch = "Batch: " + rs.getString(12);}
                                                
                        printRes( batchNum,  batchId,  sentDate,  state,  t1,  t2,  check,  piece,  sap,  status,  batch);
                
            }                    
                          
        }
        catch(SQLException sqlex){ sqlex.printStackTrace(); }

        finally {

            try {
                if(null != connection) {
                    rs.close();
                    // bnum.close();
                    statement.close();
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
        if(vname.equals("ALL")){
            vars("SELECT * FROM MAILERS");  
            System.out.print(B);             
        }            
        if(vname.contains("NUM>")){
            numbatch = vname.substring(4,vname.length());
            vars("SELECT * FROM MAILERS WHERE ID='" + numbatch + "'");
        }
        if(vname.contains("DATE>")){
            datebatch = vname.substring(5,vname.length()); 
            vars("SELECT * FROM MAILERS WHERE [Sent Date]='" + datebatch + "'");
        }
        if(vname.contains("STATE>")){
            statebatch = vname.substring(6,vname.length());
            vars("SELECT * FROM MAILERS WHERE [State] LIKE'%" + statebatch + "%'");
        }
        if(vname.contains("CHECK>")){
            checkbatch = vname.substring(6,vname.length());
            vars("SELECT * FROM MAILERS WHERE [Check #]='" + checkbatch + "'");
        }
        if(vname.contains("BATCH>")){
            bbatch = vname.substring(6,vname.length());  
            vars("SELECT * FROM MAILERS WHERE [Batch]='" + bbatch + "'");
        }
        if(vname.contains("STATUS>")){
            statusbatch = vname.substring(7,vname.length());
            vars("SELECT * FROM MAILERS WHERE [Batch]='" + statusbatch + "'");
            System.out.print(B);
        }
        if(vname.contains("SAP>")){
            sapbatch = vname.substring(4,vname.length());
            vars("SELECT * FROM MAILERS WHERE [Batch]='" + sapbatch + "'");
            System.out.print(B);
        }
    }


    public static void main(String[] args) {
          
        Sasaccessdb mailersData = new Sasaccessdb();
        Scanner in = new Scanner(System.in);    
 
            
        if(Pass.equals("sasan")){ 
            while ( Pass.equals("sasan") ) {
                try {
                String name = in.next(); 
                if(name.equals("exit")){ 
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor(); 
                    System.exit(0);
                }
                mailersData.vName(name.toUpperCase());
                } catch (Exception e) { 
                    System.out.print("ERROR:  wrong input"); break;
                }
            }    
        } 
        else { System.out.print("WRONG PASSWORD!"); }
        in.close();         
    }
}


