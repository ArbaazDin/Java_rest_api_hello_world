package com.leadows.rest_api_mock_one;

import java.util.ArrayList;

public interface Repository {
    ArrayList<Book> getBooks(); 
    boolean addBook(String title, String author, int price);
    String getBook(int id);
}
