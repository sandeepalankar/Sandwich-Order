/**
 * 
 */
package SandwichOrder;

import java.util.ArrayList;

/**
 * @author gdeub
 *
 */
public abstract class Sandwich implements Customizable {

    static final int MAX_EXTRAS = 6;
    static final double PER_EXTRA = 1.99;
    protected ArrayList<Extra> extras;
    public abstract double price();
    public String toString() { return null; //temporary
    }
    
    @Override
    public boolean add(Object obj) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean remove(Object obj) {
        // TODO Auto-generated method stub
        return false;
    }

}
