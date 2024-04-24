package Java;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Part3Run {
    public void run() throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose the number of the operation you want to preform.");
        System.out.println("1: Write Cube Root Data.");
        System.out.println("2: Graph a CSV file");

        while(true){
            if(scanner.hasNextInt()){
                int choice = scanner.nextInt();
                if(choice == 1){
                    CubeRootPart3 cbrt = new CubeRootPart3();
                    ArrayList<Double> data = cbrt.cubeRootData();
                    cbrt.csvWriter(data);
                    break;
                }
                if(choice == 2){
                    FilePicker fp = new FilePicker();
                    CubeRootPart3 cbrt = new CubeRootPart3();
                    File file = fp.filePicker();
                    cbrt.graphData(file);
                    break;
                }
            }
            else{
                System.out.println("Enter a number.");
                scanner.next();
            }
        }
    }
}
