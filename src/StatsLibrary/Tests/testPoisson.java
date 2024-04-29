package Tests;

import Java.Library;

public class testPoisson {

    public static void main(String[] args) {
        Library lib = new Library();
        //Expected Answer .156
        System.out.println("Poisson Answer Lambda 4 Trials 5: " + lib.poissonDistribution(4,5));
        System.out.println("Poisson variance and expected: " + lib.poissonVarianceExpected(4));
    }
}
