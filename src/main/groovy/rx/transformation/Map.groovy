package rx.transformation

import rx.Observable

Observable.from(*[1..20])
	.filter { it % 2 == 0 }
	.map { [number: it] }
	.subscribe { println "Even number received: $it" }
