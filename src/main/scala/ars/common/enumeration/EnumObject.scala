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

import ars.precondition.require.Require.Default._

import scala.util.{Failure, Success, Try}

/** Enumeration object base trait.
  *
  * @tparam EnumValueType the enumeration type
  * @tparam CodeType the enumeration code type
  *
  * @author Arsen Ibragimov (ars)
  * @since 0.0.1
  */
trait EnumObject[EnumValueType <: EnumValue[CodeType], CodeType] {

  /** All values. */
  def values: Seq[EnumValueType]

  /**
    * Gets enum value by code.
    *
    * @param code the code (non-null)
    *
    * @return the value
    */
  def valueOf(code: CodeType): Try[EnumValueType] = {
    requireNotNull(code, "code")

    values.filter(_.code == code) match {
      case value :: _ => Success(value)
      case _ => Failure(new IllegalArgumentException(s"Unknown enumeration value for code '$code'."))
    }
  }
}
