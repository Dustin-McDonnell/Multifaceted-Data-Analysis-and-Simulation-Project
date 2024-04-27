package Java;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Running {
    private Scanner scanner = new Scanner(System.in);
    private ManipulateDataPart3 md = new ManipulateDataPart3();
    private FilePickerPart3 fp = new FilePickerPart3();
    private StockBot sb = new StockBot();
    public void running() throws IOException {
        while(true){

            System.out.println("Choose the number of the operation you want to preform.");
            System.out.println("0: Ends Program");
            System.out.println("1: Calculate RSI and Moving Average for entire CSV.");
            System.out.println("2: Run the StockBot");

            if(scanner.hasNextInt()){
                int choice = scanner.nextInt();
                if(choice == 1){
                    ArrayList<ArrayList<String>> movingAverage = md.smoothData();
                    int n = md.chooseN();
                    ArrayList<String> rsi = md.calculateRSI(movingAverage, n);
                    md.totalCSV(movingAverage,rsi,n);
                }
                if(choice == 0){
                    break;
                }
                if(choice == 2){
                    System.out.println("The file chosen here must be a csv that has previously has the RSI and moving average");
                    System.out.println("calculated already. This can be done with option 1.");
                    File file = fp.filePicker();
                    ArrayList<ArrayList<String>> data = fp.csvReader(file);
                    ArrayList<String> qrt = sb.stockBot(data);
                    for (int i = 0; i < qrt.size(); i ++){
                        System.out.println(qrt.get(i));
                    }
                }
            }
            else{
                System.out.println("Enter a number.");
                scanner.next();
            }
        }
    }
}

