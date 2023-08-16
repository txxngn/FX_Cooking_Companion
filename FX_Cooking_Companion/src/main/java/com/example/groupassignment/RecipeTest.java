package com.example.groupassignment;


import java.util.ArrayList;

public class RecipeTest {
    public static void main(String[] args) {
        String[] ingredients1 = {"Noodles", "Tomato sauce 1 can", "Parmesan cheese"};
        String[] instructions1 = {"Boil water", "Add noodles", "Drain noodles", "Add tomato sauce", "Serve with parmesan cheese optional"};
        Recipe recipe1 = new Recipe("Pasta ", ingredients1, instructions1);


        String[] ingredients2 = {"175 grams pizza dough", "1 tbs tomato paste", "1/4 cup Parmesan Cheese", "4 slices of Prosciutto"};
        String[] instructions2 = {"Preheat oven to 800F. Create sauce.", "Prepare the dough to 12 inches in diameter", "Place sauce, cheese, and prosciutto on the dough", "Bake in oven until the crust rises."};
        Recipe recipe2 = new Recipe("Pizza", ingredients2, instructions2);

        String[] ingredients3 = {"Ground beef", "Buns", "Lettuce"};
        String[] instructions3 = {"Prepare ground beef into patties", "Grill burgers on grill", "Toast buns"};
        Recipe recipe3 = new Recipe("Burgers", ingredients3, instructions3);


        ArrayList<Recipe> recipelist = new ArrayList<Recipe>();
        recipelist.add(recipe1);
        recipelist.add(recipe2);
        recipelist.add(recipe3);


        RecipeDAO dao = new RecipeDAO();
        dao.writeAllRecipes(recipelist);

        System.out.println("Read Recipes: "+dao.readAllRecipes());

    }
}
