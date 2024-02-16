/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.analysis.api.standalone.fir.test.cases.generated.cases.components.diagnosticProvider;

import com.intellij.testFramework.TestDataPath;
import org.jetbrains.kotlin.test.util.KtTestUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.kotlin.analysis.api.standalone.fir.test.configurators.AnalysisApiFirStandaloneModeTestConfiguratorFactory;
import org.jetbrains.kotlin.analysis.test.framework.test.configurators.AnalysisApiTestConfiguratorFactoryData;
import org.jetbrains.kotlin.analysis.test.framework.test.configurators.AnalysisApiTestConfigurator;
import org.jetbrains.kotlin.analysis.test.framework.test.configurators.TestModuleKind;
import org.jetbrains.kotlin.analysis.test.framework.test.configurators.FrontendKind;
import org.jetbrains.kotlin.analysis.test.framework.test.configurators.AnalysisSessionMode;
import org.jetbrains.kotlin.analysis.test.framework.test.configurators.AnalysisApiMode;
import org.jetbrains.kotlin.analysis.api.impl.base.test.cases.components.diagnosticProvider.AbstractCodeFragmentCollectDiagnosticsTest;
import org.jetbrains.kotlin.test.TestMetadata;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.regex.Pattern;

/** This class is generated by {@link org.jetbrains.kotlin.generators.tests.analysis.api.GenerateAnalysisApiTestsKt}. DO NOT MODIFY MANUALLY */
@SuppressWarnings("all")
@TestMetadata("analysis/analysis-api/testData/components/diagnosticsProvider/codeFragmentDiagnostics")
@TestDataPath("$PROJECT_ROOT")
public class FirStandaloneNormalAnalysisSourceModuleCodeFragmentCollectDiagnosticsTestGenerated extends AbstractCodeFragmentCollectDiagnosticsTest {
  @NotNull
  @Override
  public AnalysisApiTestConfigurator getConfigurator() {
    return AnalysisApiFirStandaloneModeTestConfiguratorFactory.INSTANCE.createConfigurator(
      new AnalysisApiTestConfiguratorFactoryData(
        FrontendKind.Fir,
        TestModuleKind.Source,
        AnalysisSessionMode.Normal,
        AnalysisApiMode.Standalone
      )
    );
  }

  @Test
  public void testAllFilesPresentInCodeFragmentDiagnostics() {
    KtTestUtil.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("analysis/analysis-api/testData/components/diagnosticsProvider/codeFragmentDiagnostics"), Pattern.compile("^([^.]+)\\.kt$"), null, true);
  }

  @Test
  @TestMetadata("evalJvm.kt")
  public void testEvalJvm() {
    runTest("analysis/analysis-api/testData/components/diagnosticsProvider/codeFragmentDiagnostics/evalJvm.kt");
  }

  @Test
  @TestMetadata("evalJvmJs.kt")
  public void testEvalJvmJs() {
    runTest("analysis/analysis-api/testData/components/diagnosticsProvider/codeFragmentDiagnostics/evalJvmJs.kt");
  }

  @Test
  @TestMetadata("localClass.kt")
  public void testLocalClass() {
    runTest("analysis/analysis-api/testData/components/diagnosticsProvider/codeFragmentDiagnostics/localClass.kt");
  }

  @Test
  @TestMetadata("localFunction.kt")
  public void testLocalFunction() {
    runTest("analysis/analysis-api/testData/components/diagnosticsProvider/codeFragmentDiagnostics/localFunction.kt");
  }

  @Test
  @TestMetadata("typeMismatch.kt")
  public void testTypeMismatch() {
    runTest("analysis/analysis-api/testData/components/diagnosticsProvider/codeFragmentDiagnostics/typeMismatch.kt");
  }
}
