import java.util.Scanner; // Import the Scanner class to read text files
import java.io.*;
import java.awt.event.*; 
import java.awt.*; 
import javax.swing.*;
import java.util.Arrays;
import java.util.Comparator;

public class Sasan {
    // public static String Pass = "1360";
    public static char[] passString = System.console().readPassword("%s", "Enter password: ");
    public static String Pass = new String(passString);
    public static String B = System.getProperty("line.separator");
    public static String[] files = {"mailers.txt","pass.txt","tax.txt"};

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
                        
    public void vName(String vname){
        
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
                        var i = 0;
                        while ( i < datas.length){
                            System.out.println("\t" + num + "\t" + batchId);
                            break;
                        }
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


    public static void main(String[] args) {
          
        Sasan mailersData = new Sasan();
        Scanner in = new Scanner(System.in);    
            
        if(Pass.equals("sasan")){ 
            // checkPasswordName.printCredit("#type help  for more information", false);  
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
        // else { checkPasswordName.printCredit("WRONG PASSWORD!", true); }
        in.close();         
    }






}




    