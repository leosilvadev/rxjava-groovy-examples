package rx.creation

import rx.Observable
import rx.Subscriber

def obs = Observable.create { Subscriber subscriber ->
	println 'Emiting number 10'
	subscriber.onNext 10
	subscriber.onCompleted()
	
//	subscriber.onError new RuntimeException('Any error')
}

def onEvent = { println 'New Event!' }
def onError = { println 'Error!' }
def onCompleted = { println 'Completed!' }

obs.subscribe(onEvent, onError, onCompleted)

