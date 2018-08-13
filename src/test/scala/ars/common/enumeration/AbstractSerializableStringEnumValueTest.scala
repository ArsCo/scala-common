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

import java.io.{ByteArrayInputStream, ByteArrayOutputStream, ObjectInputStream, ObjectOutputStream}

import ars.common.AbstractBaseTest

/** Tests for [[AbstractSerializableStringEnumValue]].
  *
  * @author Arsen Ibragimov (ars)
  * @since 0.0.1
  */
class AbstractSerializableStringEnumValueTest extends AbstractBaseTest {

  "AbstractSerializableStringEnumValue" must "be serializable" in {
    val oos = new ObjectOutputStream(new ByteArrayOutputStream())
    oos.writeObject(AbsMyStringEnumValues.FirstValue)
  }

  it must "be deserializable" in {
    val os = new ByteArrayOutputStream()
    val oos = new ObjectOutputStream(os)
    oos.writeObject(AbsMyStringEnumValues.FirstValue)
    oos.flush()

    val serializedEnum = os.toByteArray

    val ois = new ObjectInputStream(new ByteArrayInputStream(serializedEnum))
    val obj = ois.readObject()
    assert(obj.isInstanceOf[AbsMyStringEnumValues.FirstValue.type], obj.getClass.getCanonicalName)
  }
}

sealed abstract class AbsMyStringEnumValue(override val code: String)
  extends AbstractSerializableStringEnumValue[AbsMyStringEnumValue, AbsMyStringEnumValues.type](code)

object AbsMyStringEnumValues extends EnumObject[AbsMyStringEnumValue, String] {
  final case object FirstValue  extends AbsMyStringEnumValue("first")
  final case object SecondValue extends AbsMyStringEnumValue("second")
  final case object ThirdValue  extends AbsMyStringEnumValue("third")

  override def values: Seq[AbsMyStringEnumValue] = Seq(FirstValue, SecondValue, ThirdValue)
}
