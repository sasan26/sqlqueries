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
            {"AL", "Alabama", "09/08/2020", "", "$350.00 \t Late Fee", "HASMAIL"},
            {"AK", "Alaska", "--/--/----", "No login credential", "$0.00", "NOMAIL"},
            {"AZ", "Arizona", "07/27/2020", "", "$0.00", "NOMAIL"},
            {"AR", "Arkansas", "07/27/2020", "", "$0.00", "NOMAIL"},
            {"CO", "Colorado", "08/26/2020", "", "$0.00", "NOMAIL"},
            {"CT", "Connecticutt", "07/27/2020", "", "$0.00", "NOMAIL"},
            {"DC", "District of Columbia", "06/04/2020", "", "$0.00", "NOMAIL"},
            {"FL", "Florida", "08/26/2020", "", "$100.00 \t Late Fee", "NOMAIL"},
            {"GA", "Georgia", "08/26/2020", "", "$0.00", "NOMAIL"},
            {"HI", "Hawaii", "07/27/2020", "", "$0.00", "NOMAIL"},
            {"IL", "Illinois", "07/27/2020", "", "$0.00", "NOMAIL"},
            {"ID", "Idaho", "--/--/----", "Wrong login credential", "$0.00", "HASMAIL"},
            {"IA", "Iowa", "07/24/2020", "", "$0.00", "NOMAIL"},
            {"KS", "Kansas", "06/08/2020", "", "$30.00 \t\t Late Fee", "HASMAIL"},
            {"KY", "Kentucky", "09/10/2020", "", "$0.00", "NOMAIL"},
            {"LA", "Louisiana", "08/26/2020", "", "$20.00 \t\t Late Fee", "NOMAIL"},
            {"ME", "Maine", "06/04/2020", "", "$0.00", "NOMAIL"},
            {"MD", "Maryland", "01/04/2020", "No tax data found", "$0.00", "NOMAIL"},
            {"MA", "Massachusetts", "07/28/2020", "", "$0.00", "NOMAIL"},
            {"MI", "Michigan", "07/28/2020", "", "$0.00", "NOMAIL"},
            {"MN", "Minnesota", "07/09/2020", "", "$0.00", "NOMAIL"},
            {"MS", "Mississippi", "07/28/2020", "", "$0.00", "NOMAIL"},
            {"MO", "Missouri", "06/04/2020", "", "$0.00", "NOMAIL"},
            {"NE", "Nebraska", "07/28/2020", "", "$0.00", "NOMAIL"},
            {"NV", "Nevada", "06/04/2020", "", "$756.92 \t Business Tax", "HASMAIL"},
            {"NH", "New Hampshire", "06/04/2020", "", "$0.00", "NOMAIL"},
            {"NJ", "New Jersey", "01/17/2020", "No tax data found", "$0.00", "NOMAIL"},
            {"NM", "New Mexico", "07/30/2020", "", "$5.00 \t\t Late Fee", "NOMAIL"},
            {"NY", "New York", "07/30/2020", "", "$100.00 \t Late Fee", "NOMAIL"},
            {"NC", "North Carolina", "07/30/2020", "", "$0.00", "NOMAIL"},
            {"ND", "North Dakota", "--/--/----", "Wrong login credential", "$0.00", "NOMAIL"},
            {"OH", "Ohio", "09/01/2020", "", "$0.00", "NOMAIL"},
            {"OK", "Oklahoma", "--/--/----", "Wrong login credential", "$0.00", "NOMAIL"},
            {"OR", "Oregon", "06/11/2020", "", "$0.00", "HASMAIL"},
            {"PA", "Pennsylvania", "01/17/2020", "No tax data found", "$0.00", "NOMAIL"},
            {"RI", "Rhode Island", "--/--/----", "No tax data found", "$0.00", "NOMAIL"},
            {"SC", "South Carolina", "08/26/2020", "", "$0.00", "NOMAIL"},
            {"SD", "South Dakota", "07/16/2020", "", "$80.00 \t\t Late Fee", "NOMAIL"},
            {"TN", "Tennessee", "07/21/2020", "", "$120.00 \t Late Fee", "NOMAIL"},
            {"TX", "Texas", "07/30/2020", "", "$51.00 \t\t Late Fee", "HASMAIL"},
            {"UT", "Utah", "--/--/----", "Wrong login credential", "$0.00", "NOMAIL"},
            {"VT", "Vermont", "07/30/2020", "", "$0.00", "NOMAIL"},
            {"VA", "Virginia", "06/04/2020", "", "$144.08 \t Late Fee", "HASMAIL"},
            {"WA", "Washington", "07/30/2020", "", "$0.00", "NOMAIL"},
            {"WV", "West Virginia", "06/04/2020", "", "$60.00 \t\t Business Registration", "NOMAIL"},
            {"WI", "Wisconsin", "07/30/2020", "", "$0.00", "NOMAIL"}        
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
            {"1", "AL", "08/07/2020", "L1859294624", "Late Fee Notice", "$50.00", "Wating for Hien"},
            {"2", "AL", "08/07/2020", "L0248681888", "Late Fee Notice", "$50.00", "Wating for Hien"},
            {"3", "AL", "08/07/2020", "L1993512352", "Late Fee Notice", "$50.00", "Wating for Hien"},
            {"4", "AL", "08/07/2020", "L1322423712", "Late Fee Notice", "$50.00", "Wating for Hien"},
            {"5", "AL", "08/07/2020", "L0785552800", "Late Fee Notice", "$50.00", "Wating for Hien"},
            {"6", "AL", "08/07/2020", "L0919770528", "Late Fee Notice", "$50.00", "Wating for Hien"},
            {"7", "VA", "08/17/2020", "Q0390999", "Late Fee Notice - Sale", "$71.77", "Wating for Hien"},
            {"8", "VA", "08/17/2020", "Q0390999", "Late Fee Notice - Use", "$72.01", "Wating for Hien"},
            {"9", "OR", "06/18/2020", "00689", "Annual Report", "$100.00", "Waiting for Hien"},
            {"10", "NV", "08/07/2020", "20003874010", "Monthly Statement", "$756.92", "Business tax - Hien"},
            {"11", "KS", "07/28/2020", "0101031961323", "Tax Bill", "$890.00", "$30 - Hien"},
            {"12", "AL", "07/26/2020", "L1976044960", "Statement", "$300.00", "Waiting for Hien"},
            {"13", "ID", "09/01/2020", "L0940296896", "Tax Notice", "$632.07", "Mail didn't received yet"},
            {"14", "ID", "06/18/2020", "L1397609152", "Tax Notice", "$527.03", "Waiting for mail with TAP details"},
            {"15", "TX", "08/07/2020", "RT562226", "Tax Statement", "$51.00", "Waiting for Hien"},
            {"16", "KS", "09/09/2020", "2020-ST-005584", "Tax Statement", "$902.00", "no sale account / Hien"},
            {"17", "AL", "09/01/2020", "L1206357408", "Late Fee Notice", "$50.00", "Wating for Hien"},
            {"18", "OR", "08/10/2020", "01903", "Annual report", "$60.64", "website is down till 09/13"},
            {"19", "SD", "08/05/2020", "10351521ST082020P", "Late Fee", "$80.00", "Wating for Hien"},
            {"20", "KS", "08/04/2020", "060120", "Final Assessment", "$400.00", "Wating for Hien"}
        };
      
        for (int i = 0; i < allAccounts.length; i++){    
          String num = allAccounts[i][0];
                String state = allAccounts[i][1];
                String lastDate = allAccounts[i][2];
                String error = allAccounts[i][3];
                String fee = allAccounts[i][4];
                String hasmail = allAccounts[i][5];
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
                for(int j = 1; j < allAccounts[i].length-1; j++){
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