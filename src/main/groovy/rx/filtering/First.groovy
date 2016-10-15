package rx.filtering

import rx.Observable

Observable.from(*[1..10])
	.first()
	.map { [number: it] }
	.subscribe { println "First event received: $it" }
