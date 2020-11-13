/**
 * 
 */
package SandwichOrder;

import java.text.DecimalFormat;

/**
 * This class represents one line in an order, meant to be stored in the Order class.
 * @author Graham Deubner, Sandeep Alankar
 *
 */
public class OrderLine {	    
	
    private int lineNumber; //a serial number created when a sandwich is added to the order
    private Sandwich sandwich;
    private double price;
    
    /**
     * This parameterized constructor initializes lineNumber and sandwich to the given values.
     * Price is initialized using the sandwich getPrice method.
     * @param lineNumber
     * @param sandwich
     */
    public OrderLine(int lineNumber, Sandwich sandwich) {
        this.lineNumber = lineNumber;
        this.sandwich = sandwich;
        price = sandwich.getPrice();
    }
    
    /**
     * This method returns the lineNumber.
     * @return int Returns the line number for this orderLine.
     */
    public int getLineNumber() {
        return lineNumber;
    }
    
    /**
     * This method sets the lineNumber for this OrderLine object.
     * @param num the number lineNumber is set to.
     * @return returns true if num is a valid value for lineNumber, false otherwise.
     */
    public boolean setLineNumber(int num) {
        if(num > 0) {
            lineNumber = num;
            return true;
        }
        return false;
    }
    
    /**
     * This method returns the sandwich of this OrderLine object.
     * @return Sandwich returns this objects sandwich.
     */
    public Sandwich getSandwich() {
        return sandwich;
    }
    
    /**
     * This method returns the price for this OrderLine.
     * @return double The price to be returned.
     */
    public double getPrice() {
        return price;
    }
    
    /**
     *This method returns the string representation of this OrderLine.
     *@return String Returns the string representation of this object
     */
    @Override
    public String toString() {
    	DecimalFormat df = new DecimalFormat(",##0.00");    	
        return "" + lineNumber + ": " + sandwich.toString() + " Price: $" + df.format(price);
    }
    
}
