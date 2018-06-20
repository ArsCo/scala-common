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

import java.io.{ObjectInputStream, ObjectOutputStream}

import com.sun.tools.javac.code.TypeTag

/** Serializable to `Int` Scala enumeration value.
  *
  * @tparam EnumType the enumeration value type
  * @author Arsen Ibragimov (ars)
  * @since 0.0.1
  */
abstract class SerializableIntEnumValue[EnumType: TypeTag](val code: Int)
  extends SerializableEnumValue[EnumType, Int] with Serializable {

  def serialize(out: ObjectOutputStream): Unit = out.writeInt(code)
  def deserialize(in: ObjectInputStream): Int = in.readInt()
}
