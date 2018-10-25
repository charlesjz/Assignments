package Kelly_Assignment_3;

/*
 * Name: Kelly Weiling Zou
 * NetID: 16KWZ
 * Student Number: 20061148
 */

import java.io.Serializable;
import java.text.DecimalFormat;

public class LineItem implements Serializable, Comparable<LineItem> {

	private static final long serialVersionUID = -5090767330478541647L;
	
	private Pizza pizzaObject;
	public Pizza getPizza() {
		return pizzaObject;
	}

	public void setPizza(Pizza pizzaObject) {
		this.pizzaObject = pizzaObject;
	}

	public int getNumber() {
		return pizzaNumber;
	}

	public void setNumber(int pizzaNumber) throws IllegalPizza{
		if (pizzaNumber > 100 || pizzaNumber < 1 ) {
			throw new IllegalPizza("You ordered an invalid number of pizzas. Please choose between a minimum of 1 and a maximum of 100.");
		} else

		this.pizzaNumber = pizzaNumber;
	}

	private int pizzaNumber;
	
	private static DecimalFormat twoDForm = new DecimalFormat("#.00");
	
	/**
	 * @param pizzaNumber - The amount of pizzas ordered, limited in the range between 1 to 100.
	 * @param pizzaOrdered - The specific pizza ordered.
	 * @throws IllegalPizza - Used for when the entered number or input isn't valid and within the range.
	 */
	public LineItem(int pizzaNumber, Pizza pizzaOrder) throws IllegalPizza {
		if (pizzaNumber > 100 || pizzaNumber < 1 || pizzaOrder == null) {
			throw new IllegalPizza("You ordered an invalid number of pizzas. Please choose between a minimum of 1 and a maximum of 100.");
		} else
			this.pizzaNumber = pizzaNumber;
			this.pizzaObject = pizzaOrder;
	}
	
	// This is the second default constructor.
	public LineItem(Pizza pizzaOrder) throws IllegalPizza {
		if (pizzaOrder == null) {
			throw new IllegalPizza("You ordered an invalid number of pizzas. Please choose between a minimum of 1 and a maximum of 100.");
		} else
			this.pizzaNumber = 1;
			this.pizzaObject = pizzaOrder;
	}
	
	// Feeds into the getCost function to determine what the order is.
	public Pizza getOrder() {
		return pizzaObject;
	}

	// Calculates the quantity.
	public int getQuantity() {
		return pizzaNumber;
	}
	
	// Cost function calculate the discounted price based on the amount of pizzas ordered.
	public double getCost() {
		double totalCost = getQuantity() * getOrder().getCost();
		
		if (getQuantity() >= 10 && getQuantity() <= 20)
			totalCost = totalCost * 0.9;
		if (getQuantity() > 20)
			totalCost = totalCost * 0.85;
		return totalCost;
	}
	
	/**
     * A String representation of the final output.
     * @return A String representation of the contents of Pizza containing the values of all the attributes.
     */
	public String toString() {
		String ret= String.format("%2s", getQuantity()) + " " + getOrder().getSize() + " pizza, " + getOrder().getCheese()
				+ " cheese, " + getOrder().getPineapple() + " pineapples, " + getOrder().getPeppers() + " peppers, "
				+ getOrder().getHam() + " ham. "+"Cost: $" + twoDForm.format(getOrder().getCost()) + " each.";
		ret = ret.replace("single pineapples", "pineapple");
		ret = ret.replace("single peppers", "peppers");
		ret = ret.replace("single ham", "ham");
		
		return ret;
	}
	
	//Checks the difference between the total cost of the item. 
	public int compareTo(LineItem otherP) {
		int comparePizza = 0;

		if ((Math.abs(getCost() - otherP.getCost())) < 1)
			comparePizza = 0;
		if (getCost() > otherP.getCost())
			comparePizza = -1;
		if (getCost() < otherP.getCost())
			comparePizza = 1;
		return comparePizza;
	}

	
} 
