package pl.coderslab.app.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import pl.coderslab.app.entity.Publisher;

@Repository
@Transactional
public class PublisherDao {

  @PersistenceContext
  private EntityManager entityManager;

  public void save(Publisher publisher) {
    entityManager.persist(publisher);
  }

  public void update(Publisher publisher) {
    entityManager.merge(publisher);
  }

  public Publisher findById(Long id) {
    return entityManager.find(Publisher.class, id);
  }
}
