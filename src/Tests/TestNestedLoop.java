package Tests;

public class TestNestedLoop {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++){
            System.out.println("I: " + i);
            for(int k = 0; k < 5; k++){
                System.out.println("K: " + k);
            }
        }
    }
}
