package rx.filtering

import rx.Observable

Observable.from(*[1..10])
	.last()
	.map { [number: it] }
	.subscribe { println "Last event received: $it" }
