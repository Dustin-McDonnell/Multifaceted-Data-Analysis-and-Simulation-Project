package Parent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

//https://www.w3schools.com/java/java_files_read.asp
//How I learned to read data from a file

public class Salter {
    FilePicker fp = new FilePicker();
    File file = fp.filePicker();
    Scanner userInput = new Scanner(System.in);
    ArrayList<String> dataFull = new ArrayList<>();
    ArrayList<String> data = new ArrayList<>();
    ArrayList<String> splitData = new ArrayList<>();
    ArrayList<String> splitDataFull = new ArrayList<>();
    Random rng = new Random();


    public void salter(){
        int chosenNumber = rngNumberChooser();

        try{
            Scanner scannerFull = new Scanner(file);
            Scanner scanner = new Scanner(file);

            while(scannerFull.hasNext()){
                dataFull.add(scannerFull.next());
            }

            //Skipping first line in File for just Y Values
            scanner.nextLine();
            while(scanner.hasNext()){
                data.add(scanner.next());
            }
        }
        catch (IOException e){
            System.out.println("An IO error occurred.");
        }

        //Loop for getting Y values in splitData array and Salt with previously chosen Rng Range
        for (int i = 0; i < data.size(); i++){
            String temp = data.get(i);
            String[] split = temp.split(",");
            double tempSalt = Double.parseDouble(split[1]) + rng.nextInt(0,chosenNumber);
            splitData.add(String.valueOf(tempSalt));
        }

        //Loop for getting every Value in data
        for(int i = 0; i < dataFull.size(); i++){
            String temp = dataFull.get(i);
            String[] split = temp.split(",");
            splitDataFull.add(split[0]);
            splitDataFull.add(split[1]);
        }

        for(int i = 0; i < splitData.size(); i++){
            System.out.println(splitData.get(i));
        }

        for(int i = 0; i < splitDataFull.size(); i++){
            System.out.println(splitDataFull.get(i));
        }

    }

    public Integer rngNumberChooser(){
        boolean running = true;
        do {
            try {
                System.out.println("Enter the range you want to salt, Ie; 1, 5, 20, 100");

                int number = userInput.nextInt();

                if (number > 0) {
                    running = false;
                    System.out.println("Number: " + number);
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
