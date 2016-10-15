package rx.creation

import rx.Observable

Observable.just(10)
	.subscribe { println "Event received: $it" }