package oct12;


public class KahanSum {

    public static void main(String[] args) {

        int n = 100000000;
        float sum;
        float tempSum;
        float calcSum;
        float term;
        float portionOfTermInSum;
        float remainder = 0;
        int i;

        sum = 0;
        calcSum = (float) n * (n + 1) / 2f;
        for (i = 1; i <= n; i++) {
            term = i / calcSum + remainder;
            tempSum = sum + term;					// Small term, large sum
            portionOfTermInSum = tempSum - sum;		// Similar size values, both large
            remainder = term - portionOfTermInSum;	// Similar size values, both small
            sum = tempSum;
            if (i % 1000000 == 0) {
                System.out.printf("%9d, %.6f\n", i, sum);
            }

        } //end for
        System.out.printf("n = %9d, sum = %.6f\n", n, sum);

    } // end main
} // end KahanSum

