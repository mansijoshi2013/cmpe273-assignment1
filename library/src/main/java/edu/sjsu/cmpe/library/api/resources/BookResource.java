package edu.sjsu.cmpe.library.api.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.yammer.dropwizard.jersey.params.LongParam;
import com.yammer.metrics.annotation.Timed;

import edu.sjsu.cmpe.library.data.LibraryData;
import edu.sjsu.cmpe.library.domain.Book;
import edu.sjsu.cmpe.library.domain.Review;
import edu.sjsu.cmpe.library.dto.AuthorDto;
import edu.sjsu.cmpe.library.dto.AuthorsDto;
import edu.sjsu.cmpe.library.dto.BookDto;
import edu.sjsu.cmpe.library.dto.LinkDto;
import edu.sjsu.cmpe.library.dto.LinksDto;
import edu.sjsu.cmpe.library.dto.ReviewDto;
import edu.sjsu.cmpe.library.dto.ReviewsDto;

@Path("/v1/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {

    public BookResource() {
	// do nothing
    }
    
    @GET
    @Path("/{isbn}")
    @Timed(name = "view-book")
    public BookDto getBookByIsbn(@PathParam("isbn") Long isbn) {
    	return LibraryData.getLibrary().getBookLinks(isbn);
    }
    
    @POST
    @Path("/")
    @Timed(name = "create-book")
    public Response createBook(Book book) {
    	Long isbn = LibraryData.getLibrary().createBook(book);
    	LinksDto links = new LinksDto();
    	links.setLinks(LibraryData.getLibrary().getBookLinks(isbn).getLinks());
    	return Response.ok(links).status(201).build();
    }

   @DELETE
   @Path("/{isbn}")
   @Timed(name="delete-book")
   public Response removeBook(@PathParam("isbn") LongParam isbn){
	   LibraryData.getLibrary().removeBook(isbn.get());
	   LinksDto links = new LinksDto();
	   links.addLink(new LinkDto("create-book", "/books", "POST"));
	   return Response.ok(links).build();
   }

   @PUT
   @Path("/{isbn}")
   @Timed(name ="update-book")
   public Response updateBookStatus(@PathParam("isbn") LongParam isbn, @QueryParam("status") String status) {
	    LibraryData.getLibrary().updateBookStatus(isbn.get(), status);
	   	LinksDto links = new LinksDto();
	   	links.setLinks(LibraryData.getLibrary().getBookLinks(isbn.get()).getLinks());
	   	return Response.ok(links).build();
	   
   }
   
   //POST - /books/{isbn}/reviews
   @POST
   @Path("/{isbn}/reviews")
   @Timed(name = "create-review")
   public Response createReview(@PathParam("isbn") Long isbn, Review review){
	   LinksDto links = LibraryData.getLibrary().createReview(isbn, review);
	   return Response.ok(links).status(201).build();
   }
   
   //GET - /books/{isbn}/reviews/{id}
   @GET
   @Path("/{isbn}/reviews/{id}")
   @Timed(name = "view-review")
   public Response viewReview(@PathParam("isbn") Long isbn, @PathParam("id") Long id){
	   ReviewDto review = LibraryData.getLibrary().viewReviewById(isbn, id);
	   return Response.ok(review).build();
   }
   
 ///books/{isbn}/reviews
   @GET
   @Path("/{isbn}/reviews")
   @Timed(name = "view-reviews")
   public Response viewReview(@PathParam("isbn") Long isbn){
	   ReviewsDto reviews = LibraryData.getLibrary().viewReviews(isbn);
	   return Response.ok(reviews).build();
   }
   
   @GET
   @Path("/{isbn}/authors/{id}")
   @Timed(name = "view-author")
   public Response viewAuthor(@PathParam("isbn") Long isbn, @PathParam("id") Long id){
	   AuthorDto author = LibraryData.getLibrary().viewAuthorById(isbn, id);
	   return Response.ok(author).build();
   }   

   @GET
   @Path("/{isbn}/authors")
   @Timed(name = "view-authors")
   public Response viewAuthors(@PathParam("isbn") Long isbn){
	   AuthorsDto authors = LibraryData.getLibrary().viewAuthors(isbn);
	   return Response.ok(authors).build();
   }
   
}

