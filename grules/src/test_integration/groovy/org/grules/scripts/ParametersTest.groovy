package org.grules.scripts

import static org.grules.TestScriptEntities.PARAMETER_NAME
import static org.grules.TestScriptEntities.DEFAULT_VALUE
import static org.grules.TestScriptEntities.PARAMETER_VALUE
import static org.grules.TestScriptEntities.INVALID_PARAMETER_VALUE
import static org.grules.TestScriptEntities.PARAMETER_NAME_AUX

import org.grules.GrulesAPI
import org.grules.GrulesLogger
import org.grules.script.RulesScriptResult

import spock.lang.Specification

class ParametersTest extends Specification {

  def setup() {
    GrulesLogger.turnOff()
  }

  def "No such parameter"() {
    when:
      GrulesAPI.applyRules(MissingParameterGrules, [:])
    then:
      thrown(MissingPropertyException)
  }

  def "Required but missing parameter is saved as missing required parameter"() {
    setup:
      RulesScriptResult result = GrulesAPI.applyRules(RequiredGrules, [:])
    expect:
      result.missingRequiredParameters == [PARAMETER_NAME] as Set
  }

  def "Required but empty parameter is saved as missing required parameter"() {
    setup:
      RulesScriptResult result = GrulesAPI.applyRules(RequiredGrules, [(PARAMETER_NAME):''])
    expect:
      result.missingRequiredParameters == [PARAMETER_NAME] as Set
  }

  def "Capitalized parameter names are processed as lowercase parameters"() {
    setup:
      def parameterName = PARAMETER_NAME.capitalize()
      RulesScriptResult result = GrulesAPI.applyRules(RequiredGrules, [(parameterName):PARAMETER_VALUE])
    expect:
      result.cleanParameters.containsKey(parameterName)
  }

  def "Optional parameter for missing value"() {
    setup:
      RulesScriptResult result = GrulesAPI.applyRules(OptionalParameterGrules, [:])
    expect:
      result.cleanParameters[PARAMETER_NAME] == DEFAULT_VALUE
  }

  def "Optional parameter for empty value"() {
    setup:
      RulesScriptResult result = GrulesAPI.applyRules(OptionalParameterGrules, [(PARAMETER_NAME):''])
    expect:
      result.cleanParameters[PARAMETER_NAME] == DEFAULT_VALUE
  }

  def "If rule dependens on invalid parameter it is saved as with missing dependency"() {
    setup:
      def parameters = [(PARAMETER_NAME):INVALID_PARAMETER_VALUE, (PARAMETER_NAME_AUX):PARAMETER_VALUE]
      RulesScriptResult result = GrulesAPI.applyRules(InvalidDependencyParameterGrules, parameters)
    expect:
      PARAMETER_NAME_AUX in result.parametersWithMissingDependency
  }

  def "If rule dependens on missing clean value it is saved as with missing dependency"() {
    setup:
      def parameters = [(PARAMETER_NAME_AUX):PARAMETER_VALUE]
      RulesScriptResult result = GrulesAPI.applyRules(InvalidDependencyParameterGrules, parameters)
    expect:
      PARAMETER_NAME_AUX in result.parametersWithMissingDependency
  }

  def "Invalid parameters are saved as invalid"() {
    setup:
      RulesScriptResult result = GrulesAPI.applyRules(InvalidParameterGrules, [(PARAMETER_NAME):PARAMETER_VALUE])
    expect:
      (result.invalidParameters as Map).containsKey(PARAMETER_NAME)
  }
}
