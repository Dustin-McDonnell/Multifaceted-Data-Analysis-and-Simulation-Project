package Parent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

//https://www.w3schools.com/java/java_files_read.asp
//How I learned to read data from a file

public class Salter {
    private FilePicker fp = new FilePicker();
    private File file = fp.filePicker();
    private Scanner userInput = new Scanner(System.in);
    private ArrayList<String> dataFull = new ArrayList<>();
    private ArrayList<String> data = new ArrayList<>();
    private ArrayList<String> splitDataFull = new ArrayList<>();
    private Random rng = new Random();
    private CreateFile cf = new CreateFile();
    private WriteData wd = new WriteData();


    public void salter(){
        int chosenNumber = rngNumberChooser();

        try{
            Scanner scannerFull = new Scanner(file);

            //Test Skipping first line
            scannerFull.next();

            while(scannerFull.hasNext()){
                dataFull.add(scannerFull.next());
            }
        }
        catch (IOException e){
            System.out.println("An IO error occurred.");
        }


        //Loop for getting every Value in data
        for(int i = 0; i < dataFull.size(); i++){
            String temp = dataFull.get(i);
            String[] split = temp.split(",");
            double tempSalt = Double.parseDouble(split[1]) + rng.nextInt(-chosenNumber,chosenNumber + 1);
            splitDataFull.add(split[0]);
            splitDataFull.add(String.valueOf(tempSalt));
        }

        //Formatting the data Correctly
        for (int i = 0; i < splitDataFull.size() - 1; i = i + 2){
            data.add(splitDataFull.get(i) + "," + splitDataFull.get(i+1));
        }

        System.out.println("Enter the name you want for the Salted Data");
        File saltedFile = cf.createFile();
        wd.writeData(saltedFile,data);


    }

    public Integer rngNumberChooser(){
        boolean running = true;
        do {
            try {
                System.out.println("Enter the range you want to salt, Ie; 1, 5, 20, 100");

                int number = userInput.nextInt();

                if (number > 0) {
                    running = false;
                    return number;
                } else {
                    System.out.println("Enter a number greater than zero");
                }

            } catch (InputMismatchException e) {
                System.out.println("Enter a valid number.");
                userInput.next();
            }
        }while (running);

        return null;
    }
}
