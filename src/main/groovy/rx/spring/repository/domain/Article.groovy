package rx.spring.repository.domain

import javax.persistence.Entity
import javax.persistence.Id

@Entity(name='articles')
class Article {

	@Id
	Long id
	String title
	String link
}
