package com.buildAPI.demoProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GenerateAPI {
	
	@Autowired
	private BookService service;

	@PostMapping("/service")
	public void service() {
		System.out.println("print hello world");
	}
	
	@PostMapping("/books")
	public String saveUser(@RequestBody Books book) {
		service.save(book);
		System.out.println("Saved Book to Database: " + book.toString());
		return book.toString();
	}
}
