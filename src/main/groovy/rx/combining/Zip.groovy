package rx.combining

import rx.Observable

def getId = Observable.create {
	sleep 200
	it.onNext 10
	it.onCompleted()
}

def getName = Observable.create {
	sleep 1000
	it.onNext "User name"
	it.onCompleted()
}

def getAge = Observable.create {
	sleep 500
	it.onNext 30
	it.onCompleted()
}

Observable.from(*[1..5])
	.zip([getId, getName, getAge]) {
		[id: it[0], name: it[1], age: it[2]]
	}
	.subscribe {
		println it
	}