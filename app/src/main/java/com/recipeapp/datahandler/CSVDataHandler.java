package com.recipeapp.datahandler;

import java.util.ArrayList;
import java.io.*;
import com.recipeapp.model.Ingredient;
import com.recipeapp.model.Recipe;

public class CSVDataHandler implements DataHandler {
    private String filePath;

    public CSVDataHandler() {
        this.filePath = "app/src/main/resources/recipes.csv";
    }

    public CSVDataHandler(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String getMode() {
        return "CSV";
    }

    @Override
    public ArrayList<Recipe> readData() throws IOException {
        ArrayList<Recipe> recipes = new ArrayList<>();
        BufferedReader reader = null;
    
        try {
            reader = new BufferedReader(new FileReader(filePath));
            String line;
    
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > 1) {
                    String name = parts[0].trim();
                    ArrayList<Ingredient> ingredients = new ArrayList<>();
                    for (int i = 1; i < parts.length; i++) {
                        ingredients.add(new Ingredient(parts[i].trim()));
                    }
                    recipes.add(new Recipe(name, ingredients));
                }
            }
        } catch (IOException e) {
            throw new IOException("Error reading file: " + e.getMessage());
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    
        return recipes;
    }
    @Override
    public void writeData(Recipe recipe) throws IOException {
    }

    @Override
    public ArrayList<Recipe> searchData(String keyword) throws IOException {
        return null;
    }
}