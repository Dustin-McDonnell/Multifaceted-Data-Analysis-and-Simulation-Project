package Java;
import org.apache.commons.math3.random.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Salter {
    private FilePicker fp = new FilePicker();
    private RandomDataGenerator random = new RandomDataGenerator();
    private Scanner scanner = new Scanner(System.in);
    private CubeRootPart3 cbrt = new CubeRootPart3();
    public void saltData() throws IOException {
        Integer range;

        System.out.println("Enter the int range you want to randomize, ie: If you choose 5, 5 will be added or subtracted");
        System.out.println("from the data point.");
        while(true){
            if(scanner.hasNextInt()){
                range = scanner.nextInt();
                break;
            }
            else{
                System.out.println("Enter a number");
            }
        }
        System.out.println("Choose the file you want to salt.");
        File file = fp.filePicker();
        ArrayList<Double> preSalt = fp.csvReader(file);
        ArrayList<Double> postSalt = new ArrayList<>();
        for (int i = 0; i < preSalt.size(); i = i + 2){
            postSalt.add(preSalt.get(i));
            postSalt.add(preSalt.get(i+1) + random.nextInt(-range,range));
    }
        cbrt.csvWriter(postSalt);
}
}
