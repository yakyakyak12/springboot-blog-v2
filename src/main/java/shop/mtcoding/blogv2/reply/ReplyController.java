package shop.mtcoding.blogv2.reply;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import shop.mtcoding.blogv2._core.error.ex.MyApiException;
import shop.mtcoding.blogv2._core.util.ApiUtil;
import shop.mtcoding.blogv2.user.User;


@Controller
public class ReplyController {
    
    @Autowired
    private ReplyService replyService;

    @Autowired
    private HttpSession session;
    
    // @PostMapping("/reply/save")
    // public String save(ReplyRequest.saveDTO saveDTO){
    //     User sessionUser = (User) session.getAttribute("sessionUser");
    //     replyService.댓글쓰기(saveDTO,sessionUser.getId());
    //     return "redirect:/board/" +saveDTO.getBoardId();
    // }

    // @PostMapping("/reply/{id}/delete")
    // public String delete(@PathVariable Integer id, Integer boardId){
    //     System.out.println("아이디 : " + id);
    //     replyService.댓글삭제(id);
    //     return "redirect:/board/" + boardId;
    // }
    @PostMapping("/api/reply/save")
    public @ResponseBody ApiUtil<String> save(@RequestBody ReplyRequest.saveDTO saveDTO){
        // System.out.println("boardId : " + saveDTO.getBoardId());
        // System.out.println("Comment : " + saveDTO.getComment());
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
            // return new ApiUtil<String>(false, "인증이 되지 않았습니다."); 오류는 스로우로 던져서 일관성을 지켜준다.
            throw new MyApiException("인증되지 않았습니다");
        }
        replyService.댓글쓰기(saveDTO, sessionUser.getId());
        return new ApiUtil<String>(true, "댓글쓰기 성공");
    }

    @DeleteMapping("/api/reply/{id}/delete")
    public @ResponseBody ApiUtil<String> delete(@PathVariable Integer id){
        // 1. 인증체크
        User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null) {
        throw new MyApiException("인증되지 않았습니다");
        }
        // 2. 핵심로직
         replyService.댓글삭제(id, sessionUser.getId());

        // 3. 응답
         return new ApiUtil<String>(true, "댓글삭제 완료");
        }
    }
   


