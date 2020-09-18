import java.io.FileWriter;
import java.io.IOException;

public class testFile {
        public static void main(String[] args){
            try {
                FileWriter vendorsList = new FileWriter("vendors.txt");
                vendorsList.write("ATD");
                vendorsList.close();
            }
            catch (IOException e) {
                    System.out.println("An error occurred.");
                    e.printStackTrace();
            }
        }
}