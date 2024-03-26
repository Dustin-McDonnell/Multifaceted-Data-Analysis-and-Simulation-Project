package Parent;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WriteData {
    public void writeData(File file, ArrayList<String> graphData){
        try{
            FileWriter fw = new FileWriter(file);
            fw.write("X_Value,Y_Value\n");
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
