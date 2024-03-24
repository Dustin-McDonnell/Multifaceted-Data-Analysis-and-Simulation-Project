import java.io.File;

public class TestFilePicker {
    public static void main(String[] args){
        FilePicker fp = new FilePicker();
        File file = fp.filePicker();
        System.out.println(file);
    }
}
