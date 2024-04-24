package Java;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class FilePicker {
    public ArrayList<Double> csvReader(File file) throws FileNotFoundException {
        try{
            ArrayList<Double> data = new ArrayList<>();
            FileReader fr = new FileReader(file);
            Iterable<CSVRecord> records = CSVFormat.EXCEL.withFirstRecordAsHeader().parse(fr);
            for (CSVRecord s:records){
                data.add(Double.parseDouble(s.get("X_Value")));
                data.add(Double.parseDouble(s.get("Y_Value")));
            }
            return data;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public File filePicker(){
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        File directory = new File("src/Part3/DataFiles");
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
