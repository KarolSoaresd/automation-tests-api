package br.testing.model;

public class ProcuctModel {

    private String description;
    private String title;
    private String brand;
    private int price;
    private int discountPercentage;
    private int rating;
    private int stock;
    private String category;
    private String thumbnail;
    private String message;
    private boolean success;
    private int status;

    public ProcuctModel(String title, String description, int price, int discountPercentage, int rating, int stock, String brand, String category, String thumbnail) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.discountPercentage = discountPercentage;
        this.rating = rating;
        this.stock = stock;
        this.brand = brand;
        this.category = category;
        this.thumbnail = thumbnail;
    }
}
