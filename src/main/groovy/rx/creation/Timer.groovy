package rx.creation

import java.util.concurrent.TimeUnit

import rx.Observable

class Timer {
	static main(args) {
		Observable.from(*[1..5])
			.flatMap { number ->
			println "[${Thread.currentThread().name}] Multiply by 2"
			Observable.timer(1, TimeUnit.SECONDS).map({ number * 2 })
		}
		.flatMap { number ->
			println "[${Thread.currentThread().name}] Multiply by 3"
			Observable.timer(1, TimeUnit.SECONDS).map({ number * 3 })
		}
		.subscribe { println "Event received: $it" }

		sleep 5000
	}
}