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
            {"AL", "Alabama", "9/8/2020", "HASMAIL"},
            {"AK", "Alaska", "", "NOMAIL"},
            {"AZ", "Arizona", "7/27/2020", "NOMAIL"},
            {"AR", "Arkansas", "7/27/2020", "NOMAIL"},
            {"CO", "Colorado", "8/26/2020", "NOMAIL"},
            {"CT", "Connecticutt", "7/27/2020", "NOMAIL"},
            {"DC", "District of Columbia", "6/4/2020", "NOMAIL"},
            {"FL", "Florida", "8/26/2020", "NOMAIL"},
            {"GA", "Georgia", "8/26/2020", "NOMAIL"},
            {"HI", "Hawaii", "7/27/2020", "NOMAIL"},
            {"IL", "Illinois", "7/27/2020", "NOMAIL"},
            {"ID", "Idaho", "", "HASMAIL"},
            {"IA", "Iowa", "7/24/2020", "NOMAIL"},
            {"KS", "Kansas", "6/8/2020", "HASMAIL"},
            {"KY", "Kentucky", "7/27/2020", "NOMAIL"},
            {"LA", "Louisiana", "8/26/2020", "NOMAIL"},
            {"ME", "Maine", "6/4/2020", "NOMAIL"},
            {"MD", "Maryland", "1/4/2020", "NOMAIL"},
            {"MA", "Massachusetts", "7/28/2020", "NOMAIL"},
            {"MI", "Michigan", "7/28/2020", "NOMAIL"},
            {"MN", "Minnesota", "7/9/2020", "NOMAIL"},
            {"MS", "Mississippi", "7/28/2020", "NOMAIL"},
            {"MO", "Missouri", "6/4/2020", "NOMAIL"},
            {"NE", "Nebraska", "7/28/2020", "NOMAIL"},
            {"NV", "Nevada", "6/4/2020", "HASMAIL"},
            {"NH", "New Hampshire", "6/4/2020", "NOMAIL"},
            {"NJ", "New Jersey", "1/17/2020", "NOMAIL"},
            {"NM", "New Mexico", "7/30/2020", "NOMAIL"},
            {"NY", "New York", "7/30/2020", "NOMAIL"},
            {"NC", "North Carolina", "7/30/2020", "NOMAIL"},
            {"ND", "North Dakota", "", "NOMAIL"},
            {"OH", "Ohio", "9/1/2020", "NOMAIL"},
            {"OK", "Oklahoma", "", "NOMAIL"},
            {"OR", "Oregon", "6/11/2020", "HASMAIL"},
            {"PA", "Pennsylvania", "1/17/2020", "NOMAIL"},
            {"RI", "Rhode Island", "", "NOMAIL"},
            {"SC", "South Carolina", "8/26/2020", "NOMAIL"},
            {"SD", "South Dakota", "7/16/2020", "NOMAIL"},
            {"TN", "Tennessee", "7/21/2020", "NOMAIL"},
            {"TX", "Texas", "7/30/2020", "HASMAIL"},
            {"UT", "Utah", "", "NOMAIL"},
            {"VT", "Vermont", "7/30/2020", "NOMAIL"},
            {"VA", "Virginia", "6/4/2020", "HASMAIL"},
            {"WA", "Washington", "7/30/2020", "NOMAIL"},
            {"WV", "West Virginia", "6/4/2020", "NOMAIL"},
            {"WI", "Wisconsin", "7/30/2020", "NOMAIL"}        
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
            {"7", "VA", "08/17/2020", "Q0390999", "Late Fee Notice - Sale", "$71.77", "Wating for Hie"},
            {"8", "VA", "08/17/2020", "Q0390999", "Late Fee Notice - Use", "$72.01", "Wating for Hie"},
            {"9", "OR", "06/18/2020", "00689", "Annual Report", "$100.00", "Waiting for Hien"},
            {"10", "NV", "08/07/2020", "20003874010", "Monthly Statement", "$756.92", "Business tax - Hien"},
            {"11", "KS", "07/28/2020", "0101031961323", "Tax Bill", "$890.00", "$30 - Hien"},
            {"12", "AL", "07/26/2020", "L1976044960", "Statement", "$300.00", "Waiting for Hien"},
            {"13", "ID", "09/01/2020", "L0940296896", "Tax Notice", "$632.07", "Mail didn't received yet"},
            {"14", "ID", "06/18/2020", "L1397609152", "Tax Notice", "$527.03", "Waiting for mail with TAP details"},
            {"15", "TX", "08/07/2020", "RT562226", "Tax Statement", "$51.00", "Waiting for Hien"}
        };
      
        for (int i = 0; i < allAccounts.length; i++){    
            if (vname.equals("ALL")){
                String num = allAccounts[i][0];
                String state = allAccounts[i][1];
                String lastDate = allAccounts[i][2];
                String hasmail = allAccounts[i][3];
                if(state.length() >= 20){
                    System.out.println("\t\t"+num +"\t"+ state +"\t"+ lastDate);
                }
                else if(state.length() >= 8){
                    System.out.println("\t\t"+num +"\t"+ state +"\t\t"+ lastDate);
                } else {
                    System.out.println("\t\t"+num +"\t"+ state +"\t\t\t"+ lastDate);
                }
            }	
            if (vname.equals(allAccounts[i][0])){
                for(int j = 1; j < allAccounts[i].length-1; j++){
                    System.out.print("\t\t"+allAccounts[i][j]);
                }
                System.out.print(B);
                break;
            } 

            if (vname.equals("MAIL>ALL")){
                for(int j = 0; j < allMails.length; j++){
                    System.out.println("\t\t"+allMails[j][0] + "\t"+allMails[j][1] + "\t"+allMails[j][2] + "\t"+allMails[j][5]);            
                }        
                break;
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
    for(int i =0; i<94; i++){System.out.print( "*" );}
    Tax checkPasswordName = new Tax();
    checkPasswordName.printCredit("Tax APP", true);
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