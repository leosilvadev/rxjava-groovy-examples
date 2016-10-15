package rx.utils

import java.util.concurrent.TimeUnit

import rx.Observable

def getId = {
	sleep 500
	Observable.just(it)
}

def getName = { id ->
	sleep 700
	Observable.just([id, "User name $id"])
}

Observable.from(*[1..5])
	.flatMap(getId)
	.flatMap(getName)
	.timeout(1, TimeUnit.SECONDS)
	.subscribe {
		def (id, name) = it
		println "Event received. id=($id), name=($name)"
	}
