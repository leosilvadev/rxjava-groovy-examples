package rx.error

import rx.Observable

def mustFail = true

def getUser = {
	if (mustFail) {
		println 'Failed!'
		mustFail = false
		throw new RuntimeException('Any error')
	}
	Observable.just(it)
}

Observable.just(1)
	.flatMap(getUser)
	.retry(3)
	.subscribe { println "Even number received: $it" }
