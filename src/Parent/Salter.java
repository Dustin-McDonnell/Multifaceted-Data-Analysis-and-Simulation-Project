package Parent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

//https://www.w3schools.com/java/java_files_read.asp
//How I learned to read data from a file

public class Salter {
    FilePicker fp = new FilePicker();
    File file = fp.filePicker();
    ArrayList<String> data = new ArrayList<>();

    public void salter(){
        try{
            Scanner scanner = new Scanner(file);
            while(scanner.hasNext()){
                System.out.println(scanner.next() );
            }
        }
        catch (IOException e){
            System.out.println("An IO error occurred.");
        }

    }
}
