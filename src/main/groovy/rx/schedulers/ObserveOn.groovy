package rx.schedulers

import rx.Observable
import rx.Subscriber

class ObserveOn {
    static main(args) {
        def generator = Observable.create { Subscriber sub ->
            (1..10).each {
                sleep 100
                log "Generating event with number $it"
                sub.onNext it
            }
            sub.onCompleted()
        }

        def multiply = { value ->
            Observable.defer {
                sleep 100
                log "Multipling $value by 2: ${value * 2}"
                Observable.just(value * 2)

            }.observeOn(Schedulers.computation())
        }

        generator
            .flatMap(multiply)
            .map { [number: it] }
            .subscribe({
                log "New event received: $it"
            }, {}, {
                println "Calc completed!"
            })

        sleep 3000
    }

    static log(message) {
        println "Thread ${Thread.currentThread().name}: $message"
    }
}