package rx.spring.service

import groovy.util.logging.Slf4j

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import rx.Observable
import rx.Subscriber
import rx.spring.repository.ArticleRepository

@Service
@Slf4j
class ArticleService {

	@Autowired
	ArticleRepository articleRepository

	Observable findAll() {
		react {
			log.info 'Finding all articles. Thread: {}', Thread.currentThread().name
			articleRepository.findAll()
		}
	}


	Observable react(Closure fn) {
		Observable.create { Subscriber sub ->
			try {
				if (!sub.isUnsubscribed()) {
					sub.onNext(fn())
				}
				sub.onCompleted()
			} catch(ex) {
				sub.onError(ex)
			}
		}
	}
}
