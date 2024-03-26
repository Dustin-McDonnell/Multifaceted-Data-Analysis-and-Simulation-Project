package Parent;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ManipulateData {
    public void writeData(File file, ArrayList<String> graphData){
        try{
            FileWriter fw = new FileWriter(file);
            fw.write("X_Value,Y_Value\n");
            for(String s: graphData){
                fw.write(s + "\n");
            }
            fw.close();
            System.out.println("Graph data written to: " + file);
        }
        catch (IOException e){
            System.out.println("An error occurred.");
        }
    }

    public ArrayList<String> readData(File file){
    ArrayList<String> data = new ArrayList<>();
        try{
            Scanner scannerFull = new Scanner(file);

            //Test Skipping first line
            scannerFull.next();

            while(scannerFull.hasNext()){
                data.add(scannerFull.next());
            }
        }
        catch (IOException e){
            System.out.println("An IO error occurred.");
        }
        return data;
    }

    public ArrayList<String> formatCSV(ArrayList<String> preFormat){
        ArrayList<String> data = new ArrayList<>();
        for (int i = 0; i < preFormat.size() - 1; i = i + 2){
            data.add(preFormat.get(i) + "," + preFormat.get(i+1));
        }
        return data;
    }

    public File filePicker(){
        Scanner scanner = new Scanner(System.in);
        boolean running = true;
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

