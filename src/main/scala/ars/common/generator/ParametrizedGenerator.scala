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

package ars.common.generator


/** Parametrized random value generator.
  *
  * @tparam T the generating value type
  * @tparam P the parameters value type
  *
  * @author Arsen Ibragimov (ars)
  * @since 0.0.5
  */
trait ParametrizedGenerator[T, P] extends Generatable[T] {

  /**
    * Generates next value.
    *
    * @param constraints the generation constraints
    *
    * @return the next generated value
    */
  def next(constraints: P): T
}

/**
  * Generator with default params.
  *
  * @tparam T the generating value type
  * @tparam P the default parameters value type
  *
  * @author Arsen Ibragimov (ars)
  * @since 0.0.5
  */
trait WithDefaultParams[T, P] extends ParametrizedGenerator[T, P] {

  /**
    * @return default params object (non-null)
    */
  def defaultParams(): P
}
