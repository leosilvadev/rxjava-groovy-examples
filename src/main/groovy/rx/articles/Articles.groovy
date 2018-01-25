package rx.articles

import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import groovy.sql.Sql
import rx.Observable
import rx.Subscriber
import rx.schedulers.Schedulers

class Articles {

	static main(args) {
		def sqlInsert = 'insert into articles (title, link, metadata) values (?, ?, ?::jsonb)'

		def dbUrl      = "jdbc:postgresql://localhost/rxjava"
		def dbUser     = "test"
		def dbPassword = "test"
		def dbDriver   = "org.postgresql.Driver"

		def sql = Sql.newInstance(dbUrl, dbUser, dbPassword, dbDriver)

		Observable getArticles = Observable.defer {
			try {
				def json = new URL('https://raw.githubusercontent.com/leosilvadev/rxjava-groovy-examples/master/src/main/resources/articles.json').text
				def articles = new JsonSlurper().parseText(json)
				log "Articles found: $articles"
				Observable.just(articles)
			} catch(ex) {
				Observable.error(ex)
			}
		}

		def splitThem = { articles ->
			log "Splitting ${articles.size()} articles..."
			Observable.from(*articles)
		}

		def insertIt = { article ->
			Observable.defer {
				try {
					log "Inserting Articles $article.title"
					sql.execute(sqlInsert, article.title, article.link, JsonOutput.toJson(article.metadata))
					sleep 1000
					Observable.just article

				} catch(ex) {
					Observable.error ex

				}
			}.subscribeOn(Schedulers.computation())
		}

		getArticles
			.subscribeOn(Schedulers.io())
			.observeOn(Schedulers.computation())
			.flatMap(splitThem)
			.flatMap(insertIt)
			.subscribe { article ->
				log "Article $article.title registered successfully"
			}

		sleep 3000
	}

	static log(message) {
		println "Thread ${Thread.currentThread().name}: $message"
	}

}