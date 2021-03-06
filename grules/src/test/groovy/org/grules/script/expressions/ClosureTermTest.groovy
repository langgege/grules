package org.grules.script.expressions

import static org.grules.TestRuleEntriesFactory.createIsEmptyValidator
import spock.lang.Specification

class ClosureTermTest extends Specification {

	def "Closure term applies method closure"() {
		setup:
		  Term term = createIsEmptyValidator()
    expect:
		  term.apply('')
	}
}
