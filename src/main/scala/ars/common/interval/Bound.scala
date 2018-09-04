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

package ars.common.interval

import ars.precondition.require.Require.Default._

/** Bound.
  *
  * @author Arsen Ibragimov (ars)
  * @since 0.0.1
  */
@SerialVersionUID(1L)
sealed abstract class Bound[+T] extends Finiteable with Serializable {

  /** Returns the bounds's value.
    *
    *  @note The option must be finite.
    *
    *  @throws java.util.NoSuchElementException if the option is not finite.
    */
  def value: T
}

/** Finite bound.
  *
  * @param v the bounded value (must be non-null)
  *
  * @tparam T the value type
  *
  * @author Arsen Ibragimov (ars)
  * @since 0.0.1
  */
@SerialVersionUID(1L)
sealed abstract class FiniteBound[+T](v: T) extends Bound[T] with Serializable {
  requireNotNull(v, "value")

  /**
    * Is included value.
    *
    * @return `true` if inclusive, `false` otherwise
    */
  def isIncluded: Boolean

  /**
    * Is excluded value.
    *
    * @return `true` if exclusive, `false` otherwise
    */
  @inline def isExcluded: Boolean = !isIncluded

  override val value: T = v
  override val isFinite: Boolean = true
}

/** Inclusive bound.
  *
  * @author Arsen Ibragimov (ars)
  * @since 0.0.1
  */
@SerialVersionUID(1L)
final case class Incl[+T](v: T) extends FiniteBound(v) {
  @inline override val isIncluded: Boolean = true
}

/** Exclusive bound.
  *
  * @author Arsen Ibragimov (ars)
  * @since 0.0.1
  */
@SerialVersionUID(1L)
final case class Excl[+T](v: T) extends FiniteBound(v) {
  @inline override val isIncluded: Boolean = false
}
