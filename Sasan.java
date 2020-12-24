
import java.io.*;
import java.awt.event.*; 
import java.awt.*; 
import javax.swing.*;
import java.util.*;

public class Sasan {
    // public static String Pass = "1360";
    public static char[] passString = System.console().readPassword("%s", "Enter password: ");
    public static String Pass = new String(passString);
    public static String B = System.getProperty("line.separator");
    public static String[] files = {"sasan.txt"};
    public static String[] passwordSas = {"sasan"};
    public static String taxDatas2 = "";

   public void output(boolean batchFind, String bid,String date,String p,String tray1, String tray2, String checknum, String sapimport, String st, String batch){        
        System.out.println("\t Id: \t\t" + bid);
        System.out.println("\t Sent: \t\t" + date);
        System.out.println("\t Pieces: \t" + p);
        System.out.println("\t Tray(1'): \t" + tray1);
        System.out.println("\t Tray(2'): \t" + tray2);
        System.out.println("\t Check#: \t" + checknum);
        System.out.println("\t Sap: \t\t" + sapimport); 
        System.out.println("\t Status: \t" + st);
        if (!batchFind) {    
            System.out.println("\t Batch: \t" + batch);
        }
   }

   public void passpopup(){
    JPanel panel = new JPanel();
    JLabel label = new JLabel("Enter password:");
    JPasswordField pass = new JPasswordField(10);
    panel.add(label);
    panel.add(pass);
    String[] options = new String[]{"OK"};
     JOptionPane.showOptionDialog(null, panel, "PASSWORD APP",
                                  JOptionPane.NO_OPTION, JOptionPane.PLAIN_MESSAGE,
                                  null, options, options[0]);
    
        char[] password = pass.getPassword();
        String Pass = new String(password);
        passwordSas[0] = Pass;
        // return Pass2;
    }

    public void changeDb(String userinput, String dbinput){
        switch (userinput){
            case "DB>MAILERS": files[0] = "mailers2.csv"; System.out.println(B+"\t\t\tCurrent database => [ Mailers ]"); break;  // mailers.txt
            case "DB>TAX": files[0] = "tax.txt"; System.out.println(B+"\t\t\tCurrent database => [ Tax ]"); break;  // tax.txt
            default: passpopup(); if(passwordSas[0].equals("sasi")){files[0] = "pass.txt"; System.out.println(B+"\t\t\tCurrent database => [ Password ]");} else{System.out.println("\t\t\tWrong Password!");}  // pass.txt
            }  
    }

                           
    public void vName(String vname){

        if (vname.equals("HELP")){
            System.out.print( B );
            for(int i =0; i<46; i++){System.out.print( "-" );}
            System.out.print(" List of commands ");
            for(int i =0; i<46; i++){System.out.print( "-" );}
            System.out.println( B );
            System.out.println("\t[ help ]\t\t\t\t\tfor information");
            System.out.println("\t[ all ]\t\t\t\t\t\tfor whole mailer IDs list");
            System.out.println("\t[ number ]\t\t\t\t\tsearch by number \t\t ex: 26");
            System.out.println("\t[ date ]\t\t\t\t\tsearch by sent Date \t\t ex: date>09/04/2020");
            System.out.println("\t[ check ] + [ >check number ]\t\t\tsearch by check number \t\t ex: check>6145");
            System.out.println("\t[ id ] + [ >ID number ]\t\t\t\tsearch by ID \t\t\t ex: id>MO-NJ_1518_2");
            System.out.println("\t[ state ] + [ >state abbreviation ]\t\tsearch by State \t\t ex: state>tx");
            System.out.println("\t[ batch ] + [ >Batch number ] \t\t\tfor all data with same batch \t ex: batch>2");
            System.out.println("\t[ batch ] + [ >Batch number ] + [ >status ]\tshipment status(batch#) \t ex: batch>2>status");
            System.out.println("\t[ batch ] + [ >Batch number ] + [ >sap ]\tSAP status(batch#) \t\t ex: batch>2>sap");
            System.out.println("\t[ exit ]\t\t\t\t\tquit from app"+B);
            for(int i =0; i<110; i++){System.out.print( "-" );} 
            System.out.print( B );
        }		
           
        // ************************************************************************************************************************
        //                                                         Passwords
        // ************************************************************************************************************************

        if(vname.equals("DB>PASSWORD")){ changeDb( vname, "DB>PASSWORD"); }
        if(files[0].equals("pass.txt")){
            try {
                File myObj3 = new File(files[0]);
                Scanner myReader3 = new Scanner(myObj3);
                
                
                while (myReader3.hasNextLine()) {
                    String db = myReader3.nextLine();
                    files[0] = db;
                    System.out.println(db);
                }
                myReader3.close();
            }
            catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }

        // ************************************************************************************************************************
        //                                                         Mailers
        // ************************************************************************************************************************

        if(vname.equals("DB>MAILERS")){ changeDb( vname, "DB>MAILERS"); }
        if(files[0].equals("mailers2.csv")){  
            try {
                File myObj = new File(files[0]);
                Scanner myReader = new Scanner(myObj);
                                
                while (myReader.hasNextLine()) { 
                    String data = myReader.nextLine();
                    String datas [] = data.split(",");
                    // int seprator = data.indexOf("|");
                    String num = datas[0];
                    String batchId = datas[1].substring(3,datas[1].length()); 
                    String sent = datas[2].substring(5,datas[2].length());
                    String pieces = datas[3].substring(7,datas[3].length());
                    String t1 = datas[4].substring(9,datas[4].length());
                    String t2 = datas[5].substring(9,datas[5].length());
                    String check = datas[6].substring(7,datas[6].length());
                    String sap = datas[7].substring(4,datas[7].length());
                    String status = datas[8].substring(7,datas[8].length());
                    String batch = datas[9].substring(6,datas[9].length()); 
                    
                    if (vname.equals("ALL")){
                        System.out.println("\t" + num + "\t" + batchId);
                    }

                    if(vname.equals(num)){
                        System.out.println(B);
                        output(false, batchId, sent, pieces, t1, t2, check, sap, status, batch);
                    } 
                    if(vname.equals("ID>"+batchId)){
                        System.out.println(B);
                        output(false, batchId, sent, pieces, t1, t2, check, sap, status, batch);
                    } 
                    if(vname.equals("DATE>"+sent)){
                        System.out.println(B);
                        output(false, batchId, sent, pieces, t1, t2, check, sap, status, batch);
                    } 
                    if(vname.equals("CHECK>"+check)){ 
                        System.out.println(B);
                        output(false, batchId, sent, pieces, t1, t2, check, sap, status, batch);                        
                    }
                    if(vname.equals("BATCH>"+batch)){
                        System.out.println(B+B+B+"\t [ Batch: " + batch + " ]");
                        System.out.println("\t =============");
                        output(true, batchId, sent, pieces, t1, t2, check, sap, status, batch);
                    }
                    if(vname.equals("BATCH>"+batch+">STATUS")){
                        switch (status){
                            case "ready": System.out.println("\t\t| ready   | => " + batchId ); break;
                            case "printed": System.out.println("\t\t| PRINTED | => " + batchId ); break;
                            case "shipped": System.out.println("\t\t| SHIPPED | => " + batchId ); break;
                            case "hold": System.out.println("\t\t| HOLD    | => " + batchId ); break;
                            default: System.out.println("\t\t| " + status + " | => " + batchId );
                            }                        
                    }
                    if(vname.equals("BATCH>"+batch+">SAP")){      
                        if (sap.equals("+")){      
                            System.out.println("\t\t [+] ADDED => "+batchId);
                        } else {System.out.println("\t\t [ ]       => " + batchId);}
                    }  

                    if(vname.contains("STATE>")){   
                        String stateRes = vname.substring(6, vname.length());
                        boolean state = batchId.contains(stateRes);                      
                        if (state){  
                            System.out.println(B+B+B+"\t [ State: " + stateRes + " ]");
                            System.out.println("\t =============");
                            output(false, batchId, sent, pieces, t1, t2, check, sap, status, batch);  
                        } 
                    } 
                } 
                System.out.println(B); 
                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }

        // ************************************************************************************************************************
        //                                                         TAX
        // ************************************************************************************************************************
        
        if(vname.equals("DB>TAX")){ changeDb( vname, "DB>TAX"); }
        if(files[0].equals("tax.txt")){
            try {
                File myObj2 = new File(files[0]);
                Scanner myReader2 = new Scanner(myObj2);
                
                
                while (myReader2.hasNextLine()) {
                    String taxData = myReader2.nextLine();
                    // taxDatas2 = taxDatas2.concat(taxData);  
                    String taxDatas [] = taxData.split(",");
                    // int seprator = data.indexOf("|");
                   
                    String abbr = taxDatas[0];
                    String state = taxDatas[1]; 
                    String filing = taxDatas[2];
                    String period = taxDatas[3];
                    String error = taxDatas[4];
                    String balance = taxDatas[5];
                    String payment = taxDatas[6];
                    String taxInfo [] = {"State Name      >","Last Filing     >","Next Filing     >","Error Message   >","Balance Due     >","Payment Status  >"};
                    Integer stateLn;
                    if(state.length() >= 20){ stateLn = 20;} else if(state.length() >= 8){ stateLn = 8;} else { stateLn = 1; }

                    if (vname.equals("STATE>ALL")){
                        // taxoutput( abbr, state, filing, period, "no error", null);
                        
                        switch(stateLn){
                            case 20:
                                System.out.println("\t\t" + abbr + "\t" + state + "\t" + filing + "\t" + period );
                                break;
                            case 8:
                                System.out.println("\t\t" + abbr + "\t" + state + "\t\t" + filing + "\t" + period );
                                break;
                            default:
                                System.out.println("\t\t" + abbr + "\t" + state + "\t\t\t" + filing + "\t" + period );
                        }
                       
                    }

                    if (vname.equals("STATE>BALANCE")){ 
                        // taxoutput( abbr, state, null, null, " ", balance);
                        if(!balance.equals("0.00")){
                            switch(stateLn){
                                case 20:
                                    System.out.println("\t\t" + abbr + "\t" + state + "\t$ " + balance );
                                    break;
                                case 8:
                                    System.out.println("\t\t" + abbr + "\t" + state + "\t\t$ " + balance );
                                    break;
                                default:
                                    System.out.println("\t\t" + abbr + "\t" + state + "\t\t\t$ " + balance );
                            }
                        }
                    }

                    if (vname.equals("STATE>ERROR")){
                        if(error.length() >1){
                            // taxoutput( abbr, state, null, null, error, null);
                            switch(stateLn){
                                case 20:
                                    System.out.println("\t\t" + abbr + "\t" + state + "\t" + error );
                                    break;
                                case 8:
                                    System.out.println("\t\t" + abbr + "\t" + state + "\t\t" + error );
                                    break;
                                default:
                                    System.out.println("\t\t" + abbr + "\t" + state + "\t\t\t" + error );
                            }
                        }
                    }

                    if (vname.equals("STATE>DATE")){    
                        System.out.println( "\t\t" + abbr + "\t" +  filing);                                                
                    }

                    if (vname.equals("STATE>PAYMENT")){ 
                        if (!payment.equals("NOPAYMENT")){   
                            System.out.println( "\t\t" + abbr + "\t" +  payment);
                        }                                                
                    }

                    if (vname.equals("STATE>"+taxDatas[0])){
                        for(int j = 1; j < taxDatas.length; j++){                           
                            switch(j){                               
                                default:
                                System.out.print( "\t\t" + taxInfo[j-1] + "\t"+taxDatas[j]+"\n");
                                break;
                            }

                        }                   
                        System.out.print(B);
                        break;
                    } 
                }
                
                // String taxDatas [] = taxDatas2.split(",");
                // for(int i=0; i < taxDatas.length; i++){
                //     System.out.println(taxDatas[i]);
                // }

                myReader2.close();
            }
            catch (FileNotFoundException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
        }
        
    }

    

    public void creditCrator(){
        System.out.print(B);
        for(int i =0; i<110; i++){System.out.print( "*" );}
        printCredit("Sasan APP", true);
        printCredit("version 2.1.0", false);
        printCredit("by Sasan Bazade", false);	 
        for(int i =0; i<110; i++){System.out.print( "*" );}
        System.out.print(B);
      }
      public void printCredit(String text, boolean brline){
        if(!brline){
          System.out.print("\t\t\t\t\t\t" + text + B );
        } else {
          System.out.print(B + "\t\t\t\t\t\t" + text + B );
        }    
      }
    
      public void checkPassword(){
        System.out.print("Password: ");
        for(int i=0; i< Pass.length(); i++){ 
          System.out.print("*");
        } 
        System.out.print(B);
      }

    public static void main(String[] args) {
          
        Sasan mailersData = new Sasan();
        Scanner in = new Scanner(System.in);    
        mailersData.checkPassword();
        mailersData.creditCrator(); 
            
        if(Pass.equals("sasan")){ 
            mailersData.printCredit("> type help  for more information", false);  
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
        else { mailersData.printCredit("WRONG PASSWORD!", true); }
        in.close();         
    }






}




    