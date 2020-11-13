/**
 * 
 */
package SandwichOrder;

/**
 * This class represents a fish sandwich.
 * @author Graham Deubner, Sandeep Alankar
 *
 */
public class Fish extends Sandwich {
    
    private double price = 12.99;
    
    /**
     * This method returns the price of this sandwich.
     * @return double Returns the price of a fish sandwich.
     */
    @Override
    public double price() {
        return price;
    }
    
    /**
     * This method returns a string containing the sandwich name, basic ingredients, and extras.
     * @return String Returns the string representation of a fish sandwich.
     */
    @Override
    public String toString() {
        return "Fish Sandwich: Grilled Snapper, Cilantro, Lime, " + super.toString();
    }

}
