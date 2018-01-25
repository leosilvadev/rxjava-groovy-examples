package rx.error

import rx.Observable

class Retry {
	static main(args) {
		def attempts = 0

		def getUser = {
			if (attempts < 3) {
				println 'Failed!'
				attempts++
				throw new RuntimeException('Any error')
			}
			Observable.just(it)
		}

		Observable.just(1)
			.flatMap(getUser)
			.retry(3)
			.subscribe { println "Event received: $it" }
	}
}
