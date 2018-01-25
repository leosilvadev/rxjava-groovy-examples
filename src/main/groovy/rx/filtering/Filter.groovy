package rx.filtering

import rx.Observable

class Filter {
	static main(args) {
		Observable.just(11)
			.filter { it % 2 == 0}
			.subscribe { println "Event received: $it" }
	}
}
