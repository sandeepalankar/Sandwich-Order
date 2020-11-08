package SandwichOrder;

import javafx.fxml.Initializable;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class OrderController implements Initializable{

	ObservableList<String> list = FXCollections.observableArrayList();

	@FXML
	private ChoiceBox<String> sandwichType;

	@FXML
	private TextArea baseIngredients;

	@FXML
	private ListView<String> ingredientSelections;

	@FXML
	private ListView<String> extraIngredients;

	@FXML
	private TextField price;

	@FXML
	private TextArea textArea;	

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
	}

	/**
	 * This method populates the ChoiceBox with the three sandwich types and sets the default 
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
	}  

	/**
	 * This method loads the Ingredient Selections ListView with 10 possible ingredients
	 * that can be added to the user's selected sandwich.
	 */
	private void loadIngredients() {
		list.removeAll(list);
		String a = "Lettuce";
		String b = "Tomatoes";
		String c = "Onion";
		String d = "Bacon";
		String e = "Mushrooms";
		String f = "Spinach";
		String g = "Pickles";
		String h = "Provolone";
		String i = "American";
		String j = "Swiss";
		list.addAll(a,b,c,d,e,f,g,h,i,j);
		ingredientSelections.getItems().addAll(list);		
	}

	/**
	 * This method loads the price TextField with the Chicken sandwich price, which is the default
	 * sandwich displayed in the ChoiceBox.
	 */
	private void loadPrice() {
		price.setText("8.99");
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
	 * on which sandwich type the user selects in the ChoiceBox.
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
	 * sandwich in the ChoiceBox.
	 */
	private void handlePrice() {
		if (sandwichType.getValue().equals("Chicken")) {
			price.setText("8.99");
		} else if (sandwichType.getValue().equals("Beef")) {
			price.setText("10.99");
		} else {
			price.setText("12.99");
		}
	}
}
