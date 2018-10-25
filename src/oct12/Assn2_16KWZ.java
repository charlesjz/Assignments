package oct12;

/*
 * Name: Kelly Weiling Zou
 * Student Number: 20061148
 * NetID: !6KWZ
 */

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Assn2_16KWZ {

    // Used to count iterations
    public static int count;
    // For screen input
    private static Scanner screenInput = new Scanner(System.in);

    // Code your methods here
    
    //Technique 1: Leibniz with Float
    public static float floatPiCalculatorLeibniz() {
		
    	float pi = 0; 
		float lastResult; 
		int n=0;
		int j=1;
		
		do {
			count++;
			lastResult = pi;	
		    pi+=4*j*(1/((float)n*2+1));
		    n++;
		    j=-j; 
		   } 
		
		while (pi != lastResult);
		return pi;
	}
    
    
    // Technique 2: ArcTan with Float
    // Calculating the arctangent value with floats.
 	public static float arcTanFloat(float x) {
 		float arctan = 0.0f;
 		float lastResult;
 		int j = 1;
 		int n = 0;
 		do {
 			lastResult = arctan;
 			arctan+=j*(Math.pow(x, n*2+1)/((float)n*2+1));
 			n += 1;
 			count ++;
 			j=-j;
 		   } while  ( arctan != lastResult);
 		 
 		return arctan;
 	}
    
    //Actually using the Taylor equation to calculate the value of pi.
 	public static float arcTanPiCalculatorFloat() {
 	   	return 4 * (8 * arcTanFloat(1.0f/10) 
     			- 4 * arcTanFloat(1.0f/515) 
     			- arcTanFloat(1.0f/239));
 	}
    
 	
 	// Technique 3: ArcTan with Double
    // Calculating the arctangent value with doubles.
	public static double arcTanDouble(double x) {
		double arctan=0.0d;
		double lastResult;
		int j = 1;
		int n = 0;
		do {
			lastResult = arctan;
			arctan+=j*(Math.pow(x, n*2+1)/((double)n*2+1));
			n += 1;
			count ++;
			j=-j;
		   } while  ( arctan != lastResult);
		 
		return arctan;

	}

	// Using the Taylor equation fo calculate the value of pi with double.
	public static double arcTanPiCalculatorDouble() {
		return 4 * (8 * arcTanDouble(1.0/10) 
    			- 4 * arcTanDouble(1.0/515) 
    			- arcTanDouble(1.0/239));
  	}
 	
	//Technique 4: Using the Ramanujan Sum
	// Using the Ramanujan Sum to calculate the arctan.
	// The technique requires compiling all positive terms separately from the negative terms, then finding their difference in conclusion.
	public static double arcTanGeneralDoubleModified(double x) {
		int n=0;
		double arctan=0;
		double lastResult;
		
		do {
			count++;
			lastResult = arctan;
			arctan+=Math.pow(-1, n)/(2*n+1)*Math.pow(x, 2*n+1);
			n++;
		} while(arctan!=lastResult);
		
		return arctan;
	}

	public static double arcTanPiCalculatorDoubleModified() {
    	return 4 * (8 * arcTanGeneralDoubleModified(1.0/10) 
    			- 4 * arcTanGeneralDoubleModified(1.0/515) 
    			- arcTanGeneralDoubleModified(1.0/239));
    }
	
	
	// Technique 5: BPP Formula with a Double
	private static double piCalculatorBBP() {
		double pi=0;
		double lastResult;
		int k=0;

			do {
				count++;
				lastResult=pi;
				
				pi += 1.0d / Math.pow(16, k)
						* (4.0d / (8 * k + 1) - 2.0d / (8 * k + 4) - 1.0d / (8 * k + 5) - 1.0d / (8 * k + 6));
				k++;

			} while (lastResult != pi);
			
		return pi;
	}

	
	// Technique 6 & 7
	@SuppressWarnings("deprecation")
	private static BigDecimal piCalculatorBBPBig(int scale) {
		BigDecimal pi = BigDecimal.ZERO;
		BigDecimal pow16k = BigDecimal.ONE;
		BigDecimal eightK = BigDecimal.ONE;

		int roundingMode=BigDecimal.ROUND_HALF_EVEN;
		
		int k=0;
		BigDecimal lastResult;
			do {
				count++;
				lastResult = pi;

				pow16k = BigDecimal.ONE;
				eightK = BigDecimal.ONE;
				pow16k = BigDecimal.valueOf(16).pow(k);
				eightK = BigDecimal.valueOf(8).multiply(BigDecimal.valueOf(k));
				pi = pi.add(BigDecimal.ONE.divide(pow16k, scale, roundingMode).multiply((

				BigDecimal.valueOf(4).divide(eightK.add(BigDecimal.ONE), scale, roundingMode)
						.subtract(BigDecimal.valueOf(2).divide(eightK.add(BigDecimal.valueOf(4)), scale, roundingMode))
						.subtract(BigDecimal.valueOf(1).divide(eightK.add(BigDecimal.valueOf(5)), scale, roundingMode))
						.subtract(BigDecimal.valueOf(1).divide(eightK.add(BigDecimal.valueOf(6)), scale,
								roundingMode)))));
				k++;
			} while (pi.compareTo(lastResult)!=0);
		return pi;
	}
	
    
    // Aids in displaying BigDecimal numbers to the screen using 100
    // digits per line.
    public static void displayResult(BigDecimal aNum) {
    	var asString = aNum.toString(); 
    	for(int i = 0; i < asString.length(); i++) {
    		System.out.print(asString.charAt(i));
    		if (i > 0 && (i + 1) % 100 == 0)
    			System.out.println();
    	}
    	System.out.println();
    } // end displayResult
    
    // Simplifies reporting the execution time and the number of iterations
    public static void timeIterationsReport(long start) {
        long finishTime = System.nanoTime();
        long diff = finishTime - start;
        if (diff <= 1e3)
            System.out.print("Took " + diff + " nanosec., ");
        else if (diff <= 1e6)
            System.out.print("Took " + Math.round(diff / 10.0) / 100.0 + " microsec. ");
        else if (diff <= 1e9)
        	System.out.print("Took " + Math.round(diff / 1e4) / 100.0 + " millisec. ");
        else
        	System.out.print("Took " + Math.round(diff / 1e7) / 100.0 + " sec. ");
        System.out.println("and required " + count + " iterations.");
        count = 0;
    } // end timeReport
    
    // Used to calculate and display the accuracy of a 16 digit result using the value of
    // pi stored in the Math class.
    public static void accuracyReport(double estimate) {
    	var error = 100 * (estimate - Math.PI) / Math.PI;
    	System.out.printf("Error is %.2e percent.\n\n", error);
    } // end accuracyReport

    // Copied from IOHelper (only method needed)
    public static int getInt(int low, String prompt, int high) {
        int numFromUser = 0;
        String dummy;
        boolean numericEntryOK;
        do {
            System.out.print(prompt);
            numericEntryOK = false;
            try {
                numFromUser = screenInput.nextInt();
                numericEntryOK = true;
            } catch (InputMismatchException e) {
                dummy = screenInput.nextLine();
                System.out.println(dummy + " is not an integer!");
                numFromUser = low;
            } // end try-catch
            // Indicate to the user why he is being prompted again.
            if (numFromUser < low || numFromUser > high) {
                System.out.println("The number is outside the legal limits.");
            }
        } while (!numericEntryOK || numFromUser < low || numFromUser > high);
        return numFromUser;
    } // end full parameter getInt method

    // This supplied main method uses assumed method names that you may certainly change.
    public static void main(String[] args) {

        long startTime;
        double estimate;
        int numDigitsDesired;

        System.out.printf("Math.PI is:\n%.16f\n\n", Math.PI);
        
        startTime = System.nanoTime();
        estimate = floatPiCalculatorLeibniz();
        System.out.printf("%.16f - using Leibniz formula with float.\n", estimate);
        timeIterationsReport(startTime);
        accuracyReport(estimate);
        
        startTime = System.nanoTime();
        estimate = arcTanPiCalculatorFloat();
        System.out.printf("%.16f - using arcTan formula with float.\n", estimate);
        timeIterationsReport(startTime);
        accuracyReport(estimate);

        startTime = System.nanoTime();
        estimate = arcTanPiCalculatorDouble();
        System.out.printf("%.16f - using arcTan formula with double.\n", estimate);
        timeIterationsReport(startTime);
        accuracyReport(estimate);

        startTime = System.nanoTime();
        estimate = arcTanPiCalculatorDoubleModified();
        System.out.printf("%.16f - using Modified arcTan formula with double.\n", estimate);
        timeIterationsReport(startTime);
        accuracyReport(estimate);

        startTime = System.nanoTime();
        estimate = piCalculatorBBP();
        System.out.printf("%.16f - using BBP formula with double.\n", estimate);
        timeIterationsReport(startTime);
        accuracyReport(estimate);

        startTime = System.nanoTime();
        System.out.println("Using BBP formula with BigDecimals for 16 digits:");
        var estimateBigD = piCalculatorBBPBig(16);
        System.out.println(estimateBigD);
        timeIterationsReport(startTime);
        accuracyReport(estimateBigD.doubleValue());

        startTime = System.nanoTime();
        System.out.println("Using BBP formula with BigDecimals for 100 digits:");
        System.out.println(piCalculatorBBPBig(100));
        timeIterationsReport(startTime);
        System.out.println();

        numDigitsDesired = getInt(1000, "How many digits do you want to try for? ", 10000);
        
        startTime = System.nanoTime();
        System.out.println("Using BBP formula with BigDecimals for " + numDigitsDesired + " digits:");
        displayResult(piCalculatorBBPBig(numDigitsDesired));
        timeIterationsReport(startTime);
        
        screenInput.close();

    } // end main
    
} // end Assignment2Start


/*
 * 1. 
 * Math.PI is:
3.1415926535897930

3.1415967941284180 - using Leibniz formula with float.
Took 200.31 millisec. and required 16777217 iterations.
Error is 1.32e-04 percent.

3.1415927410125732 - using arcTan formula with float.
Took 88.84 microsec. and required 11 iterations.
Error is 2.78e-06 percent.

3.1415926535897927 - using arcTan formula with double.
Took 76.83 microsec. and required 18 iterations.
Error is -1.41e-14 percent.

3.1415926535897927 - using Modified arcTan formula with double.
Took 176.28 microsec. and required 18 iterations.
Error is -1.41e-14 percent.

3.1415926535897930 - using BBP formula with double.
Took 634.16 microsec. and required 12 iterations.
Error is 0.00e+00 percent.

Using BBP formula with BigDecimals for 16 digits:
3.14159265358979320317595567556788
Took 2.61 millisec. and required 15 iterations.
Error is 0.00e+00 percent.

Using BBP formula with BigDecimals for 100 digits:
3.14159265358979323846264338327950288419716939937510582097494459230781640628620899862803482534211706794715819091680081975291169746753947024414324665724419487001027458418144244366469909721753069771172363
Took 15.71 millisec. and required 85 iterations.

How many digits do you want to try for? 1001
Using BBP formula with BigDecimals for 1001 digits:
3.14159265358979323846264338327950288419716939937510582097494459230781640628620899862803482534211706
7982148086513282306647093844609550582231725359408128481117450284102701938521105559644622948954930381
9644288109756659334461284756482337867831652712019091456485669234603486104543266482133936072602491412
7372458700660631558817488152092096282925409171536436789259036001133053054882046652138414695194151160
9433057270365759591953092186117381932611793105118548074462379962749567351885752724891227938183011949
1298336733624406566430860213949463952247371907021798609437027705392171762931767523846748184676694051
3200056812714526356082778577134275778960917363717872146844090122495343014654958537105079227968925892
3542019956112129021960864034418159813629774771309960518707211349999998372978049951059731732816096318
5950244594553469083026425223082533446850352619311881710100031378387528865875332083814206171776691473
0359825349042875546873115956286388235378759375195778185778053217122680661300192787661119590921642019
8934408262900974344653713804301451773892655128217089484590125936969895081121817585889620621836041661
7992191646247527104131201864137034234044419707481548336588080762877240330400983779973872538922316383
8505653512845487771984352293491653799234037536776768957717632086351535122704466070220063621389137454
2172961302124014618364903141927780656949093416361619221203845052725049856075316900496509627414372787
4902699797530290100223818933928297844688047889404641873758305805150454875474835336762716157898330735
8782355526982141282573654900094731973279092379613905036472368642860850581279097641603556139573391632
2438719217297271889841581430398699471708172086012652598759901801701476815321160156613629095932594540
0493653377707632248525818445910738516283271220963221752842512239148647662245823064299534802103243077
0892743926106347322386886994897258829552690134052438291030195755299496481737880145380112743286478299
4112323002365523096659027266919675727644244967959063313265527788734944932313991139931689454949360895
9108
Took 132.19 millisec. and required 833 iterations.

 * 2. In my opinion,  believe that technique 2 is far superior to technique 1 for calculating pi. 
 *     From examining the time taken to calculate it, the number of iterations, and the level of error, we see the following:
 *     Technique 2 requires significantly less time to calculate pi as there are less iterations.
 *     Additionally, as there are less iterations, calculating error is much lower due to minimized rounding errors.
 *     
 * 3. With a float, it shortens the number to 6 digits after the decimal point.
 * Using a double means that it would result in around 14 r 15 digits after the decimal point.
 * Using a double would mean that the summation would calculate for an infinite amount of time and thus, exceed Java's storage space.
 * 
 * 4. When comparing between techniques 2 and 3, I think technique 3 is comparatively better for calculating pi.
 * Technique 3 took less time to iterate though it iterated more frequently. 
 * Additionally, the error is significantly less, meaning that technique 3 is more accurate.
 * This is probably as technique 3 uses double which is capable of storing more data, meaning less inaccurate data.
 * 
 * 5. From my results, I can see that the Ramanujan Sum in my modified technique did not improve the accuracy.
 * Though the percentage error is the same and required the same amount of iterations, we can see that it took longer to calculate.
 * Thus, it is apparent that it was not successful in improving the accuracy.
 * 
 * 6. Technique 5 is clearly better than technique 3 with regards to its accuracy.
 * I personally value accuracy above all else and technique 3 has a 0% error. 
 * Additionally, it does not take significantly more iterations, though more time is required.
 * 
 * 7. The use of the big decimal was very good in the sense that it can calculate pi pi perfectly to an infinite amount of digits.
 * The use of the other data types would cut off the values are the decimal point and thus produce rounding errors which become more significant overtime.
 */
