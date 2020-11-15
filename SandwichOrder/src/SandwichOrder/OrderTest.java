/**
 * 
 */
package SandwichOrder;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * This class is used to test all of the public methods in the Order class.
 * @author Sandeep Alankar, Graham Deubner
 *
 */
class OrderTest {

	/**
	 * Test method for {@link SandwichOrder.Order#add(java.lang.Object)}.
	 */
	@Test
	void testAdd() {
		Order order = new Order();
		Sandwich sandwich = new Chicken();
		assertTrue(order.add(new OrderLine(order.getLineNumber(), sandwich))); 	
		//successful add should	return true
	}

	/**
	 * Test method for {@link SandwichOrder.Order#remove(java.lang.Object)}.
	 */
	@Test
	void testRemove() {
		Order order = new Order();
		Sandwich sandwich = new Chicken();	
		OrderLine orderlist = new OrderLine(order.getLineNumber(), sandwich);		
		assertFalse(order.remove(orderlist));
		//removal should be unsuccessful if the orderlines ArrayList is empty
		order.add(orderlist);
		assertTrue(order.remove(orderlist));	
		//removal should be successful since orderlist was added to current order
	}

	/**
	 * Test method for {@link SandwichOrder.Order#clear()}.
	 */
	@Test
	void testClear() {
		Order order = new Order();
		Sandwich sandwich = new Chicken();
		assertFalse(order.clear()); //should return false since no sandwiches are in orderlines ArrayList
		order.add(new OrderLine(order.getLineNumber(), sandwich));
		assertTrue(order.clear()); 
		//should return true since 1 sandwich was added to orderlines, clear is successful
		
	}

	/**
	 * Test method for {@link SandwichOrder.Order#getLineNumber()}.
	 */
	@Test
	void testGetLineNumber() {
		Order order = new Order();
		Sandwich sandwich = new Chicken();
		new OrderLine(order.getLineNumber(), sandwich);
		assertEquals(order.getLineNumber(), 1); //line number of first order should be 1
		Sandwich sandwich2 = new Fish();
		order.add(new OrderLine(order.getLineNumber(), sandwich2));
		assertEquals(order.getLineNumber(), 2); //line number of second order should be 2
	}

	/**
	 * Test method for {@link SandwichOrder.Order#getOrderlines()}.
	 */
	@Test
	void testGetOrderlines() {
		Order order = new Order();
		Sandwich sandwich = new Fish();
		order.add(new OrderLine(order.getLineNumber(), sandwich));
		assertEquals(order.getOrderlines().toString(), "[1: Fish Sandwich: Grilled Snapper, Cilantro, "
				+ "Lime,  Price: $12.99]"); 
		//output ArrayList should only contain fish sandwich with base ingredients and base price	
	}

	/**
	 * Test method for {@link SandwichOrder.Order#getTotal()}.
	 */
	@Test
	void testGetTotal() {
		Order order = new Order();
		Sandwich sandwich = new Fish(); //costs 12.99
		order.add(new OrderLine(order.getLineNumber(), sandwich)); 
		Sandwich sandwich2 = new Chicken(); //costs 10.99
		order.add(new OrderLine(order.getLineNumber(), sandwich2));
		assertEquals(order.getTotal(), 21.98); //total should be 12.99 + 10.99
	}	

	/**
	 * Test method for {@link SandwichOrder.Order#duplicate(SandwichOrder.OrderLine)}.
	 */
	@Test
	void testDuplicate() {
		Order order = new Order();
		Sandwich sandwich = new Fish();
		OrderLine order1 = new OrderLine(order.getLineNumber(), sandwich);
		OrderLine order2 = new OrderLine(order.getLineNumber(), sandwich);		
        assertEquals(order2.toString(), order.duplicate(order1).toString());  
        //order1 and order2 should be exactly the same if order1 was duplicated correctly        
	}

	/**
	 * Test method for {@link SandwichOrder.Order#exportOrder(java.lang.String)}.
	 */
	@Test
	void testExportOrder() {
		fail("Not yet implemented");
	}

}
