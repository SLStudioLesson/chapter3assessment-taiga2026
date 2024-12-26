package com.recipeapp.datahandler;

import java.util.ArrayList;
import java.io.IOException;
import com.recipeapp.ui.RecipeUI;
import com.recipeapp.model.Recipe;

public class JSONDataHandler implements DataHandler {

    @Override
    public String getMode() {
        return "JSON";
    }

    @Override
    public ArrayList<Recipe> readData() throws IOException {
        return null;
    }

    @Override
    public void writeData(Recipe recipe) throws IOException {
        
    }

    @Override
    public ArrayList<Recipe> searchData(String keyword) throws IOException {
        return null;
    }
}