package rx.spring.config

import javax.annotation.PostConstruct

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.method.support.HandlerMethodReturnValueHandler
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter

import rx.spring.handler.ObservableReturnValueHandler

@Configuration
class WebConfig {

	@Autowired
	RequestMappingHandlerAdapter requestMappingHandlerAdapter
	
	@PostConstruct
	void init() {
		def handlers = new ArrayList(requestMappingHandlerAdapter.getReturnValueHandlers())
		requestMappingHandlerAdapter.setReturnValueHandlers([observableReturnValueHandler()] + handlers)
	}
	
	@Bean
	HandlerMethodReturnValueHandler observableReturnValueHandler() {
		new ObservableReturnValueHandler()
	}
}