package com.example.groupassignment;

import java.util.*;

public class Recipe {
    private String foodName;
    private String[] ingredients;
    private String[] instructions;

    /**
     *  no arg constructor
     */
    Recipe(){}


    /**
     * 3-arg instructor
     * @param name
     * @param ingredients
     * @param instructions
     */
    Recipe(String name, String[] ingredients, String[] instructions){
        setFoodName(name);
        setIngredients(ingredients);
        setInstructions(instructions);
    }

    public String getFoodName(){
        return foodName;
    }
    public String[] getIngredients(){
        return ingredients;
    }
    public String[] getInstructions(){
        return instructions;
    }
    public void setFoodName(String name){
        foodName=name;
    }
    public void setIngredients(String[] ingredients){
        this.ingredients=ingredients;
    }
    public void setInstructions(String[] instructions){
        this.instructions=instructions;
    }

    /**
     * prints the string as one giant string, easier for recipes to read each line
     * @return
     */
    public String toString(){
        String result = "Food Name: " + foodName;
        result += " Ingredients:";
        for (String ingredient : ingredients) {
            result += "-" + ingredient + " ";
        }
        result += " Instructions:";
        for (String instructions : instructions) {
            result += "-" + instructions + " ";
        }
        return result;
    }

    public String formatRecipeIngredients(){
        String result = "";
        for (String ingredient : ingredients) {
            result += "-" + ingredient + "\n";
        }
        return result;
    }
    public String formatRecipeInstructions() {
        String result = "";
        for (String instructions : instructions) {
            result += "-" + instructions + "\n";
        }
        return result;
    }


    /**
     * Divides the string into a Recipe
     * @param str of Recipe object (from toString())
     * @return
     */
    public static Recipe parseRecipe(String str){

        String name = str.substring(str.indexOf(":")+2, str.indexOf("Ingredients")-1);
        String[] ingredientsParse = extractIngredients(str);
        String[] instructionsParse = extractInstructions(str);
        return new Recipe(name, ingredientsParse, instructionsParse);
    }

    /**
     * Extracts the instructions and splits them at the delimiter '-'
     * @param str
     * @return
     */
    private static String[] extractInstructions(String str) {
        int instructionsStart = str.indexOf("Instructions:") + "Instructions:".length()+1;
        String instructionsExtract = str.substring(instructionsStart).trim();
        return instructionsExtract.split("-");
    }

    /**
     * Extracts the ingredients and splits them at the delimiter '-'
     * @param str
     * @return
     */
    private static String[] extractIngredients(String str) {
        int ingredientsStart = str.indexOf("Ingredients:") + "Ingredients:".length()+1;
        int instructionsStart = str.indexOf("Instructions:")-1;
        String ingredientsExtract = str.substring(ingredientsStart, instructionsStart).trim();
        return ingredientsExtract.split("-");
    }
}