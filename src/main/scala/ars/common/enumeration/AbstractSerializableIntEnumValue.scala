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

import scala.reflect.runtime.universe._


/** Serializable to `Int` Scala enumeration value implementation.
  * This abstract class already implements method `objectTypeTag()` of [[SerializableIntEnumValue]].
  *
  * @tparam EnumValueType the enumeration value type
  * @tparam EnumObjectType the enumeration object type
  *
  * @author Arsen Ibragimov (ars)
  * @since 0.0.1
  */
abstract class AbstractSerializableIntEnumValue[
    EnumValueType <: EnumValue[Int],
    EnumObjectType <: EnumObject[EnumValueType, Int]: TypeTag
](override val code: Int) extends SerializableIntEnumValue[EnumValueType, EnumObjectType] {

  override protected[this] def objectTypeTag(): TypeTag[EnumObjectType] = typeTag[EnumObjectType]
}
