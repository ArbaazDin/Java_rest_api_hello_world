package com.leadows.rest_api_mock_one;

import java.util.ArrayList;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class RestApiMockOneApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestApiMockOneApplication.class, args);
	}

}

@RestController
class HelloController {

	@Autowired
	Repository bookRepository;

	@RequestMapping("/hello")
	public String hello() {
		return "Hello World RESTful with Spring Boot";
	}

	@RequestMapping("/getBooks")
	public String getBooks() {
		return Book.getBooksInJsonString(bookRepository.getBooks());
	}

	@GetMapping("/getBook")
	public String getBook(@RequestParam("id") int id) {
		System.out.println(id);
		return bookRepository.getBook(id);
	}

	@PostMapping("/addBook")
	public String addBook(@RequestBody String body) {
		JSONObject book = new JSONObject(body);
		
		String title = book.getString("title");
		String author = book.getString("author");
		int price = book.getInt("price");
		
		boolean isBookNotAdded = bookRepository.addBook(title, author, price);
		if(isBookNotAdded) {
			return "Book not added. Please try again";
		} else {
			return "Book added successfully.";
		}
	}
}
