package pl.coderslab.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.coderslab.app.entity.Book;


public interface BookRepository extends JpaRepository<Book, Long> {

}
