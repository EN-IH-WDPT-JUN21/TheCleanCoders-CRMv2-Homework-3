package com.ironhack.TheCleanCodersCRMv2homework3.classes;

import java.util.ArrayList;
import java.util.List;

public abstract class Item {
    private int id;
    private static List<Item> itemList = new ArrayList<>();

    // Constructor

    public Item(List<Item> listOfItems) {
        setId(listOfItems);
        setItemList(listOfItems);
        addItem(this);
    }

    public Item(int id, List<Item> listOfItems) {
        setId(id);
        setItemList(listOfItems);
        addItem(this);
    }

    // Methods

    public void addItem(Item item){
        itemList.add(item);
    }

    public static void removeAllFromItemList() {
        itemList.removeAll(itemList);
    }

    public static Item getById(int id, List<Item> itemList){
        for(Item item : itemList){
            if(id == item.getId()){
                return item;
            }
        }

        throw new NullPointerException("This id has no related item. Please try again.");
    }

    public static boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        
        try {
            Long.parseLong(str.replaceAll(" ", ""));
        } catch (NumberFormatException nfe) {
            return false;
        }
      
        return true;
    }

    // Setters

    public void setId(List<Item> itemList){
        if (itemList.size() == 0){
            this.id = 1;
        } else {
            this.id = itemList.get(itemList.size() - 1).getId() + 1;
        }
    }

    public void setId(int id) {
        this.id = id;
    }

    public static void setItemList(List<Item> itemList) {
        Item.itemList = itemList;
    }

    // Getters

    public int getId(){
        return this.id;
    }

    public static List<Item> getItemList() {
        return itemList;
    }

}
