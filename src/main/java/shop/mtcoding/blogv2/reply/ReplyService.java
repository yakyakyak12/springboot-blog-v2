package shop.mtcoding.blogv2.reply;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.mtcoding.blogv2.board.Board;
import shop.mtcoding.blogv2.user.User;

@Service
public class ReplyService {
    @Autowired
    private ReplyRepository replyRepository;

    @Transactional
    public void 댓글쓰기(ReplyRequest.saveDTO saveDTO, Integer userId) {
        Reply reply = Reply.builder()
        .comment(saveDTO.getComment())
        .board(Board.builder().id(saveDTO.getBoardId()).build())
        .user(User.builder().id(userId).build())
        .build();
        replyRepository.save(reply);
    }
    
    @Transactional
    public void 댓글삭제(Integer id) {
        System.out.println("아이디 : " + id);
        replyRepository.deleteById(id);
    }

    


   

    // replyRepository.save();
    // }



}
