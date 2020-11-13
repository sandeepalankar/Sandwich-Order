/**
 * 
 */
package SandwichOrder;

/**
 * This class represents a beef sandwich.
 * @author Graham Deubner, Sandeep Alankar
 *
 */
public class Beef extends Sandwich {

    private double price = 10.99;
   
    /**
     * This method returns the base price of a Beef Sandwich.
     * @return double, base price of a beef sandwich.
     */
    @Override
    public double price() {
        return price;
    }
    
    /**
     * This method returns a string representation of this sandwich object.
     * @return String Return a String representation of the sandwich
     */
    @Override
    public String toString() {
        return "Beef Sandwich: Roast Beef, Provolone Cheese, Mustard, " + super.toString();
    }

}
