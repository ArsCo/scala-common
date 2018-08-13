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

package ars.common.exception

import ars.common.AbstractBaseTest

/**
  *
  *
  * @author Arsen Ibragimov (ars)
  * @since 0.0.1
  */
class ExceptionUtilsTest extends AbstractBaseTest {

  "wrapInRuntimeException" must "wraps non-runtime throwable into runtime" in {

    val e = new Exception
    assert(!e.isInstanceOf[RuntimeException])

    val r = ExceptionUtils.wrapInRuntimeException(e)
    assert(r.isInstanceOf[RuntimeException])
    assert(r.getCause != null)
    assert(r.getCause eq e)
  }

  it must "do nothing if throwable ars is already runtime exception" in {
    val e = new RuntimeException
    val r = ExceptionUtils.wrapInRuntimeException(e)
    assert(r == e)

    val e1 = new RuntimeException {} // Subclass of RuntimeException
    val r1 = ExceptionUtils.wrapInRuntimeException(e1)
    assert(r1 == e1)
  }
}
