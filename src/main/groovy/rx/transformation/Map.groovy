package rx.transformation

import rx.Observable

class Map {
	static main(args) {
		Observable.from(*[1..20])
			.map { [number: it] }
			.last()
			.subscribe { println "Even number received: $it" }
	}
}
