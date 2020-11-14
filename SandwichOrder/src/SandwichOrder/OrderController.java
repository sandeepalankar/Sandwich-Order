package SandwichOrder;

import javafx.fxml.Initializable;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * This class is the controller behind the main "My Sandwich Store" GUI. It handles all of the operations
 * relating to choosing a sandwich, its ingredients, and clearing/adding the sandwich to the current order.
 * @author Sandeep Alankar, Graham Deubner
 *
 */
public class OrderController implements Initializable {

	private Sandwich mySandwich = new Chicken();

	private Order order = null;

	private DecimalFormat df = null;	

	private ObservableList<String> list = FXCollections.observableArrayList();

	@FXML
	private ComboBox<String> sandwichType;

	@FXML
	private TextArea baseIngredients;

	@FXML
	private ImageView sandwichImage;

	@FXML
	private ListView<String> ingredientSelections;

	@FXML
	private Button addIngredients;

	@FXML
	private Button removeIngredients;

	@FXML
	private Button clearIngredients;

	@FXML
	private ListView<String> extraIngredients;

	@FXML
	private TextField price;

	@FXML
	private Button addToOrder;

	@FXML
	private Button showOrder;

	@FXML
	private TextArea textArea;	

	/**
	 * This method loads all the necessary fields with the default properties of the default
	 * chicken sandwich before the user inputs anything.
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loadsandwichType();
		loadIngredients();
		loadPrice();	
		loadBaseIngredients();		
		sandwichType.getSelectionModel().selectedItemProperty().addListener( 
				(v, oldValue, newValue) -> handleBaseIngredients());
		sandwichType.getSelectionModel().selectedItemProperty().addListener( 
				(v, oldValue, newValue) -> handlePrice());
		sandwichType.getSelectionModel().selectedItemProperty().addListener(
				(v, oldValue, newValue) -> handlePicture());		
	}

	/**
	 * This method populates the ComboBox with the three sandwich types and sets the default 
	 * sandwich type as Chicken.
	 */
	private void loadsandwichType() {
		list.removeAll(list);
		String a = "Chicken";
		String b = "Beef";
		String c = "Fish";
		list.addAll(a,b,c);
		sandwichType.getItems().addAll(list);	
		sandwichType.setValue(a);
		Image image = new Image("/images/chicken.jpg");
		sandwichImage.setImage(image);
	}  

	/**
	 * This method loads the Ingredient Selections ListView with 10 possible ingredients
	 * that can be added to the user's selected sandwich.
	 */
	private void loadIngredients() {
		list.removeAll(list);
		String a = String.valueOf(Extra.LETTUCE);		
		String b = String.valueOf(Extra.TOMATO);
		String c = String.valueOf(Extra.ONION);
		String d = String.valueOf(Extra.BACON);
		String e = String.valueOf(Extra.MUSHROOMS);
		String f = String.valueOf(Extra.SPINACH);
		String g = String.valueOf(Extra.PICKLES);
		String h = String.valueOf(Extra.PROVOLONE);
		String i = String.valueOf(Extra.AMERICAN);
		String j = String.valueOf(Extra.SWISS);		
		list.addAll(a,b,c,d,e,f,g,h,i,j);
		ingredientSelections.getItems().addAll(list);			
	}

	/**
	 * This method loads the price TextField with the Chicken sandwich price, which is the default
	 * sandwich displayed in the ComboBox.
	 */
	private void loadPrice() {
		price.setText(String.valueOf(mySandwich.price()));
	}

	/**
	 * This method populates the baseIngredients TextArea with the base ingredients of a Chicken
	 * sandwich.
	 */
	private void loadBaseIngredients() {
		baseIngredients.appendText("Fried Chicken \n");
		baseIngredients.appendText("Spicy Sauce \n");
		baseIngredients.appendText("Pickles");
		baseIngredients.setDisable(true);		
	}

	/**
	 * This method populates the baseIngredients TextArea with the appropriate base ingredients based
	 * on which sandwich type the user selects in the ComboBox.
	 */	
	private void handleBaseIngredients() {
		if (sandwichType.getValue().equals("Chicken")) {
			baseIngredients.clear();
			baseIngredients.appendText("Fried Chicken \n");
			baseIngredients.appendText("Spicy Sauce \n");
			baseIngredients.appendText("Pickles");
		} else if (sandwichType.getValue().equals("Beef")) {
			baseIngredients.clear();
			baseIngredients.appendText("Roast Beef \n");
			baseIngredients.appendText("Provolone Cheese \n");
			baseIngredients.appendText("Mustard");
		} else {
			baseIngredients.clear();
			baseIngredients.appendText("Grilled Snapper \n");
			baseIngredients.appendText("Cilantro \n");
			baseIngredients.appendText("Lime");
		}
	}

	/**
	 * This method changes the value of the price TextField to represent the price of the selected
	 * sandwich in the ComboBox.
	 */
	private void handlePrice() {
		if (sandwichType.getValue().equals("Chicken")) {			
			price.setText(String.valueOf(mySandwich.price()));			
		} else if (sandwichType.getValue().equals("Beef")) {
			mySandwich = new Beef();
			price.setText(String.valueOf(mySandwich.price()));
		} else {
			mySandwich = new Fish();
			price.setText(String.valueOf(mySandwich.price()));
		}
	}

	/**
	 * This method adds the selected ingredient from the Ingredient Selections ListView
	 * to the Extra Ingredients ListView and only allows the user to add 6 extra ingredients to their
	 * sandwich.
	 */	
	private void handleAddIngredients() {		
		if (extraIngredients.getItems().size() < mySandwich.getMaxExtras()) {
			String selectedIngredient = ingredientSelections.getSelectionModel().getSelectedItem();
			if (selectedIngredient != null) {
				extraIngredients.getItems().add(ingredientSelections.getSelectionModel().getSelectedItem());	
				ingredientSelections.getItems().remove(ingredientSelections.getSelectionModel().getSelectedIndex());
			}						
		}
		else {
			textArea.appendText("Can't add more than 6 ingredients!\n");
		}
	}

	/**
	 * This method removes the selected ingredient from the Extra Ingredients ListView and adds it back
	 * to the Ingredient Selections ListView.
	 */	
	private void handleRemoveIngredients() {			
		String selectedIngredient = extraIngredients.getSelectionModel().getSelectedItem();
		if (selectedIngredient != null) {
			ingredientSelections.getItems().add(extraIngredients.getSelectionModel().getSelectedItem());			
			extraIngredients.getItems().remove(extraIngredients.getSelectionModel().getSelectedIndex()); 			
		}	
		else {
			return;
		}
	}

	/**
	 * This method is called everytime the "Add >>" button is selected. It adds to the Extra Ingredients
	 * ListView and also updates the price to reflect the current price of the sandwich.
	 */
	@FXML
	private void AddButton() {			
		if (ingredientSelections.getSelectionModel().getSelectedItem() != null) {
			df = new DecimalFormat(",##0.00");						
			mySandwich.add(Extra.valueOf(ingredientSelections.getSelectionModel().getSelectedItem()));
			handleAddIngredients();
			price.setText(df.format(mySandwich.getPrice()));					
		}		
	}

	/**
	 * This method is called everytime the "<< Remove" button is selected. It removes the selected
	 * ingredient from the Extra Ingredients ListView and adds it back to the Ingredient Selections
	 * ListView. It also updates the sandwich price with the current price of the sandwich.
	 */
	@FXML
	private void RemoveButton() {		
		if (extraIngredients.getSelectionModel().getSelectedItem() != null) {			
			df = new DecimalFormat(",##0.00");				
			mySandwich.remove(Extra.valueOf(extraIngredients.getSelectionModel().getSelectedItem()));
			handleRemoveIngredients();
			price.setText(df.format(mySandwich.getPrice()));			
		}		
	}	

	/**
	 * This method displays a picture of the sandwich type selected by the user in the ComboBox.
	 */
	private void handlePicture() {
		if (sandwichType.getValue().equals("Chicken")) {
			ingredientSelections.getItems().removeAll(list);
			ingredientSelections.getItems().addAll(list);
			extraIngredients.getItems().removeAll(extraIngredients.getItems());	
			Image image = new Image("/images/chicken.jpg");
			sandwichImage.setImage(image);
			mySandwich = new Chicken();
		} else if (sandwichType.getValue().equals("Beef")) {
			ingredientSelections.getItems().removeAll(list);
			ingredientSelections.getItems().addAll(list);
			extraIngredients.getItems().removeAll(extraIngredients.getItems());	
			Image image = new Image("/images/beef.jpg");
			sandwichImage.setImage(image);	
			mySandwich = new Beef();
		} else {
			ingredientSelections.getItems().removeAll(list);
			ingredientSelections.getItems().addAll(list);
			extraIngredients.getItems().removeAll(extraIngredients.getItems());	
			Image image = new Image("/images/fish.jpg");
			sandwichImage.setImage(image);	
			mySandwich = new Fish();
		} 
	}

	/**
	 * This method sets the Sandwich Store back to its default setting, clearing all the selected
	 * ingredients.
	 */
	@FXML
	private void handleClearSelected() {		
		textArea.setText("");		
		mySandwich = new Chicken();
		sandwichType.setValue("Chicken");
		ingredientSelections.getItems().removeAll(list);
		ingredientSelections.getItems().addAll(list);
		extraIngredients.getItems().removeAll(extraIngredients.getItems());		
		price.setText(String.valueOf(mySandwich.price()));
	}	

	/**
	 * This method adds the current sandwich with its ingredients to the order.
	 */
	@FXML
	private void handleAddToOrder() {		
		df = new DecimalFormat(",##0.00");
		if (order == null) {
			order = new Order();
		}		
		order.add(new OrderLine(order.getLineNumber(), mySandwich));			
		textArea.appendText("Sandwich added to order.\n");		
	}

	/**
	 * This method opens the second "Order Details" window where the user can see all of the sandwiches 
	 * added to the current order. Background window may not be accessed while second window is open.
	 */
	@FXML
	private void openOrderDetails(ActionEvent event) {
		if(order == null) {
			textArea.appendText("Order is empty.\n");
			return;
		}
		try {				
			FXMLLoader loader =	new FXMLLoader(getClass().getResource("OrderDetailsManager.fxml"));									
			//pass the reference of “this” controller to controller2					
			
			Stage newStage = new Stage();
			BorderPane rootPane = (BorderPane)loader.load();
			OrderDetailsControler controller2 = loader.getController();
			controller2.setView1Controller(this);
			controller2.slowInitiate();
			//BorderPane rootPane = (BorderPane)FXMLLoader.load(getClass().getResource("OrderDetailsManager.fxml"));
			Scene scene = new Scene(rootPane,675,575);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			newStage.setScene(scene);
			newStage.setTitle("Order Display");
			newStage.initModality(Modality.WINDOW_MODAL);
			newStage.initOwner(((Node)event.getSource()).getScene().getWindow() );
			newStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method returns order.
	 * @return order
	 */
	public Order getOrder() {
		return order;
	}
}
