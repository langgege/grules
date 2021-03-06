package org.grules

import java.util.logging.Level

import org.grules.config.GrulesConfig
import org.grules.config.GrulesConfigFactory
import org.grules.config.OnValidationEventAction
import org.grules.http.HttpRequestParametersGroup

/**
 * Entities used in tests.
 */
class TestScriptEntities {

  static final GrulesConfig TEST_CONFIG = new GrulesConfigFactory().createDefaultConfig()

	static final List FALSE_PARAMETER = []
	static final String PARAMETER_NAME = 'parameterName'
	static final String PARAMETER_NAME_AUX = 'parameterNameAux'
	static final String PARAMETER_VALUE = 'parameterValue'
	static final String PARAMETER_VALUE_AUX = 'parameterValueAux'
	static final String CLEAN_PARAMETER_VALUE = 'cleanParameterValue'
	static final String VARIABLE_NAME = 'variableName'
	static final String VARIABLE_NAME_AUX = 'variableNameAux'
	static final String VARIABLE_VALUE = 'variableValue'
	static final String CONSTANT_NAME = 'constantName'
	static final String CONSTANT_VALUE = 'constantValue'
	static final String DEFAULT_VALUE = 'defaultValue'
	static final String DATE_FORMAT = 'yyyy-MM-dd'
	static final String ERROR_ID = 'errorId'
  static final String ERROR_MESSAGE = 'errorMessage'
	static final Integer VALID_INTEGER = 1
	static final String VALID_INTEGER_STRING = VALID_INTEGER
	static final String FUNCTION_ARGUMENT = VALID_INTEGER_STRING
	static final String FUNCTION_NAME = 'functionName'
	static final String VALID_PARAMETER = VALID_INTEGER_STRING
	static final String INVALID_PARAMETER_VALUE = '1a'
	static final String SUBRULES_SEQ_NAME = 'subrulesSeqName'
	static final String GROUP = TEST_CONFIG.defaultGroup
	static final String GROUP_AUX = HttpRequestParametersGroup.GET.name()
	static final Level CONFIG_LOG_LEVEL = Level.INFO
  static final OnValidationEventAction NOT_VALIDATED_PARAMETERS_ACTION = TEST_CONFIG.notValidatedParametersAction
  static final String JOIN_SEPARATOR = ' '
  static final Closure FUNCTION_FOR_LIST = { list -> list.join(JOIN_SEPARATOR) }
  static final Closure FUNCTION_FOR_ONE_ARGUMENT = { it + 1 }
  static final Closure FUNCTION_FOR_TWO_ARGUMENTS = { value1, value2 -> value1 + value2 }
}
