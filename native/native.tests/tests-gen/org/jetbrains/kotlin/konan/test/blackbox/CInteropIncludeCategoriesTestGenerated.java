/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.konan.test.blackbox;

import com.intellij.testFramework.TestDataPath;
import org.jetbrains.kotlin.test.util.KtTestUtil;
import org.jetbrains.kotlin.test.TestMetadata;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.regex.Pattern;

/** This class is generated by {@link org.jetbrains.kotlin.generators.tests.GenerateNativeTestsKt}. DO NOT MODIFY MANUALLY */
@SuppressWarnings("all")
@TestMetadata("native/native.tests/testData/CInterop/frameworkIncludeCategories/cases")
@TestDataPath("$PROJECT_ROOT")
public class CInteropIncludeCategoriesTestGenerated extends AbstractNativeCInteropIncludeCategoriesTest {
  @Test
  public void testAllFilesPresentInCases() {
    KtTestUtil.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("native/native.tests/testData/CInterop/frameworkIncludeCategories/cases"), Pattern.compile("^([^_](.+))$"), null, false);
  }

  @Test
  @TestMetadata("smoke0")
  public void testSmoke0() {
    runTest("native/native.tests/testData/CInterop/frameworkIncludeCategories/cases/smoke0/");
  }

  @Test
  @TestMetadata("smoke1")
  public void testSmoke1() {
    runTest("native/native.tests/testData/CInterop/frameworkIncludeCategories/cases/smoke1/");
  }

  @Test
  @TestMetadata("smoke2")
  public void testSmoke2() {
    runTest("native/native.tests/testData/CInterop/frameworkIncludeCategories/cases/smoke2/");
  }

  @Test
  @TestMetadata("smoke3")
  public void testSmoke3() {
    runTest("native/native.tests/testData/CInterop/frameworkIncludeCategories/cases/smoke3/");
  }

  @Test
  @TestMetadata("smoke4")
  public void testSmoke4() {
    runTest("native/native.tests/testData/CInterop/frameworkIncludeCategories/cases/smoke4/");
  }
}
