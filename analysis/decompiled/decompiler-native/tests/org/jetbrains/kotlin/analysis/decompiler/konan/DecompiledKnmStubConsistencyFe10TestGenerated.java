/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.analysis.decompiler.konan;

import com.intellij.testFramework.TestDataPath;
import org.jetbrains.kotlin.test.util.KtTestUtil;
import org.jetbrains.kotlin.test.TestMetadata;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.regex.Pattern;

/** This class is generated by {@link org.jetbrains.kotlin.generators.tests.analysis.api.GenerateAnalysisApiTestsKt}. DO NOT MODIFY MANUALLY */
@SuppressWarnings("all")
@TestMetadata("analysis/decompiled/decompiler-to-file-stubs/testData/clsFileStubBuilder")
@TestDataPath("$PROJECT_ROOT")
public class DecompiledKnmStubConsistencyFe10TestGenerated extends AbstractDecompiledKnmStubConsistencyFe10Test {
  @Test
  public void testAllFilesPresentInClsFileStubBuilder() {
    KtTestUtil.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("analysis/decompiled/decompiler-to-file-stubs/testData/clsFileStubBuilder"), Pattern.compile("^([^\\.]+)$"), null, false);
  }

  @Test
  @TestMetadata("AnnotatedFlexibleTypes")
  public void testAnnotatedFlexibleTypes() {
    runTest("analysis/decompiled/decompiler-to-file-stubs/testData/clsFileStubBuilder/AnnotatedFlexibleTypes/");
  }

  @Test
  @TestMetadata("AnnotatedParameterInEnumConstructor")
  public void testAnnotatedParameterInEnumConstructor() {
    runTest("analysis/decompiled/decompiler-to-file-stubs/testData/clsFileStubBuilder/AnnotatedParameterInEnumConstructor/");
  }

  @Test
  @TestMetadata("AnnotatedParameterInInnerClassConstructor")
  public void testAnnotatedParameterInInnerClassConstructor() {
    runTest("analysis/decompiled/decompiler-to-file-stubs/testData/clsFileStubBuilder/AnnotatedParameterInInnerClassConstructor/");
  }

  @Test
  @TestMetadata("AnnotationClass")
  public void testAnnotationClass() {
    runTest("analysis/decompiled/decompiler-to-file-stubs/testData/clsFileStubBuilder/AnnotationClass/");
  }

  @Test
  @TestMetadata("AnnotationValues")
  public void testAnnotationValues() {
    runTest("analysis/decompiled/decompiler-to-file-stubs/testData/clsFileStubBuilder/AnnotationValues/");
  }

  @Test
  @TestMetadata("Annotations")
  public void testAnnotations() {
    runTest("analysis/decompiled/decompiler-to-file-stubs/testData/clsFileStubBuilder/Annotations/");
  }

  @Test
  @TestMetadata("AnnotationsOnNullableTypes")
  public void testAnnotationsOnNullableTypes() {
    runTest("analysis/decompiled/decompiler-to-file-stubs/testData/clsFileStubBuilder/AnnotationsOnNullableTypes/");
  }

  @Test
  @TestMetadata("AnnotationsOnParenthesizedTypes")
  public void testAnnotationsOnParenthesizedTypes() {
    runTest("analysis/decompiled/decompiler-to-file-stubs/testData/clsFileStubBuilder/AnnotationsOnParenthesizedTypes/");
  }

  @Test
  @TestMetadata("AnonymousReturnWithGenericType")
  public void testAnonymousReturnWithGenericType() {
    runTest("analysis/decompiled/decompiler-to-file-stubs/testData/clsFileStubBuilder/AnonymousReturnWithGenericType/");
  }

  @Test
  @TestMetadata("ClassMembers")
  public void testClassMembers() {
    runTest("analysis/decompiled/decompiler-to-file-stubs/testData/clsFileStubBuilder/ClassMembers/");
  }

  @Test
  @TestMetadata("ClassObject")
  public void testClassObject() {
    runTest("analysis/decompiled/decompiler-to-file-stubs/testData/clsFileStubBuilder/ClassObject/");
  }

  @Test
  @TestMetadata("Const")
  public void testConst() {
    runTest("analysis/decompiled/decompiler-to-file-stubs/testData/clsFileStubBuilder/Const/");
  }

  @Test
  @TestMetadata("ContextReceiversCallableMembers")
  public void testContextReceiversCallableMembers() {
    runTest("analysis/decompiled/decompiler-to-file-stubs/testData/clsFileStubBuilder/ContextReceiversCallableMembers/");
  }

  @Test
  @TestMetadata("ContextReceiversOnClass")
  public void testContextReceiversOnClass() {
    runTest("analysis/decompiled/decompiler-to-file-stubs/testData/clsFileStubBuilder/ContextReceiversOnClass/");
  }

  @Test
  @TestMetadata("ContextReceiversOnFunctionType")
  public void testContextReceiversOnFunctionType() {
    runTest("analysis/decompiled/decompiler-to-file-stubs/testData/clsFileStubBuilder/ContextReceiversOnFunctionType/");
  }

  @Test
  @TestMetadata("ContextReceiversOnTopLevelCallables")
  public void testContextReceiversOnTopLevelCallables() {
    runTest("analysis/decompiled/decompiler-to-file-stubs/testData/clsFileStubBuilder/ContextReceiversOnTopLevelCallables/");
  }

  @Test
  @TestMetadata("Contracts")
  public void testContracts() {
    runTest("analysis/decompiled/decompiler-to-file-stubs/testData/clsFileStubBuilder/Contracts/");
  }

  @Test
  @TestMetadata("DataClass")
  public void testDataClass() {
    runTest("analysis/decompiled/decompiler-to-file-stubs/testData/clsFileStubBuilder/DataClass/");
  }

  @Test
  @TestMetadata("DefinitelyNotNullTypes")
  public void testDefinitelyNotNullTypes() {
    runTest("analysis/decompiled/decompiler-to-file-stubs/testData/clsFileStubBuilder/DefinitelyNotNullTypes/");
  }

  @Test
  @TestMetadata("Delegation")
  public void testDelegation() {
    runTest("analysis/decompiled/decompiler-to-file-stubs/testData/clsFileStubBuilder/Delegation/");
  }

  @Test
  @TestMetadata("DependencyOnNestedClasses")
  public void testDependencyOnNestedClasses() {
    runTest("analysis/decompiled/decompiler-to-file-stubs/testData/clsFileStubBuilder/DependencyOnNestedClasses/");
  }

  @Test
  @TestMetadata("Enum")
  public void testEnum() {
    runTest("analysis/decompiled/decompiler-to-file-stubs/testData/clsFileStubBuilder/Enum/");
  }

  @Test
  @TestMetadata("FlexibleTypes")
  public void testFlexibleTypes() {
    runTest("analysis/decompiled/decompiler-to-file-stubs/testData/clsFileStubBuilder/FlexibleTypes/");
  }

  @Test
  @TestMetadata("FunInterfaceDeclaration")
  public void testFunInterfaceDeclaration() {
    runTest("analysis/decompiled/decompiler-to-file-stubs/testData/clsFileStubBuilder/FunInterfaceDeclaration/");
  }

  @Test
  @TestMetadata("InheritingClasses")
  public void testInheritingClasses() {
    runTest("analysis/decompiled/decompiler-to-file-stubs/testData/clsFileStubBuilder/InheritingClasses/");
  }

  @Test
  @TestMetadata("InnerClassEnumEntry")
  public void testInnerClassEnumEntry() {
    runTest("analysis/decompiled/decompiler-to-file-stubs/testData/clsFileStubBuilder/InnerClassEnumEntry/");
  }

  @Test
  @TestMetadata("InnerTypes")
  public void testInnerTypes() {
    runTest("analysis/decompiled/decompiler-to-file-stubs/testData/clsFileStubBuilder/InnerTypes/");
  }

  @Test
  @TestMetadata("LocalClass")
  public void testLocalClass() {
    runTest("analysis/decompiled/decompiler-to-file-stubs/testData/clsFileStubBuilder/LocalClass/");
  }

  @Test
  @TestMetadata("Modifiers")
  public void testModifiers() {
    runTest("analysis/decompiled/decompiler-to-file-stubs/testData/clsFileStubBuilder/Modifiers/");
  }

  @Test
  @TestMetadata("MultifileClass")
  public void testMultifileClass() {
    runTest("analysis/decompiled/decompiler-to-file-stubs/testData/clsFileStubBuilder/MultifileClass/");
  }

  @Test
  @TestMetadata("NamedCompanionObject")
  public void testNamedCompanionObject() {
    runTest("analysis/decompiled/decompiler-to-file-stubs/testData/clsFileStubBuilder/NamedCompanionObject/");
  }

  @Test
  @TestMetadata("NestedClasses")
  public void testNestedClasses() {
    runTest("analysis/decompiled/decompiler-to-file-stubs/testData/clsFileStubBuilder/NestedClasses/");
  }

  @Test
  @TestMetadata("Objects")
  public void testObjects() {
    runTest("analysis/decompiled/decompiler-to-file-stubs/testData/clsFileStubBuilder/Objects/");
  }

  @Test
  @TestMetadata("PrivateConstField")
  public void testPrivateConstField() {
    runTest("analysis/decompiled/decompiler-to-file-stubs/testData/clsFileStubBuilder/PrivateConstField/");
  }

  @Test
  @TestMetadata("PrivateToThis")
  public void testPrivateToThis() {
    runTest("analysis/decompiled/decompiler-to-file-stubs/testData/clsFileStubBuilder/PrivateToThis/");
  }

  @Test
  @TestMetadata("Sealed")
  public void testSealed() {
    runTest("analysis/decompiled/decompiler-to-file-stubs/testData/clsFileStubBuilder/Sealed/");
  }

  @Test
  @TestMetadata("SealedInterface")
  public void testSealedInterface() {
    runTest("analysis/decompiled/decompiler-to-file-stubs/testData/clsFileStubBuilder/SealedInterface/");
  }

  @Test
  @TestMetadata("SecondaryConstructors")
  public void testSecondaryConstructors() {
    runTest("analysis/decompiled/decompiler-to-file-stubs/testData/clsFileStubBuilder/SecondaryConstructors/");
  }

  @Test
  @TestMetadata("SpecialNames")
  public void testSpecialNames() {
    runTest("analysis/decompiled/decompiler-to-file-stubs/testData/clsFileStubBuilder/SpecialNames/");
  }

  @Test
  @TestMetadata("SuspendLambda")
  public void testSuspendLambda() {
    runTest("analysis/decompiled/decompiler-to-file-stubs/testData/clsFileStubBuilder/SuspendLambda/");
  }

  @Test
  @TestMetadata("TopJvmPackageName")
  public void testTopJvmPackageName() {
    runTest("analysis/decompiled/decompiler-to-file-stubs/testData/clsFileStubBuilder/TopJvmPackageName/");
  }

  @Test
  @TestMetadata("TopLevelMembersAnnotatedKt")
  public void testTopLevelMembersAnnotatedKt() {
    runTest("analysis/decompiled/decompiler-to-file-stubs/testData/clsFileStubBuilder/TopLevelMembersAnnotatedKt/");
  }

  @Test
  @TestMetadata("TopLevelMembersKt")
  public void testTopLevelMembersKt() {
    runTest("analysis/decompiled/decompiler-to-file-stubs/testData/clsFileStubBuilder/TopLevelMembersKt/");
  }

  @Test
  @TestMetadata("TypeAliases")
  public void testTypeAliases() {
    runTest("analysis/decompiled/decompiler-to-file-stubs/testData/clsFileStubBuilder/TypeAliases/");
  }

  @Test
  @TestMetadata("TypeBoundsAndDelegationSpecifiers")
  public void testTypeBoundsAndDelegationSpecifiers() {
    runTest("analysis/decompiled/decompiler-to-file-stubs/testData/clsFileStubBuilder/TypeBoundsAndDelegationSpecifiers/");
  }

  @Test
  @TestMetadata("TypeModifiers")
  public void testTypeModifiers() {
    runTest("analysis/decompiled/decompiler-to-file-stubs/testData/clsFileStubBuilder/TypeModifiers/");
  }

  @Test
  @TestMetadata("TypeParams")
  public void testTypeParams() {
    runTest("analysis/decompiled/decompiler-to-file-stubs/testData/clsFileStubBuilder/TypeParams/");
  }

  @Test
  @TestMetadata("Types")
  public void testTypes() {
    runTest("analysis/decompiled/decompiler-to-file-stubs/testData/clsFileStubBuilder/Types/");
  }

  @Test
  @TestMetadata("UnderscoreParameterName")
  public void testUnderscoreParameterName() {
    runTest("analysis/decompiled/decompiler-to-file-stubs/testData/clsFileStubBuilder/UnderscoreParameterName/");
  }
}
