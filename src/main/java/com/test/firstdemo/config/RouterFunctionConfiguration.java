package com.test.firstdemo.config;

import java.util.Collection;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.test.firstdemo.entity.User;
import com.test.firstdemo.repository.UserRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 
 * @author tangqichang
 *
 * 2019年1月24日-上午9:46:07
 * 路由器函数 配置
 */
@Configuration
public class RouterFunctionConfiguration {
	/**
	 * Servlet
	 * 请求接口：ServletRequest HttpServletRequest
	 * 响应接口：ServletResponse HttpServletResponse
	 * Spring5.0重新定义了服务请求和响应接口
	 * 请求接口：ServerRequest
	 * 响应接口：ServerResponse
	 * 既可支持Servlet规范，也可以支持自定义，比如Netty(Web Server)
	 * 以本例：
	 * 定义Get请求，并且返回所有的用户对象URI：/person/find/all
	 * Flux是0-N个集合
	 * Mono是0-1个集合
	 * Reactive中的Flux或者Mono是异步处理(非阻塞)
	 */
	@Bean
	@Autowired
	public RouterFunction<ServerResponse> personFindAll(UserRepository userRepository){
		
		return RouterFunctions.route(RequestPredicates.GET("/person/find/all"), request ->{
			   //返回所有数据对象
			   Collection<User> users = userRepository.findAll();
			   Flux<User> userFlux = Flux.fromIterable(users);
			   return ServerResponse.ok().body(userFlux, User.class);
			});
		
	}
}
