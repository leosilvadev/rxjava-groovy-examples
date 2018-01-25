package rx.spring.service

import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import rx.Observable
import rx.spring.repository.ArticleRepository

import static rx.Observable.*

@Service
@Slf4j
class ArticleService {

	@Autowired
	ArticleRepository articleRepository

	Observable findAll() {
		defer {
			log.info 'Finding all articles. Thread: {}', Thread.currentThread().name
			just articleRepository.findAll()
		}
	}

}
