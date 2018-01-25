package rx.creation

import rx.Observable
import rx.Subscriber

class Create {

    static main(args) {
        Observable obs = Observable.create { Subscriber subscriber ->
            println 'Emiting number 10'
            subscriber.onNext 10
//            subscriber.onCompleted()

            subscriber.onError new RuntimeException('Any error')
        }

        def onEvent = { println 'New Event!' }
        def onError = { println 'Error!' }
        def onCompleted = { println 'Completed!' }

        obs
            .onErrorResumeNext {

            }
            .subscribe(onEvent, onError, onCompleted)
    }

}

