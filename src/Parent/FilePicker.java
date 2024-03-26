package Parent;

import java.io.File;
import java.util.InputMismatchException;
import java.util.Scanner;

//https://stackoverflow.com/questions/1844688/how-can-i-read-all-files-in-a-folder-from-java
//How I learned to read the files from a directory

public class FilePicker {
    Scanner scanner = new Scanner(System.in);
    boolean running = true;
    public File filePicker(){
        File directory = new File("src/DataFiles");
        File[] files = directory.listFiles();
        File file = null;

        for(int i = 0; i < files.length; i++){
            System.out.println("Index " + (i+1) + ": " + files[i]);
        }
        System.out.println("\nChoose the file you want to alter.");

        while(running){
            try{
                int chosenFile = scanner.nextInt();
                if(chosenFile >= 0 && chosenFile <= files.length){
                    System.out.println("You chose " + files[chosenFile - 1]);
                    file = files[chosenFile - 1];
                    running = false;
                }
                else{
                    System.out.println("Enter a number in the index range.");
                }
            }
            catch (InputMismatchException e){
                System.out.println("Enter a number.");
                scanner.next();
            }
        }
        return file;
    }
}
