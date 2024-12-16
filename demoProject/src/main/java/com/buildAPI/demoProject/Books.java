package com.buildAPI.demoProject;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Books {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int BookId;
	@JsonProperty("BookName")
	private String BookName;
	@JsonProperty("BookPrice")
	private long BookPrice;
	
	public int getBookId() {
		return BookId;
	}


	public void setBookId(int bookId) {
		BookId = bookId;
	}


	public String getBookName() {
		return BookName;
	}


	public void setBookName(String bookName) {
		BookName = bookName;
	}


	public long getBookPrice() {
		return BookPrice;
	}


	public void setBookPrice(long bookPrice) {
		BookPrice = bookPrice;
	}
		
	@Override
	public String toString() {
		return "Books [BookId=" + BookId + ", BookName=" + BookName + ", BookPrice=" + BookPrice + "]";
	}
	
	
}
