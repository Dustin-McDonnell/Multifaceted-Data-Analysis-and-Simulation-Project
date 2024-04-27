package Parent;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Run {
    private CreateFile cf = new CreateFile();
    private CubeRoot cr = new CubeRoot();
    private ManipulateData wd = new ManipulateData();
    private Scanner scanner = new Scanner(System.in);

    public void run(){
        int input;
        do{
            System.out.println("Enter the number of the function you want to use.");
            System.out.println("1: Graph a CSV.");
            System.out.println("2: Salt a CSV");
            System.out.println("3: Smooth a CSV");
            while(!scanner.hasNextInt()) {
                System.out.println("Enter a number.");
                scanner.next();
            }
            input = scanner.nextInt();
            if(input == 1){
                graph();
                break;
            }
            if(input ==2){
                Salter salter = new Salter();
                salter.salter();
                break;
            }
            if(input ==3){
                Smoother sm = new Smoother();
                sm.smoothData();
                break;
            }
        } while(input != 1);
    }

    public void graph(){
        File file = cf.createFile();
        if(file != null) {
            ArrayList<Double> graphSpecifics = cr.graphSpecifics();
            ArrayList<String> graphData = cr.cubeRootData(graphSpecifics);
            wd.writeData(file, graphData);
        }
    }

}
