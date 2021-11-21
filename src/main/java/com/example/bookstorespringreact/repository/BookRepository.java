package com.example.bookstorespringreact.repository;

import com.example.bookstorespringreact.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



@Repository
public interface BookRepository extends PagingAndSortingRepository<Book, Long> {

    @Query("FROM Book b WHERE b.title LIKE %:searchText% OR b.author LIKE %:searchText% OR b.language LIKE %:searchText% OR b.type LIKE %:searchText% ")
    Page<Book> findAllBooks(Pageable pageable, @Param("searchText") String searchText);

    @Query("FROM Book b WHERE b.title LIKE %:searchText% OR b.author LIKE %:searchText% OR b.language LIKE %:searchText% OR b.type LIKE %:searchText% AND b.user = :user ")
    Page<Book> findAllBooksByUser(Pageable pageable, @Param("searchText") String searchText, @Param("user") String user);

    @Query("FROM Book b WHERE b.user = :user")
    Page<Book> findAllBooksByUser(Pageable pageable,  @Param("user") String user);
}
