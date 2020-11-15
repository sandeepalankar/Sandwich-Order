package SandwichOrder;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * This class defines the elements in an order and allows for multiple sandwiches to be added
 * into the same order. It also includes the functionality to export an order.
 * @author Graham Deubner, Sandeep Alankar
 *
 */
public class Order implements Customizable {
    
    public static int lineNumber; //reset for each new order;
    private ArrayList<OrderLine> orderlines;
    
    /**
     * Default constructor for the Order class. Sets lineNumber to 1 and initializes orderLines.
     */
    public Order() {
        orderlines = new ArrayList<OrderLine>();
        lineNumber = 1;
    }
    
    /**
     *This method adds a given orderLine object to orderLines
     *@param obj The orderLine object to be removed
     *@return returns true on success, false on failure.
     */
    @Override
    public boolean add(Object obj) {
    	lineNumber++;
        boolean success = orderlines.add((OrderLine)obj);
        if(success) {            
            return true;
        }
        lineNumber--;
        return false;
    }

    /**
     * This method removes an orderLine from the order.
     * @param obj the orderline to be removed.
     *@return boolean Returns true on success, false on failure.
     */
    @Override
    public boolean remove(Object obj) {
        boolean success = orderlines.remove((OrderLine)obj);
        lineNumber--;
        if (success) {
            if(orderlines.size() == 0)
                lineNumber = 1;
        }
        updateLineNumbers();
        return success;
    }
    
    /**
     * This method deletes the current order and resets the lineNumber to 1.
     * @return returns true on success, false if size of order was 0.
     */
    public boolean clear() {
        if(orderlines.size() == 0)
            return false;
        orderlines = new ArrayList<OrderLine>();
        lineNumber = 1;
        return true;
    }
    
    /**
     * This method returns the line number of the order.
     * @return lineNumber
     */
    public int getLineNumber() {
        return lineNumber;
    }
    
    /**
     * This method returns the orderlines ArrayList.
     * @return orderlines
     */
    public ArrayList<OrderLine> getOrderlines(){
        return orderlines;
    }
    
    /**
     * This method keeps a running total of the cost of all the orders in the orderlines
     * ArrayList.
     * @return total price of all orders
     */
    public double getTotal() {
        double total = 0;
        for(OrderLine l : orderlines) {
            total += l.getPrice();
        }
        return total;
    }
    
    /**
     * This method updates the line number of an order to its index in the orderlines ArrayList.
     */
    private void updateLineNumbers() {
        for (int i = 0; i < orderlines.size(); i++) {
            orderlines.get(i).setLineNumber(i+1);
        }
    }
    
    /**
     * This method duplicates a given OrderLine object
     * @param old The OrderLine object to be duplicated
     * @return the duplicate OrderLine object
     */
    public OrderLine duplicate(OrderLine old) {
        OrderLine duplicate = new OrderLine(lineNumber, old.getSandwich());
        return duplicate;
    }
    
    /**
     * This class exports the the current order to the given file path.
     * @param path The file path to which the order will be saved.
     * @return Returns true on success, false otherwise.
     */
    public boolean exportOrder(String path) {
        File file = new File(path);
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(file);
        }catch(Exception e) {
            return false;
        }
        for(OrderLine l : orderlines) {
            pw.println(l.toString());
        }
        pw.close();
        return true;
    }
}
