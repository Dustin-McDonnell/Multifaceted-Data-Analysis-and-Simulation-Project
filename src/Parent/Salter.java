package Parent;
import java.io.File;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

//https://www.w3schools.com/java/java_files_read.asp
//How I learned to read data from a file

public class Salter {
    private Scanner userInput = new Scanner(System.in);
    private ArrayList<String> dataFull = new ArrayList<>();
    private ArrayList<String> data = new ArrayList<>();
    private ArrayList<String> splitDataFull = new ArrayList<>();
    private Random rng = new Random();
    private CreateFile cf = new CreateFile();
    private ManipulateData md = new ManipulateData();
    private File file = md.filePicker();


    public void salter(){
        int chosenNumber = rngNumberChooser();

        dataFull = md.readData(file);

        //Loop for getting every Value in data and Salting it
        for(int i = 0; i < dataFull.size(); i++){
            String[] split = dataFull.get(i).split(",");
            double tempSalt = Double.parseDouble(split[1]) + rng.nextInt(-chosenNumber,chosenNumber + 1);
            splitDataFull.add(split[0]);
            splitDataFull.add(String.valueOf(tempSalt));
        }

        //Formatting the manipulated data back to CSV
        data = md.formatCSV(splitDataFull);

        System.out.println("Enter the name you want for the Salted Data");
        File saltedFile = cf.createFile();
        md.writeData(saltedFile,data);


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
