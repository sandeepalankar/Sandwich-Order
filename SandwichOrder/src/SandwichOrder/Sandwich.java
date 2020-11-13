/**
 * 
 */
package SandwichOrder;

import java.util.ArrayList;

/**
 * This class represents a sandwich.
 * @author Graham Deubner, Sandeep Alankar
 *
 */
public abstract class Sandwich implements Customizable {

	static final int MAX_EXTRAS = 6;
	static final double PER_EXTRA = 1.99;
	protected ArrayList<Extra> extras;     

	/**
	 * This method returns the constant MAX_EXTRAS.
	 */
	public int getMaxExtras() {
		return MAX_EXTRAS;
	}

	/**
	 * This method returns the constant PER_EXTRA
	 */
	public double getPerExtra() {
		return PER_EXTRA;
	}

	/**
	 * This default constructor initializes extras.
	 */
	public Sandwich() {
		extras = new ArrayList<Extra>(0);
	}

	/**
	 * This abstract method gets implemented in all of the child classes, returning the base price
	 * of the specific child sandwich.
	 * @return base price of sandwich
	 */
	public abstract double price();   
	
	/**
	 *This method returns the string representation of this sandwich.
	 *@return returns the string representation of this sandwich.
	 */
	@Override
	public String toString() { 
		String extrasString = "";
		if(extras.size()>0) {
			extrasString += "Extras: ";
			for(int i = 0; i < extras.size(); i++) {
				extrasString += extras.get(i);
				if (i != extras.size()-1)
					extrasString += ", ";
			}
		}
		return extrasString; 
	}

	/**
	 *This method adds an extra to this sandwich's extra ArrayList.
	 *@return boolean Returns true on success, false otherwise.
	 */
	@Override
	public boolean add(Object obj) {
		if(extras.size() < MAX_EXTRAS) {
			return extras.add((Extra)obj);
		}
		else
			return false;
	}

	/**
	 *This method removes an extra from this sandwich's extra ArrayList.
	 *@return boolean Returns true on success, false otherwise.
	 */
	@Override
	public boolean remove(Object obj) {
		return extras.remove((Extra) obj);
	}

	/**
	 * This method calculates the total price of this sandwich, including the cost of extras.
	 * @return double, price of the sandwich.
	 */
	public double getPrice() {
		double total = price();
		for (int i = 0; i < extras.size(); i++) {
			total += PER_EXTRA;
		}
		return total;
	}
}
