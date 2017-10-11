package com.example.ribhav.smartsociety.ResourceClasses;

/**
 * Created by Nimit Arora on 10/10/2017.
 */

public class MenuItem {
    private String Category;

    public MenuItem(String utilities) {
        this.Category=utilities;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }
}
