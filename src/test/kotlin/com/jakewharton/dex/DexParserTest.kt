package com.jakewharton.dex

import com.google.common.io.Resources
import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameter
import org.junit.runners.Parameterized.Parameters
import java.io.File

@RunWith(Parameterized::class)
class DexParserTest {
  companion object {
    @JvmStatic
    @Parameters(name = "{0}")
    fun parameters() = listOf(arrayOf<Any>(false), arrayOf<Any>(true))
  }

  @Parameter @JvmField var legacyDxParam: Boolean? = null
  private val legacyDx get() = legacyDxParam!!

  @Test fun types() {
    val types = File(Resources.getResource("types.dex").file)
    val methods = DexParser.fromFile(types)
        .withLegacyDx(legacyDx)
        .list()
        .map { it.toString() }
    assertThat(methods).containsExactly(
        "Types <init>()",
        "Types test(String)",
        "Types test(String[])",
        "Types test(boolean)",
        "Types test(byte)",
        "Types test(char)",
        "Types test(double)",
        "Types test(float)",
        "Types test(int)",
        "Types test(long)",
        "Types test(short)",
        "Types returnsRunnable() → Runnable",
        "Types returnsString() → String",
        "Types returnsBoolean() → boolean",
        "Types valueBoolean: boolean",
        "Types valueByte: byte",
        "Types valueChar: char",
        "Types valueDouble: double",
        "Types valueFloat: float",
        "Types valueInt: int",
        "Types valueLong: long",
        "Types valueShort: short",
        "Types valueString: String",
        "Types valueStringArray: String[]",
        "java.lang.Object <init>()")
  }
}
