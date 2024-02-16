/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.modules.xml;

import com.intellij.testFramework.TestDataPath;
import org.jetbrains.kotlin.test.JUnit3RunnerWithInners;
import org.jetbrains.kotlin.test.KotlinTestUtils;
import org.jetbrains.kotlin.test.util.KtTestUtil;
import org.jetbrains.kotlin.test.TestMetadata;
import org.junit.runner.RunWith;

import java.io.File;
import java.util.regex.Pattern;

/** This class is generated by {@link org.jetbrains.kotlin.test.generators.GenerateCompilerTestsKt}. DO NOT MODIFY MANUALLY */
@SuppressWarnings("all")
@TestMetadata("compiler/testData/modules.xml")
@TestDataPath("$PROJECT_ROOT")
@RunWith(JUnit3RunnerWithInners.class)
public class ModuleXmlParserTestGenerated extends AbstractModuleXmlParserTest {
  private void runTest(String testDataFilePath) {
    KotlinTestUtils.runTest(this::doTest, this, testDataFilePath);
  }

  public void testAllFilesPresentInModules_xml() {
    KtTestUtil.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("compiler/testData/modules.xml"), Pattern.compile("^(.+)\\.xml$"), null, true);
  }

  @TestMetadata("allOnce.xml")
  public void testAllOnce() {
    runTest("compiler/testData/modules.xml/allOnce.xml");
  }

  @TestMetadata("comments.xml")
  public void testComments() {
    runTest("compiler/testData/modules.xml/comments.xml");
  }

  @TestMetadata("empty.xml")
  public void testEmpty() {
    runTest("compiler/testData/modules.xml/empty.xml");
  }

  @TestMetadata("emptyModule.xml")
  public void testEmptyModule() {
    runTest("compiler/testData/modules.xml/emptyModule.xml");
  }

  @TestMetadata("manyTimes.xml")
  public void testManyTimes() {
    runTest("compiler/testData/modules.xml/manyTimes.xml");
  }

  @TestMetadata("onlySources.xml")
  public void testOnlySources() {
    runTest("compiler/testData/modules.xml/onlySources.xml");
  }

  @TestMetadata("twoModules.xml")
  public void testTwoModules() {
    runTest("compiler/testData/modules.xml/twoModules.xml");
  }

  @TestMetadata("typeTestModule.xml")
  public void testTypeTestModule() {
    runTest("compiler/testData/modules.xml/typeTestModule.xml");
  }
}
