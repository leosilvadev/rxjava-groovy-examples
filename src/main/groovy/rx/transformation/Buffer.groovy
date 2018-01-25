package rx.transformation

import java.util.concurrent.TimeUnit

import rx.Observable

class Buffer {
    static main(args) {
        println '>>>>>>>>>>>>>>>>>>>>>>>>> BUFFERING BY NUMBER OF EVENTS'

        Observable.from(*[1..100])
            .buffer(50)
            .map { [number: it] }
            .subscribe { println "Event received: $it" }

        println '<<<<<<<<<<<<<<<<<<<<<<<<< BUFFERING BY NUMBER OF EVENTS\n\n'

        println '>>>>>>>>>>>>>>>>>>>>>>>>> BUFFERING BY TIME'

        Observable.from(*[1..100])
            .flatMap {
                sleep 10
                Observable.just([id: it])
            }
            .buffer(100, TimeUnit.MILLISECONDS)
                .map { [number: it] }
                .subscribe { println "Event received: $it" }

        println '<<<<<<<<<<<<<<<<<<<<<<<<< BUFFERING BY TIME\n\n'
    }
}