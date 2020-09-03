import java.util.Scanner;
import java.io.Console;
import java.awt.event.*; 
import java.awt.*; 
import javax.swing.*;

public class Password {
  //public static String inputValue = JOptionPane.showInputDialog("Enter Password: ");
  //public static char[] passString = System.console().readPassword("%s", "Enter password: ");
  //public static String Pass = new String(inputValue);

  public static String B = System.getProperty("line.separator");
  public void vName(String vname){
      String[][] allAccounts = { 
        {"REMOTE", "Sasan / Skippy92831!"}, 
        {"SQL", "Sasan / SasanSQL92831!"}, 
        {"FILEZILLA", "10.10.0.33:21 / vftpadmin / 862457Ftp!"} ,
        {"3CX","Admin / !UTk3n6iftbcggenirltrtddgljjdggne"},
        {"EMAILADMIN","sasan@discountedwheelwarehouse.net / Skippy92831!"},
        {"TSHEET","itdepartment2@dwwclub.com / Sasan6081!"},
        {"GOOGLE","itdepartment2@dwwclub.com / skippy92831"}
      };

      for (int i = 0; i < allAccounts.length; i++){
        
      if (vname.equals("PASSWORDS")){
        System.out.println("\t"+allAccounts[i][0] + " | Credential: " + allAccounts[i][1]);
      }	
      if (vname.equals("ALL")){
        System.out.println("\t"+allAccounts[i][0]);
      }	
      if (vname.equals(allAccounts[i][0])){
          System.out.println("\t"+allAccounts[i][0] + " | Credential: " + allAccounts[i][1] );
          break;
      } 
        
      if (vname.equals("HELP")){
        System.out.println("\t####### List of commands #######");
        System.out.println("\ttype:   help<Enter>\t\t\tfor information");
        System.out.println("\ttype:   all<Enter>\t\t\tfor whole list");
        System.out.println("\ttype:   'account name'<Enter>\t\tfor current account credential");
        System.out.println("\ttype:   exit<Enter>\t\t\tfor quit from app"+B);
        break;
      }		
      if (vname.equals("EXIT")){ System.exit(0);} 
    }   
  }

  public void creditCrator(){
    System.out.print(B);
    for(int i =0; i<82; i++){System.out.print( "*" );}
    Password checkPasswordName = new Password();
    checkPasswordName.printCredit("PASSWORDS APP", true);
    checkPasswordName.printCredit("version 1.0.0", false);
    checkPasswordName.printCredit("by Sasan Bazade", false);	
    for(int i =0; i<82; i++){System.out.print( "*" );}
    System.out.print(B);
  }
  public void printCredit(String text, boolean brline){
    if(!brline){
      System.out.print("\t\t\t\t" + text + B );
    } else {
      System.out.print(B + "\t\t\t\t" + text + B );
    }    
  }

  public static String Pass;
  public void checkPassword(){
    System.out.print("Password: ");
    for(int i=0; i< Pass.length(); i++){ 
      System.out.print("*");
    } 
    System.out.print(B);
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
        Pass = new String(password);
        //return Pass;
  }

  public static void main(String[] args) {
   
    Password checkPasswordName = new Password();
    Scanner in = new Scanner(System.in);    
    checkPasswordName.creditCrator(); 
    checkPasswordName.passpopup();
    checkPasswordName.checkPassword();	
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