package Java;
import org.apache.commons.math3.random.*;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ManipulateData {
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

    //Used this for info
    //https://commons.apache.org/proper/commons-math/userguide/stat.html Rolling Mean
    public void smoothData() throws IOException{
        DescriptiveStatistics stats = new DescriptiveStatistics();
        System.out.println("Enter the int range you want to smooth. ie: If you enter 5 it's 5 points to the left and right");
        System.out.println("of the initial value.");
        while(true){
            if(scanner.hasNextInt()){
                stats.setWindowSize((scanner.nextInt() * 2) + 1);
            }
            else{
                System.out.println("Enter a number.");
            }
            System.out.println("Choose the file you want to smooth.");
            File file = fp.filePicker();
            ArrayList<Double> preSmooth = fp.csvReader(file);
            ArrayList<Double> preSmoothX = new ArrayList<>();
            ArrayList<Double> smoothData = new ArrayList<>();
            ArrayList<Double> smoothDataTransfer = new ArrayList<>();
            ArrayList<Double> postSmooth = new ArrayList<>();

            for(int i = 0; i < preSmooth.size(); i = i + 2){
                if (i+1 < preSmooth.size()) {
                    smoothData.add(preSmooth.get(i+1));
                }
            }

            for(int i = 0; i < preSmooth.size(); i = i + 2){
                if (i+1 < preSmooth.size()) {
                    preSmoothX.add(preSmooth.get(i));
                }
            }

            for (int i = 0; i < smoothData.size(); i++){
                stats.clear();
                int start = Math.max(0, i - stats.getWindowSize());
                int end = Math.min(smoothData.size() - 1, i + stats.getWindowSize());
                for (int k = start; k <= end; k++){
                    stats.addValue(smoothData.get(k));
                }
                smoothDataTransfer.add(stats.getMean());
            }

            for(int i = 0; i < preSmoothX.size(); i++){
                postSmooth.add(preSmoothX.get(i));
                if (i < smoothDataTransfer.size()) {
                    postSmooth.add(smoothDataTransfer.get(i));
                }
            }

            cbrt.csvWriter(postSmooth);
            break;
        }
    }
}
