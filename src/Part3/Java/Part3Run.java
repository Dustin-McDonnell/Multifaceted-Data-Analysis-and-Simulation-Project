package Java;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Part3Run {
    public void run() throws IOException {
        Scanner scanner = new Scanner(System.in);

        while(true){

            System.out.println("Choose the number of the operation you want to preform.");
            System.out.println("0: Ends Program");
            System.out.println("1: Write Cube Root Data.");
            System.out.println("2: Graph a CSV file");
            System.out.println("3: Salt a CSV file");

            if(scanner.hasNextInt()){
                int choice = scanner.nextInt();
                if(choice == 1){
                    CubeRootPart3 cbrt = new CubeRootPart3();
                    ArrayList<Double> data = cbrt.cubeRootData();
                    cbrt.csvWriter(data);
                }
                if(choice == 2){
                    FilePicker fp = new FilePicker();
                    CubeRootPart3 cbrt = new CubeRootPart3();
                    File file = fp.filePicker();
                    cbrt.graphData(file);
                }
                if(choice == 3){
                    Salter salt = new Salter();
                    salt.saltData();
                }
                if(choice == 0){
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
