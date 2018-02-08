/*
 *  Copyright 2018 JetBrains s.r.o.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.jetbrains.kotlinx.serialization.sourcegen

import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.asTypeName
import java.io.File

fun test(): FileSpec {
    val spec = serializableClass("", "MyData") {
        dataClass = true
        property("x", Int::class.asTypeName().asNullable()) {}
        property("y", String::class) {
            optional = true
            defaultValue = "\"kek\""
        }
    }.render()
    return FileSpec.builder("", "HelloWorld").indent("    ").addType(spec).build()
}

fun main(args: Array<String>) {
    val f = File("./src/test/kotlin")
    val test = test()
    test.writeTo(f)
    test.writeTo(System.out)
}