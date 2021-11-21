package com.example.bookstorespringreact.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IPageService<T> {
	Page<T> findAll(Pageable pageable, String searchText, String user);

	Page<T> findAll(Pageable pageable, String user);
}
