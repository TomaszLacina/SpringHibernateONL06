package pl.coderslab.app.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import pl.coderslab.app.entity.Book;

@Repository
@Transactional
public class BookDao {

  @PersistenceContext
  EntityManager entityManager;


  public void save(Book book) {
    entityManager.persist(book);
  }

  public void update(Book book) {

    entityManager.merge(book);
  }

  public Book findById(Long id) {
    return entityManager.find(Book.class, id);
  }

  public void remove(Long id) {
    entityManager.remove(findById(id));
  }
}
