package com.example.bookstorespringreact.service.impl;

import java.util.Collection;
import java.util.Optional;

import com.example.bookstorespringreact.model.Book;
import com.example.bookstorespringreact.repository.BookRepository;
import com.example.bookstorespringreact.service.IPageService;
import com.example.bookstorespringreact.service.IService;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class BookServiceImpl implements IService<Book>, IPageService<Book> {

	@Autowired
	private BookRepository bookRepository;
	
	@Override
	public Collection<Book> findAll() {
		return (Collection<Book>) bookRepository.findAll();
	}

	@Override
	public Page<Book> findAll(Pageable pageable, String searchText, String user) {
		return bookRepository.findAllBooksByUser(pageable, searchText,user);
	}

	@Override
	public Page<Book> findAll(Pageable pageable, String user) {
		return bookRepository.findAllBooksByUser(pageable,user);
	}

	@Override
	public Optional<Book> findById(Long id) {
		return bookRepository.findById(id);
	}

	@Override
	public Book saveOrUpdate(Book book) {
		return bookRepository.save(book);
	}

	@Override
	public String deleteById(Long id) {
		JSONObject jsonObject = new JSONObject();
		try {
			bookRepository.deleteById(id);
			jsonObject.put("message", "Book deleted successfully");
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return jsonObject.toString();
	}

}
