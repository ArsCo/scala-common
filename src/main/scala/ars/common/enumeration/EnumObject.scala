/*
 * Copyright 2018 Arsen Ibragimov (ars)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ars.common.enumeration

import ars.precondition.MessageBuilder.NoNameParameter
import ars.precondition.require.Require

import scala.util.Try

/** Enumeration object base trait.
  *
  * @tparam EnumValueType the enumeration type
  * @tparam CodeType the enumeration code type
  *
  * @author Arsen Ibragimov (ars)
  * @since 0.0.1
  */
trait EnumObject[EnumValueType <: EnumValue[CodeType], CodeType] {
  import EnumObjectRequireUtils._

  requireUniqueCodes[EnumValueType, CodeType](values, "values")
  requireUniqueNames[EnumValueType](values, "values")

  /** @return all enumeration values (non-null) */
  def values: Seq[EnumValueType]

  /**
    * Gets enum value by code.
    *
    * @param code the code (non-null)
    *
    * @throws IllegalArgumentException if `code` is null
    *
    * @return the enum value
    */
  def valueOf(code: CodeType): Try[EnumValueType] = {
    requireNotNull(code, "code")

    _enumByField(code, "code")(_.code)
  }

  /**
    * Gets enum value by name.
    *
    * @param name the name (non-blank)
    *
    * @throws IllegalArgumentException if `name` is blank
    *
    * @return the enum value
    */
  def valueByName(name: String): Try[EnumValueType] = {
    requireNotBlank(name, "name")

    _enumByField(name, "name")(_.name)

  }

  private type FieldValueExtractor[FieldValue] = EnumValueType => FieldValue

  /**
    * Gets enum value by unique field value.
    *
    * @param value the field value
    * @param name the field name
    * @param extractor the field value extractor
    *
    * @tparam FieldValue the field value type
    *
    * @return the enum value
    */
  private def _enumByField[FieldValue](value: FieldValue, name: String)
                                      (extractor:  FieldValueExtractor[FieldValue]): Try[EnumValueType] = {

    def exception = new IllegalArgumentException(s"Unknown enumeration value with $name '$value'.")

    Try { values.find(extractor(_) == value).getOrElse(throw exception) }
  }
}

/**
  * Utility object to test [[EnumObject]] preconditions.
  *
  * @author Arsen Ibragimov (ars)
  * @since 0.0.1
  */
protected object EnumObjectRequireUtils extends Require {

  /**
    * Tests that iterable has no enum values with the same codes.
    *
    * @param values the values (must be non-null)
    * @param name the parameter name (must be non-null)
    *
    * @tparam EnumValueType the enum value type
    * @tparam CodeType the code type
    */
  def requireUniqueCodes[EnumValueType <: EnumValue[CodeType], CodeType]
                        (values: Iterable[EnumValueType], name: String): Unit = {
    requireUniqueField[EnumValueType, CodeType](values, name, "code")(_.code)
  }

  /**
    * Tests that iterable has no enum values with the same name.
    *
    * @param values the values (must be non-null)
    * @param name the parameter name (must be non-null)
    *
    * @tparam EnumValueType the enum value type
    */
  def requireUniqueNames[EnumValueType <: EnumValue[_]]
                        (values: Iterable[EnumValueType], name: String): Unit = {
    requireUniqueField[EnumValueType, String](values, name, "name")(_.name)
  }
}
