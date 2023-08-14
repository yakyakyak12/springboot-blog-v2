package shop.mtcoding.blogv2.user;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

@Import(UserQueryRepasitory.class)
@DataJpaTest // JpaRepository만 IoC 메모리에 올려준다.
public class UserQueryRepositoryTest {

  @Autowired
  private UserQueryRepasitory userQueryRepasitory;

  @Autowired
  private EntityManager em;

  @Test
  public void save_test() {
    User user = User.builder()
        .username("love")
        .password("1234")
        .email("love@nate.com")
        .build();
    userQueryRepasitory.save(user); // 영속화
    // em.flush();
  }

  // 1차 캐시
  @Test
  public void findById() {
    System.out.println("1. pc는 비어있다");
    userQueryRepasitory.findById(1);
    System.out.println("2. pc의 user 1은 영속화 되어 있다");
    em.clear();
    userQueryRepasitory.findById(1);
    System.out.println("??????????????????");
  }

  @Test
  public void update_test() {
    // update 알고리즘
    // 1. 업데이트 할 객체를 영속화
    // 2. 객체 상태 변경
    // 3. em.flush() or @Transactional 종료
    User user = userQueryRepasitory.findById(1); // 1번을 찾아
    user.setEmail("ssarmango@nate.com");
    em.flush();
  } // rollback

}
