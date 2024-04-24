package Java;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.math3.analysis.function.Cbrt;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CubeRootPart3 {

    public ArrayList<Double> graphSpecifics(){
        Scanner scanner = new Scanner(System.in);
        ArrayList<Double> graph = new ArrayList<>();
        boolean working = true;
        while (working){
            try{
                System.out.println("Enter your starting number.");
                graph.add(scanner.nextDouble());
                System.out.println("Enter your ending number.");
                graph.add(scanner.nextDouble());
                System.out.println("Enter your data interval IE. .1,.10,1");
                graph.add(scanner.nextDouble());

                if(graph.get(1) > graph.get(0) && graph.get(2) > 0 && graph.get(2) <= Math.abs(graph.get(1) - graph.get(0))){
                    return graph;
                }

                else{
                    System.out.println("Ending number needs to be greater than starting number or" +
                            " interval needs to be greater than zero but less than the distance between the two points.");
                    graph.clear();
                }
            }
            catch (InputMismatchException e){
                System.out.println("You did not enter a proper number.\n");
                graph.clear();
                scanner.next();
            }
        }
        return null;
    }

    //Used Apache Cbrt() class to calculate data, even though java.Math natively has cuberoot
    //Doing this for the sake of understanding packages
    public ArrayList<Double> cubeRootData(){
        Cbrt cbrt = new Cbrt();
        ArrayList<Double> graphSpecifics = graphSpecifics();
        ArrayList<Double> data = new ArrayList<>();
        for (Double i = graphSpecifics.get(0); i <= graphSpecifics.get(1); i = i + graphSpecifics.get(2)){
            data.add(i);
            data.add(cbrt.value(i));
        }
        return data;
    }

    //This helped me with csvReading and csvWriting
    //https://www.baeldung.com/apache-commons-csv
    public void csvWriter(ArrayList<Double> data) throws IOException {
        try (FileWriter writer = new FileWriter(createFile())) {
            CSVPrinter printer = new CSVPrinter(writer, CSVFormat.EXCEL);
            printer.printRecord("X_Value","Y_Value");
            for(int i = 0; i < data.size(); i = i + 2) {
                printer.printRecord(data.get(i), data.get(i + 1));
            }
            if (data.size() % 2 != 0) {
                printer.printRecord(data.getLast());
            }
        }
    }


    public String createFile(){
        Scanner scanner = new Scanner(System.in);
        try{
            System.out.println("Enter the name of the data file you want to create.");
            String fileName = scanner.nextLine();
            //Path I currently use on my macbook not sure how that will work for the Prof
            //Working on this on my pc instead of macbook can confirm this is a problem
            File file = new File("src/Part3/DataFiles/" + fileName + ".csv");

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

    //This helped me with this
    //https://www.tutorialspoint.com/jfreechart/jfreechart_xy_chart.htm
    public void graphData(File file) throws IOException {
        int width = 1080;
        int height = 720;
        String fileName = null;
        FilePicker fp = new FilePicker();
        ArrayList<Double> data = fp.csvReader(file);
        Scanner scanner = new Scanner(System.in);
        XYSeries cubeRoot = new XYSeries("Cube Root");
        XYSeriesCollection cubeRootDataSet= new XYSeriesCollection();
        for (int i = 0; i < data.size(); i = i + 2) {
            cubeRoot.add(data.get(i), data.get(i + 1));
        }
        if (data.size() % 2 != 0) {
            cubeRoot.add(data.get(data.size() - 2),data.get(data.size() - 1));
        }

        cubeRootDataSet.addSeries(cubeRoot);

        JFreeChart graph = ChartFactory.createScatterPlot("Cube Root Graph","X Value","Y Value", cubeRootDataSet);
        System.out.println("Enter the name of the graph you want to create.");
        while(true){
            if(scanner.hasNext()){
                fileName = scanner.next();
                break;
            }
            else{
                System.out.println("Enter a string.");
            }
        }
        File xyChart = new File("src/Part3/DataFiles/Graphs/" + fileName + ".jpg");
        ChartUtils.saveChartAsJPEG(xyChart,graph,width,height);
    }
}
