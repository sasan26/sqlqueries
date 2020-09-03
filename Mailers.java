import java.util.Scanner;
import java.io.Console;
import java.awt.event.*; 
import java.awt.*; 
import javax.swing.*;

public class Mailers {
  public static char[] passString = System.console().readPassword("%s", "Enter password: ");
  public static String Pass = new String(passString);

  public static String B = System.getProperty("line.separator");
  public void vName(String vname){
      String[][] allAccounts = { 
        // Batch 1
        {"1", "ID: \t\t TX-2016-2018-1", "SENT: \t\t 05/08/2020", "PIECES: \t 10,148", "TRAY(1'): \t ", "TRAY(2'): \t ", "CHECK#: \t ", "SAP: \t\t YES", "STATUS: \t Shipped"},
        {"2", "ID: \t\t TX-2016-2018-2", "SENT: \t\t 05/12/2020", "PIECES: \t 9,904", "TRAY(1'): \t 9", "TRAY(2'): \t 10", "CHECK#: \t 6145", "SAP: \t\t YES", "STATUS: \t Shipped"},
        {"3", "ID: \t\t TX-2016-2018-3", "SENT: \t\t 05/14/2020", "PIECES: \t 9,743", "TRAY(1'): \t 12", "TRAY(2'): \t 8", "CHECK#: \t 6146", "SAP: \t\t YES", "STATUS: \t Shipped"},
        {"4", "ID: \t\t TX-OH-2016-2018-4", "SENT: \t\t 05/20/2020", "PIECES: \t 10,022", "TRAY(1'): \t 4", "TRAY(2'): \t 12", "CHECK#: \t 6147", "SAP: \t\t YES", "STATUS: \t Shipped"},
        {"5", "ID: \t\t OH-CO-FL-2016-2018-5", "SENT: \t\t 05/22/2020", "PIECES: \t 9,469", "TRAY(1'): \t 9", "TRAY(2'): \t 9", "CHECK#: \t 6165", "SAP: \t\t YES", "STATUS: \t Shipped"},
        {"6", "ID: \t\t FL-2016-2018-6", "SENT: \t\t 05/27/2020", "PIECES: \t 9,584", "TRAY(1'): \t 4", "TRAY(2'): \t 11", "CHECK#: \t 6166", "SAP: \t\t YES", "STATUS: \t Shipped"},
        {"7", "ID: \t\t FL-IL-2016-2018-7", "SENT: \t\t 06/03/2020", "PIECES: \t 9,560", "TRAY(1'): \t 6", "TRAY(2'): \t 12", "CHECK#: \t 6169", "SAP: \t\t YES", "STATUS: \t Shipped"},
        {"8", "ID: \t\t IL-2016-2018-8", "SENT: \t\t 06/05/2020", "PIECES: \t 9,127", "TRAY(1'): \t 7", "TRAY(2'): \t 9", "CHECK#: \t 6184", "SAP: \t\t YES", "STATUS: \t Shipped"},
        // Batch 2
        {"9", "ID: \t\t GA-2016-2018-1", "SENT: \t\t 6/10/2020", "PIECES: \t 12,413", "TRAY(1'): \t 16", "TRAY(2'): \t 11", "CHECK#: \t 6186", "SAP: \t\t YES", "STATUS: \t Shipped"},
        {"10", "ID: \t\t GA-2016-2018-2", "SENT: \t\t 6/12/2020", "PIECES: \t 12,398", "TRAY(1'): \t 11", "TRAY(2'): \t 12", "CHECK#: \t 6187", "SAP: \t\t YES", "STATUS: \t Shipped"},
        {"11", "ID: \t\t GA-2016-2018-3", "SENT: \t\t 6/17/2020", "PIECES: \t 11,881", "TRAY(1'): \t 10", "TRAY(2'): \t 12", "CHECK#: \t 6203", "SAP: \t\t YES", "STATUS: \t Shipped"},
        {"12", "ID: \t\t GA-2016-2018-6", "SENT: \t\t 6/24/2020", "PIECES: \t 11,684", "TRAY(1'): \t 9", "TRAY(2'): \t 12", "CHECK#: \t 6211", "SAP: \t\t YES", "STATUS: \t Shipped"},
        {"13", "ID: \t\t GA-OR-2016-2018-7", "SENT: \t\t 6/26/2020", "PIECES: \t 9,425", "TRAY(1'): \t 6", "TRAY(2'): \t 13", "CHECK#: \t 6212", "SAP: \t\t YES", "STATUS: \t Shipped"},
        {"14", "ID: \t\t OR-VA-2016-2018-10", "SENT: \t\t 7/1/2020", "PIECES: \t 10,588", "TRAY(1'): \t 4", "TRAY(2'): \t 13", "CHECK#: \t 6213", "SAP: \t\t YES", "STATUS: \t Shipped"},
        // Batch 3
        {"15", "ID: \t\t NC-2015-2018-1", "SENT: \t\t 7/3/2020", "PIECES: \t 12,462", "TRAY(1'): \t 8", "TRAY(2'): \t 13", "CHECK#: \t 6243", "SAP: \t\t YES", "STATUS: \t Shipped"},
        {"16", "ID: \t\t NC-MD-2015-2018-2", "SENT: \t\t 7/8/2020", "PIECES: \t 12,281", "TRAY(1'): \t 12", "TRAY(2'): \t 12", "CHECK#: \t 6245", "SAP: \t\t YES", "STATUS: \t Shipped"},
        {"17", "ID: \t\t MD-CO-2015-2018-3", "SENT: \t\t 7/15/2020", "PIECES: \t 12,038", "TRAY(1'): \t 9", "TRAY(2'): \t 13", "CHECK#: \t 6246", "SAP: \t\t YES", "STATUS: \t Shipped"},
        {"18", "ID: \t\t CO-CA-2015-2018-4", "SENT: \t\t 7/17/2020", "PIECES: \t 12,325", "TRAY(1'): \t 11", "TRAY(2'): \t 12", "CHECK#: \t 6267", "SAP: \t\t YES", "STATUS: \t Shipped"},
        {"19", "ID: \t\t CA-2015-2018-5", "SENT: \t\t 7/22/2020", "PIECES: \t 12,406", "TRAY(1'): \t 11", "TRAY(2'): \t 12", "CHECK#: \t 6272", "SAP: \t\t YES", "STATUS: \t Shipped"},
        {"20", "ID: \t\t CA-2015-2018-6", "SENT: \t\t 8/12/2020", "PIECES: \t 12,518", "TRAY(1'): \t 9", "TRAY(2'): \t 14", "CHECK#: \t 6301", "SAP: \t\t YES", "STATUS: \t Shipped"},
        {"21", "ID: \t\t CA-2015-2018-7", "SENT: \t\t 8/19/2020", "PIECES: \t 12,655", "TRAY(1'): \t 9", "TRAY(2'): \t 14", "CHECK#: \t 6324", "SAP: \t\t YES", "STATUS: \t Shipped"},
        {"22", "ID: \t\t CA-2015-2018-8", "SENT: \t\t 8/28/2020", "PIECES: \t 12,516", "TRAY(1'): \t 9", "TRAY(2'): \t 14", "CHECK#: \t 6332", "SAP: \t\t YES", "STATUS: \t Shipped"},
        {"23", "ID: \t\t CA-2015-2018-9", "SENT: \t\t 9/2/2020", "PIECES: \t 12,360", "TRAY(1'): \t 9", "TRAY(2'): \t 14", "CHECK#: \t 6368", "SAP: \t\t ", "STATUS: \t printed"},
        {"24", "ID: \t\t CA-2015-2018-10", "SENT: \t\t 9/16/2020", "PIECES: \t 12,317", "TRAY(1'): \t 11", "TRAY(2'): \t 12", "CHECK#: \t ", "SAP: \t\t ", "STATUS: \t printing..."},
        {"25", "ID: \t\t CA-2015-2018-11", "SENT: \t\t ", "PIECES: \t 12,528", "TRAY(1'): \t 7", "TRAY(2'): \t 15", "CHECK#: \t ", "SAP: \t\t ", "STATUS: \t "},
        {"26", "ID: \t\t CA-2015-2018-12", "SENT: \t\t ", "PIECES: \t 12,460", "TRAY(1'): \t 10", "TRAY(2'): \t 13", "CHECK#: \t ", "SAP: \t\t ", "STATUS: \t "},
        {"27", "ID: \t\t CA-2015-2018-13", "SENT: \t\t ", "PIECES: \t 12,598", "TRAY(1'): \t 11", "TRAY(2'): \t 12", "CHECK#: \t ", "SAP: \t\t ", "STATUS: \t "},
        {"28", "ID: \t\t CA-2015-2018-14", "SENT: \t\t ", "PIECES: \t 12,536", "TRAY(1'): \t 9", "TRAY(2'): \t 14", "CHECK#: \t ", "SAP: \t\t ", "STATUS: \t "},
        // Batch 4
        {"29", "ID: \t\t MN-MO_1518_1", "SENT: \t\t 7/24/2020", "PIECES: \t 9,800", "TRAY(1'): \t 4", "TRAY(2'): \t 11", "CHECK#: \t 6274", "SAP: \t\t YES", "STATUS: \t Shipped"},
        {"30", "ID: \t\t MO-NJ_1518_2", "SENT: \t\t 7/29/2020", "PIECES: \t 9,771", "TRAY(1'): \t 7", "TRAY(2'): \t 10", "CHECK#: \t 6275", "SAP: \t\t YES", "STATUS: \t Shipped"},
        {"31", "ID: \t\t NJ_1518_3", "SENT: \t\t 8/5/2020", "PIECES: \t 9,853", "TRAY(1'): \t 4", "TRAY(2'): \t 11", "CHECK#: \t 6299", "SAP: \t\t YES", "STATUS: \t Shipped"},
        {"32", "ID: \t\t NJ-NY_1518_4", "SENT: \t\t 8/7/2020", "PIECES: \t 9,593", "TRAY(1'): \t 10", "TRAY(2'): \t 9", "CHECK#: \t 6300", "SAP: \t\t YES", "STATUS: \t Shipped"},
        {"33", "ID: \t\t NY_1518_5", "SENT: \t\t 8/13/2020", "PIECES: \t 9,438", "TRAY(1'): \t 8", "TRAY(2'): \t 10", "CHECK#: \t 6303", "SAP: \t\t YES", "STATUS: \t Shipped"},
        {"34", "ID: \t\t NY_1518_6", "SENT: \t\t 8/26/2020", "PIECES: \t 10,495", "TRAY(1'): \t 6", "TRAY(2'): \t 12", "CHECK#: \t 6330", "SAP: \t\t YES", "STATUS: \t Shipped"},
        // Batch 5
        {"35", "ID: \t\t MI-1518_1", "SENT: \t\t 9/2/2020", "PIECES: \t 8,770", "TRAY(1'): \t 5", "TRAY(2'): \t 9", "CHECK#: \t 6367", "SAP: \t\t YES", "STATUS: \t Shipped"},
        {"36", "ID: \t\t MI-NE-NM-NV-1518_2", "SENT: \t\t 9/4/2020", "PIECES: \t 8,742", "TRAY(1'): \t 9", "TRAY(2'): \t 9", "CHECK#: \t 6369", "SAP: \t\t ", "STATUS: \t Printed"},
        {"37", "ID: \t\t NV-SC-1518_3", "SENT: \t\t 8/14/2020", "PIECES: \t 8,671", "TRAY(1'): \t 15", "TRAY(2'): \t 7", "CHECK#: \t 6322", "SAP: \t\t YES", "STATUS: \t Shipped"},
        {"38", "ID: \t\t SC-UT-1518_4", "SENT: \t\t 9/11/2020", "PIECES: \t 8,666", "TRAY(1'): \t 6", "TRAY(2'): \t 8", "CHECK#: \t ", "SAP: \t\t YES", "STATUS: \t Printed"},
        // Batch 6
        {"39", "ID: \t\t MA-MN-1519_1", "SENT: \t\t ", "PIECES: \t 12,473", "TRAY(1'): \t ", "TRAY(2'): \t ", "CHECK#: \t ", "SAP: \t\t ", "STATUS: \t HOLD"},
        {"40", "ID: \t\t MN-MS-TX-1519_2", "SENT: \t\t ", "PIECES: \t 10,696", "TRAY(1'): \t ", "TRAY(2'): \t ", "CHECK#: \t ", "SAP: \t\t ", "STATUS: \t HOLD"},
        {"41", "ID: \t\t TX-1519_3", "SENT: \t\t ", "PIECES: \t 12,993", "TRAY(1'): \t ", "TRAY(2'): \t ", "CHECK#: \t ", "SAP: \t\t ", "STATUS: \t HOLD"},
        {"42", "ID: \t\t TX-1519_4", "SENT: \t\t ", "PIECES: \t 10,758", "TRAY(1'): \t ", "TRAY(2'): \t ", "CHECK#: \t ", "SAP: \t\t ", "STATUS: \t HOLD"},
        {"43", "ID: \t\t TX-1519_5", "SENT: \t\t ", "PIECES: \t 14,350", "TRAY(1'): \t ", "TRAY(2'): \t ", "CHECK#: \t ", "SAP: \t\t ", "STATUS: \t HOLD"}
      };
      
    for (int i = 0; i < allAccounts.length; i++){    
      if (vname.equals("ALL")){
          String str = allAccounts[i][1];
        System.out.println("\t\t"+allAccounts[i][0] +"\t"+ str.substring(7, str.length()));
      }	
      if (vname.equals(allAccounts[i][0])){
          for(int j = 1; j < allAccounts[i].length; j++){
            System.out.println("\t\t"+allAccounts[i][j] );
          }
          break;
      } 
      String check = allAccounts[i][6];
      String checkSearch = check.substring(10, check.length()); 
      if (vname.equals("CHECK:"+checkSearch)){
        for(int j = 1; j < allAccounts[i].length; j++){
          System.out.println("\t\t"+allAccounts[i][j] );
        }
        break;
      }
      String idmail = allAccounts[i][1];
      String searchID = idmail.substring(7, idmail.length()); 
      if (vname.equals("ID:"+searchID)){
        for(int j = 1; j < allAccounts[i].length; j++){
          System.out.println("\t\t"+allAccounts[i][j] );
        }
        break;
      } 
      boolean state = searchID.contains(vname);
      if (state){
        for(int j = 1; j < allAccounts[i].length; j++){
          System.out.println("\t\t"+allAccounts[i][j] );
        }
      System.out.println(B);  
      } 
      if (vname.equals("HELP")){
        System.out.println("\t####### List of commands #######");
        System.out.println("\thelp<Enter>\t\t\tfor information");
        System.out.println("\tall<Enter>\t\t\tfor whole mailer IDs list");
        System.out.println("\t'number'<Enter>\t\t\tsearch by number \t \\\\example: 26");
        System.out.println("\t'check:'(+check number)<Enter>\tsearch by check number \t \\\\example: check:6145");
        System.out.println("\t'id:'(+ID number)<Enter>\tsearch by ID \t\t \\\\example: id:MO-NJ_1518_2");
        System.out.println("\t'state abbreviation'<Enter>\tsearch by State \t \\\\example: tx");
        System.out.println("\texit<Enter>\t\t\tfor quit from app"+B);
        break;
      }		
      if (vname.equals("EXIT")){ System.exit(0);} 
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

  public static void main(String[] args) {
    Mailers checkPasswordName = new Mailers();
    Scanner in = new Scanner(System.in);    
    checkPasswordName.checkPassword();
    checkPasswordName.creditCrator(); 
    	
    if(Pass.equals("sasan")){ 
      checkPasswordName.printCredit("#type help<Enter>  for more information", false);  
      while ( Pass.equals("sasan") ) {
        try {
          String name = in.next(); 
          checkPasswordName.vName(name.toUpperCase());
        } catch (Exception e) { 
            System.out.print("ERROR:  wrong input"); break;
          }
      }    
    } else { checkPasswordName.printCredit("WRONG PASSWORD!", true); }
      in.close();         
  }  
}