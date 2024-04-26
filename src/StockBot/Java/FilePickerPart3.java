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

public class FilePickerPart3 {
    public File filePicker(){
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
        File directory = new File("src/StockBot/DataFiles");
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

    public ArrayList<ArrayList<String>> csvReader(File file) throws FileNotFoundException {
        try{
            ArrayList<ArrayList<String>> structure = new ArrayList<>();
            FileReader fr = new FileReader(file);
            Iterable<CSVRecord> records = CSVFormat.EXCEL.withFirstRecordAsHeader().parse(fr);
            for (CSVRecord s:records){
                ArrayList<String> data = new ArrayList<>();
                data.add((s.get("Date")));
                data.add((s.get("Open")));
                data.add((s.get("High")));
                data.add((s.get("Low")));
                data.add((s.get("Close")));
                data.add((s.get("Adj Close")));
                data.add((s.get("Volume")));
                structure.add(data);
            }
            return structure;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
