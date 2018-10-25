package Kelly_Assignment_3;

/*
 * Name: Kelly Weiling Zou
 * NetID: 16KWZ
 * Student Number: 20061148
 */

public class IllegalPizza extends Exception {

	private static final long serialVersionUID = 4593713417540203652L;

	/**
	 * Passes along the message supplied to the exception.
	 * @param message A more specific message.
	 */
	
	public IllegalPizza (String message) {
		super(message);
	} 
	
}
