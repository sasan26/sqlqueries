
import java.io.*;
import java.awt.event.*; 
import java.awt.*; 
import javax.swing.*;
import java.util.*;

public class Khodi {
    // public static String Pass = "1360";
    public static char[] passString = System.console().readPassword("%s", "Enter password: ");
    public static String Pass = new String(passString);
    public static String B = System.getProperty("line.separator");
    public static String[] files = {"sasan.txt"};
    public static String[] passwordSas = {"sasan"};
    public static String taxDatas2 = "";

   public void output(String n, String id, String name, String g, String u){        
        System.out.println("\t Num: \t\t" + n);
        System.out.println("\t ID: \t\t" + id);
        System.out.println("\t Name: \t\t" + name);
        System.out.println("\t GPA: \t\t" + g);
        System.out.println("\t Units: \t" + u);
   }
    
    public void vName(String vname){

        if (vname.equals("HELP")){
            System.out.print( B );
            for(int i =0; i<46; i++){System.out.print( "-" );}
            System.out.print(" List of commands ");
            for(int i =0; i<46; i++){System.out.print( "-" );}
            System.out.println( B );
            System.out.println("\t[ help ]\t\t\t\t\tfor information");
            System.out.println("\tdb>[ database name ]\t\t\t\tconnect to a database \t\t ex: db>student");
            System.out.println("\t[ all ]\t\t\t\t\t\tfor wholestudents list");
            System.out.println("\tnum>[ number ]\t\t\t\t\tsearch by number \t\t ex: num>26");
            System.out.println("\tname>[ student name ]\t\t\t\tsearch by student name \t\t ex: name>sasan bazade");
            System.out.println("\tid>[ student id ]\t\t\t\tsearch by student id \t\t ex: id>22192136060");
            System.out.println("\tset>[number],[id],[name],[GPA],[Units]\t\tadd a new student \t\t ex: set>126,22192136070,Sasan Bazadeh,18.50,98");
            System.out.println("\t[ exit ]\t\t\t\t\tquit from app\t\t\t ex: exit"+B);
            for(int i =0; i<110; i++){System.out.print( "-" );} 
            System.out.print( B );
        }		
        
        // ************************************************************************************************************************
        //                                                         Student
        // ************************************************************************************************************************

        if(vname.equals("DB>STUDENT")){ files[0] = "student.csv"; System.out.println("\n\t\t Connected to this database => [ " + files[0] + " ]");}
        if(files[0].equals("student.csv")){ 
            if(vname.contains("SET>")){
            try {
                FileWriter newSas = new FileWriter(files[0], true);
                String newData = vname.substring(4, vname.length());
                String newDatas [] = newData.split(",");
                if(newDatas.length != 5){
                    System.out.println("\n\t\t wrong format! Your input MUST has 5 entries!");
                    newDatas = null;
                }
                else{
                    for( Integer i = 0; i < newDatas.length; i++){
                        if(newDatas[0].length() != 3){
                            System.out.println("\n\t\tWrong format!\t\t [Num] length is invalid!\n\t\texample: 111,221926000,sasan bazade, 19.99, 120"); break;
                        }
                        else if(newDatas[1].length() != 11){
                            System.out.println("\n\t\tWrong format!\t\t [ID] length is invalid!\n\t\texample: 111,22192136000,sasan bazade, 19.99, 120"); break;
                        }
                        else{
                            String newRecord = newDatas[0]+",ID:"+newDatas[1]+",NAME:"+newDatas[2]+",GPA"+newDatas[3]+",UNITS"+newDatas[4]+"\n";
                            newSas.write(newRecord);
                            System.out.println("\n\t New Student has been added to database.\n");
                            output(newDatas[0],newDatas[1],newDatas[2],newDatas[3],newDatas[4]);
                            break;
                        }
                        
                    }
                }
               
                newSas.close();
            }
            catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
            }
            }
            else{
                try {
                    File myObj = new File(files[0]);
                    Scanner myReader = new Scanner(myObj);
                    
                    if (vname.equals("ALL")){ System.out.println("\t[NUM] Student ID\tStudent Name\n"); }

                    while (myReader.hasNextLine()) { 
                        String data = myReader.nextLine();
                        String datas [] = data.split(",");
                        // int seprator = data.indexOf("|");
                        String num = datas[0];
                        String studentId = datas[1].substring(3,datas[1].length()); 
                        String studentName = datas[2].substring(5,datas[2].length());
                        String gpa = datas[3].substring(4,datas[3].length());
                        String units = datas[4].substring(6,datas[4].length());
                        
                        
                        if (vname.equals("ALL")){ 
                            System.out.println("\t[" + num + "] " + studentId + "\t" + studentName);
                        }

                        else if(vname.equals("NUM>" + num)){
                            System.out.println(B);
                            output(num, studentId, studentName, gpa, units);
                        } 
                        else if(vname.equals("ID>" + studentId)){
                            System.out.println(B);
                            output(num, studentId, studentName, gpa, units);
                        }
                        else if(vname.equals("NAME>" + studentName.toUpperCase())){
                            System.out.println(B);
                            output(num, studentId, studentName, gpa, units);
                        }
                    } 
                    System.out.println(B); 
                    myReader.close();
                } catch (FileNotFoundException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
                }
            }
        }

    }

    

    public void creditCrator(){
        System.out.print(B);
        for(int i =0; i<110; i++){System.out.print( "*" );}
        printCredit("KHODI APP", true);
        printCredit("version 2.1.0", false);
        printCredit("by Khodamorad Chehlmard", false);	 
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
          
        Khodi studentsData = new Khodi();
        Scanner in = new Scanner(System.in);   
        studentsData.checkPassword();
        studentsData.creditCrator(); 
            
        if(Pass.equals(passwordSas[0])){ 
            studentsData.printCredit("> type help  for more information", false);  
            while ( Pass.equals(passwordSas[0]) ) {
                try {
                    String name = in.nextLine(); 
                    if(name.equals("exit")){ 
                        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor(); 
                        System.exit(0);
                }
                studentsData.vName(name.toUpperCase());
                } catch (Exception e) { 
                    System.out.print("ERROR:  wrong input"); break;
                }
            }    
        } 
        else { studentsData.printCredit("WRONG PASSWORD!", true); }
        in.close();         
    }






}




    