/**
 * Use the BBP formula with a double.
 */
package oct12;

public class BBPDouble {

	public static void main(String[] args) {
		
		double pi=0;
		for(int k=0;k<1000;k++){
			pi+=1.0d/Math.pow(16, k)*(4.0d/(8*k+1)-2.0d/(8*k+4)-1.0d/(8*k+5)-1.0d/(8*k+6));
		}
		System.out.println("pi= "+pi);
//		pi= 3.141592653589793

	}

}
