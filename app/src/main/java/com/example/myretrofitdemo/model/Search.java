package com.example.myretrofitdemo.model;

import java.util.ArrayList;

public class Search {
    private ArrayList<ImageModel> results;

    public Search(ArrayList<ImageModel> results) {
        this.results = results;
    }

    public ArrayList<ImageModel> getResults() {
        return results;
    }

    public void setResults(ArrayList<ImageModel> results) {
        this.results = results;
    }
}
