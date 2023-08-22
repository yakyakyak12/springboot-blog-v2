package shop.mtcoding.blogv2.board;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import shop.mtcoding.blogv2.user.User;

@DataJpaTest
public class BoardRepositoryTest {

  @Autowired
  private BoardRepository boardRepository;

  @Autowired
  private EntityManager em;
  // @Test
  // public void findById_test() {
  // Optional<Board> boardOP = boardRepository.findById(5);
  // if (boardOP.isPresent()) { // Board가 존재하면 !! (null 안정성)
  // System.out.println("테스트 : board가 있습니다.");
  // }
  // }

  @Test
  public void findById_test() {
    Board board = boardRepository.findById(5).get();
    board.setTitle("나 바꼈어!!");
    board.setContent("나도 바꼈어!!");
    em.flush();
    System.out.println(board.getTitle());
  }

  @Test
  public void mFindByIdJoinRepliesInUser_test(){
    Board board = boardRepository.mFindByIdJoinRepliesInUser(1).get();
    System.out.println("board : id : "+board.getId());
    System.out.println("board : title : "+board.getTitle());
    System.out.println("board : content : "+board.getContent());
    System.out.println("board : createdAt : "+board.getCreatedAt());
    System.out.println("============================================");
    board.getReplies().stream().forEach(r -> {
      System.out.println("board in replies : id : " + r.getId());
      System.out.println("board in replies : comment : " + r.getComment());
      System.out.println("board in replies in user : id : " + r.getUser().getId());
      System.out.println("board in replies in user : username : " + r.getUser().getUsername());
    });
    
  }

  @Test
  public void findAll_paging_test() throws JsonProcessingException {
    Pageable pageable = PageRequest.of(0, 3, Sort.Direction.DESC, "id");
    Page<Board> boardPG = boardRepository.findAll(pageable);
    ObjectMapper om = new ObjectMapper();

    // ObjectMapper는 BoardPG객체의 getter를 호출하면서 json을 만든다.
    String json = om.writeValueAsString(boardPG); // 자바객체를 JSON으로 변환
    System.out.println(json);
  }

  @Test
  public void findAll_test() {
    System.out.println("조회 직전");
    List<Board> boardList = boardRepository.findAll();
    System.out.println("조회 후 : Lazy");
    // 행 : 5개 -> 객체 : 5개
    // 각행 : Board (id=1, title =제목1, content=내용1, created_at=날짜, User(id=1))
    System.out.println(boardList.get(0).getId()); // 1번
    System.out.println(boardList.get(0).getUser().getId()); // 1번

    // Lazy Loading - 지연로딩
    // 연관된 객체에 null을 참조하려고 하면 조회가 일어남
    System.out.println(boardList.get(0).getUser().getUsername()); // null -> ssar
  }

  @Test
  public void mFindAll_test() {
    boardRepository.mFindAll();
  }

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
