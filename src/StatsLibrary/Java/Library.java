package Java;

public class Library {
    //This is from the first Stats Library I'm using it here as a helper function.
    public double factorial(double userInput){
        double result = 1;
        for (double i = userInput; i > 0; i --){
            result = result * i;
        }
        return result;
    }
    public double poissonDistribution(double lambda, double y){
        double yFactorial = factorial(y);
        double numerator = Math.pow(lambda, y) * Math.exp(-lambda);
        return numerator/yFactorial;
    }

    public double poissonVarianceExpected(double lambda){
        //This isn't much of a function
        return lambda;
    }

    public double tchebysheff(double k){
        return 1-(1/Math.pow(k,2));
    }

    public double uniformProbabilityDistribution(double thetaOne, double thetaTwo){
        return 1/(thetaTwo - thetaOne);
    }

    public double uniformProbabilityDistributionExpected(double thetaOne, double thetaTwo){
        return (thetaTwo + thetaOne)/2;
    }

    public double uniformProbabilityDistributionVariance(double thetaOne, double thetaTwo){
        return Math.pow((thetaTwo - thetaOne),2)/12;
    }

    public double gammaDistributionExpected(double alpha, double beta){
        return alpha * beta;
    }

    public double gammaDistributionVariance(double alpha, double beta){
        return alpha * (Math.pow(beta,2));
    }

    //Very detailed
    public double gammaChiSquareExpected(double v){
        return v;
    }

    public double gammaChiSquareVariance(double v){
        return 2*v;
    }

    public double exponentialDistribution(double bravo, double y){
        return (1/bravo) * Math.exp(-y/bravo);
    }

    public double exponentialExpected(double bravo){
        return bravo;
    }

    public double exponentialVariance(double bravo){
        return Math.pow(bravo,2);
    }


}
