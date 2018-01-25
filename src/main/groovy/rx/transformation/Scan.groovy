package rx.transformation

import rx.Observable

class Scan {
    static main(args) {
        Observable.from(*[1..5])
            .scan(0) { acc, value ->
                acc + value
            }
            .last().subscribe { println "Event received: $it" }
    }
}
