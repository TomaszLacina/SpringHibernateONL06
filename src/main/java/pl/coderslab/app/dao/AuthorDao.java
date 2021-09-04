package pl.coderslab.app.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import pl.coderslab.app.entity.Author;

@Repository
@Transactional
public class AuthorDao {

  @PersistenceContext
  EntityManager entityManager;

  public void save(Author author) {
    entityManager.persist(author);
  }

  public void update(Author author) {

    entityManager.merge(author);
  }

  public Author findById(Long id) {
    return entityManager.find(Author.class, id);
  }

  public void remove(Long id) {
    entityManager.remove(findById(id));
  }
}
