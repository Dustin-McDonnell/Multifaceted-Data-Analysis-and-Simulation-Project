package Tests;

import Java.Library;
import org.jfree.data.json.JSONUtils;

public class testExponentialDistribution {
    public static void main(String[] args) {
        Library lib = new Library();
        System.out.println("Exponential Distribution: " + lib.exponentialDistribution(4,9));
        System.out.println("Exponential Distribution expected: " + lib.exponentialExpected(4));
        System.out.println("Exponential Distribution variance: " + lib.exponentialVariance(4));
    }
}
