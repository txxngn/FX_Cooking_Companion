
package com.example.groupassignment;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import com.example.groupassignment.Recipe;
import com.example.groupassignment.RecipeDAO;
import javafx.stage.Stage;

import java.util.ArrayList;

public class CookingController {

    RecipeDAO dao = new RecipeDAO();
    int recipeIndex =-1;

    @FXML
    private BorderPane mainbody;

//3 Main Buttons
    @FXML
    void goAddRecipe(ActionEvent event) {
        setView("AddRecipeView");
    }

    @FXML
    void goCalc(ActionEvent event) {
        setView("CalculatorView");
    }


    @FXML
    void goHome(ActionEvent event) {
        setView("HomeView");
    }




//Events for HomePage

    @FXML
    private Button btnPizza;


    @FXML
    private Button btnSearch;

    @FXML
    private TextField tfSearchFood;

    @FXML
    void searchFood(ActionEvent event) {
        searching();
    }

    public String getFoodName(){
        return tfSearchFood.getText();
    }

    public void searching(){
        tfSearchFood.setText("Searching for " + getFoodName() + " ...");
    }

    @FXML
    void pizzaRecipe(ActionEvent event) {
        setView("PizzaView");
    }

    @FXML
    void goRecipeList(ActionEvent event) {

        setView("RecipeListView");
        showListRecipe();
    }

    //Top menu:

    @FXML
    void exitApp(ActionEvent event) {
        ((Stage)mainbody.getScene().getWindow()).close();
    }

    @FXML
    void openAbout(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Cooking Companton Information");
        alert.setHeaderText("Cooking Companion v1.0");
        alert.setContentText("App used to calculate your calorie intake, and to record and view your recipes.\nBy:   Quoc Thai Nguyen\n\tKaitlin Saqui\n\tTaymour Breiche");
        alert.showAndWait();
    }
//-----------------------------------------------------------------------------------------------------

//AddingRecipe Event
    @FXML
    private Button btnAddRecipe;

    @FXML
    private TextField tfIngredients1;

    @FXML
    private TextField tfIngredients2;

    @FXML
    private TextField tfIngredients3;

    @FXML
    private TextField tfIngredients4;

    @FXML
    private TextField tfInstructions1;

    @FXML
    private TextField tfInstructions2;

    @FXML
    private TextField tfInstructions3;

    @FXML
    private TextField tfInstructions4;

    @FXML
    private TextField tfRecipeName;



    @FXML
    void addRecipe(ActionEvent event) {
        String recipeName = tfRecipeName.getText();
        String[] ingredients = {
                tfIngredients1.getText(),
                tfIngredients2.getText(),
                tfIngredients3.getText(),
                tfIngredients4.getText()
        };
        String[] instructions = {
                tfInstructions1.getText(),
                tfInstructions2.getText(),
                tfInstructions3.getText(),
                tfInstructions4.getText()
        };
        System.out.println("Recipe Saved!");
        Recipe newRecipe = new Recipe(recipeName, ingredients, instructions);

        dao.addRecipe(newRecipe);

        showListRecipe();

        //clear all text fields after click save/add
        tfRecipeName.clear();
        tfIngredients1.clear();
        tfIngredients2.clear();
        tfIngredients3.clear();
        tfIngredients4.clear();
        tfInstructions1.clear();
        tfInstructions2.clear();
        tfInstructions3.clear();
        tfInstructions4.clear();

    }


//-----------------------------------------------------------------------------------------------------

//Recipe List View
    @FXML
    private ListView<Recipe> listViewRecipe;

    @FXML
    void addARecipe(ActionEvent event) {
        setView("AddRecipeView");
    }

    void showListRecipe(){
        setView("RecipeListView");
        ArrayList<Recipe> recipeList = dao.readAllRecipes();
        listViewRecipe.getItems().setAll(recipeList);
    }

    @FXML
    void openSelectedRecipe(MouseEvent event) {       //open a section in ListView page will show detail of that recipe
        System.out.println("List View item clicked");
        Recipe recipe = (Recipe) listViewRecipe.getSelectionModel().getSelectedItem();
        recipeIndex = listViewRecipe.getSelectionModel().getSelectedIndex();
        setView("RecipeDetailedView");
        setRecipe(recipe);
    }

//-----------------------------------------------------------------------------------------------------

//Recipe Detail View
    @FXML
    private Button btnBack;

    @FXML
    private TextArea taInstructionsDetails;

    @FXML
    private TextArea taIngredientDetails;

    @FXML
    private TextField tfRecipeNameDetails;

    @FXML
    void Back(ActionEvent event) {
        setView("RecipeListView");
        showListRecipe();
    }

    public void setRecipe(Recipe recipe){
        tfRecipeNameDetails.setText(recipe.getFoodName());
        taIngredientDetails.setText(recipe.formatRecipeIngredients());
        taInstructionsDetails.setText(recipe.formatRecipeInstructions());

    }

//-----------------------------------------------------------------------------------------------------

//Calculator Events
    @FXML
    private Button btnCalculate;

    @FXML
    private CheckBox cbBubbleTea;

    @FXML
    private CheckBox cbCheese;

    @FXML
    private CheckBox cbETC;

    @FXML
    private CheckBox cbRamen;

    @FXML
    private CheckBox cbRice;

    @FXML
    private TextField txtTotal;

    @FXML
    void addResult(ActionEvent event) {
        setResult(getCal());
    }

    //set total result
    public void setResult(double value){
        txtTotal.setText(value + "");
    }

    //get values from CheckBox
    public double getCal(){
        double sum = 0;
        if (cbCheese.isSelected())
            sum += 1500;
        if (cbRamen.isSelected())
            sum += 500;
        if (cbRice.isSelected())
            sum += 105;
        if (cbBubbleTea.isSelected())
            sum += 272;
        if (cbETC.isSelected())
            sum += 0;

        return sum;
    }

//-----------------------------------------------------------------------------------------------------

//Pizza Events
    @FXML
    void startCooking(ActionEvent event) {
    //display instructions (do later)
}

    @FXML
    private Button btnFav;

    @FXML
    private Button btnStart;

    @FXML
    private CheckBox cbDough;

    @FXML
    private CheckBox cbMeat;

    @FXML
    private CheckBox cbTomato;

    @FXML
    private CheckBox cbParm;



/*    public void initialize() {
        btnStart.disableProperty().bind(cbParm.selectedProperty().not().or(cbDough.selectedProperty().not())
                .or(cbMeat.selectedProperty().not()).or(cbTomato.selectedProperty().not()));
    }
*/

    public void setView(String file){
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource(file + ".fxml"));
            loader.setController(this);
            Pane view = loader.load();
            mainbody.setCenter(view);
            //initialize();
        }
        catch(Exception ex){
            System.out.println("Error Loading View: "+ ex);
        }


    }
}