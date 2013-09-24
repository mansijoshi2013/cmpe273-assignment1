package edu.sjsu.cmpe.library.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import edu.sjsu.cmpe.library.constants.StatusEnum;
import edu.sjsu.cmpe.library.domain.Author;
import edu.sjsu.cmpe.library.domain.Book;
import edu.sjsu.cmpe.library.domain.Review;
import edu.sjsu.cmpe.library.dto.AuthorDto;
import edu.sjsu.cmpe.library.dto.AuthorsDto;
import edu.sjsu.cmpe.library.dto.BookDto;
import edu.sjsu.cmpe.library.dto.LinkDto;
import edu.sjsu.cmpe.library.dto.LinksDto;
import edu.sjsu.cmpe.library.dto.ReviewDto;
import edu.sjsu.cmpe.library.dto.ReviewsDto;


public class LibraryData {
	private static LibraryData data=null;
	private static Long index = 0L;
	private Map<Long, BookDto> books = new HashMap<Long, BookDto>();
	//private Map<Long, Long> reviewCount = new HashMap<Long, Long>();
	
	//private Map<Long, Map<Long, Review>> reviews = new HashMap<Long, Map<Long,Review>>();
	
	private LibraryData(){}
	
	public static LibraryData getLibrary(){
		if(data==null)
			data = new LibraryData();
		
		return data;
	}

	public Long createBook(Book book){
		if(book==null)
			return -1L;
		
		Long isbn = ++index;
		book.setIsbn(isbn);
		Long id =1l;
		for(Author author : book.getAuthors()){
			author.setId(id++);
		}
		
		BookDto bookResponse = new BookDto(book);
		bookResponse.addLink(new LinkDto("view-book", "/books/" + isbn,
			"GET"));
		bookResponse.addLink(new LinkDto("update-book",
			"/books/" + isbn, "PUT"));
		bookResponse.addLink(new LinkDto("delete-book", "/books/" + isbn, "Delete"));	
		bookResponse.addLink(new LinkDto("create-review","/books/" + isbn + "/reviews","POST"));
		
		books.put(isbn, bookResponse);
		return isbn;
	}
	
	public Book getBook(Long isbn){
		if(books.containsKey(isbn)){
			return books.get(isbn).getBook();}
		
		return null;
	}
	
	public void updateBookStatus(Long isbn, String status){
		if(books.containsKey(isbn)){
			books.get(isbn).getBook().setStatus(StatusEnum.getStatusByName(status));
		}
	}
	
	
	public void removeBook(Long isbn){
		if(books.containsKey(isbn))
			books.remove(isbn);
	}
	
	public BookDto getBookLinks(Long isbn){
		if(! books.containsKey(isbn))
			return null;
		
		return books.get(isbn);
	}
	
	public LinksDto createReview(Long isbn, Review review){
		if(isbn==null || books.containsKey(isbn)==false || review == null)
			return null;
		
		List<Review> reviews = books.get(isbn).getBook().getReviews();
		Long id = Long.valueOf(reviews.size()) + 1;
		review.setId(id);
		reviews.add(review);
		
		//{ “rel”: “view-review”, “herf”: “/books/1/reviews/1”, “method”: “GET” },
		LinkDto link = new LinkDto("view-review", "/books/" + isbn.toString() + "/reviews/" + id.toString(), "GET");
		
		LinksDto links = new LinksDto();
		links.addLink(link);
		
		return links;
	}
	
	public ReviewsDto viewReviews(Long isbn){
		if(isbn==null || books.containsKey(isbn)==false)
			return null;
		
		ReviewsDto reviews = new ReviewsDto();
		reviews.setReviews(books.get(isbn).getBook().getReviews());
		
		return reviews;
	}

	
	public ReviewDto viewReviewById(Long isbn, Long id){
		if(isbn==null || books.containsKey(isbn)==false || id < 1)
			return null;
		
		LinkDto link = new LinkDto("view-review", "/books/" + isbn.toString() + "/reviews/" + id.toString(), "GET");
		LinksDto links = new LinksDto();
		links.addLink(link);
		
		Book book = books.get(isbn).getBook();
		List<Review> reviews = book.getReviews(); 
		if( reviews != null && reviews.size() >= id){
			ReviewDto reviewDto = new ReviewDto(reviews.get(id.intValue() - 1));
			reviewDto.setLinks(links.getLinks());
			return reviewDto;
		}
		
		return null;
	}
	
	public AuthorsDto viewAuthors(Long isbn){
		if(isbn==null || books.containsKey(isbn)==false)
			return null;
		
		AuthorsDto authors = new AuthorsDto();
		authors.setAuthors(books.get(isbn).getBook().getAuthors());
		
		return authors;
	}
	
	public AuthorDto viewAuthorById(Long isbn, Long id){
		if(isbn==null || books.containsKey(isbn)==false || id < 1)
			return null;
		
		LinkDto link = new LinkDto("view-author", "/books/" + isbn.toString() + "/authors/" + id.toString(), "GET");
		LinksDto links = new LinksDto();
		links.addLink(link);
		
		Book book = books.get(isbn).getBook();
		List<Author> authors = book.getAuthors(); 
		if( authors != null && authors.size() >= id){
			AuthorDto authorDto = new AuthorDto(authors.get(id.intValue() - 1));
			authorDto.setLinks(links.getLinks());
			return authorDto;
		}
		
		return null;
	}

}
