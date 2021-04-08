package com.leadows.rest_api_mock_one;

import java.sql.ResultSet;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class Book {
    private String title;
    private String author;
    private int price;

    // --------------- Getter and setters ------------------- //
    String getTitle() {
        return this.title;
    }

    String getAuthor() {
        return this.author;
    }

    int getPrice() {
        return this.price;
    }

    void setTitle(String titleToSet) {
        this.title = titleToSet;
    }

    void setAuthor(String authorNameToSet) {
        this.author = authorNameToSet;
    }

    void setPrice(int priceToSet) {
        this.price = priceToSet;
    }

    public Book(String title, String author, int price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }

    public static Book getBookFromResultSet(ResultSet resultSet) throws Exception {
        Book bookToReturn = null;
        try {
            bookToReturn = new Book(resultSet.getString("title"), resultSet.getString("author"),
                    resultSet.getInt("price"));
        } catch (Exception e) {
            throw e;
        }
        return bookToReturn;
    }

    public static String getBooksInJsonString(ArrayList<Book> books) {
        JSONArray jsonBookArray = new JSONArray();

        books.forEach((book) -> {
            JSONObject bookInJsonObject = book.toJSON();
            jsonBookArray.put(bookInJsonObject);
        });

        return jsonBookArray.toString();

    }

    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", this.title);
        jsonObject.put("author", this.author);
        jsonObject.put("price", this.price);
        return jsonObject;
    }
}
