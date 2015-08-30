package com.springapp.mvc.dao;

import com.springapp.mvc.model.Book;

import java.util.List;

/**
 * Created by ���� on 30.07.2015.
 */
public interface BookDao {
    boolean saveBook(Book book);
    List<Book> getBooks();
    void updateBook(Book book);
    void deleteBook(Book book);
}
