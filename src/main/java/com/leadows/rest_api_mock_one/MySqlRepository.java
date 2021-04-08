package com.leadows.rest_api_mock_one;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import org.springframework.stereotype.Component;

@Component
public class MySqlRepository implements Repository {

    @Override
    public ArrayList<Book> getBooks() {
        ArrayList<Book> booksToReturn = new ArrayList<Book>(); // List of reference variables of type Book

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sys", "arbaaz", "arbaaz");
                Statement sqlstatement = conn.createStatement();) {

            ResultSet resultSet = sqlstatement.executeQuery("SELECT * from sys.books;");

            while (resultSet.next()) {
                booksToReturn.add(Book.getBookFromResultSet(resultSet));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return booksToReturn;
    }

    @Override
    public boolean addBook(String title, String author, int price) {

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sys", "arbaaz", "arbaaz");
                Statement sqlStatement = conn.createStatement();) {

            return sqlStatement.execute("INSERT INTO sys.books VALUES(74589" + ",'" + title + "','" + author + "',"
                    + price + ",'saved'" + ");");

        } catch (Exception e) {
            e.printStackTrace();
            return true;
        }

    }

    @Override
    public String getBook(int id) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/sys", "arbaaz", "arbaaz");
                Statement sqlStatement = conn.createStatement();) {
            ResultSet resultSet = sqlStatement.executeQuery("SELECT * FROM sys.books WHERE id=" + id + ";");
            // Assume resultset to be similar to an array
            // By default resultset points to null (Iterator of the resultset)
            // even if there is only one object in the resultset
            // use resultset.next in order to access the first element or the next
            // corresponding element in resultset
            if (resultSet.next()) {

                return Book.getBookFromResultSet(resultSet).toJSON().toString();
            }
            else{
                return "Book not found";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Book not found";
        }
    }

}
