package lv.venta.repo;

import lv.venta.model.Book;
import lv.venta.model.BookCondition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBookRepo extends JpaRepository<Book, Long> {
    List<Book> findByAuthor(String author);
    List<Book> findByCondition(BookCondition condition);
    List<Book> findByRatingGreaterThanEqual(double rating);
    List<Book> findByLibraryDepartmentSpecialization(String specialization);
}