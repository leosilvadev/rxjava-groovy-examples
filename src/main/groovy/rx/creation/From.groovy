package rx.creation

import rx.Observable

class From {
	static main(args) {
		Observable.from(*[1..100])
			.map { it * 2 }
			.subscribe { println "[${Thread.currentThread().name}] Event received: $it" }
	}
}