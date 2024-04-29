package Java;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ManipulateDataPart3 {
    private CubeRootPart3 cbrt = new CubeRootPart3();
    private Scanner scanner = new Scanner(System.in);
    private FilePickerPart3 fp = new FilePickerPart3();

    public Integer chooseN(){
        Integer n = null;
        System.out.println("Enter the range you want the RSI to be calculated for: ");
        System.out.println("If you enter 5 it will a 5 day range behind each element except ");
        System.out.println("for the first 10 days.");
        while (n == null) {
            if (scanner.hasNextInt()){
                n = scanner.nextInt();
            }
            else{
                System.out.println("Enter a number");
                scanner.next();
            }
        }
        return n;
    }
    public ArrayList<String> calculateRSI(ArrayList<ArrayList<String>> data, Integer n){
        ArrayList<String> rsiData = new ArrayList<>();

        for (int i = 2*n; i < data.size(); i++){
            Double avgU = 0.0;
            Double avgD = 0.0;

            for (int j = i; j > i - n; j--) {
                Double currentClose = Double.parseDouble(data.get(j).get(4));
                Double previousClose = Double.parseDouble(data.get(j - 1).get(4));
                Double change = currentClose - previousClose;
                if(change > 0){
                    avgU += change;
                }
                else{
                    avgD += Math.abs(change);
                }
            }

            avgU = avgU / n;
            avgD = avgD / n;
            Double relativeStrength = avgU / avgD;
            Double relativeStrengthIndex = 100 - (100 / (1 + relativeStrength));
            rsiData.add(String.valueOf(relativeStrengthIndex));
        }
        return rsiData;
    }



    public ArrayList<ArrayList<String>> smoothData() throws IOException {
        DescriptiveStatistics stats = new DescriptiveStatistics();
        System.out.println("Enter the int range you want for the moving average. ie: If you enter 5 it's 5 points to the left and right");
        System.out.println("of the initial value.");
        while (true) {
            if (scanner.hasNextInt()) {
                stats.setWindowSize((scanner.nextInt() * 2) + 1);
            } else {
                System.out.println("Enter a number.");
            }
            System.out.println("Choose the file you want to smooth.");
            File file = fp.filePicker();
            ArrayList<ArrayList<String>> preSmooth = fp.csvReader(file);
            ArrayList<Double> preSmoothX = new ArrayList<>();
            ArrayList<Double> smoothData = new ArrayList<>();
            ArrayList<Double> smoothDataTransfer = new ArrayList<>();

            for (int i = 0; i < preSmooth.size(); i ++) {
                smoothData.add(Double.parseDouble(preSmooth.get(i).get(5)));
            }

            for (int i = 0; i < preSmooth.size(); i ++) {
                preSmoothX.add(Double.parseDouble(preSmooth.get(i).get(5)));
            }

            //This maybe fixed the smoothing issue?
            for (int i = 0; i < smoothData.size(); i++) {
                stats.clear();
                int windowSize = Math.min(Math.min(i, smoothData.size() - i - 1), stats.getWindowSize());
                int start = Math.max(0, i - windowSize);
                int end = Math.min(smoothData.size() - 1, i + windowSize);
                for (int k = start; k <= end; k++) {
                    stats.addValue(smoothData.get(k));
                }
                smoothDataTransfer.add(stats.getMean());
            }

            for(int i = 0; i < preSmooth.size(); i++){
                if(i < smoothDataTransfer.size()){
                    preSmooth.get(i).add(String.valueOf(smoothDataTransfer.get(i)));
                }
            }
            return preSmooth;
        }

    }

    public void totalCSV(ArrayList<ArrayList<String>> data, ArrayList<String> rsi, int n) throws IOException {
        try (FileWriter writer = new FileWriter(createFile())) {
            CSVPrinter printer = new CSVPrinter(writer, CSVFormat.EXCEL);
            int rsiIndex = 0;
            //I probably could have used a loop to make this look a lot cleaner but at this point I'm in too deep.
            if(data.get(0).size() != 6) {
                printer.printRecord("Date","Open","High","Low","Close","Adj Close","Volume","Moving Average","RSI");
                for (int i = 0; i < data.size(); i++) {
                    if(i < n*2){
                        printer.printRecord(data.get(i).get(0), data.get(i).get(1), data.get(i).get(2), data.get(i).get(3),
                                data.get(i).get(4), data.get(i).get(5), data.get(i).get(6),data.get(i).get(7));
                    }
                    else{
                            printer.printRecord(data.get(i).get(0), data.get(i).get(1), data.get(i).get(2), data.get(i).get(3),
                                    data.get(i).get(4), data.get(i).get(5), data.get(i).get(6),data.get(i).get(7),rsi.get(rsiIndex));
                                    rsiIndex++;
                    }
                }
            }
            //This was originally for if I wanted to use this function for other things, but then it just ended up not happening.
            else{
                printer.printRecord("Date","Open","High","Low","Close","Adj Close","Volume");
                for (int i = 0; i < data.size(); i++) {
                    printer.printRecord(data.get(i).get(0), data.get(i).get(1), data.get(i).get(2), data.get(i).get(3),
                            data.get(i).get(4), data.get(i).get(5), data.get(i).get(6));
                }
            }
        }
    }



    public String createFile(){
        Scanner scanner = new Scanner(System.in);
        try{
            System.out.println("Enter the name of the data file you want to create.");
            String fileName = scanner.nextLine();
            File file = new File("src/StockBot/DataFiles/" + fileName + ".csv");

            if (file.createNewFile()){
                System.out.println("File created: " + file);
                return file.getAbsolutePath();
            }

            else{
                System.out.println("File Already Exists");
                return file.getAbsolutePath();
            }


        }
        catch (IOException e){
            System.out.println("An error occurred.");
        }
        return null;
    }
}
