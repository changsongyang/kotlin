/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.analysis.api.standalone.fir.test.cases.generated.cases.components.expressionTypeProvider;

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
import org.jetbrains.kotlin.analysis.api.impl.base.test.cases.components.expressionTypeProvider.AbstractDeclarationReturnTypeTest;
import org.jetbrains.kotlin.test.TestMetadata;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.regex.Pattern;

/** This class is generated by {@link org.jetbrains.kotlin.generators.tests.analysis.api.GenerateAnalysisApiTestsKt}. DO NOT MODIFY MANUALLY */
@SuppressWarnings("all")
@TestMetadata("analysis/analysis-api/testData/components/expressionTypeProvider/declarationReturnType")
@TestDataPath("$PROJECT_ROOT")
public class FirStandaloneNormalAnalysisSourceModuleDeclarationReturnTypeTestGenerated extends AbstractDeclarationReturnTypeTest {
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
  public void testAllFilesPresentInDeclarationReturnType() {
    KtTestUtil.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("analysis/analysis-api/testData/components/expressionTypeProvider/declarationReturnType"), Pattern.compile("^(.+)\\.kt$"), null, true);
  }

  @Test
  @TestMetadata("declarationInPrimaryConstructor.kt")
  public void testDeclarationInPrimaryConstructor() {
    runTest("analysis/analysis-api/testData/components/expressionTypeProvider/declarationReturnType/declarationInPrimaryConstructor.kt");
  }

  @Test
  @TestMetadata("delegateProperties.kt")
  public void testDelegateProperties() {
    runTest("analysis/analysis-api/testData/components/expressionTypeProvider/declarationReturnType/delegateProperties.kt");
  }

  @Test
  @TestMetadata("functionWithoutParameterList.kt")
  public void testFunctionWithoutParameterList() {
    runTest("analysis/analysis-api/testData/components/expressionTypeProvider/declarationReturnType/functionWithoutParameterList.kt");
  }

  @Test
  @TestMetadata("funtionType.kt")
  public void testFuntionType() {
    runTest("analysis/analysis-api/testData/components/expressionTypeProvider/declarationReturnType/funtionType.kt");
  }

  @Test
  @TestMetadata("localDeclarations.kt")
  public void testLocalDeclarations() {
    runTest("analysis/analysis-api/testData/components/expressionTypeProvider/declarationReturnType/localDeclarations.kt");
  }

  @Test
  @TestMetadata("overriddenMember.kt")
  public void testOverriddenMember() {
    runTest("analysis/analysis-api/testData/components/expressionTypeProvider/declarationReturnType/overriddenMember.kt");
  }

  @Test
  @TestMetadata("platformType.kt")
  public void testPlatformType() {
    runTest("analysis/analysis-api/testData/components/expressionTypeProvider/declarationReturnType/platformType.kt");
  }

  @Test
  @TestMetadata("propertyAccessor.kt")
  public void testPropertyAccessor() {
    runTest("analysis/analysis-api/testData/components/expressionTypeProvider/declarationReturnType/propertyAccessor.kt");
  }

  @Test
  @TestMetadata("simpleDeclaration.kt")
  public void testSimpleDeclaration() {
    runTest("analysis/analysis-api/testData/components/expressionTypeProvider/declarationReturnType/simpleDeclaration.kt");
  }

  @Test
  @TestMetadata("typeParameters.kt")
  public void testTypeParameters() {
    runTest("analysis/analysis-api/testData/components/expressionTypeProvider/declarationReturnType/typeParameters.kt");
  }

  @Test
  @TestMetadata("varargParam.kt")
  public void testVarargParam() {
    runTest("analysis/analysis-api/testData/components/expressionTypeProvider/declarationReturnType/varargParam.kt");
  }
}
