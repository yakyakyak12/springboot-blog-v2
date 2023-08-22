package shop.mtcoding.blogv2.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import shop.mtcoding.blogv2._core.error.ex.MyException;
import shop.mtcoding.blogv2._core.util.Script;
import shop.mtcoding.blogv2.user.UserRequest.loginDTO;

// 요청을 잘 받고 위임하고 응답하는 것 
@Controller
public class UserController {

  @Autowired
  private HttpSession session;

  @Autowired // DI하는거
  private UserService userService;

  // C - V
  @GetMapping("/joinForm")
  public String joinForm() {
    return "user/joinForm";
  }

  // M - V - C
  @PostMapping("join")
  public String join(UserRequest.JoinDTO joinDTO) {
    userService.회원가입(joinDTO);
    return "user/loginForm"; // 응답될 때 persist 초기화
  }

  @GetMapping("/loginForm")
  public String loginForm() {
    return "user/loginForm";
  }

  @PostMapping("/login")
  public String login(UserRequest.loginDTO loginDTO) {
    User sessionUser = userService.로그인(loginDTO);
    session.setAttribute("sessionUser", sessionUser);
    return "redirect:/";
  }

  @GetMapping("/user/updateForm")
  public String updateForm(HttpServletRequest request) {
    User sessionUser = (User) session.getAttribute("sessionUser");
    User user = userService.회원정보보기(sessionUser.getId());
    request.setAttribute("user", user);
    return "user/updateForm";
  }

  @PostMapping("/user/update") // 업데이트 쿼리는 반드시 세션을 동기화 해줘야 한다.
  public String update(UserRequest.UpdateDTO updateDTO) {
    // 1. 회원수정 (서비스) - 핵심로직
    // 2. 세션동기화
    User sessionUser = (User) session.getAttribute("sessionUser"); // 세션을 가져온다.
    User user = userService.회원수정(updateDTO, sessionUser.getId()); // 수정이 된 객체를 받음
    session.setAttribute("sessionUser", user); // 수정된 것으로 세션을 동기화 해준다.
    return "redirect:/";
  }

  // 브라우저 GET /logout 요청을 함 (requst 1)
  // 서버는 / 주소를 응답의 헤더에 담음 (Location), 상태코드 302를 응답
  // 브라우저는 GET /로 재요청을 함 (requst 2)
  // index 페이지 응답받고 랜더링함
  @GetMapping("/logout")
  public String logout() {
    session.invalidate();
    return "redirect:/";
  }

}
