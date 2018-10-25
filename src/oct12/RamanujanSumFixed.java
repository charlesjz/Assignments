package oct12;


public class RamanujanSumFixed {

    public static void main(String[] args) {

        int k = 0;
        double negSum = 0;
        double posSum = 0;
        double prevNegSum;
        double prevPosSum;
        double kfactorial = 1;
        int sign = 1;
        int twoKPlusOne;
        int twoKPlusOneSquared;
        int twoKPlusOneCubed;
        double term;

        do {
            prevNegSum = negSum;
            prevPosSum = posSum;
            twoKPlusOne = 2 * k + 1;
            twoKPlusOneSquared = twoKPlusOne * twoKPlusOne;
            twoKPlusOneCubed = twoKPlusOneSquared * twoKPlusOne;
            if (k > 0) {
                kfactorial *= (double) k;
            }
            term = (twoKPlusOneSquared + twoKPlusOneCubed) / kfactorial;
            if (sign > 0) {
                posSum += term;
            } else {
                negSum += term;
            }
            sign = -sign;
            k++;
            System.out.println(-negSum + " and " + posSum);
        } while (!(negSum == prevNegSum && posSum == prevPosSum));

        System.out.println("\nSummation result = " + (posSum - negSum));

    } // end main
} // end RamanujanSumFixed

