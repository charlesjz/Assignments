package Kelly_Assignment_3;

/*
 * Name: Kelly Weiling Zou
 * NetID: 16KWZ
 * Student Number: 20061148
 */


import java.io.Serializable;

public class Pizza implements Serializable {

	private static final long serialVersionUID = 1495366373184045874L;
	private String pizzaSize;
	private String pizzaCheese;
	private String pizzaPineapple;
	private String pizzaHam;
	private String pizzaPeppers;
	
	/**
     * Pizza Constructor - Full parameter constructor.
     * @param pizzaSize - The size of the Pizza.
     * @param pizzaCheese - The amount of cheese on the pizza.
     * @param pizzaPineapple - Whether or not there is pineapple on the pizza.
     * @param pizzaHam - Whether or not there is ham on the pizza.
     * @param pizzaPeppers - Whether or not there are peppers on the pizza.
     * @throws IllegalPizza - Used for when the arguments are not legal.
     * One thing to note is that peppers and pineapples cannot be toppings if there isn't already ham as a topping.
     */
	
	// 5 parameter constructor that invokes mutators
	public Pizza(String pizzaSize, String pizzaCheese, String pizzaPineapple, String pizzaHam, String pizzaPeppers) throws IllegalPizza {
		if(pizzaSize==null || pizzaCheese==null|| pizzaPineapple==null ||pizzaHam==null||pizzaPeppers==null ) {
			throw new IllegalPizza("There was a null value!");
		}
		setSize(pizzaSize.toLowerCase());
		setCheese(pizzaCheese.toLowerCase());
		setPineapple(pizzaPineapple.toLowerCase());
		setHam(pizzaHam.toLowerCase());
		setPeppers(pizzaPeppers.toLowerCase());
		
		// This works to identify the condition that peppers and pineapple cannot be toppings if there isn't already ham as a topping.
		if (this.pizzaHam.equalsIgnoreCase("none") && 
				(!this.pizzaPeppers.equalsIgnoreCase("none") || !this.pizzaPineapple.equalsIgnoreCase("none")))
			throw new IllegalPizza("You cannot have a pizza with pineapple and/or peppers if there is no ham! \n Please account for taste.");
	}
	
	
	// This is the default constructor.
	public Pizza() throws IllegalPizza {
		this("small", "single", "none", "single", "none");
	}
	
	// The prices of all the pizza size options and the toppings.
	private float smallCheese = 7.00F;
	private float mediumCheese = 9.00F;
	private float largeCheese = 11.00F;
	private float perTopping = 1.50F;
	
	// This goes through the size of the pizza and calculates part of the price depending on that.
	private float sizePrice(String pizzaSize) {
		if (pizzaSize.equalsIgnoreCase("small"))
			return smallCheese;
		if (pizzaSize.equalsIgnoreCase("medium"))
			return mediumCheese;
		if (pizzaSize.equalsIgnoreCase("large"))
			return largeCheese;
		return 0;
	}
	
	// This goes through the amount of toppings present for each type of topping and returns an integer value.
	private int toppingPrice(String toppingAmount) {
		if (toppingAmount.equalsIgnoreCase("none"))
			return 0;
		if (toppingAmount.equalsIgnoreCase("single"))
			return 1;
		if (toppingAmount.equalsIgnoreCase("double"))
			return 2;
		if (toppingAmount.equalsIgnoreCase("triple"))
			return 3;
		return 0;
	}
	
	// Calculating the cost by accounting for the price derived from size of the pizza then from the amount of toppings present.
	public float getCost() {
		float sizeBased = sizePrice(pizzaSize);
		float totalPrice = sizeBased + ((toppingPrice(pizzaCheese)-1) + toppingPrice(pizzaPineapple) + toppingPrice(pizzaHam) + toppingPrice(pizzaPeppers)) * perTopping;
		// A note here that I am assuming that the price of the pizza accounts for having a single layer of cheese.
		return totalPrice;
	}
	
	/**
     * Sets the size of the pizza.
     * @param size is referring to whether the pizza is a small, medium, or large.
     * @throws IllegalPizza is if the input of the pizza is not small, medium, or large.
     */
	private void setSize(String pizzaSize) throws IllegalPizza {
		if (pizzaSize.equalsIgnoreCase("small") || pizzaSize.equalsIgnoreCase("medium") || pizzaSize.equalsIgnoreCase("large"))
			this.pizzaSize = pizzaSize;
		else
			throw new IllegalPizza("That was not one of the three size options!");	
	}
	
	public String getSize() {
		return pizzaSize;
	}


	public void setPizzaSize(String pizzaSize) {
		this.pizzaSize = pizzaSize;
	}


	public String getCheese() {
		return pizzaCheese;
	}


	public void setPizzaCheese(String pizzaCheese) {
		this.pizzaCheese = pizzaCheese;
	}


	public String getPineapple() {
		return pizzaPineapple;
	}


	public void setPizzaPineapple(String pizzaPineapple) {
		this.pizzaPineapple = pizzaPineapple;
	}


	public String getHam() {
		return pizzaHam;
	}


	public void setPizzaHam(String pizzaHam) {
		this.pizzaHam = pizzaHam;
	}


	public String getPeppers() {
		return pizzaPeppers;
	}


	public void setPizzaPeppers(String pizzaPeppers) {
		this.pizzaPeppers = pizzaPeppers;
	}


	/**
     * Refers to the amount of cheese on the pizza.
     * @param cheese is the three options for the amount of cheese are "single", "double", or "triple".
     * @throws IllegalPizza is if the cheese selection is not one of the three available.
     */
	private void setCheese(String pizzaCheese) throws IllegalPizza{
		if (pizzaCheese.equalsIgnoreCase("single") || pizzaCheese.equalsIgnoreCase("double") || pizzaCheese.equalsIgnoreCase("triple"))
			this.pizzaCheese = pizzaCheese;
		else
			throw new IllegalPizza("That was not one of the three cheese quantity options!");
	}
	
	/**
     * Refers to whether or not pineapple is on the pizza.
     * @param pineapple is referring to whether there is "none" (no) pineapple on the pizza or if there is a "single" serving.
     * @throws IllegalPizza is if the pineapple selection is not one of the two available.
     */
	private void setPineapple(String pizzaPineapple) throws IllegalPizza {
		if (pizzaPineapple.equalsIgnoreCase("none") || pizzaPineapple.equalsIgnoreCase("single"))
			this.pizzaPineapple = pizzaPineapple;
		else
			throw new IllegalPizza("You can either have a single serving or no pineapple on your pizza, your option was invalid!");	
	}
	
	/**
     * Refers to whether or not ham is on the pizza.
     * @param ham is referring to whether there is "none" (no) ham on the pizza or if there is a "single" serving.
     * @throws IllegalPizza is if the ham selection is not one of the two available.
     */
	private void setHam(String pizzaHam) throws IllegalPizza {
		if (pizzaHam.equalsIgnoreCase("none") || pizzaHam.equalsIgnoreCase("single"))
			this.pizzaHam = pizzaHam;
		else
			throw new IllegalPizza("You can either have a single serving or no ham on your pizza, your option was invalid!");	
	}
	
	/**
     * Refers to whether or not peppers are on the pizza.
     * @param peppers is referring to whether there is "none" (no) peppers on the pizza or if there is a "single" serving.
     * @throws IllegalPizza is if the peppers selection is not one of the two available.
     */
	private void setPeppers(String pizzaPeppers) throws IllegalPizza {
		if (pizzaPeppers.equalsIgnoreCase("none") || pizzaPeppers.equalsIgnoreCase("single"))
			this.pizzaPeppers = pizzaPeppers;
		else
			throw new IllegalPizza("You can either have a single serving or no peppers on your pizza, your option was invalid!");	
	}
	
	/**
     * A String representation of the pizza object.
     * @return A String representation of the contents of Pizza containing the values of all the attributes.
     * The stringNOne checks if the topping input is none, and states accordingly.
     */
	
	private String stringNone(String toppingAmount) {
		if (toppingAmount.equals("none"))
			return "no";
		return toppingAmount;
	}
	

	public String toString() {
		String printedMessage1 = pizzaSize + " pizza, " + pizzaCheese + " cheese, ";
		String printedMessage2="";
		printedMessage2 += (pizzaPineapple + " pineapple, ");
		printedMessage2 += (pizzaPeppers + " peppers, ");
		printedMessage2 += (pizzaHam + " ham, ");
		printedMessage2=printedMessage2.replace("none pineapple, ", "");
		printedMessage2=printedMessage2.replace("none peppers, ", "");
		printedMessage2=printedMessage2.replace("none ham, ", "");
		printedMessage2=printedMessage2.replaceAll("single ", "");
		printedMessage1 += printedMessage2;
		printedMessage1 = printedMessage1.substring(0, printedMessage1.lastIndexOf(","))+".";
		return printedMessage1 += String.format(" Cost: $%.2f each.", getCost());
	}

	/**
     * Tests two Pizza objects for equality.
     * @return <code>true</code> if all the attributes of both objects are exactly equal, <code>false</code>
     * otherwise.
     * @param otherObject The other Pizza object.
     */
	 public boolean equals(Object otherObject) {
		 if (otherObject instanceof Pizza) {
			 Pizza otherP = (Pizza)otherObject;
	         return (pizzaSize.equalsIgnoreCase(otherP.pizzaSize) && pizzaCheese.equalsIgnoreCase(otherP.pizzaCheese) && 
	        		 	pizzaPineapple.equalsIgnoreCase(otherP.pizzaPineapple) && pizzaHam.equalsIgnoreCase(otherP.pizzaHam)
	            		&& pizzaPeppers.equalsIgnoreCase(otherP.pizzaPeppers));
		 }
	     return false;
	 }
	 
	 /**
	 * Returns a copy of the of the current Pizza object.
	 * @return A copy of the current object.
	 */
	 public Pizza cloneMethod() {
		 Pizza pCopy = null;
		 try {
			 pCopy = new Pizza(pizzaSize, pizzaCheese, pizzaPineapple, pizzaHam, pizzaPeppers);
		 } catch (IllegalPizza e) {
	     }
		 return pCopy;	 
	 }
}
