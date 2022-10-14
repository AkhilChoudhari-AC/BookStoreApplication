package com.example.bookstoreapplication.controller;

import com.example.bookstoreapplication.dto.BookDTO;
import com.example.bookstoreapplication.dto.ResponseDTO;
import com.example.bookstoreapplication.model.Book;
import com.example.bookstoreapplication.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

// In Controller class we write the API's here

@RestController
@RequestMapping("/book")
public class BookController {

    // Autowired IBookService to inject its dependency here

    @Autowired
    IBookService bookService;

      //Ability to call api to insert Book record

    @PostMapping("/insert")
    public ResponseEntity<String> addBookToRepository(@Valid @RequestBody BookDTO bookDTO){
        Book newBook= bookService.createBook(bookDTO);
        ResponseDTO responseDTO=new ResponseDTO("New Book Added in Book Store",newBook);
        return new ResponseEntity(responseDTO, HttpStatus.CREATED);
    }

    // Ability to call api to retrieve all book records

    @GetMapping(value = "/getAll")
    public ResponseEntity<String> getAllBookData()
    {
        List<Book> listOfBooks = bookService.getAllBookData();
        ResponseDTO dto = new ResponseDTO("Data retrieved successfully (:",listOfBooks);
        return new ResponseEntity(dto,HttpStatus.OK);
    }

    // Ability to call api to get record by BookId

    @GetMapping(value = "/getBy/{bookId}")
    public ResponseEntity<String> getBookDataById(@PathVariable Integer bookId)
    {
        Optional<Book> Book = bookService.getBookDataById(bookId);
        ResponseDTO dto = new ResponseDTO("Data retrieved successfully by id (:",Book);
        return new ResponseEntity(dto,HttpStatus.OK);
    }

    // Ability to call api to delete book record by BookId

    @DeleteMapping("/delete/{bookId}")
    public ResponseEntity<String> deleteRecordById(@PathVariable Integer bookId){
        ResponseDTO dto = new ResponseDTO("Book Record deleted successfully", bookService.deleteRecordById(bookId));
        return new ResponseEntity(dto,HttpStatus.OK);
    }

    // Ability to call api to update book record by BookId

    @PutMapping("/updateBookById/{bookId}")
    public ResponseEntity<String> updateRecordById(@PathVariable Integer bookId,@Valid @RequestBody BookDTO bookDTO){
        Book updateRecord = bookService.updateRecordById(bookId,bookDTO);
        ResponseDTO dto = new ResponseDTO(" Book Record updated successfully by Id",updateRecord);
        return new ResponseEntity(dto,HttpStatus.ACCEPTED);
    }

    // ability to search Book by particular book name

    @GetMapping("searchByBookName/{bookName}")
    public ResponseEntity<ResponseDTO> getBookByName(@PathVariable("bookName") String bookName)
    {
        List<Book> listOfBooks = bookService.getBookByName(bookName);
        ResponseDTO dto = new ResponseDTO("Data retrieved successfully (:",listOfBooks);
        return new ResponseEntity(dto,HttpStatus.OK);
    }

     //Ability to get book data by author name.

    @GetMapping("searchByAuthorName/{authorName}")
    public ResponseEntity<ResponseDTO> getBookByAuthorName(@PathVariable("authorName") String authorName)
    {
        List<Book> listOfBooks = bookService.getBookByAuthorName(authorName);
        ResponseDTO dto = new ResponseDTO("Data retrieved successfully (:",listOfBooks);
        return new ResponseEntity(dto,HttpStatus.OK);
    }

   // ability to sort the Books by its Price in Ascending Order

    @GetMapping("/sortAsc")
    public ResponseEntity<ResponseDTO> getBooksInAscendingOrder()
    {
        List<Book> listOfBooks = bookService.sortedListOfBooksInAscendingOrder();
        ResponseDTO dto = new ResponseDTO("Data retrieved successfully (:",listOfBooks);
        return new ResponseEntity(dto,HttpStatus.OK);
    }

    // ability to sort the Books by its Price in Descending Order

    @GetMapping("/sortDesc")
    public ResponseEntity<ResponseDTO> getBooksInDescendingOrder()
    {
        List<Book> listOfBooks =bookService.sortedListOfBooksInDescendingOrder();
        ResponseDTO dto = new ResponseDTO("Data retrieved successfully (:",listOfBooks);
        return new ResponseEntity(dto,HttpStatus.OK);
    }

}
