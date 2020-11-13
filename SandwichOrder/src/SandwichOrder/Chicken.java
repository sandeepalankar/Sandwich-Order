/**
 * 
 */
package SandwichOrder;

/**
 * This class represents a chicken sandwich.
 * @author Graham Deubner, Sandeep Alankar
 *
 */
public class Chicken extends Sandwich {
    private double price = 8.99;   
    
    /**
     * This method returns the base price of a chicken sandwich
     * @return double Returns a price of a chicken sandwich.
     */
    @Override
    public double price() {
        return price;
    }
    
    /**
     * This method returns the String representation of the sandwich object.
     * @return String Returns the string representation of the sandwich.
     */
    @Override
    public String toString() {
        return "Chicken Sandwich: Fried Chicken, Spicy Sauce, Pickles, " + super.toString();
    }

}
