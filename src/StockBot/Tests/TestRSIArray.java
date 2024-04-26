package Tests;

import Java.FilePickerPart3;
import Java.ManipulateDataPart3;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class TestRSIArray {
    public static void main(String[] args) throws IOException {
        ManipulateDataPart3 md = new ManipulateDataPart3();
        FilePickerPart3 fp = new FilePickerPart3();
        ArrayList<ArrayList<String>> ahh = md.smoothData();
        System.out.println(ahh.size());
        Integer n = md.chooseN();
        ArrayList<String> kill = md.calculateRSI(ahh,n);
        System.out.println(kill);
        md.totalCSV(ahh,kill,n);
    }
}
