package com.example.bookstorespringreact.rest.impl;

import com.example.bookstorespringreact.model.Book;
import com.example.bookstorespringreact.rest.Resource;
import com.example.bookstorespringreact.service.IPageService;
import com.example.bookstorespringreact.service.IService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

@RestController
@RequestMapping("/books")
@CrossOrigin(origins="http://localhost:3000")
public class BookResourceImpl implements Resource<Book> {

	private final IService<Book> bookService;

	private final IPageService<Book> bookPageService;

	public BookResourceImpl(IService<Book> bookService, IPageService<Book> bookPageService) {
		this.bookService = bookService;
		this.bookPageService = bookPageService;
	}

	@Override
	public ResponseEntity<Page<Book>> findAll(Pageable pageable, String searchText, String user) {
		return new ResponseEntity<>(bookPageService.findAll(pageable, searchText,  user), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Page<Book>> findAll(int pageNumber, int pageSize, String user) {
		return new ResponseEntity<>(bookPageService.findAll(PageRequest.of(pageNumber, pageSize),  user), HttpStatus.OK);
	}

	@Override

	public ResponseEntity<Book> findById(@PathVariable Long id) {
		return new ResponseEntity<>(bookService.findById(id).get(), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Book> save(@RequestBody Book book) {
		return new ResponseEntity<>(bookService.saveOrUpdate(book), HttpStatus.CREATED);
	}

	@Override
	@CrossOrigin
	public ResponseEntity<Book> update(@RequestBody Book book) {
		return new ResponseEntity<>(bookService.saveOrUpdate(book), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> deleteById(Long id) {
		return new ResponseEntity<>(bookService.deleteById(id), HttpStatus.OK);
	}

	@GetMapping("/languages")
	public  ResponseEntity<Set<String>> findAllLanguages() {
        return new ResponseEntity<>(new TreeSet<>(Arrays.asList("French", "Portuguese", "English", "Russian", "Hindi", "Arabic", "Spanish", "Chinese")), HttpStatus.OK);
    }

    @GetMapping("/types")
    public  ResponseEntity<Set<String>> findAllTypes() {
        return new ResponseEntity<>(new TreeSet<>(Arrays.asList("Audio", "Electronic", "Paper")), HttpStatus.OK);
    }
}
