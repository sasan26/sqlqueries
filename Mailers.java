import java.util.Scanner;
import java.io.*;
import java.awt.event.*; 
import java.awt.*; 
import javax.swing.*;

// public class CLS {
//   public static void main(String... arg) throws IOException, InterruptedException {
//       new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
//   }
// }

public class Mailers {
  public static char[] passString = System.console().readPassword("%s", "Enter password: ");
  public static String Pass = new String(passString);

  public static String B = System.getProperty("line.separator");
  public void vName(String vname){
      String[][] allAccounts = { 
        // BATCH-1
        {"1", "ID: \t\t TX-2016-2018-1", "SENT: \t\t 05/08/2020", "PIECES: \t 10,148", "TRAY(1'): \t ", "TRAY(2'): \t ", "CHECK#: \t ", "SAP: \t\t YES", "STATUS: \t Shipped", "BATCH-1"},
        {"2", "ID: \t\t TX-2016-2018-2", "SENT: \t\t 05/12/2020", "PIECES: \t 9,904", "TRAY(1'): \t 9", "TRAY(2'): \t 10", "CHECK#: \t 6145", "SAP: \t\t YES", "STATUS: \t Shipped", "BATCH-1"},
        {"3", "ID: \t\t TX-2016-2018-3", "SENT: \t\t 05/14/2020", "PIECES: \t 9,743", "TRAY(1'): \t 12", "TRAY(2'): \t 8", "CHECK#: \t 6146", "SAP: \t\t YES", "STATUS: \t Shipped", "BATCH-1"},
        {"4", "ID: \t\t TX-OH-2016-2018-4", "SENT: \t\t 05/20/2020", "PIECES: \t 10,022", "TRAY(1'): \t 4", "TRAY(2'): \t 12", "CHECK#: \t 6147", "SAP: \t\t YES", "STATUS: \t Shipped", "BATCH-1"},
        {"5", "ID: \t\t OH-CO-FL-2016-2018-5", "SENT: \t\t 05/22/2020", "PIECES: \t 9,469", "TRAY(1'): \t 9", "TRAY(2'): \t 9", "CHECK#: \t 6165", "SAP: \t\t YES", "STATUS: \t Shipped", "BATCH-1"},
        {"6", "ID: \t\t FL-2016-2018-6", "SENT: \t\t 05/27/2020", "PIECES: \t 9,584", "TRAY(1'): \t 4", "TRAY(2'): \t 11", "CHECK#: \t 6166", "SAP: \t\t YES", "STATUS: \t Shipped", "BATCH-1"},
        {"7", "ID: \t\t FL-IL-2016-2018-7", "SENT: \t\t 06/03/2020", "PIECES: \t 9,560", "TRAY(1'): \t 6", "TRAY(2'): \t 12", "CHECK#: \t 6169", "SAP: \t\t YES", "STATUS: \t Shipped", "BATCH-1"},
        {"8", "ID: \t\t IL-2016-2018-8", "SENT: \t\t 06/05/2020", "PIECES: \t 9,127", "TRAY(1'): \t 7", "TRAY(2'): \t 9", "CHECK#: \t 6184", "SAP: \t\t YES", "STATUS: \t Shipped", "BATCH-1"},
        // BATCH-2
        {"9", "ID: \t\t GA-2016-2018-1", "SENT: \t\t 06/10/2020", "PIECES: \t 12,413", "TRAY(1'): \t 16", "TRAY(2'): \t 11", "CHECK#: \t 6186", "SAP: \t\t YES", "STATUS: \t Shipped", "BATCH-2"},
        {"10", "ID: \t\t GA-2016-2018-2", "SENT: \t\t 06/12/2020", "PIECES: \t 12,398", "TRAY(1'): \t 11", "TRAY(2'): \t 12", "CHECK#: \t 6187", "SAP: \t\t YES", "STATUS: \t Shipped", "BATCH-2"},
        {"11", "ID: \t\t GA-2016-2018-3", "SENT: \t\t 06/17/2020", "PIECES: \t 11,881", "TRAY(1'): \t 10", "TRAY(2'): \t 12", "CHECK#: \t 6203", "SAP: \t\t YES", "STATUS: \t Shipped", "BATCH-2"},
        {"12", "ID: \t\t GA-2016-2018-6", "SENT: \t\t 06/24/2020", "PIECES: \t 11,684", "TRAY(1'): \t 9", "TRAY(2'): \t 12", "CHECK#: \t 6211", "SAP: \t\t YES", "STATUS: \t Shipped", "BATCH-2"},
        {"13", "ID: \t\t GA-OR-2016-2018-7", "SENT: \t\t 06/26/2020", "PIECES: \t 9,425", "TRAY(1'): \t 6", "TRAY(2'): \t 13", "CHECK#: \t 6212", "SAP: \t\t YES", "STATUS: \t Shipped", "BATCH-2"},
        {"14", "ID: \t\t OR-VA-2016-2018-10", "SENT: \t\t 07/01/2020", "PIECES: \t 10,588", "TRAY(1'): \t 4", "TRAY(2'): \t 13", "CHECK#: \t 6213", "SAP: \t\t YES", "STATUS: \t Shipped", "BATCH-2"},
        // BATCH-3
        {"15", "ID: \t\t NC-2015-2018-1", "SENT: \t\t 07/03/2020", "PIECES: \t 12,462", "TRAY(1'): \t 8", "TRAY(2'): \t 13", "CHECK#: \t 6243", "SAP: \t\t YES", "STATUS: \t Shipped", "BATCH-3"},
        {"16", "ID: \t\t NC-MD-2015-2018-2", "SENT: \t\t 07/08/2020", "PIECES: \t 12,281", "TRAY(1'): \t 12", "TRAY(2'): \t 12", "CHECK#: \t 6245", "SAP: \t\t YES", "STATUS: \t Shipped", "BATCH-3"},
        {"17", "ID: \t\t MD-CO-2015-2018-3", "SENT: \t\t 07/15/2020", "PIECES: \t 12,038", "TRAY(1'): \t 9", "TRAY(2'): \t 13", "CHECK#: \t 6246", "SAP: \t\t YES", "STATUS: \t Shipped", "BATCH-3"},
        {"18", "ID: \t\t CO-CA-2015-2018-4", "SENT: \t\t 07/17/2020", "PIECES: \t 12,325", "TRAY(1'): \t 11", "TRAY(2'): \t 12", "CHECK#: \t 6267", "SAP: \t\t YES", "STATUS: \t Shipped", "BATCH-3"},
        {"19", "ID: \t\t CA-2015-2018-5", "SENT: \t\t 07/22/2020", "PIECES: \t 12,406", "TRAY(1'): \t 11", "TRAY(2'): \t 12", "CHECK#: \t 6272", "SAP: \t\t YES", "STATUS: \t Shipped", "BATCH-3"},
        {"20", "ID: \t\t CA-2015-2018-6", "SENT: \t\t 08/12/2020", "PIECES: \t 12,518", "TRAY(1'): \t 9", "TRAY(2'): \t 14", "CHECK#: \t 6301", "SAP: \t\t YES", "STATUS: \t Shipped", "BATCH-3"},
        {"21", "ID: \t\t CA-2015-2018-7", "SENT: \t\t 08/19/2020", "PIECES: \t 12,655", "TRAY(1'): \t 9", "TRAY(2'): \t 14", "CHECK#: \t 6324", "SAP: \t\t YES", "STATUS: \t Shipped", "BATCH-3"},
        {"22", "ID: \t\t CA-2015-2018-8", "SENT: \t\t 08/28/2020", "PIECES: \t 12,516", "TRAY(1'): \t 9", "TRAY(2'): \t 14", "CHECK#: \t 6332", "SAP: \t\t YES", "STATUS: \t Shipped", "BATCH-3"},
        {"23", "ID: \t\t CA-2015-2018-9", "SENT: \t\t 09/03/2020", "PIECES: \t 12,360", "TRAY(1'): \t 9", "TRAY(2'): \t 14", "CHECK#: \t 6368", "SAP: \t\t ", "STATUS: \t Problem with USPS", "BATCH-3"},
        {"24", "ID: \t\t CA-2015-2018-10", "SENT: \t\t 09/16/2020", "PIECES: \t 12,317", "TRAY(1'): \t 11", "TRAY(2'): \t 12", "CHECK#: \t ", "SAP: \t\t ", "STATUS: \t printing...", "BATCH-3"},
        {"25", "ID: \t\t CA-2015-2018-11", "SENT: \t\t ", "PIECES: \t 12,528", "TRAY(1'): \t 7", "TRAY(2'): \t 15", "CHECK#: \t ", "SAP: \t\t ", "STATUS: \t ", "BATCH-3"},
        {"26", "ID: \t\t CA-2015-2018-12", "SENT: \t\t ", "PIECES: \t 12,460", "TRAY(1'): \t 10", "TRAY(2'): \t 13", "CHECK#: \t ", "SAP: \t\t ", "STATUS: \t ", "BATCH-3"},
        {"27", "ID: \t\t CA-2015-2018-13", "SENT: \t\t ", "PIECES: \t 12,598", "TRAY(1'): \t 11", "TRAY(2'): \t 12", "CHECK#: \t ", "SAP: \t\t ", "STATUS: \t ", "BATCH-3"},
        {"28", "ID: \t\t CA-2015-2018-14", "SENT: \t\t ", "PIECES: \t 12,536", "TRAY(1'): \t 9", "TRAY(2'): \t 14", "CHECK#: \t ", "SAP: \t\t ", "STATUS: \t ", "BATCH-3"},
        // BATCH-4
        {"29", "ID: \t\t MN-MO_1518_1", "SENT: \t\t 07/24/2020", "PIECES: \t 9,800", "TRAY(1'): \t 4", "TRAY(2'): \t 11", "CHECK#: \t 6274", "SAP: \t\t YES", "STATUS: \t Shipped", "BATCH-4"},
        {"30", "ID: \t\t MO-NJ_1518_2", "SENT: \t\t 07/29/2020", "PIECES: \t 9,771", "TRAY(1'): \t 7", "TRAY(2'): \t 10", "CHECK#: \t 6275", "SAP: \t\t YES", "STATUS: \t Shipped", "BATCH-4"},
        {"31", "ID: \t\t NJ_1518_3", "SENT: \t\t 08/05/2020", "PIECES: \t 9,853", "TRAY(1'): \t 4", "TRAY(2'): \t 11", "CHECK#: \t 6299", "SAP: \t\t YES", "STATUS: \t Shipped", "BATCH-4"},
        {"32", "ID: \t\t NJ-NY_1518_4", "SENT: \t\t 08/07/2020", "PIECES: \t 9,593", "TRAY(1'): \t 10", "TRAY(2'): \t 9", "CHECK#: \t 6300", "SAP: \t\t YES", "STATUS: \t Shipped", "BATCH-4"},
        {"33", "ID: \t\t NY_1518_5", "SENT: \t\t 08/13/2020", "PIECES: \t 9,438", "TRAY(1'): \t 8", "TRAY(2'): \t 10", "CHECK#: \t 6303", "SAP: \t\t YES", "STATUS: \t Shipped", "BATCH-4"},
        {"34", "ID: \t\t NY_1518_6", "SENT: \t\t 08/26/2020", "PIECES: \t 10,495", "TRAY(1'): \t 6", "TRAY(2'): \t 12", "CHECK#: \t 6330", "SAP: \t\t YES", "STATUS: \t Shipped", "BATCH-4"},
        // BATCH-5
        {"35", "ID: \t\t MI-1518_1", "SENT: \t\t 09/02/2020", "PIECES: \t 8,770", "TRAY(1'): \t 5", "TRAY(2'): \t 9", "CHECK#: \t 6367", "SAP: \t\t YES", "STATUS: \t Shipped", "BATCH-5"},
        {"36", "ID: \t\t MI-NE-NM-NV-1518_2", "SENT: \t\t 09/04/2020", "PIECES: \t 8,742", "TRAY(1'): \t 9", "TRAY(2'): \t 9", "CHECK#: \t 6369", "SAP: \t\t YES", "STATUS: \t Shipped", "BATCH-5"},
        {"37", "ID: \t\t NV-SC-1518_3", "SENT: \t\t 08/14/2020", "PIECES: \t 8,671", "TRAY(1'): \t 15", "TRAY(2'): \t 7", "CHECK#: \t 6322", "SAP: \t\t YES", "STATUS: \t Shipped", "BATCH-5"},
        {"38", "ID: \t\t SC-UT-1518_4", "SENT: \t\t 09/11/2020", "PIECES: \t 8,666", "TRAY(1'): \t 6", "TRAY(2'): \t 8", "CHECK#: \t ", "SAP: \t\t ", "STATUS: \t Printed", "BATCH-5"},
        // BATCH-6
        {"39", "ID: \t\t MA-MN-1519_1", "SENT: \t\t ", "PIECES: \t 12,473", "TRAY(1'): \t ", "TRAY(2'): \t ", "CHECK#: \t ", "SAP: \t\t ", "STATUS: \t HOLD", "BATCH-6"},
        {"40", "ID: \t\t MN-MS-TX-1519_2", "SENT: \t\t ", "PIECES: \t 10,696", "TRAY(1'): \t ", "TRAY(2'): \t ", "CHECK#: \t ", "SAP: \t\t ", "STATUS: \t HOLD", "BATCH-6"},
        {"41", "ID: \t\t TX-1519_3", "SENT: \t\t ", "PIECES: \t 12,993", "TRAY(1'): \t ", "TRAY(2'): \t ", "CHECK#: \t ", "SAP: \t\t ", "STATUS: \t HOLD", "BATCH-6"},
        {"42", "ID: \t\t TX-1519_4", "SENT: \t\t ", "PIECES: \t 10,758", "TRAY(1'): \t ", "TRAY(2'): \t ", "CHECK#: \t ", "SAP: \t\t ", "STATUS: \t HOLD", "BATCH-6"},
        {"43", "ID: \t\t TX-1519_5", "SENT: \t\t ", "PIECES: \t 14,350", "TRAY(1'): \t ", "TRAY(2'): \t ", "CHECK#: \t ", "SAP: \t\t ", "STATUS: \t HOLD", "BATCH-6"},
        // BATCH-7
        {"44", "ID: \t\t GA-1519_1", "SENT: \t\t ", "PIECES: \t ", "TRAY(1'): \t ", "TRAY(2'): \t ", "CHECK#: \t ", "SAP: \t\t ", "STATUS: \t ", "BATCH-7"},
        {"45", "ID: \t\t GA-1519_2", "SENT: \t\t ", "PIECES: \t ", "TRAY(1'): \t ", "TRAY(2'): \t ", "CHECK#: \t ", "SAP: \t\t ", "STATUS: \t ", "BATCH-7"},
      };
      
    for (int i = 0; i < allAccounts.length; i++){    
      if (vname.equals("ALL")){
          String str = allAccounts[i][1];
          System.out.println("\t\t"+allAccounts[i][0] +"\t"+ str.substring(7, str.length()) );
      }	
      if (vname.equals(allAccounts[i][0])){
          for(int j = 1; j < allAccounts[i].length-1; j++){
            System.out.println("\t\t"+allAccounts[i][j] );
          }
          break;
      } 
      String mdate = allAccounts[i][2];
      String dateSearch = mdate.substring(9, mdate.length()); 
      if (vname.equals(dateSearch)){
        System.out.println("\t\t("+allAccounts[i][9]+")"+ B);
        for(int j = 1; j < allAccounts[i].length-1; j++){
          System.out.println("\t\t"+allAccounts[i][j] );
        }
        System.out.println(B);
      }
      String check = allAccounts[i][6];
      String checkSearch = check.substring(10, check.length()); 
      if (vname.equals("CHECK:"+checkSearch)){
        System.out.println("\t\t("+allAccounts[i][9]+")"+ B);
        for(int j = 1; j < allAccounts[i].length-1; j++){
          System.out.println("\t\t"+allAccounts[i][j] );
        }
        break;
      }
      String idmail = allAccounts[i][1];
      String searchID = idmail.substring(7, idmail.length()); 
      if (vname.equals("ID:"+searchID)){
        System.out.println("\t\t("+allAccounts[i][9]+")"+ B);
        for(int j = 1; j < allAccounts[i].length-1; j++){
          System.out.println("\t\t"+allAccounts[i][j] );
        }
        break;
      } 
      if(vname.length() > 6 && vname.length() >= 8){
        String stateRes = vname.substring(6, vname.length());
        String st = vname.substring(0, 5);
        if(st.equals("STATE")){
          boolean state = searchID.contains(stateRes); 
          if (state){ 
            System.out.println("\t\t("+allAccounts[i][9]+")");
            for(int j = 1; j < allAccounts[i].length-1; j++){
              System.out.println("\t\t"+allAccounts[i][j] );
            }
            System.out.println(B);  
          } 
        }
      }
      String status = allAccounts[i][8];
      String statusSearch = status.substring(10, status.length());
      String batch = allAccounts[i][9]; 
      char b1 = batch.charAt(0);  
      String blast = batch.substring(6, batch.length()); 
      if (vname.equals(b1+blast+":STATUS")){            
            System.out.println("\t\t"+searchID+" ("+statusSearch+")" );
      } 

      String sap = allAccounts[i][7];
      String sapSearch = sap.substring(8, sap.length());
      if (vname.equals(b1+blast+":SAP")){            
        System.out.println("\t\t"+searchID+" ("+sapSearch+")" );
      }  
      if (vname.equals(dateSearch+":SAP")){            
        System.out.println("\t\t"+searchID+" ("+sapSearch+")" );
      } 
      if (vname.equals(dateSearch+":STATUS")){            
        System.out.println("\t\t"+searchID+" ("+statusSearch+")" );
      } 
      
      if (vname.equals("HELP")){
        System.out.println("\t####### List of commands #######");
        System.out.println("\t'help'\t\t\t\tfor information");
        System.out.println("\t'all'\t\t\t\tfor whole mailer IDs list");
        System.out.println("\tnumber\t\t\t\tsearch by number \t\t ex:26");
        System.out.println("\t'check:'(+check number)\t\tsearch by check number \t\t ex:check:6145");
        System.out.println("\t'id:'(+ID number)\t\tsearch by ID \t\t\t ex:id:MO-NJ_1518_2");
        System.out.println("\t'state:'+state abbreviation\tsearch by State \t\t ex:state:tx");
        System.out.println("\tdate\t\t\t\tsearch by sent Date \t\t ex:09/04/2020");
        System.out.println("\t'b'(+Batch number)+':status'\tshipment status(batch#) \t ex:b2:status");
        System.out.println("\t'b'(+Batch number)+':sap'\tSAP status(batch#) \t\t ex:b2:sap");
        System.out.println("\tdate+':status'\t\t\tshipment status(date) \t\t ex:09/02/2020:status");
        System.out.println("\tdate+':sap'\t\t\tSAP status(batch#) \t\t ex:09/02/2020:sap");
        System.out.println("\texit\t\t\t\tfor quit from app"+B);
        break;
      }		
    }   
  }

  public void creditCrator(){
    System.out.print(B);
    for(int i =0; i<94; i++){System.out.print( "*" );}
    Mailers checkPasswordName = new Mailers();
    checkPasswordName.printCredit("Mailers APP", true);
    checkPasswordName.printCredit("version 1.0.0", false);
    checkPasswordName.printCredit("by Sasan Bazade", false);	 
    for(int i =0; i<94; i++){System.out.print( "*" );}
    System.out.print(B);
  }
  public void printCredit(String text, boolean brline){
    if(!brline){
      System.out.print("\t\t\t\t\t" + text + B );
    } else {
      System.out.print(B + "\t\t\t\t\t" + text + B );
    }    
  }

  public void checkPassword(){
    System.out.print("Password: ");
    for(int i=0; i< Pass.length(); i++){ 
      System.out.print("*");
    } 
    System.out.print(B);
  }

  public static void main(String[] args) throws IOException, InterruptedException{
    Mailers checkPasswordName = new Mailers();
    Scanner in = new Scanner(System.in);    
    checkPasswordName.checkPassword();
    checkPasswordName.creditCrator(); 
    	
    if(Pass.equals("sasan")){ 
      checkPasswordName.printCredit("#type help  for more information", false);  
      while ( Pass.equals("sasan") ) {
        try {
          String name = in.next(); 
          if(name.equals("exit")){ 
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor(); 
            System.exit(0);
          }
          checkPasswordName.vName(name.toUpperCase());
        } catch (Exception e) { 
            System.out.print("ERROR:  wrong input"); break;
          }
      }    
    } else { checkPasswordName.printCredit("WRONG PASSWORD!", true); }
      in.close();         
  }  
}