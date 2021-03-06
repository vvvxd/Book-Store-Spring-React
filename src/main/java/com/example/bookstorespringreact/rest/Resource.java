package com.example.bookstorespringreact.rest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface Resource<T> {
	@GetMapping("/search/{searchText}")
	ResponseEntity<Page<T>> findAll(Pageable pageable, @PathVariable String searchText, String user);

	@GetMapping
	ResponseEntity<Page<T>> findAll(int pageNumber, int pageSize, String user);

	@GetMapping("/{id}")
	ResponseEntity<T> findById(@PathVariable Long id);

	@PostMapping("/save")
	ResponseEntity<T> save(@RequestBody T t);

	@PostMapping("/update")
	ResponseEntity<T> update(@RequestBody T t);
	
	@DeleteMapping("{id}")
	ResponseEntity<String> deleteById(@PathVariable Long id);
}
