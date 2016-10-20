package rx.spring.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

import rx.spring.repository.domain.Article

@Repository
interface ArticleRepository extends CrudRepository<Article, Long> {
}
