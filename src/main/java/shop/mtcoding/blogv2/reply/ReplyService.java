package shop.mtcoding.blogv2.reply;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.mtcoding.blogv2._core.error.ex.MyApiException;
import shop.mtcoding.blogv2.board.Board;
import shop.mtcoding.blogv2.reply.ReplyRequest.saveDTO;
import shop.mtcoding.blogv2.user.User;

@Service
public class ReplyService {
    @Autowired
    private ReplyRepository replyRepository;

    @Transactional
    public void 댓글쓰기(saveDTO saveDTO, Integer sessionId) {
        
        // 비지니스 로직
        // 1. board id가 존재하는지 유무체크 (클라이언트로 받은 body 데이터는 신뢰할 수 없다)
        Board board = Board.builder().id(saveDTO.getBoardId()).build();
        User user = User.builder().id(sessionId).build();

        Reply reply = Reply.builder()
        .comment(saveDTO.getComment())
        .board(board)
        .user(user)
        .build();
        // 이 전까지는 비영속 객체
        replyRepository.save(reply); // entity : Reply 객체를 넣어줘야 함.
        // 여기부터 영속화된 객체가 된다. 
    }

    @Transactional
    public void 댓글삭제(Integer id, Integer sessionUserId) {
        // 권한체크
        Optional<Reply> replyOP = replyRepository.findById(id);

        if (replyOP.isEmpty()) {
            throw new MyApiException("삭제할 댓글이 없습니다");
        }

        Reply reply = replyOP.get();
            if (reply.getUser().getId() != sessionUserId) {
                throw new MyApiException("해당 댓글을 삭제할 권한이 없습니다");
            }
        
        replyRepository.deleteById(id);
    }
    // @Transactional
    // public void 댓글쓰기(ReplyRequest.saveDTO saveDTO, Integer userId) {
    //     Reply reply = Reply.builder()
    //     .comment(saveDTO.getComment())
    //     .board(Board.builder().id(saveDTO.getBoardId()).build())
    //     .user(User.builder().id(userId).build())
    //     .build();
    //     replyRepository.save(reply);
    // }
    
    // @Transactional
    // public void 댓글삭제(Integer id) {
    //     System.out.println("아이디 : " + id);
    //     replyRepository.deleteById(id);
    // }

    


   

    // replyRepository.save();
    // }



}
