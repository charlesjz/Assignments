package oct12;


public class FiddledArithmeticSum {

    public static void main(String[] args) {

        float sum;
        float calcSum;
        int i;

        for (int n = 10; n <= 100000000; n *= 10) {
            sum = 0;
            calcSum = (float) n * (n + 1) / 2f;
            for (i = 1; i <= n; i++) {
                sum = sum + i / calcSum;
            }
            System.out.printf("n = %9d, sum = %.6f\n", n, sum);
        } // end outer loop

    } // end main
    
} // end FiddledArithmeticSum

