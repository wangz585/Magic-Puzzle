package com.puzzle.mazing.Models;


import android.view.View;
import android.widget.ImageView;

public class Animal {

    private  int order;
    /**
     * The appearance of the animal
     */
    private int drawable;
    /**
     * The visibility of the animal
     */


    private ImageView view;
    private boolean visibility;
    private String Species;

    Animal(int appearance, int Order, String species){
        this.drawable = appearance;
        this.order = Order;
        this.Species = species;
        this.visibility = false;


    }

    public void setVisibility(boolean visible) {

        
        if (visible){
            this.view.setVisibility(View.VISIBLE);
        }
        else{
            this.view.setVisibility(View.INVISIBLE);
        }
        this.visibility = visible;
    }
    public boolean getVisibility() {
        return visibility;
    }

    public void setSpecies(String species) {
        Species = species;
    }

    public String getSpecies() {
        return Species;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public void setAppearance(int appearance) {
        view.setImageResource(appearance);
    }
    public void setView(ImageView id){
        this.view = id;
    }

    public int getDrawable() {
        return drawable;
    }

    public ImageView getView() {
        return view;
    }


}
