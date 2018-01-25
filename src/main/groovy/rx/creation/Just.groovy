package rx.creation

import rx.Observable

class Just {
	static main(args) {
		Observable.just(10)
			.subscribe { println "Event received: $it" }
	}
}