package shop.mtcoding.blogv2.user;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shop.mtcoding.blogv2.user.UserRequest.UpdateDTO;
import shop.mtcoding.blogv2.user.UserRequest.loginDTO;

// 핵심로직 처리, 트랜잭션 관리, 예외 처리 
@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  @Transactional
  public void 회원가입(UserRequest.JoinDTO joinDTO) {
    User user = User.builder()
        .username(joinDTO.getUsername())
        .password(joinDTO.getPassword())
        .email(joinDTO.getEmail())
        .build();
    userRepository.save(user); // em.persist
    // user를 관리하는 레파지토리이기 때문에 user를 넘겨줘야함
  }

  public User 로그인(loginDTO loginDTO) {
    User user = userRepository.findByUsername(loginDTO.getUsername());
    // 1. 유저네임 검증
    if (user == null) {
      return null;
    }

    // 2. 패스워드 검증
    if (!user.getPassword().equals(loginDTO.getPassword())) {
      return null;
    }

    return user;
  }

  public User 회원정보보기(Integer id) {
    return userRepository.findById(id).get();
  }

  @Transactional // 붙이지 않으면 flush가 안됨
  public User 회원수정(UpdateDTO updateDTO, Integer id) {
    // 1. 조회 (영속화)
    User user = userRepository.findById(id).get();
    // 2. 변경
    user.setPassword(updateDTO.getPassword());
    return user;
  }// 3. flush

}
