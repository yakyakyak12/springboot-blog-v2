package shop.mtcoding.blogv2._core.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

import shop.mtcoding.blogv2._core.filter.MyFilter1;

public class FiterConfig {
    
    @Bean
    public FilterRegistrationBean<MyFilter1> myFilter1(){ // 필터를 등록한
        FilterRegistrationBean<MyFilter1> bean = new FilterRegistrationBean<>();
        bean.addUrlPatterns("/*"); // 슬래시로 시작하는 모든 주소에 발동됨
        bean.setOrder(0); // 낮은 번호부터 실행됨
        return bean; // 필터가 등록된다,
    }

}
