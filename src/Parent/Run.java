package Parent;

import Parent.CubeRoot;

import java.io.File;
import java.util.ArrayList;

public class Run {
    private CreateFile cf = new CreateFile();
    private CubeRoot cr = new CubeRoot();
    private WriteData wd = new WriteData();

    public void graph(){
        File file = cf.createFile();
        if(file != null) {
            ArrayList<Double> graphSpecifics = cr.graphSpecifics();
            ArrayList<String> graphData = cr.cubeRootData(graphSpecifics);
            wd.writeData(file, graphData);
        }
    }

}
