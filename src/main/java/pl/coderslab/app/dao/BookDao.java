package pl.coderslab.app.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import pl.coderslab.app.entity.Book;

@Repository
@Transactional
public class BookDao {

  @PersistenceContext
  private EntityManager entityManager;

  public List<Book> findAll(){
    Query findAllQuery = entityManager.createQuery("SELECT b FROM Book b");

    return findAllQuery.getResultList();
  }

  public List<Book> findByRating(int rating){
    Query query = entityManager.createQuery("SELECT b FROM Book b WHERE b.rating > :rating");
    query.setParameter("rating", rating);

    return query.getResultList();
  }

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
