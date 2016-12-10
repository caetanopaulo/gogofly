package com.gogoflyapp.gogofly.tools;

/**
 * Created by Kees on 12/10/2016.
 */

public class Theme {
    private String category;

    public Theme(String category) {
        this.setCategory(category);
    }

    // Setters and Getters
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
