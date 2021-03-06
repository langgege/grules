package org.grules.functions.lib

import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat

import org.grules.ValidationErrorProperties
import org.grules.ValidationException
import org.grules.functions.Converter
import org.grules.functions.Functions

/**
 * Standard type converters.
 */
@Functions
class TypeFunctions {

  static final String NEGATIVE_ERROR_ID = 'Value is negative'
  static final String NOT_POSITIVE_ERROR_ID = 'Value is not positive'

  private static Exception createValidationException(Exception e) {
    new ValidationException([(ValidationErrorProperties.MESSAGE):e.message,
      (ValidationErrorProperties.EXCEPTION):e])
  }

  /**
   * Parse a String into a BigDecimal.
   */
  BigDecimal toBigDecimal(String value) {
    try {
      value.toBigDecimal()
    } catch (NumberFormatException e) {
      throw createValidationException(e)
    }
  }

  /**
   * Returns true if a value coerces to <code>true</code> and <code>false</code> otherwise.
   */
  @Converter
  Boolean toBoolean(value) {
    value ? true : false
  }

  /**
   * Returns a list of booleans, whose elements identify which elements of the specified <code>values</code> list coerce
   * to <code>true</code>.
   */
  List<Boolean> toBooleanList(List values) {
    values.collect { Object value -> toBoolean(value).value }
  }

  /**
   * Converts a list of string to a list of integers.
   */
  List<Character> toCharList(List<String> values) {
    values.collect { String value -> toChar(value) }
  }

  /**
   * Converts a list of string to a list of integers.
   */
  List<Integer> toIntList(List<String> values) {
    values.collect { String value -> toInt(value) }
  }

  /**
   * Converts a list of string to a list of longs.
   */
  List<Long> toLongList(List<String> values) {
    values.collect { String value -> toLong(value) }
  }

  /**
   * Converts a list of string to a list of big decimals.
   */
  List<BigDecimal> toBigDecimalList(List<String> values) {
    values.collect { String value -> toBigDecimal(value) }
  }

  /**
   * Converts a list of string to a list of floats.
   */
  List<Float> toFloatList(List<String> values) {
    values.collect { String value -> toFloat(value) }
  }

  /**
   * Converts a list of string to a list of doubles.
   */
  List<Double> toDoubleList(List<String> values) {
    values.collect { String value -> toDouble(value) }
  }

  /**
   * Returns the first string character.
   */
  Character toChar(String string) {
    if (string.isEmpty()) {
      throw new ValidationException('String is empty')
    } else {
      string[0]
    }
  }

  /**
   * Parses a value to produce a date.
   */
  Date toDate(String value, String pattern, Locale locale = Locale.default) {
    DateFormat dateFormatter = new SimpleDateFormat(pattern, locale)
    try {
      dateFormatter.parse(value)
    } catch (ParseException e) {
      throw createValidationException(e)
    }
  }

  /**
   * Parse a String into a Double.
   */
  Double toDouble(String value) {
    try {
      value.toDouble()
    } catch (NumberFormatException e) {
      throw createValidationException(e)
    }
  }

  /**
   * Returns the enum constant of the specified enum type with the specified name.
   */
  Enum toEnum(String value, Class enumClass) {
    try {
      value.asType(enumClass)
    } catch (IllegalArgumentException e) {
      throw new ValidationException(e.message)
    }
  }

  /**
   * Parse a String into a Float.
   */
  Float toFloat(String value) {
    try {
      value.toFloat()
    } catch (NumberFormatException e) {
      throw createValidationException(e)
    }
  }

  /**
   * Parse a String into a nonnegative BigDecimal.
   */
  BigDecimal toNonnegativeBigDecimal(String value) {
    BigDecimal bigDecimalValue = toBigDecimal(value)
    if (bigDecimalValue >= 0) {
      bigDecimalValue
    } else {
      throw new ValidationException(NEGATIVE_ERROR_ID)
    }
  }

  /**
   * Parse a String into a nonnegative Double.
   */
  Double toNonnegativeDouble(String value) {
    Double doubleValue = toDouble(value)
    if (doubleValue >= 0) {
      doubleValue
    } else {
      throw new ValidationException(NEGATIVE_ERROR_ID)
    }
  }

  /**
   * Parse a String into a nonnegative Float.
   */
  Float toNonnegativeFloat(String value) {
    Float floatValue = toFloat(value)
    if (floatValue >= 0) {
      floatValue
    } else {
      throw new ValidationException(NEGATIVE_ERROR_ID)
    }
  }

  /**
   * Parse a String into a positive BigDecimal.
   */
  BigDecimal toPositiveBigDecimal(String value) {
    BigDecimal bigDecimalValue = toBigDecimal(value)
    if (bigDecimalValue > 0) {
      bigDecimalValue
    } else {
      throw new ValidationException(NOT_POSITIVE_ERROR_ID)
    }
  }

  /**
   * Parse a String into a positive Double.
   */
  Double toPositiveDouble(String value) {
    Double doubleValue = toDouble(value)
    if (doubleValue > 0) {
      doubleValue
    } else {
      throw new ValidationException(NOT_POSITIVE_ERROR_ID)
    }
  }

  /**
   * Parse a String into a positive Float.
   */
  Float toPositiveFloat(String value) {
    Float floatValue = toFloat(value)
    if (floatValue > 0) {
      floatValue
    } else {
      throw new ValidationException(NOT_POSITIVE_ERROR_ID)
    }
  }

  /**
   * Parse a String into a Long.
   */
  Long toLong(String value) {
    try {
      value.toLong()
    } catch (NumberFormatException e) {
      throw createValidationException(e)
    }
  }

  /**
   * Parse a String into a natural Long.
   */
  Long toNaturalLong(String value) {
    Long longValue = toLong(value)
    if (longValue >= 0) {
      longValue
    } else {
      throw new ValidationException(NEGATIVE_ERROR_ID)
    }
  }

  /**
   * Parse a String into a positive Long.
   */
  Long toPositiveLong(String value) {
    Long longValue = toLong(value)
    if (longValue > 0) {
      longValue
    } else {
      throw new ValidationException(NOT_POSITIVE_ERROR_ID)
    }
  }

  /**
   * Parse a String into an Integer.
   */
  Integer toInt(String value) {
    try {
      value.toInteger()
    } catch (NumberFormatException e) {
      throw createValidationException(e)
    }
  }

  /**
   * Parse a String into a natural Integer.
   */
  Integer toNaturalInt(String value) {
    Integer intValue = toInt(value)
    if (intValue >= 0) {
      intValue
    } else {
      throw new ValidationException(NEGATIVE_ERROR_ID)
    }
  }

  /**
   * Parse a String into a positive Integer.
   */
  Integer toPositiveInt(String value) {
    Integer intValue = toInt(value)
    if (intValue > 0) {
      intValue
    } else {
      throw new ValidationException(NOT_POSITIVE_ERROR_ID)
    }
  }
}

