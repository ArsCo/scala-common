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

import scala.language.implicitConversions

/** Range implicit conversions.
  *
  * @author Arsen Ibragimov (ars)
  * @since 0.0.1
  */
object implicits {

  /** Implicit conversions of any value to [[Bound]].
    *
    * @param value the value (must be non-null)
    * @tparam T the value type
    * @author Arsen Ibragimov (ars)
    * @since 0.0.1
    */
  implicit class RichBound[+T](val value: T) extends AnyVal {

    /**
      * Converts implicitly value to [[Incl]] of value.
      *
      * @return the [[Incl]] of value (non-null)
      */
    def incl: Incl[T] = Incl(value)

    /**
      * Converts implicitly value to [[Excl]] of value.
      *
      * @return the [[Excl]] of value (non-null)
      */
    def excl: Excl[T] = Excl(value)
  }
}
