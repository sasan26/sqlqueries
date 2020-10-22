import java.util.Scanner;
import java.io.*;
import java.awt.event.*; 
import java.awt.*; 
import javax.swing.*;
import java.util.Arrays;
import java.util.Comparator;

public class Tax {
    // public static char[] passString = System.console().readPassword("%s", "Enter password: ");
    // public static String Pass = new String(passString);
    public static String Pass = "SASAN";

    public static String B = System.getProperty("line.separator");
    public void vName(String vname){
        String[][] allAccounts = { 
          {"AL", "Alabama", "10/06/2020", "", "$50.00 \t\t Late Fee", "NOPAYMENT"},
          {"AK", "Alaska", "--/--/----", "No login credential", "$0.00", "NOPAYMENT"},
          {"AZ", "Arizona", "10/02/2020", "", "$0.00", "NOPAYMENT"},
          {"AR", "Arkansas", "07/27/2020", "", "$0.00", "NOPAYMENT"},
          {"CO", "Colorado", "10/01/2020", "", "$0.00", "NOPAYMENT"},
          {"CT", "Connecticutt", "10/06/2020", "", "$0.00", "NOPAYMENT"},
          {"DC", "District of Columbia", "06/04/2020", "", "$0.00", "NOPAYMENT"},
          {"FL", "Florida", "08/26/2020", "", "$100.00 \t Late Fee", "Paid by mail 09/14/2020"},
          {"GA", "Georgia", "10/02/2020", "", "$110.00", "Paid by mail 09/14/2020"},
          {"HI", "Hawaii", "07/27/2020", "", "$0.00", "NOPAYMENT"},
          {"IL", "Illinois", "10/06/2020", "", "$0.00", "NOPAYMENT"},
          {"ID", "Idaho", "10/12/2020", "", "$1,162.56", "NOPAYMENT"},
          {"IA", "Iowa", "07/24/2020", "", "$0.00", "NOPAYMENT"},
          {"KS", "Kansas", "10/07/2020", "", "$30.00 \t\t Late Fee", "NOPAYMENT"},
          {"KY", "Kentucky", "10/06/2020", "", "$0.00", "Paid by mail 09/14/2020"},
          {"LA", "Louisiana", "08/26/2020", "", "$20.00 \t\t Late Fee", "NOPAYMENT"},
          {"ME", "Maine", "10/01/2020", "", "$0.00", "NOPAYMENT"},
          {"MD", "Maryland", "--/--/----", "No tax data found", "$0.00", "NOPAYMENT"},
          {"MA", "Massachusetts", "07/28/2020", "", "$0.00", "NOPAYMENT"},
          {"MI", "Michigan", "10/01/2020", "", "$0.00", "NOPAYMENT"},
          {"MN", "Minnesota", "10/01/2020", "", "$0.00", "NOPAYMENT"},
          {"MS", "Mississippi", "10/01/2020", "", "$0.00", "NOPAYMENT"},
          {"MO", "Missouri", "06/04/2020", "", "$0.00", "NOPAYMENT"},
          {"NE", "Nebraska", "10/06/2020", "", "$0.00", "NOPAYMENT"},
          {"NV", "Nevada", "06/04/2020", "", "$756.92 \t Business Tax", "NOPAYMENT"},
          {"NH", "New Hampshire", "06/04/2020", "", "$0.00", "NOPAYMENT"},
          {"NJ", "New Jersey", "10/06/2020", "", "$0.00", "NOPAYMENT"},
          {"NM", "New Mexico", "10/01/2020", "", "$0.00", "NOPAYMENT"},
          {"NY", "New York", "10/01/2020", "", "$0.00", "NOPAYMENT"},
          {"NC", "North Carolina", "10/02/2020", "", "$0.00", "NOPAYMENT"},
          {"ND", "North Dakota", "--/--/----", "Wrong login credential", "$0.00", "NOPAYMENT"},
          {"OH", "Ohio", "10/02/2020", "", "$0.00", "NOPAYMENT"},
          {"OK", "Oklahoma", "--/--/----", "Wrong login credential", "$0.00", "NOPAYMENT"},
          {"OR", "Oregon", "06/11/2020", "", "$0.00", "NOPAYMENT"},
          {"PA", "Pennsylvania", "--/--/----", "No tax data found", "$0.00", "NOPAYMENT"},
          {"RI", "Rhode Island", "--/--/----", "No tax data found", "$0.00", "NOPAYMENT"},
          {"SC", "South Carolina", "08/26/2020", "", "$0.00", "NOPAYMENT"},
          {"SD", "South Dakota", "10/01/2020", "", "$10.00 \t\t Late Fee", "NOPAYMENT"},
          {"TN", "Tennessee", "09/17/2020", "", "$0.00", "NOPAYMENT"},
          {"TX", "Texas", "10/05/2020", "", "$0.00", "NOPAYMENT"},
          {"UT", "Utah", "--/--/----", "Wrong login credential", "$0.00", "NOPAYMENT"},
          {"VT", "Vermont", "07/30/2020", "", "$0.00", "NOPAYMENT"},
          {"VA", "Virginia", "10/01/2020", "", "$0.00", "NOPAYMENT"},
          {"WA", "Washington", "07/30/2020", "", "$0.00", "NOPAYMENT"},
          {"WV", "West Virginia", "06/04/2020", "", "$60.00 \t\t Business Registration", "NOPAYMENT"},
          {"WI", "Wisconsin", "07/30/2020", "", "$0.00", "NOPAYMENT"}        
        };

        Arrays.sort(allAccounts, new Comparator<String[]>() {
            @Override
            public int compare(final String[] entry1, final String[] entry2) {
                final String time1 = entry1[2];
                final String time2 = entry2[2];
                return time1.compareTo(time2);
            }
        });


        String[][] allMails = { 
            {"9", "OR", "06/18/2020", "00689", "Annual Report", "$100.00", "Waiting"},
            {"10", "NV", "08/07/2020", "20003874010", "Monthly Statement", "$756.92", "Already gave it to Hien"},
            {"16", "KS", "09/09/2020", "2020-ST-005584", "Tax Statement", "$902.00", "Already gave it to Hien | no sae account"},
            {"18", "OR", "08/10/2020", "01903", "Annual report", "$60.64", "waiting"},
            {"25", "FL", "09/15/2020", "DR-307004", "Notice of Final Assessment", "$1415.68", "Flied - waiting for update"},
            {"26", "OH", "09/20/2020", "0027 029762", "Collection", "$2,641.52", "Flied - waiting for update"},
            {"27", "SD", "10/06/2020", "12894", "Payment", "$10.00", "PAID - waiting for update"}
        };
      
        for (int i = 0; i < allAccounts.length; i++){    
          String num = allAccounts[i][0];
                String state = allAccounts[i][1];
                String lastDate = allAccounts[i][2];
                String error = allAccounts[i][3];
                String fee = allAccounts[i][4];
                String payment = allAccounts[i][5];
            if (vname.equals("STATE>ALL")){                
                if(state.length() >= 20){
                    System.out.println("\t\t"+num +"\t"+ state +"\t"+ lastDate +"\t"+ error);
                }
                else if(state.length() >= 8){
                    System.out.println("\t\t"+num +"\t"+ state +"\t\t"+ lastDate +"\t"+ error);
                } else {
                    System.out.println("\t\t"+num +"\t"+ state +"\t\t\t"+ lastDate +"\t"+ error);
                }
            }	
            if (vname.equals("STATE>"+allAccounts[i][0])){
                for(int j = 1; j < allAccounts[i].length; j++){
                    System.out.print("\t\t"+allAccounts[i][j]);
                }
                System.out.print(B);
                break;
            } 
            if (vname.equals("STATE>FEE")){
              if(fee != "$0.00"){
                if(state.length() >= 20){
                    System.out.println("\t\t"+num +"\t"+ state +"\t"+ fee);
                }
                else if(state.length() >= 8){
                    System.out.println("\t\t"+num +"\t"+ state +"\t\t"+ fee);
                } else {
                    System.out.println("\t\t"+num +"\t"+ state +"\t\t\t"+ fee);
                }
              }
            }
            if (vname.equals("STATE>ERROR")){
              if(error != ""){
                if(state.length() >= 20){
                    System.out.println("\t\t"+num +"\t"+ state +"\t"+ error);
                }
                else if(state.length() >= 8){
                    System.out.println("\t\t"+num +"\t"+ state +"\t\t"+ error);
                } else {
                    System.out.println("\t\t"+num +"\t"+ state +"\t\t\t"+ error);
                }
              }
            }	
            if (vname.equals("STATE>PAY")){
              if(payment != "NOPAYMENT"){
                if(state.length() >= 13){
                  System.out.println("\t\t"+num +"\t"+ state +"\t"+ payment); 
                } else if(state.length() >= 8){
                  System.out.println("\t\t"+num +"\t"+ state +"\t\t"+ payment); 
                }
                else {
                  System.out.println("\t\t"+num +"\t"+ state +"\t\t\t"+ payment); 
                }                                  
              }
            }		

            if (vname.equals("MAIL>ALL")){
                for(int j = 0; j < allMails.length; j++){
                    System.out.println("\t\t"+allMails[j][0] + "\t"+allMails[j][1] + "\t"+allMails[j][2] + "\t"+allMails[j][5]);            
                }        
                break;
            }
        
            if (vname.equals("HELP")){
                System.out.println("\t### List of commands ###");
                System.out.println("\t'help'\t\t\t\tfor information");
                System.out.println("\t'state>all'\t\t\tfor whole satates & filing date list");
                System.out.println("\t'state>'(+state)\t\tfor selected satate & filing date \t ex:state>ID");
                System.out.println("\t'state>fee'\t\t\tfor whole satates & filing date list");
                System.out.println("\t'state>pay'\t\t\tfor whole payment list");
                System.out.println("\t'state>error'\t\t\tfor whole satates & filing date list");
                System.out.println("\t'mail>all'\t\t\tfor whole active mails list");
                System.out.println("\t'mail>(+state)'\t\t\tfor all <selected state> active mails \t ex:mail>ID");
                System.out.println("\t'mail>(+number)'\t\tfor more details about the mail\t\t ex:mail>13");
                System.out.println("\texit\t\t\t\tfor quit from app"+B);
                break;
            }		
        }  
        
        for(int j = 0; j < allMails.length; j++){
            if (vname.equals("MAIL>"+allMails[j][0])){  
                for(int s = 1; s < allMails[j].length; s++){
                    System.out.println("\t\t"+allMails[j][s]);
                }
            }
            if (vname.equals("MAIL>"+allMails[j][1])){ 
                System.out.println("\t\t"+allMails[j][0] + "\t"+allMails[j][1] + "\t"+allMails[j][2] + "\t"+allMails[j][5]);
            }
        }        
    }

  public void creditCrator(){
    System.out.print(B);
    for(int i =0; i<34; i++){System.out.print( "_" );}
    Tax checkPasswordName = new Tax();
    checkPasswordName.printCredit("Tax APP", true);
    checkPasswordName.printCredit("version 1.0.0", false);
    checkPasswordName.printCredit("by Sasan Bazade", false);	 
    for(int i =0; i<34; i++){System.out.print( "_" );}
    System.out.print(B);
  }
  public void printCredit(String text, boolean brline){
    if(!brline){
      System.out.print(" " + text + B );
    } else {
      System.out.print(B+B + " " + text + B );
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
    Tax checkPasswordName = new Tax();
    Scanner in = new Scanner(System.in);    
    checkPasswordName.checkPassword();
    checkPasswordName.creditCrator(); 
    	
    if(Pass.equals("SASAN")){ 
      checkPasswordName.printCredit("#type help  for more information", false);  
      while ( Pass.equals("SASAN") ) {
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