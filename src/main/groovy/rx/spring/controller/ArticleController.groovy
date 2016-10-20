package rx.spring.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.async.DeferredResult

import rx.spring.service.ArticleService

@RestController
@RequestMapping('/v1/articles')
class ArticleController {

	@Autowired
	ArticleService articleService

	@GetMapping
	def list() {
		def df = new DeferredResult(3000)
		articleService.findAll().map(ResponseEntity.&ok)
	}
}
