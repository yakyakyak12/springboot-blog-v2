package shop.mtcoding.blogv2.reply;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface ReplyRepository extends JpaRepository<Reply, Integer>{


    List<Reply> findByBoardId(@Param("boardId") Integer boardId);

}
