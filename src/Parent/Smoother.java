package Parent;
import java.io.File;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Smoother {
    private ManipulateData md = new ManipulateData();

    public void smoothData() {
        CreateFile cf = new CreateFile();
        File file = md.filePicker();
        ArrayList<String> preFormat = md.readData(file);
        ArrayList<String> fullArray = new ArrayList<>();
        ArrayList<String> yValue = new ArrayList<>();
        ArrayList<String> smoothedValue = new ArrayList<>();
        int windowValue = windowValue();
        int yValueCounter = 0;

        //Pull Y Values into a separate Array
        for (int i = 0; i < preFormat.size(); i++) {
            String[] temp = preFormat.get(i).split(",");
            yValue.add(temp[1]);
        }

        for(int i = 0; i < yValue.size(); i++){
            double counter = 0;
            double yValueData = 0;
            for(int k = -windowValue; k <= windowValue; k++){
                if(i + k >= 0 && i + k < yValue.size()){
                    yValueData = yValueData + Double.parseDouble(yValue.get(i + k));
                    counter++;
                }
            }
            smoothedValue.add(String.valueOf(yValueData/counter));
        }

        for (int i = 0; i < preFormat.size(); i++){
            String[] temp = preFormat.get(i).split(",");
            temp[1] = smoothedValue.get(i);
            fullArray.add(temp[0]);
            fullArray.add(temp[1]);
        }



        System.out.println("Enter the name of the Smooth File you want to create");
        fullArray = md.formatCSV(fullArray);
        File smoothFile = cf.createFile();
        md.writeData(smoothFile,fullArray);

    }

    public Integer windowValue(){
        Scanner scanner = new Scanner(System.in);
        Integer windowValue = null;

        do{
            System.out.println("Enter the window value to use for smoothing range");
            try{
                int chosenNumber = scanner.nextInt();
                if(chosenNumber > 0){
                    windowValue = chosenNumber;
                }
                else {
                    System.out.println("Enter a value greater than zero");
                }
            }
            catch (InputMismatchException e){
                System.out.println("Enter a valid number.");
                scanner.next();
            }
        }
        while (windowValue == null);
        return windowValue;
    }

}
