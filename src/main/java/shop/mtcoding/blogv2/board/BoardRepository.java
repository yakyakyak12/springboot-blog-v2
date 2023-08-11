package shop.mtcoding.blogv2.board;

import org.springframework.data.jpa.repository.JpaRepository;

/*
 * save(), findById(), findAll(), count(), deletById() // update빼고 다나옴 
 */

public interface BoardRepository extends JpaRepository<Board, Integer> {

}
