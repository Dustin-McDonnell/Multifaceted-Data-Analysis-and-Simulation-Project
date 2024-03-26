package Parent;
import java.io.File;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Smoother {
    private ManipulateData md = new ManipulateData();

    public void smoothData() {
        File file = md.filePicker();
        ArrayList<String> preFormat = md.readData(file);
        ArrayList<String> yValue = new ArrayList<>();
        int windowValue = windowValue();
        int yValueCounter = 0;
        int smoothValue = 0;

        //Pull Y Values into a seperate Array
        for (int i = 0; i < preFormat.size(); i++) {
            String[] temp = preFormat.get(i).split(",");
            yValue.add(temp[1]);
        }

        for (int i = 0; i < yValue.size(); i++) {
            for (int k = -windowValue; k < windowValue; k++) {
                if (yValue.get(i + k) != null) {
                    smoothValue = smoothValue + Integer.parseInt(yValue.get(k));
                }
            }

        }
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
