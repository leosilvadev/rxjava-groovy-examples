package rx.creation

import rx.Observable

Observable.from(*[1..100])
	.subscribe { println "Event received: $it" }