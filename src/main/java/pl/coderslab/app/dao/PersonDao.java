package pl.coderslab.app.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import pl.coderslab.app.entity.Person;

@Repository
@Transactional
public class PersonDao {

  @PersistenceContext
  private EntityManager entityManager;

  public void save(Person person) {
    entityManager.persist(person);
  }

  public void update(Person person) {

    entityManager.merge(person);
  }

  public Person findById(Long id) {
    return entityManager.find(Person.class, id);
  }

  public void remove(Long id) {
    entityManager.remove(findById(id));
  }
}
