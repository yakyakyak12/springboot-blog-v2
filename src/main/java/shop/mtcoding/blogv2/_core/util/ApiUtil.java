package shop.mtcoding.blogv2._core.util;

import lombok.Getter;
import lombok.Setter;

@Getter 
@Setter
public class ApiUtil<T> {
    private boolean sucuess; // true
    private T data; // 댓글쓰기 성공 

    // 공통 DTO라고 합니다. 
    public ApiUtil(boolean sucuess, T data) {
        this.sucuess = sucuess;
        this.data = data;
    }
    
}
