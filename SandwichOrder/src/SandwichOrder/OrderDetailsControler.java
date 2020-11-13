package SandwichOrder;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
	private OrderController orderController = null;
	private DecimalFormat df = null;

	ObservableList<OrderLine> list = null;

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
	 * 
	 * @param event
	 */
	public void setView1Controller(OrderController obj) {
		order = obj.getOrder();		
	}

	/**
	 * 
	 * @param event
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

	@FXML
	void clearOrder(ActionEvent event) {
		if(orderController != null) {
			orderList.getItems().clear();
			order.clear();
		}
	}

	@FXML
	void closeWindow(ActionEvent event) {
		Stage stage = (Stage) backButton.getScene().getWindow();
		stage.close();
	}

	@FXML
	void removeItem(ActionEvent event) {
		OrderLine selected = orderList.getSelectionModel().getSelectedItem();
		if (selected != null) {
			order.remove(selected);
			orderList.getItems().remove(selected);
			orderTotalDisplay.setText(df.format(order.getTotal()));
		}
	}

	@FXML
	void duplicateItem(ActionEvent event) {
		OrderLine selected = orderList.getSelectionModel().getSelectedItem();
		if (selected != null) {
			order.add(selected);
			orderList.getItems().add(selected);
			orderTotalDisplay.setText(df.format(order.getTotal()));
		}
	}

	@FXML
	void exportOrder() {
		if(orderController != null) {
			try {
				order.exportOrder("SandwichOrderExport.txt"); 
				orderList.getItems().clear();
				order.clear();
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
}