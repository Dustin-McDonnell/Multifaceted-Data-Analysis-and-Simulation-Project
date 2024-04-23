package Parent;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class CreateFile {
    Scanner scanner = new Scanner(System.in);
    public File createFile(){
        try{
            System.out.println("Enter the name of the data file you want to create.");
            String fileName = scanner.nextLine();
            //Path I currently use on my macbook not sure how that will work for the Prof
            //Working on this on my pc instead of macbook can confirm this is a problem
            File file = new File("src/Part1/DataFiles/" + fileName + ".csv");

            if (file.createNewFile()){
                System.out.println("File created: " + file);
                return file;
            }

            else{
                System.out.println("File Already Exists");
                return file;
            }


        }
        catch (IOException e){
            System.out.println("An error occurred.");
        }
        return null;
    }
}
