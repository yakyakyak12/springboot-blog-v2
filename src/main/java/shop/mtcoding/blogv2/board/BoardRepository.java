package shop.mtcoding.blogv2.board;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/*
 * save(), findById(), findAll(), count(), deletById() // update빼고 다나옴 
 */
// 스프링이 실행될 때, BoardRepository의 구현체가 IoC 컨테이너에 생성된다. 
public interface BoardRepository extends JpaRepository<Board, Integer> {

  // select id, title, content, user_id created_at from board_tb b inner join
  // user_tb u on b.user_id = u.id;
  // fetch를 붙여야 *를 한다. (전체를 프로젝션 한다.)
  @Query("select b from Board b join fetch b.user")
  List<Board> mFindAll(); // 객체 지향으로 쿼리를 짬.
}
