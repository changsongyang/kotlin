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
@TestMetadata("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations")
@TestDataPath("$PROJECT_ROOT")
public class ScriptLazyTypeAnnotationsTestGenerated extends AbstractScriptLazyTypeAnnotationsTest {
  @Test
  public void testAllFilesPresentInLazyResolveTypeAnnotations() {
    KtTestUtil.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations"), Pattern.compile("^(.+)\\.(kts)$"), null, true);
  }

  @Nested
  @TestMetadata("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/class")
  @TestDataPath("$PROJECT_ROOT")
  public class Class {
    @Test
    public void testAllFilesPresentInClass() {
      KtTestUtil.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/class"), Pattern.compile("^(.+)\\.(kts)$"), null, true);
    }

    @Test
    @TestMetadata("classAnnotationsInLocalClassScript.kts")
    public void testClassAnnotationsInLocalClassScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/class/classAnnotationsInLocalClassScript.kts");
    }

    @Test
    @TestMetadata("delegateFieldWithAnnotationClashScript.kts")
    public void testDelegateFieldWithAnnotationClashScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/class/delegateFieldWithAnnotationClashScript.kts");
    }

    @Test
    @TestMetadata("delegatedFieldNestedNameClashAndAnnotationsScript.kts")
    public void testDelegatedFieldNestedNameClashAndAnnotationsScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/class/delegatedFieldNestedNameClashAndAnnotationsScript.kts");
    }

    @Test
    @TestMetadata("delegatedFieldNestedNameClashWithNestedTypesAndAnnotationsScript.kts")
    public void testDelegatedFieldNestedNameClashWithNestedTypesAndAnnotationsScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/class/delegatedFieldNestedNameClashWithNestedTypesAndAnnotationsScript.kts");
    }

    @Test
    @TestMetadata("parameterTypeCollisionAndAnnotationsScript.kts")
    public void testParameterTypeCollisionAndAnnotationsScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/class/parameterTypeCollisionAndAnnotationsScript.kts");
    }

    @Test
    @TestMetadata("superTypeCallNameClashWithAnnotationImplicitConstructorScript.kts")
    public void testSuperTypeCallNameClashWithAnnotationImplicitConstructorScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/class/superTypeCallNameClashWithAnnotationImplicitConstructorScript.kts");
    }

    @Test
    @TestMetadata("superTypeCallNameClashWithAnnotationScript.kts")
    public void testSuperTypeCallNameClashWithAnnotationScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/class/superTypeCallNameClashWithAnnotationScript.kts");
    }

    @Test
    @TestMetadata("superTypeCallNestedNameClashWithAnnotationImplicitConstructorScript.kts")
    public void testSuperTypeCallNestedNameClashWithAnnotationImplicitConstructorScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/class/superTypeCallNestedNameClashWithAnnotationImplicitConstructorScript.kts");
    }

    @Test
    @TestMetadata("superTypeCallNestedNameClashWithAnnotationScript.kts")
    public void testSuperTypeCallNestedNameClashWithAnnotationScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/class/superTypeCallNestedNameClashWithAnnotationScript.kts");
    }
  }

  @Nested
  @TestMetadata("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/constructor")
  @TestDataPath("$PROJECT_ROOT")
  public class Constructor {
    @Test
    public void testAllFilesPresentInConstructor() {
      KtTestUtil.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/constructor"), Pattern.compile("^(.+)\\.(kts)$"), null, true);
    }

    @Test
    @TestMetadata("callScript.kts")
    public void testCallScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/constructor/callScript.kts");
    }

    @Test
    @TestMetadata("callWithConstructorScript.kts")
    public void testCallWithConstructorScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/constructor/callWithConstructorScript.kts");
    }

    @Test
    @TestMetadata("parameterWithAnnotationsScript.kts")
    public void testParameterWithAnnotationsScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/constructor/parameterWithAnnotationsScript.kts");
    }

    @Test
    @TestMetadata("parameterWithAnnotationsScriptBodyResolve.kts")
    public void testParameterWithAnnotationsScriptBodyResolve() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/constructor/parameterWithAnnotationsScriptBodyResolve.kts");
    }

    @Test
    @TestMetadata("propagationToLocalMemberFunctionScript.kts")
    public void testPropagationToLocalMemberFunctionScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/constructor/propagationToLocalMemberFunctionScript.kts");
    }

    @Test
    @TestMetadata("referenceScript.kts")
    public void testReferenceScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/constructor/referenceScript.kts");
    }

    @Test
    @TestMetadata("referenceWithConstructorScript.kts")
    public void testReferenceWithConstructorScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/constructor/referenceWithConstructorScript.kts");
    }

    @Test
    @TestMetadata("secondaryConstructorScript.kts")
    public void testSecondaryConstructorScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/constructor/secondaryConstructorScript.kts");
    }
  }

  @Nested
  @TestMetadata("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/delegate")
  @TestDataPath("$PROJECT_ROOT")
  public class Delegate {
    @Test
    public void testAllFilesPresentInDelegate() {
      KtTestUtil.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/delegate"), Pattern.compile("^(.+)\\.(kts)$"), null, true);
    }

    @Test
    @TestMetadata("delegateFieldWithAnnotationClashScript.kts")
    public void testDelegateFieldWithAnnotationClashScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/delegate/delegateFieldWithAnnotationClashScript.kts");
    }

    @Test
    @TestMetadata("delegateWithExplicitTypeScript.kts")
    public void testDelegateWithExplicitTypeScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/delegate/delegateWithExplicitTypeScript.kts");
    }

    @Test
    @TestMetadata("delegateWithExplicitTypeUnavailableScript.kts")
    public void testDelegateWithExplicitTypeUnavailableScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/delegate/delegateWithExplicitTypeUnavailableScript.kts");
    }

    @Test
    @TestMetadata("delegateWithImplicitTypeScript.kts")
    public void testDelegateWithImplicitTypeScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/delegate/delegateWithImplicitTypeScript.kts");
    }

    @Test
    @TestMetadata("delegateWithImplicitTypeUnavailableScript.kts")
    public void testDelegateWithImplicitTypeUnavailableScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/delegate/delegateWithImplicitTypeUnavailableScript.kts");
    }

    @Test
    @TestMetadata("delegatedFieldNestedNameClashAndAnnotationsScript.kts")
    public void testDelegatedFieldNestedNameClashAndAnnotationsScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/delegate/delegatedFieldNestedNameClashAndAnnotationsScript.kts");
    }

    @Test
    @TestMetadata("delegatedFieldNestedNameClashWithNestedTypesAndAnnotationsScript.kts")
    public void testDelegatedFieldNestedNameClashWithNestedTypesAndAnnotationsScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/delegate/delegatedFieldNestedNameClashWithNestedTypesAndAnnotationsScript.kts");
    }

    @Test
    @TestMetadata("delegatedFieldNestedNameScript.kts")
    public void testDelegatedFieldNestedNameScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/delegate/delegatedFieldNestedNameScript.kts");
    }

    @Test
    @TestMetadata("fieldScript.kts")
    public void testFieldScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/delegate/fieldScript.kts");
    }

    @Test
    @TestMetadata("propertyWithExplicitTypeScript.kts")
    public void testPropertyWithExplicitTypeScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/delegate/propertyWithExplicitTypeScript.kts");
    }

    @Test
    @TestMetadata("propertyWithExplicitTypeUnavailableScript.kts")
    public void testPropertyWithExplicitTypeUnavailableScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/delegate/propertyWithExplicitTypeUnavailableScript.kts");
    }

    @Test
    @TestMetadata("propertyWithImplicitTypeScript.kts")
    public void testPropertyWithImplicitTypeScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/delegate/propertyWithImplicitTypeScript.kts");
    }

    @Test
    @TestMetadata("propertyWithImplicitTypeUnavailableScript.kts")
    public void testPropertyWithImplicitTypeUnavailableScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/delegate/propertyWithImplicitTypeUnavailableScript.kts");
    }
  }

  @Nested
  @TestMetadata("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/destructuringDeclaration")
  @TestDataPath("$PROJECT_ROOT")
  public class DestructuringDeclaration {
    @Test
    public void testAllFilesPresentInDestructuringDeclaration() {
      KtTestUtil.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/destructuringDeclaration"), Pattern.compile("^(.+)\\.(kts)$"), null, true);
    }

    @Test
    @TestMetadata("destruct.kts")
    public void testDestruct() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/destructuringDeclaration/destruct.kts");
    }

    @Test
    @TestMetadata("destructEntry.kts")
    public void testDestructEntry() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/destructuringDeclaration/destructEntry.kts");
    }

    @Test
    @TestMetadata("destructEntryUsage.kts")
    public void testDestructEntryUsage() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/destructuringDeclaration/destructEntryUsage.kts");
    }

    @Test
    @TestMetadata("destructEntryWithType.kts")
    public void testDestructEntryWithType() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/destructuringDeclaration/destructEntryWithType.kts");
    }

    @Test
    @TestMetadata("destructEntryWithTypeUsage.kts")
    public void testDestructEntryWithTypeUsage() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/destructuringDeclaration/destructEntryWithTypeUsage.kts");
    }

    @Test
    @TestMetadata("scriptClassLevel.kts")
    public void testScriptClassLevel() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/destructuringDeclaration/scriptClassLevel.kts");
    }

    @Test
    @TestMetadata("scriptLevel.kts")
    public void testScriptLevel() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/destructuringDeclaration/scriptLevel.kts");
    }

    @Test
    @TestMetadata("scriptStatementLevel.kts")
    public void testScriptStatementLevel() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/destructuringDeclaration/scriptStatementLevel.kts");
    }

    @Test
    @TestMetadata("scriptStatementLevelAsLastStatement.kts")
    public void testScriptStatementLevelAsLastStatement() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/destructuringDeclaration/scriptStatementLevelAsLastStatement.kts");
    }
  }

  @Nested
  @TestMetadata("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/errorType")
  @TestDataPath("$PROJECT_ROOT")
  public class ErrorType {
    @Test
    public void testAllFilesPresentInErrorType() {
      KtTestUtil.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/errorType"), Pattern.compile("^(.+)\\.(kts)$"), null, true);
    }

    @Test
    @TestMetadata("errorTypeScript.kts")
    public void testErrorTypeScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/errorType/errorTypeScript.kts");
    }

    @Test
    @TestMetadata("nestedErrorReturnTypeScript.kts")
    public void testNestedErrorReturnTypeScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/errorType/nestedErrorReturnTypeScript.kts");
    }
  }

  @Nested
  @TestMetadata("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/function")
  @TestDataPath("$PROJECT_ROOT")
  public class Function {
    @Test
    public void testAllFilesPresentInFunction() {
      KtTestUtil.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/function"), Pattern.compile("^(.+)\\.(kts)$"), null, true);
    }

    @Test
    @TestMetadata("component1TypeCollisionAndAnnotationsScript.kts")
    public void testComponent1TypeCollisionAndAnnotationsScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/function/component1TypeCollisionAndAnnotationsScript.kts");
    }

    @Test
    @TestMetadata("copyTypeCollisionAndAnnotationsScript.kts")
    public void testCopyTypeCollisionAndAnnotationsScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/function/copyTypeCollisionAndAnnotationsScript.kts");
    }

    @Test
    @TestMetadata("delegatedFieldNestedNameClashAndAnnotationsAsConstructorScript.kts")
    public void testDelegatedFieldNestedNameClashAndAnnotationsAsConstructorScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/function/delegatedFieldNestedNameClashAndAnnotationsAsConstructorScript.kts");
    }

    @Test
    @TestMetadata("delegatedFieldNestedNameClashWithNestedTypesAndAnnotationsAsConstructorScript.kts")
    public void testDelegatedFieldNestedNameClashWithNestedTypesAndAnnotationsAsConstructorScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/function/delegatedFieldNestedNameClashWithNestedTypesAndAnnotationsAsConstructorScript.kts");
    }

    @Test
    @TestMetadata("explicitTypeScript.kts")
    public void testExplicitTypeScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/function/explicitTypeScript.kts");
    }

    @Test
    @TestMetadata("generatedComponentNScript.kts")
    public void testGeneratedComponentNScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/function/generatedComponentNScript.kts");
    }

    @Test
    @TestMetadata("generatedCopyScript.kts")
    public void testGeneratedCopyScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/function/generatedCopyScript.kts");
    }

    @Test
    @TestMetadata("implicitTypeScript.kts")
    public void testImplicitTypeScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/function/implicitTypeScript.kts");
    }

    @Test
    @TestMetadata("implicitTypeUnavailableScript.kts")
    public void testImplicitTypeUnavailableScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/function/implicitTypeUnavailableScript.kts");
    }

    @Test
    @TestMetadata("localImplicitTypeUnavailableInImplicitBodyScript.kts")
    public void testLocalImplicitTypeUnavailableInImplicitBodyScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/function/localImplicitTypeUnavailableInImplicitBodyScript.kts");
    }

    @Test
    @TestMetadata("localImplicitTypeUnavailableScript.kts")
    public void testLocalImplicitTypeUnavailableScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/function/localImplicitTypeUnavailableScript.kts");
    }

    @Test
    @TestMetadata("multiDeclarationScript.kts")
    public void testMultiDeclarationScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/function/multiDeclarationScript.kts");
    }

    @Test
    @TestMetadata("parameterAsImplicitReturnTypePropagationScript.kts")
    public void testParameterAsImplicitReturnTypePropagationScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/function/parameterAsImplicitReturnTypePropagationScript.kts");
    }

    @Test
    @TestMetadata("parameterAsImplicitReturnTypeScript.kts")
    public void testParameterAsImplicitReturnTypeScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/function/parameterAsImplicitReturnTypeScript.kts");
    }

    @Test
    @TestMetadata("parameterAsImplicitReturnTypeScriptBodyResolve.kts")
    public void testParameterAsImplicitReturnTypeScriptBodyResolve() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/function/parameterAsImplicitReturnTypeScriptBodyResolve.kts");
    }

    @Test
    @TestMetadata("propagationBetweenLocalMemberFunctionsImplicitBodyScript.kts")
    public void testPropagationBetweenLocalMemberFunctionsImplicitBodyScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/function/propagationBetweenLocalMemberFunctionsImplicitBodyScript.kts");
    }

    @Test
    @TestMetadata("propagationBetweenLocalMemberFunctionsScript.kts")
    public void testPropagationBetweenLocalMemberFunctionsScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/function/propagationBetweenLocalMemberFunctionsScript.kts");
    }

    @Test
    @TestMetadata("propagationToLocalMemberFunctionImplicitBodyScript.kts")
    public void testPropagationToLocalMemberFunctionImplicitBodyScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/function/propagationToLocalMemberFunctionImplicitBodyScript.kts");
    }

    @Test
    @TestMetadata("propagationToLocalMemberFunctionScript.kts")
    public void testPropagationToLocalMemberFunctionScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/function/propagationToLocalMemberFunctionScript.kts");
    }

    @Test
    @TestMetadata("receiverAsImplicitReturnTypePropagationScript.kts")
    public void testReceiverAsImplicitReturnTypePropagationScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/function/receiverAsImplicitReturnTypePropagationScript.kts");
    }

    @Test
    @TestMetadata("receiverAsImplicitReturnTypeScript.kts")
    public void testReceiverAsImplicitReturnTypeScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/function/receiverAsImplicitReturnTypeScript.kts");
    }

    @Test
    @TestMetadata("superTypeCallNameClashWithAnnotationImplicitConstructorScript.kts")
    public void testSuperTypeCallNameClashWithAnnotationImplicitConstructorScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/function/superTypeCallNameClashWithAnnotationImplicitConstructorScript.kts");
    }

    @Test
    @TestMetadata("superTypeCallNameClashWithAnnotationScript.kts")
    public void testSuperTypeCallNameClashWithAnnotationScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/function/superTypeCallNameClashWithAnnotationScript.kts");
    }

    @Test
    @TestMetadata("superTypeCallNestedNameClashWithAnnotationImplicitConstructorScript.kts")
    public void testSuperTypeCallNestedNameClashWithAnnotationImplicitConstructorScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/function/superTypeCallNestedNameClashWithAnnotationImplicitConstructorScript.kts");
    }

    @Test
    @TestMetadata("superTypeCallNestedNameClashWithAnnotationScript.kts")
    public void testSuperTypeCallNestedNameClashWithAnnotationScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/function/superTypeCallNestedNameClashWithAnnotationScript.kts");
    }

    @Test
    @TestMetadata("typeParameterAnnotationsInLocalClassScript.kts")
    public void testTypeParameterAnnotationsInLocalClassScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/function/typeParameterAnnotationsInLocalClassScript.kts");
    }
  }

  @Nested
  @TestMetadata("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/initializer")
  @TestDataPath("$PROJECT_ROOT")
  public class Initializer {
    @Test
    public void testAllFilesPresentInInitializer() {
      KtTestUtil.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/initializer"), Pattern.compile("^(.+)\\.(kts)$"), null, true);
    }

    @Test
    @TestMetadata("classInitializerScript.kts")
    public void testClassInitializerScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/initializer/classInitializerScript.kts");
    }
  }

  @Nested
  @TestMetadata("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/property")
  @TestDataPath("$PROJECT_ROOT")
  public class Property {
    @Test
    public void testAllFilesPresentInProperty() {
      KtTestUtil.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/property"), Pattern.compile("^(.+)\\.(kts)$"), null, true);
    }

    @Test
    @TestMetadata("constructorParameterScript.kts")
    public void testConstructorParameterScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/property/constructorParameterScript.kts");
    }

    @Test
    @TestMetadata("explicitTypeScript.kts")
    public void testExplicitTypeScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/property/explicitTypeScript.kts");
    }

    @Test
    @TestMetadata("generatedPropertyFromConstructorScript.kts")
    public void testGeneratedPropertyFromConstructorScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/property/generatedPropertyFromConstructorScript.kts");
    }

    @Test
    @TestMetadata("implicitTypeFromIncorrectAccessorsPropagationScript.kts")
    public void testImplicitTypeFromIncorrectAccessorsPropagationScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/property/implicitTypeFromIncorrectAccessorsPropagationScript.kts");
    }

    @Test
    @TestMetadata("implicitTypeFromIncorrectAccessorsScript.kts")
    public void testImplicitTypeFromIncorrectAccessorsScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/property/implicitTypeFromIncorrectAccessorsScript.kts");
    }

    @Test
    @TestMetadata("implicitTypeFromIncorrectSetterPropagationScript.kts")
    public void testImplicitTypeFromIncorrectSetterPropagationScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/property/implicitTypeFromIncorrectSetterPropagationScript.kts");
    }

    @Test
    @TestMetadata("implicitTypeFromIncorrectSetterScript.kts")
    public void testImplicitTypeFromIncorrectSetterScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/property/implicitTypeFromIncorrectSetterScript.kts");
    }

    @Test
    @TestMetadata("implicitTypeScript.kts")
    public void testImplicitTypeScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/property/implicitTypeScript.kts");
    }

    @Test
    @TestMetadata("localDelegatedPropertyWithPropagatedTypeScript.kts")
    public void testLocalDelegatedPropertyWithPropagatedTypeScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/property/localDelegatedPropertyWithPropagatedTypeScript.kts");
    }

    @Test
    @TestMetadata("localDelegatedPropertyWithPropagatedTypeUnavailableScript.kts")
    public void testLocalDelegatedPropertyWithPropagatedTypeUnavailableScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/property/localDelegatedPropertyWithPropagatedTypeUnavailableScript.kts");
    }

    @Test
    @TestMetadata("localImplicitTypeUnavailableInImplicitBodyScript.kts")
    public void testLocalImplicitTypeUnavailableInImplicitBodyScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/property/localImplicitTypeUnavailableInImplicitBodyScript.kts");
    }

    @Test
    @TestMetadata("localImplicitTypeUnavailableScript.kts")
    public void testLocalImplicitTypeUnavailableScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/property/localImplicitTypeUnavailableScript.kts");
    }

    @Test
    @TestMetadata("localPropertyWithPropagatedTypeScript.kts")
    public void testLocalPropertyWithPropagatedTypeScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/property/localPropertyWithPropagatedTypeScript.kts");
    }

    @Test
    @TestMetadata("propagationBetweenLocalMemberPropertiesImplicitBodyScript.kts")
    public void testPropagationBetweenLocalMemberPropertiesImplicitBodyScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/property/propagationBetweenLocalMemberPropertiesImplicitBodyScript.kts");
    }

    @Test
    @TestMetadata("propagationBetweenLocalMemberPropertiesScript.kts")
    public void testPropagationBetweenLocalMemberPropertiesScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/property/propagationBetweenLocalMemberPropertiesScript.kts");
    }

    @Test
    @TestMetadata("propagationToLocalMemberPropertyImplicitBodyScript.kts")
    public void testPropagationToLocalMemberPropertyImplicitBodyScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/property/propagationToLocalMemberPropertyImplicitBodyScript.kts");
    }

    @Test
    @TestMetadata("propagationToLocalMemberPropertyScript.kts")
    public void testPropagationToLocalMemberPropertyScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/property/propagationToLocalMemberPropertyScript.kts");
    }

    @Test
    @TestMetadata("propertyTypeCollisionAndAnnotationsScript.kts")
    public void testPropertyTypeCollisionAndAnnotationsScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/property/propertyTypeCollisionAndAnnotationsScript.kts");
    }

    @Test
    @TestMetadata("receiverAsImplicitReturnTypePropagationScript.kts")
    public void testReceiverAsImplicitReturnTypePropagationScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/property/receiverAsImplicitReturnTypePropagationScript.kts");
    }

    @Test
    @TestMetadata("receiverAsImplicitReturnTypeScript.kts")
    public void testReceiverAsImplicitReturnTypeScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/property/receiverAsImplicitReturnTypeScript.kts");
    }

    @Test
    @TestMetadata("resultWithPropagatedType.kts")
    public void testResultWithPropagatedType() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/property/resultWithPropagatedType.kts");
    }

    @Test
    @TestMetadata("resultWithPropagatedTypeUnavailable.kts")
    public void testResultWithPropagatedTypeUnavailable() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/property/resultWithPropagatedTypeUnavailable.kts");
    }

    @Test
    @TestMetadata("typeParameterAnnotationsInLocalClassScript.kts")
    public void testTypeParameterAnnotationsInLocalClassScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/property/typeParameterAnnotationsInLocalClassScript.kts");
    }
  }

  @Nested
  @TestMetadata("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/substitutionOverride")
  @TestDataPath("$PROJECT_ROOT")
  public class SubstitutionOverride {
    @Test
    public void testAllFilesPresentInSubstitutionOverride() {
      KtTestUtil.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/substitutionOverride"), Pattern.compile("^(.+)\\.(kts)$"), null, true);
    }

    @Test
    @TestMetadata("constructorCallSiteScript.kts")
    public void testConstructorCallSiteScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/substitutionOverride/constructorCallSiteScript.kts");
    }

    @Test
    @TestMetadata("constructorScript.kts")
    public void testConstructorScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/substitutionOverride/constructorScript.kts");
    }

    @Test
    @TestMetadata("functionScript.kts")
    public void testFunctionScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/substitutionOverride/functionScript.kts");
    }

    @Test
    @TestMetadata("functionUnavailableScript.kts")
    public void testFunctionUnavailableScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/substitutionOverride/functionUnavailableScript.kts");
    }

    @Test
    @TestMetadata("implicitFunctionScript.kts")
    public void testImplicitFunctionScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/substitutionOverride/implicitFunctionScript.kts");
    }

    @Test
    @TestMetadata("implicitFunctionUnavailableScript.kts")
    public void testImplicitFunctionUnavailableScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/substitutionOverride/implicitFunctionUnavailableScript.kts");
    }

    @Test
    @TestMetadata("implicitPropertyAndReceiverScript.kts")
    public void testImplicitPropertyAndReceiverScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/substitutionOverride/implicitPropertyAndReceiverScript.kts");
    }

    @Test
    @TestMetadata("implicitPropertyScript.kts")
    public void testImplicitPropertyScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/substitutionOverride/implicitPropertyScript.kts");
    }

    @Test
    @TestMetadata("implicitPropertyUnavailableScript.kts")
    public void testImplicitPropertyUnavailableScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/substitutionOverride/implicitPropertyUnavailableScript.kts");
    }

    @Test
    @TestMetadata("propertyScript.kts")
    public void testPropertyScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/substitutionOverride/propertyScript.kts");
    }

    @Test
    @TestMetadata("propertyUnavailableScript.kts")
    public void testPropertyUnavailableScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/substitutionOverride/propertyUnavailableScript.kts");
    }
  }

  @Nested
  @TestMetadata("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/syntheticProperty")
  @TestDataPath("$PROJECT_ROOT")
  public class SyntheticProperty {
    @Test
    public void testAllFilesPresentInSyntheticProperty() {
      KtTestUtil.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/syntheticProperty"), Pattern.compile("^(.+)\\.(kts)$"), null, true);
    }
  }

  @Nested
  @TestMetadata("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/typeAlias")
  @TestDataPath("$PROJECT_ROOT")
  public class TypeAlias {
    @Test
    public void testAllFilesPresentInTypeAlias() {
      KtTestUtil.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/typeAlias"), Pattern.compile("^(.+)\\.(kts)$"), null, true);
    }

    @Test
    @TestMetadata("insideFunctionComplexScript.kts")
    public void testInsideFunctionComplexScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/typeAlias/insideFunctionComplexScript.kts");
    }

    @Test
    @TestMetadata("insideFunctionScript.kts")
    public void testInsideFunctionScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/typeAlias/insideFunctionScript.kts");
    }

    @Test
    @TestMetadata("nestedAliasWithNestedAnnotationInLocalClassScript.kts")
    public void testNestedAliasWithNestedAnnotationInLocalClassScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/typeAlias/nestedAliasWithNestedAnnotationInLocalClassScript.kts");
    }

    @Test
    @TestMetadata("simpleScript.kts")
    public void testSimpleScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/typeAlias/simpleScript.kts");
    }

    @Test
    @TestMetadata("withMissedArgumentsScript.kts")
    public void testWithMissedArgumentsScript() {
      runTest("analysis/low-level-api-fir/testData/lazyResolveTypeAnnotations/typeAlias/withMissedArgumentsScript.kts");
    }
  }
}
