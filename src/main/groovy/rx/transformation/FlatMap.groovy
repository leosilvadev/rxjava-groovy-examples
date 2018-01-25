package rx.transformation

import rx.Observable

class FlatMap {
	static main(args) {
		def expensivePlus = {
			sleep 200
			Observable.just it + it
		}

		def cheapMinusTwo = {
			it - 2
		}

		println '>>>>>>>>>>>>>>>>>>>>>>>>> ONE EVENT GENERATING A NEW ONE'

		Observable.from(*[1..10])
			.flatMap(expensivePlus)
			.map(cheapMinusTwo)
			.subscribe { println "Event received: $it" }

		println '<<<<<<<<<<<<<<<<<<<<<<<<< ONE EVENT GENERATING A NEW ONE\n\n'

		println '>>>>>>>>>>>>>>>>>>>>>>>>> ONE EVENT GENERATING MANY'

		def splitValue = {
			Observable.from(*[0..it])
		}

		Observable.from(*[1..10])
			.flatMap(splitValue)
			.subscribe { println "Event received: $it" }

		println '<<<<<<<<<<<<<<<<<<<<<<<<< ONE EVENT GENERATING MANY\n\n'
	}
}
