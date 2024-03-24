import java.io.File;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.Math;

//https://www.w3schools.com/java/java_files_create.asp This is what I used to learn to write data to files

public class CubeRoot {
    //Create Scanner
    Scanner scanner = new Scanner(System.in);
    //Creates the initial blank csv file if there are no duplicates
    public File createFile(){
        try{
            System.out.println("Enter the name of the data file you want to create.");
            String fileName = scanner.nextLine();
            //Path I currently use on my pc not sure how that will work for the Prof
            File file = new File("/Users/dustinmcdonnell/Desktop/Project 2/src/DataFiles/" + fileName + ".csv");

            if (file.createNewFile()){
                System.out.println("File created: " + file);
                return file;
            }

        }
        catch (IOException e){
            System.out.println("An error occurred.");
        }
        return null;
    }

    public ArrayList<Double> graphSpecifics(){
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

    public ArrayList<String> cubeRootData(ArrayList<Double> graph){
        ArrayList<String> data = new ArrayList<>();
        for (double i = graph.get(0); i <= graph.get(1); i = i + graph.get(2)){
            data.add(i + "," + Math.cbrt(i));
        }
        return data;
    }

    public void writeCubeRootData(File file, ArrayList<String> graphData){
    try{
        FileWriter fw = new FileWriter(file);
        fw.write("X Value,Y Value\n");
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
}
