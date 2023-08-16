package com.example.groupassignment;

import java.io.*;
import java.util.*;

public class RecipeDAO {

    File recipefile = new File("recipes.txt");

    public void writeAllRecipes(ArrayList<Recipe> recipelist) {
        PrintWriter pw = null;
        try {
            pw = new PrintWriter(recipefile);

            for (Recipe recipe : recipelist) {
                pw.println(recipe);
            }
        } catch (Exception e) {
            System.out.println("Error writing recipe: " + e);
        } finally {
            if (pw != null) pw.close();
        }
    }

    public ArrayList<Recipe> readAllRecipes() {
        ArrayList<Recipe> recipelist = new ArrayList<Recipe>();
        Scanner scan = null;
        try {
            scan = new Scanner(recipefile);
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                Recipe recipe = Recipe.parseRecipe(line);
                recipelist.add(recipe);
            }
        } catch (Exception e) {
            System.out.println("Error reading recipe: " + e);
        } finally {
            if (scan != null) scan.close();
        }
        return recipelist;
    }


    public void addRecipe(Recipe recipe) {
        ArrayList<Recipe> recipelist = readAllRecipes();
        recipelist.add(recipe);
        writeAllRecipes(recipelist);
    }


}