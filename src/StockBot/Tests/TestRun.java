package Tests;

import Java.FilePickerPart3;
import Java.ManipulateDataPart3;
import Java.StockBot;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class TestRun {
    public static void main(String[] args) throws IOException {
        FilePickerPart3 fp = new FilePickerPart3();
        ManipulateDataPart3 md = new ManipulateDataPart3();
        StockBot sb = new StockBot();
        File file = fp.filePicker();
        ArrayList<ArrayList<String>> data = fp.csvReader(file);
        ArrayList<String> qrt = sb.stockBot(data);
        for (int i = 0; i < qrt.size(); i ++){
            System.out.println(qrt.get(i));
        }
    }
}
