package oct12;


public class RamanujanSum {

    public static void main(String[] args) {

        int k = 0;
        double sum = 0;
        double prevSum;
        double kfactorial = 1;
        int sign = 1;
        int twoKPlusOne;
        int twoKPlusOneSquared;
        int twoKPlusOneCubed;
        double term;

        do {
            prevSum = sum;
            twoKPlusOne = 2 * k + 1;
            twoKPlusOneSquared = twoKPlusOne * twoKPlusOne;
            twoKPlusOneCubed = twoKPlusOneSquared * twoKPlusOne;
            if (k > 0) {
                kfactorial *= (double) k;
            }
            term = (twoKPlusOneSquared + twoKPlusOneCubed) / kfactorial;
            sum += sign * term;
            sign = -sign;
            k++;
            System.out.println(sum);
        } while (sum != prevSum);

    } // end main
} // end RamanujanSum

