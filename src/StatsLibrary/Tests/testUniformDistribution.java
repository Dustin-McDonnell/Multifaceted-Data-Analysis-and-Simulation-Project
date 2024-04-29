package Tests;

import Java.Library;

public class testUniformDistribution {
    public static void main(String[] args) {
        Library lib = new Library();
        System.out.println("Uniform Probability Distribution f(y): " + lib.uniformProbabilityDistribution(3,7));
        System.out.println("Uniform Probability Distribution Expected: " +lib.uniformProbabilityDistributionExpected(3,7));
        System.out.println("Uniform Probability Distribution Variance: " +lib.uniformProbabilityDistributionVariance(3,7));
    }
}
