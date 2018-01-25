package rx.transformation

import rx.Observable
import rx.internal.operators.OperatorGroupBy
import rx.internal.operators.OperatorGroupBy.GroupedUnicast

class GroupBy {
	static main(args) {
		Observable.from(*[1..20])
			.groupBy {
				it % 2 == 0
			}
			.flatMap { GroupedUnicast i -> i.toList() }
				.subscribe { println "Event received: $it" }
	}
}
