/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.analysis.api.standalone.fir.test.cases.generated.cases.symbols;

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
import org.jetbrains.kotlin.analysis.api.impl.base.test.cases.symbols.AbstractSymbolByReferenceTest;
import org.jetbrains.kotlin.test.TestMetadata;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.regex.Pattern;

/** This class is generated by {@link org.jetbrains.kotlin.generators.tests.analysis.api.GenerateAnalysisApiTestsKt}. DO NOT MODIFY MANUALLY */
@SuppressWarnings("all")
@TestMetadata("analysis/analysis-api/testData/symbols/symbolByReference")
@TestDataPath("$PROJECT_ROOT")
public class FirStandaloneNormalAnalysisSourceModuleSymbolByReferenceTestGenerated extends AbstractSymbolByReferenceTest {
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
  @TestMetadata("accessorField.kt")
  public void testAccessorField() {
    runTest("analysis/analysis-api/testData/symbols/symbolByReference/accessorField.kt");
  }

  @Test
  public void testAllFilesPresentInSymbolByReference() {
    KtTestUtil.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("analysis/analysis-api/testData/symbols/symbolByReference"), Pattern.compile("^(.+)\\.kt$"), null, true, "withTestCompilerPluginEnabled");
  }

  @Test
  @TestMetadata("constructorValueParameter.kt")
  public void testConstructorValueParameter() {
    runTest("analysis/analysis-api/testData/symbols/symbolByReference/constructorValueParameter.kt");
  }

  @Test
  @TestMetadata("constructorViaTypeAlias.kt")
  public void testConstructorViaTypeAlias() {
    runTest("analysis/analysis-api/testData/symbols/symbolByReference/constructorViaTypeAlias.kt");
  }

  @Test
  @TestMetadata("enumEntries.kt")
  public void testEnumEntries() {
    runTest("analysis/analysis-api/testData/symbols/symbolByReference/enumEntries.kt");
  }

  @Test
  @TestMetadata("enumValueOf.kt")
  public void testEnumValueOf() {
    runTest("analysis/analysis-api/testData/symbols/symbolByReference/enumValueOf.kt");
  }

  @Test
  @TestMetadata("enumValues.kt")
  public void testEnumValues() {
    runTest("analysis/analysis-api/testData/symbols/symbolByReference/enumValues.kt");
  }

  @Test
  @TestMetadata("explicitLambdaParameter.kt")
  public void testExplicitLambdaParameter() {
    runTest("analysis/analysis-api/testData/symbols/symbolByReference/explicitLambdaParameter.kt");
  }

  @Test
  @TestMetadata("functionReceiverParameter.kt")
  public void testFunctionReceiverParameter() {
    runTest("analysis/analysis-api/testData/symbols/symbolByReference/functionReceiverParameter.kt");
  }

  @Test
  @TestMetadata("genericFromFunctionInLocalClass.kt")
  public void testGenericFromFunctionInLocalClass() {
    runTest("analysis/analysis-api/testData/symbols/symbolByReference/genericFromFunctionInLocalClass.kt");
  }

  @Test
  @TestMetadata("genericFromOuterClassInInnerClass.kt")
  public void testGenericFromOuterClassInInnerClass() {
    runTest("analysis/analysis-api/testData/symbols/symbolByReference/genericFromOuterClassInInnerClass.kt");
  }

  @Test
  @TestMetadata("implicitLambdaParameter.kt")
  public void testImplicitLambdaParameter() {
    runTest("analysis/analysis-api/testData/symbols/symbolByReference/implicitLambdaParameter.kt");
  }

  @Test
  @TestMetadata("javaClassWithEmptyAnnotationName.kt")
  public void testJavaClassWithEmptyAnnotationName() {
    runTest("analysis/analysis-api/testData/symbols/symbolByReference/javaClassWithEmptyAnnotationName.kt");
  }

  @Test
  @TestMetadata("javaField.kt")
  public void testJavaField() {
    runTest("analysis/analysis-api/testData/symbols/symbolByReference/javaField.kt");
  }

  @Test
  @TestMetadata("javaFieldWithTypeAnnotation.kt")
  public void testJavaFieldWithTypeAnnotation() {
    runTest("analysis/analysis-api/testData/symbols/symbolByReference/javaFieldWithTypeAnnotation.kt");
  }

  @Test
  @TestMetadata("javaMethod.kt")
  public void testJavaMethod() {
    runTest("analysis/analysis-api/testData/symbols/symbolByReference/javaMethod.kt");
  }

  @Test
  @TestMetadata("javaMethodWithExternalNotNullAnnotation.kt")
  public void testJavaMethodWithExternalNotNullAnnotation() {
    runTest("analysis/analysis-api/testData/symbols/symbolByReference/javaMethodWithExternalNotNullAnnotation.kt");
  }

  @Test
  @TestMetadata("javaMethodWithNotNullAnnotation.kt")
  public void testJavaMethodWithNotNullAnnotation() {
    runTest("analysis/analysis-api/testData/symbols/symbolByReference/javaMethodWithNotNullAnnotation.kt");
  }

  @Test
  @TestMetadata("javaStaticField.kt")
  public void testJavaStaticField() {
    runTest("analysis/analysis-api/testData/symbols/symbolByReference/javaStaticField.kt");
  }

  @Test
  @TestMetadata("javaStaticMethod.kt")
  public void testJavaStaticMethod() {
    runTest("analysis/analysis-api/testData/symbols/symbolByReference/javaStaticMethod.kt");
  }

  @Test
  @TestMetadata("javaSyntheticAccessor.kt")
  public void testJavaSyntheticAccessor() {
    runTest("analysis/analysis-api/testData/symbols/symbolByReference/javaSyntheticAccessor.kt");
  }

  @Test
  @TestMetadata("libraryConstructorProperty.kt")
  public void testLibraryConstructorProperty() {
    runTest("analysis/analysis-api/testData/symbols/symbolByReference/libraryConstructorProperty.kt");
  }

  @Test
  @TestMetadata("libraryConstructorPropertyWithAnnotations.kt")
  public void testLibraryConstructorPropertyWithAnnotations() {
    runTest("analysis/analysis-api/testData/symbols/symbolByReference/libraryConstructorPropertyWithAnnotations.kt");
  }

  @Test
  @TestMetadata("libraryConstructorVariable.kt")
  public void testLibraryConstructorVariable() {
    runTest("analysis/analysis-api/testData/symbols/symbolByReference/libraryConstructorVariable.kt");
  }

  @Test
  @TestMetadata("libraryConstructorVariableWithAnnotations.kt")
  public void testLibraryConstructorVariableWithAnnotations() {
    runTest("analysis/analysis-api/testData/symbols/symbolByReference/libraryConstructorVariableWithAnnotations.kt");
  }

  @Test
  @TestMetadata("libraryMemberProperty.kt")
  public void testLibraryMemberProperty() {
    runTest("analysis/analysis-api/testData/symbols/symbolByReference/libraryMemberProperty.kt");
  }

  @Test
  @TestMetadata("libraryMemberPropertyWithAnnotations.kt")
  public void testLibraryMemberPropertyWithAnnotations() {
    runTest("analysis/analysis-api/testData/symbols/symbolByReference/libraryMemberPropertyWithAnnotations.kt");
  }

  @Test
  @TestMetadata("libraryMemberVariable.kt")
  public void testLibraryMemberVariable() {
    runTest("analysis/analysis-api/testData/symbols/symbolByReference/libraryMemberVariable.kt");
  }

  @Test
  @TestMetadata("libraryMemberVariableWithAnnotations.kt")
  public void testLibraryMemberVariableWithAnnotations() {
    runTest("analysis/analysis-api/testData/symbols/symbolByReference/libraryMemberVariableWithAnnotations.kt");
  }

  @Test
  @TestMetadata("libraryProperty.kt")
  public void testLibraryProperty() {
    runTest("analysis/analysis-api/testData/symbols/symbolByReference/libraryProperty.kt");
  }

  @Test
  @TestMetadata("libraryPropertyWithAnnotations.kt")
  public void testLibraryPropertyWithAnnotations() {
    runTest("analysis/analysis-api/testData/symbols/symbolByReference/libraryPropertyWithAnnotations.kt");
  }

  @Test
  @TestMetadata("libraryVariable.kt")
  public void testLibraryVariable() {
    runTest("analysis/analysis-api/testData/symbols/symbolByReference/libraryVariable.kt");
  }

  @Test
  @TestMetadata("libraryVariableWithAnnotations.kt")
  public void testLibraryVariableWithAnnotations() {
    runTest("analysis/analysis-api/testData/symbols/symbolByReference/libraryVariableWithAnnotations.kt");
  }

  @Test
  @TestMetadata("primaryConstructorValueParameter.kt")
  public void testPrimaryConstructorValueParameter() {
    runTest("analysis/analysis-api/testData/symbols/symbolByReference/primaryConstructorValueParameter.kt");
  }

  @Test
  @TestMetadata("primaryConstructorValueParameterAsProperty.kt")
  public void testPrimaryConstructorValueParameterAsProperty() {
    runTest("analysis/analysis-api/testData/symbols/symbolByReference/primaryConstructorValueParameterAsProperty.kt");
  }

  @Test
  @TestMetadata("propertyReceiverParameter.kt")
  public void testPropertyReceiverParameter() {
    runTest("analysis/analysis-api/testData/symbols/symbolByReference/propertyReceiverParameter.kt");
  }

  @Test
  @TestMetadata("samConstructor.kt")
  public void testSamConstructor() {
    runTest("analysis/analysis-api/testData/symbols/symbolByReference/samConstructor.kt");
  }

  @Test
  @TestMetadata("setterValueParameter.kt")
  public void testSetterValueParameter() {
    runTest("analysis/analysis-api/testData/symbols/symbolByReference/setterValueParameter.kt");
  }

  @Test
  @TestMetadata("syntheticProperty.kt")
  public void testSyntheticProperty() {
    runTest("analysis/analysis-api/testData/symbols/symbolByReference/syntheticProperty.kt");
  }

  @Test
  @TestMetadata("typeAnnotationOnBackingField.kt")
  public void testTypeAnnotationOnBackingField() {
    runTest("analysis/analysis-api/testData/symbols/symbolByReference/typeAnnotationOnBackingField.kt");
  }

  @Test
  @TestMetadata("typeParameter.kt")
  public void testTypeParameter() {
    runTest("analysis/analysis-api/testData/symbols/symbolByReference/typeParameter.kt");
  }

  @Test
  @TestMetadata("valueParameter.kt")
  public void testValueParameter() {
    runTest("analysis/analysis-api/testData/symbols/symbolByReference/valueParameter.kt");
  }

  @Nested
  @TestMetadata("analysis/analysis-api/testData/symbols/symbolByReference/js")
  @TestDataPath("$PROJECT_ROOT")
  public class Js {
    @Test
    public void testAllFilesPresentInJs() {
      KtTestUtil.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("analysis/analysis-api/testData/symbols/symbolByReference/js"), Pattern.compile("^(.+)\\.kt$"), null, true, "withTestCompilerPluginEnabled");
    }

    @Test
    @TestMetadata("functionFromJsDynamicScopeNoArguments.kt")
    public void testFunctionFromJsDynamicScopeNoArguments() {
      runTest("analysis/analysis-api/testData/symbols/symbolByReference/js/functionFromJsDynamicScopeNoArguments.kt");
    }

    @Test
    @TestMetadata("functionFromJsDynamicScopeWithArguments.kt")
    public void testFunctionFromJsDynamicScopeWithArguments() {
      runTest("analysis/analysis-api/testData/symbols/symbolByReference/js/functionFromJsDynamicScopeWithArguments.kt");
    }

    @Test
    @TestMetadata("propertyFromJsDynamicScope.kt")
    public void testPropertyFromJsDynamicScope() {
      runTest("analysis/analysis-api/testData/symbols/symbolByReference/js/propertyFromJsDynamicScope.kt");
    }
  }
}
