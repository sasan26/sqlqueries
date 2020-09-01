import java.util.Scanner;
import java.io.Console;
import javax.swing.JPasswordField;

public class Password {
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
       String B= System.getProperty("line.separator");
      for (int i = 0; i < allAccounts.length; i++){
          
        if (vname.equals("PASSWORDS")){
          System.out.println("          "+allAccounts[i][0] + " | Credential: " + allAccounts[i][1]);
        }	
        if (vname.equals("ALL")){
          System.out.println("          "+allAccounts[i][0]);

        }	
        if (vname.equals(allAccounts[i][0])){
            System.out.println("          "+allAccounts[i][0] + " | Credential: " + allAccounts[i][1] );
            //System.out.println(allAccounts[i][0] + " | Credential: " + allAccounts[i][1]);
            break;
        } 
          
        if (vname.equals("HELP")){

          System.out.println("          ####### List of commands #######");
          System.out.println("          type:   help<Enter>   	 	  for information");
          System.out.println("          type:   all<Enter>   		  	  for whole list");
          //System.out.println("          type:   passwords<Enter>       for whole list including passwords");
          System.out.println("          type:   'account name'<Enter>     	  for current account credential");
          System.out.println("          type:   exit<Enter>   	 	  for quit from app"+B);
          break;
        }		
        if (vname.equals("EXIT")){ System.exit(0);} 
    } 
   
  }
  public static void main(String[] args) {
	
    String B = System.getProperty("line.separator");
    Password checkPasswordName = new Password();
    //PassReader passread = new PassReader();
    Scanner in = new Scanner(System.in);
    System.out.print( B + "**********************************************************************************" + B);	
    System.out.print("		   		PASSWORDS APP" + B );
    System.out.print("		   		version 1.0.0" + B );
    System.out.print("		   		by Sasan Bazade" + B + "**********************************************************************************" + B);	
	//passread.readPassword();
    char[] passString = System.console().readPassword("%s", "Enter password: ");

    String Pass = new String(passString );
 	
  if(Pass.equals("sasan")){   
    System.out.print("#type help<Enter>  for more information" + B);
    while ( Pass.equals("sasan") ) {
      try {
        String name = in.next(); 
        checkPasswordName.vName(name.toUpperCase());
      } catch (Exception e) { 
      System.out.print("ERROR:  wrong input"); break;}
      }    
    } else { System.out.println(B + "	WRONG PASSWORD!");}
    in.close();         
  }
}