package shop.mtcoding.blogv2.user;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserQueryRepasitory {

  @Autowired
  private EntityManager em;

  public void save(User user) {
    em.persist(user);
  }

  public User findById(Integer id) {
    return em.find(User.class, id);
  }
}
