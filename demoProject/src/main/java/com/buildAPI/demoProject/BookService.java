package com.buildAPI.demoProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
	@Autowired
	private BookRepository repo; 

	public Books save(Books book) {
		return repo.save(book);
	}
}
