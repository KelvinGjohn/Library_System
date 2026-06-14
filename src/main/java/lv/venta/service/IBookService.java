package lv.venta.service;

import lv.venta.model.Book;
import lv.venta.model.BookCondition;

import java.util.List;

public interface IBookService {
    // CRUD
    List<Book> selectAll();
    Book selectById(long id) throws Exception;
    void add(Book book);
    void updateById(long id, Book updatedBook) throws Exception;
    void removeById(long id) throws Exception;

    // Extra queries
    List<Book> selectByAuthor(String author);
    List<Book> selectByCondition(BookCondition condition);
    List<Book> selectByMinRating(double rating);
    List<Book> selectByDepartmentSpecialization(String specialization);

    // changeCondition wrapper
    void changeBookCondition(long id, BookCondition newCondition) throws Exception;
}