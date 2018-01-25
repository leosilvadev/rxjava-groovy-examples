package rx.error

import java.util.concurrent.TimeUnit

import rx.Observable

class RetryDelay {
	static main(args) {
		def mustFail = true

		def getUser = {
			println 'Trying to get the User...'
			if (mustFail) {
				println 'Failed!'
				//mustFail = false
				throw new RuntimeException('Any error')
			}
			Observable.just(it)
		}

		def times = 0

		Observable.just(1)
			.flatMap(getUser)
			.retryWhen({ Observable<Exception> attempts ->
				attempts.flatMap { Exception exception ->
					if (times < 3) {
						times++
						Observable.timer(2, TimeUnit.SECONDS)
					} else {
						Observable.error(exception)
					}
				}
			})
			.subscribe { println "Event received: $it" }

		sleep(10000)
	}
}