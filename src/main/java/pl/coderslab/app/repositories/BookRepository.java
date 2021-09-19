package pl.coderslab.app.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.coderslab.app.entity.Book;
import pl.coderslab.app.entity.Category;


public interface BookRepository extends JpaRepository<Book, Long> {

  List<Book> findByTitle(String title);

  @Query("SELECT b FROM Book b where b.title = ?1")
  List<Book> pleaseGiveMeBooksByTitle(String title);

  List<Book> findByCategory(Category category);

  @Query("SELECT b FROM Book b where b.category = ?1")
  List<Book> pleaseGiveMeBooksByCategory(Category category);

  List<Book> findByCategoryId(Long categoryId);

  List<Book> findByCategoryName(String categoryName);
}
