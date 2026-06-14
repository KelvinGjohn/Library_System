package lv.venta.service;

import lv.venta.model.Book;
import lv.venta.model.BookCondition;
import lv.venta.repo.IBookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements IBookService {

    @Autowired
    private IBookRepo bookRepo;

    @Override
    public List<Book> selectAll() {
        return bookRepo.findAll();
    }

    @Override
    public Book selectById(long id) throws Exception {
        return bookRepo.findById(id)
                .orElseThrow(() -> new Exception("Book with id " + id + " not found"));
    }

    @Override
    public void add(Book book) {
        bookRepo.save(book);
    }

    @Override
    public void updateById(long id, Book updatedBook) throws Exception {
        Book existing = selectById(id);
        existing.setIsbn(updatedBook.getIsbn());
        existing.setYear(updatedBook.getYear());
        existing.setAuthor(updatedBook.getAuthor());
        existing.setTitle(updatedBook.getTitle());
        existing.setRating(updatedBook.getRating());
        existing.setCondition(updatedBook.getCondition());
        existing.setRarity(updatedBook.getRarity());
        bookRepo.save(existing);
    }

    @Override
    public void removeById(long id) throws Exception {
        Book existing = selectById(id);
        bookRepo.delete(existing);
    }

    @Override
    public List<Book> selectByAuthor(String author) {
        return bookRepo.findByAuthor(author);
    }

    @Override
    public List<Book> selectByCondition(BookCondition condition) {
        return bookRepo.findByCondition(condition);
    }

    @Override
    public List<Book> selectByMinRating(double rating) {
        return bookRepo.findByRatingGreaterThanEqual(rating);
    }

    @Override
    public List<Book> selectByDepartmentSpecialization(String specialization) {
        return bookRepo.findByLibraryDepartmentSpecialization(specialization);
    }

    @Override
    public void changeBookCondition(long id, BookCondition newCondition) throws Exception {
        Book book = selectById(id);
        book.changeCondition(newCondition);
        bookRepo.save(book);
    }
}
