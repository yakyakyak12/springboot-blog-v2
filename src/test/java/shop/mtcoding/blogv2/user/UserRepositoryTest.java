package shop.mtcoding.blogv2.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class UserRepositoryTest {

  @Autowired
  private UserRepository userRepository;

  @Test
  public void save_test() {
    User user = User.builder()
        .username("love")
        .password("1111")
        .email("love@naver.com")
        .build();

    userRepository.save(user);

  }
}