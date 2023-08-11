package shop.mtcoding.blogv2.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import shop.mtcoding.blogv2.user.User;

@DataJpaTest
public class BoardRepositoryTest {

  @Autowired
  private BoardRepository boardRepository;

  @Test
  public void save_test() {
    // 비영속 객체
    Board board = Board.builder()
        .title("제목1")
        .content("내용2")
        .user(User.builder().id(1).build())
        .build();

    // 영속 객체
    boardRepository.save(board);

    // 디비데이터와 동기화 됨
    System.out.println(board.getId());
  }
}
