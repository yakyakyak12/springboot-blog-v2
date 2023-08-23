package shop.mtcoding.blogv2._core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration // 설정 파일을 정해준다. (메모리에 뜬다)
public class WebMvcConfig implements WebMvcConfigurer{

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        WebMvcConfigurer.super.addResourceHandlers(registry); // 기존에 하는 일 (지우면 안됨)

        registry.addResourceHandler("/images/**") // 누군가 /images//**(머든지) 요청을 하면
        .addResourceLocations("file:"+"./images/") // ./images/ 로 가서 **칸에 들어가는 값을 찾아준다.
        .setCachePeriod(10) // 10 (초) (초단위)
        .resourceChain(true) // 안중요함 생각없이 걸면됨
        .addResolver(new PathResourceResolver());
    }
    
    
}
