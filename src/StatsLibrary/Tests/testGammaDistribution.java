package Tests;

import Java.Library;

public class testGammaDistribution {
    public static void main(String[] args) {
        Library lib = new Library();
        System.out.println("Gamma distribution expected: " + lib.gammaDistributionExpected(4,9));
        System.out.println("Gamma distribution variance: " + lib.gammaDistributionVariance(4, 9));
        System.out.println("Gamma distribution chi expected: " + lib.gammaChiSquareExpected(4));
        System.out.println("Gamma distribution chi variance: " + lib.gammaChiSquareVariance(4));
    }
}
