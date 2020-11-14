package SandwichOrder;
import java.text.DecimalFormat;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


/**
 * This is the controller class for the orderDetailsManager.fxml file
 * @author Graham Deubner, Sandeep Alankar
 *
 */
public class OrderDetailsControler {

	private Order order = null;
	private DecimalFormat df = null;

	private ObservableList<OrderLine> list = null;

	@FXML
	private ListView<OrderLine> orderList;

	@FXML
	private Button backButton;

	@FXML
	private Button clearButton;

	@FXML
	private Button duplicateButton;

	@FXML
	private Button removeButton;

	@FXML
	private TextField orderTotalDisplay;

	@FXML
	private Button exportButton;
	
	/**
	 * This method sets the is to be used by the OrderController to pass the OrderDetailsController
	 * a reference to the OrderController.
	 */
	public void setView1Controller(OrderController obj) {
		order = obj.getOrder();		
	}

	/**
	 * This method fills the OrderList with the initial orderList objects.
	 */
	public void slowInitiate() {
		try { 			
			list = FXCollections.observableList(order.getOrderlines());			
			orderList.getItems().addAll(list);
			df = new DecimalFormat(",##0.00");
			orderTotalDisplay.setText(df.format(order.getTotal()));
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method clears the orderList.
	 */
	@FXML
	void clearOrder() {
		if(order != null) {
		    orderList.getItems().removeAll(orderList.getItems());
		    order.clear();
		}
	}

	/**
	 * This method closes the orderDetailsWindow.
	 */
	@FXML
	void closeWindow() {
		Stage stage = (Stage) backButton.getScene().getWindow();
		stage.close();
	}

	/**
	 * This method removes an OrderLine from the OrderLine list.
	 */
	@FXML
	void removeItem() {
		OrderLine selected = orderList.getSelectionModel().getSelectedItem();
		if (selected != null) {
			order.remove(selected);
			orderList.getItems().remove(selected);
			orderTotalDisplay.setText(df.format(order.getTotal()));
		}
	}

	/**
	 * This method duplicates a selected OrderLine in the list of OrderLines.
	 */
	@FXML
	void duplicateItem() {
		OrderLine selected = orderList.getSelectionModel().getSelectedItem();
		if (selected != null) {
		    OrderLine duplicate = order.duplicate(selected);
			order.add(duplicate);
			orderList.getItems().add(duplicate);
			orderTotalDisplay.setText(df.format(order.getTotal()));
		}
	}

	/**
	 * This method exports the current order to the file "SandwichOrderExport.txt" and clears the order.
	 */
	@FXML
	void exportOrder() {
		if(order != null) {
			try {
				order.exportOrder("SandwichOrderExport.txt"); 
	            orderList.getItems().removeAll(orderList.getItems());
				order.clear();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}