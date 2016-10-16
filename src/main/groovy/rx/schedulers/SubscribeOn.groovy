package rx.schedulers

import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

import rx.Observable
import rx.Subscriber

def executor = Executors.newFixedThreadPool(2)

def generator = Observable.create { Subscriber sub ->
	(1..10).each {
		sleep 100
		log "Generating event with number $it"
		sub.onNext it
	}
	sub.onCompleted()
}

def multiply = { value ->
	Observable.create { Subscriber sub ->
		sleep 100
		log "Multipling $value by 2: ${value * 2}"
		sub.onNext(value * 2)
		sub.onCompleted()
	}.subscribeOn(Schedulers.computation())
}

generator
	.subscribeOn(Schedulers.computation())
	.flatMap(multiply)
	.map { [number: it] }
	.subscribe({
		log "New event received: $it"
	}, {}, {
		println "Calc completed!"
	})

sleep 3000


def log(message) {
	println "Thread ${Thread.currentThread().name}: $message"
}