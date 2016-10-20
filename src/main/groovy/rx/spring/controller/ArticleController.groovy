package rx.spring.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import rx.spring.repository.ArticleRepository

@RestController
@RequestMapping('/v1/articles')
class ArticleController {

	@Autowired
	ArticleRepository articleRepository

	@GetMapping
	def list() {
		ResponseEntity.ok(articleRepository.findAll())
	}
}