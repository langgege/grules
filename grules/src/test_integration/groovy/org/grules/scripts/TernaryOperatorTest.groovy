package org.grules.scripts

import static org.grules.TestScriptEntities.FUNCTION_FOR_ONE_ARGUMENT
import static org.grules.TestScriptEntities.PARAMETER_NAME
import static org.grules.TestScriptEntities.VALID_INTEGER
import static org.grules.TestScriptEntities.VALID_INTEGER_STRING

import org.grules.GrulesAPI
import org.grules.script.RulesScriptResult

import spock.lang.Specification

class TernaryOperatorTest extends Specification {

  def "Ternary operator true and false expressions are converted to rule expressions"() {
    setup:
      RulesScriptResult result = GrulesAPI.applyRules(TernaryOperatorGrules, [(PARAMETER_NAME):VALID_INTEGER_STRING])
    expect:
      result.cleanParameters[PARAMETER_NAME] == FUNCTION_FOR_ONE_ARGUMENT(VALID_INTEGER)
  }
}
