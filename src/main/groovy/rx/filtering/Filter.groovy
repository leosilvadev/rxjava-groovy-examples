package rx.filtering

import rx.Observable

Observable.just(10)
	.map { it + 20 }
	.map { it * 2 }
	.map { [number: it] }
	.subscribe { println "Event received: $it" }
