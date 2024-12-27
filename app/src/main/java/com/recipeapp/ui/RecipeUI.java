package com.recipeapp.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import com.recipeapp.datahandler.DataHandler;
import com.recipeapp.model.Ingredient;
import com.recipeapp.model.Recipe;
import java.util.ArrayList;

public class RecipeUI {
    private BufferedReader reader;
    private DataHandler dataHandler;

    public RecipeUI(DataHandler dataHandler) {
        reader = new BufferedReader(new InputStreamReader(System.in));
        this.dataHandler = dataHandler;
    }

    // レシピデータをコンソールに表示するメソッド
    private void displayRecipes() {
        try {
            // DataHandlerからレシピデータを取得
            ArrayList<Recipe> recipes = dataHandler.readData();

            if (recipes.isEmpty()) {
                System.out.println("No recipes available.");
            } else {
                System.out.println("Recipes:");
                System.out.println("-----------------------------------");

                // レシピごとに詳細表示
                for (Recipe recipe : recipes) {
                    System.out.println("Recipe Name: " + recipe.getName());
                    System.out.println("Main Ingredients:");

                    // 材料の表示
                    for (int i = 0; i < recipe.getIngredients().size(); i++) {
                        System.out.print(recipe.getIngredients().get(i).getName());
                        if (i < recipe.getIngredients().size() - 1) {
                            System.out.print(", ");
                        }
                    }
                    System.out.println("\n-----------------------------------");
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    // メニューを表示するメソッド
    public void displayMenu() {
        System.out.println("Current mode: " + dataHandler.getMode());

        while (true) {
            try {
                // メインメニューの表示
                System.out.println();
                System.out.println("Main Menu:");
                System.out.println("1: Display Recipes");
                System.out.println("2: Add New Recipe");
                System.out.println("3: Search Recipe");
                System.out.println("4: Exit Application");
                System.out.print("Please choose an option: ");

                String choice = reader.readLine();

                switch (choice) {
                    case "1":
                        // Display Recipesを表示
                        displayRecipes();
                        break;
                    case "2":
                        // 新しいレシピを追加する場合
                        addNewRecipe();
                        break;
                    case "3":
                        // レシピ検索（未実装）
                        System.out.println("Search Recipe - This functionality is not yet implemented.");
                        break;
                    case "4":
                        // アプリケーション終了
                        System.out.println("Exiting the application.");
                        return;
                    default:
                        // 不正な選択肢
                        System.out.println("Invalid choice. Please select again.");
                        break;
                }
            } catch (IOException e) {
                System.out.println("Error reading input from user: " + e.getMessage());
            }
        }
    }

    private void addNewRecipe() {
        try {
            //レシピ名入力
            System.out.print("Enter recipe name:");
            String recipeName = reader.readLine();

            //材料リスト
            ArrayList<Ingredient> ingredients = new ArrayList<>();
            System.out.println("Enter ingredients (type 'done' when finished):");

            //材料入力
            while (true) {
                System.out.print("Ingredient:");
                String ingredientName = reader.readLine();

                if (ingredientName.equals("done")) {
                    break;
                }

                //リストに追加
                ingredients.add(new Ingredient(ingredientName));
            }
            //新しいレシピ作成
            Recipe newRecipe = new Recipe(recipeName, ingredients);

            //保存
            dataHandler.writeData(newRecipe);

            System.out.println("Recipe added successfully.");
        } catch (IOException e) {
            System.out.println("Failed to add new recipe:" + e.getMessage());
        }
    }
}