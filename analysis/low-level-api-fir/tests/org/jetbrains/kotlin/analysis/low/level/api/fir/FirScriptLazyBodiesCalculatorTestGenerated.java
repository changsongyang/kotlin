/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.analysis.low.level.api.fir;

import com.intellij.testFramework.TestDataPath;
import org.jetbrains.kotlin.test.util.KtTestUtil;
import org.jetbrains.kotlin.test.TestMetadata;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.regex.Pattern;

/** This class is generated by {@link org.jetbrains.kotlin.generators.tests.analysis.api.GenerateAnalysisApiTestsKt}. DO NOT MODIFY MANUALLY */
@SuppressWarnings("all")
@TestMetadata("compiler/fir/raw-fir/psi2fir/testData/rawBuilder")
@TestDataPath("$PROJECT_ROOT")
public class FirScriptLazyBodiesCalculatorTestGenerated extends AbstractFirScriptLazyBodiesCalculatorTest {
  @Test
  public void testAllFilesPresentInRawBuilder() {
    KtTestUtil.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("compiler/fir/raw-fir/psi2fir/testData/rawBuilder"), Pattern.compile("^(.+)\\.(kts)$"), null, true);
  }

  @Nested
  @TestMetadata("compiler/fir/raw-fir/psi2fir/testData/rawBuilder/declarations")
  @TestDataPath("$PROJECT_ROOT")
  public class Declarations {
    @Test
    public void testAllFilesPresentInDeclarations() {
      KtTestUtil.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("compiler/fir/raw-fir/psi2fir/testData/rawBuilder/declarations"), Pattern.compile("^(.+)\\.(kts)$"), null, true);
    }

    @Test
    @TestMetadata("script.kts")
    public void testScript() {
      runTest("compiler/fir/raw-fir/psi2fir/testData/rawBuilder/declarations/script.kts");
    }

    @Test
    @TestMetadata("scriptLevelDestructuringWithAnnotation.kts")
    public void testScriptLevelDestructuringWithAnnotation() {
      runTest("compiler/fir/raw-fir/psi2fir/testData/rawBuilder/declarations/scriptLevelDestructuringWithAnnotation.kts");
    }

    @Test
    @TestMetadata("scriptStatementLevelDestructuringWithAnnotation.kts")
    public void testScriptStatementLevelDestructuringWithAnnotation() {
      runTest("compiler/fir/raw-fir/psi2fir/testData/rawBuilder/declarations/scriptStatementLevelDestructuringWithAnnotation.kts");
    }

    @Test
    @TestMetadata("scriptStatementLevelDestructuringWithAnnotationAsLastStatement.kts")
    public void testScriptStatementLevelDestructuringWithAnnotationAsLastStatement() {
      runTest("compiler/fir/raw-fir/psi2fir/testData/rawBuilder/declarations/scriptStatementLevelDestructuringWithAnnotationAsLastStatement.kts");
    }

    @Nested
    @TestMetadata("compiler/fir/raw-fir/psi2fir/testData/rawBuilder/declarations/contracts")
    @TestDataPath("$PROJECT_ROOT")
    public class Contracts {
      @Test
      public void testAllFilesPresentInContracts() {
        KtTestUtil.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("compiler/fir/raw-fir/psi2fir/testData/rawBuilder/declarations/contracts"), Pattern.compile("^(.+)\\.(kts)$"), null, true);
      }

      @Nested
      @TestMetadata("compiler/fir/raw-fir/psi2fir/testData/rawBuilder/declarations/contracts/newSyntax")
      @TestDataPath("$PROJECT_ROOT")
      public class NewSyntax {
        @Test
        public void testAllFilesPresentInNewSyntax() {
          KtTestUtil.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("compiler/fir/raw-fir/psi2fir/testData/rawBuilder/declarations/contracts/newSyntax"), Pattern.compile("^(.+)\\.(kts)$"), null, true);
        }
      }

      @Nested
      @TestMetadata("compiler/fir/raw-fir/psi2fir/testData/rawBuilder/declarations/contracts/oldSyntax")
      @TestDataPath("$PROJECT_ROOT")
      public class OldSyntax {
        @Test
        public void testAllFilesPresentInOldSyntax() {
          KtTestUtil.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("compiler/fir/raw-fir/psi2fir/testData/rawBuilder/declarations/contracts/oldSyntax"), Pattern.compile("^(.+)\\.(kts)$"), null, true);
        }
      }
    }

    @Nested
    @TestMetadata("compiler/fir/raw-fir/psi2fir/testData/rawBuilder/declarations/noParameterType")
    @TestDataPath("$PROJECT_ROOT")
    public class NoParameterType {
      @Test
      public void testAllFilesPresentInNoParameterType() {
        KtTestUtil.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("compiler/fir/raw-fir/psi2fir/testData/rawBuilder/declarations/noParameterType"), Pattern.compile("^(.+)\\.(kts)$"), null, true);
      }
    }
  }

  @Nested
  @TestMetadata("compiler/fir/raw-fir/psi2fir/testData/rawBuilder/expressions")
  @TestDataPath("$PROJECT_ROOT")
  public class Expressions {
    @Test
    public void testAllFilesPresentInExpressions() {
      KtTestUtil.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("compiler/fir/raw-fir/psi2fir/testData/rawBuilder/expressions"), Pattern.compile("^(.+)\\.(kts)$"), null, true);
    }
  }
}
