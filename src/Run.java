import java.io.File;
import java.util.ArrayList;

public class Run {
    CubeRoot cr = new CubeRoot();
    public void graph(){
        File file = cr.createFile();
        if(file != null) {
            ArrayList<Double> graphSpecifics = cr.graphSpecifics();
            ArrayList<String> graphData = cr.cubeRootData(graphSpecifics);
            cr.writeCubeRootData(file, graphData);
        }
        else{
            System.out.println("File already exists.");
        }
    }
}
