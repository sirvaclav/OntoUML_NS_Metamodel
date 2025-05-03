package org.normalizedsystems.ontouml;

import net.democritus.expander.TestExpansion;
import net.democritus.expander.junit.ExpanderTest;
import net.democritus.expander.junit.TestModel;
import net.democritus.spec.Spec;
import ontoUml.model.KindComposite;
import ontoUml.model.ObjectTypeComposite;
import org.junit.jupiter.api.Test;

import static elements.spec.ElementsSpecs.*;
import static net.democritus.spec.CommonSpecs.dataRef;
import static ontoUml.spec.OntoUmlSpecs.kind;
import static ontoUml.spec.OntoUmlSpecs.objectType;

@ExpanderTest
class KindExpanderTest {
  @TestModel(select = "ontoUml::CityKind")
  Spec baseModel() {
    return component("ontoUml",
        dataElement("Person"),
        objectType(kind("CityKind",
            dataRef("dataElement", "ontoUml::Person")))
    );
  }

  @Test
  void testArtifactPath(TestExpansion<ObjectTypeComposite> expansion) {
    expansion
        .assertThatExpander()
        .hasArtifactPath("C:/MODULE_ROOT/shared/gen/common/src/CityKind.java");
  }

  @Test
  void testIsApplicable(TestExpansion<ObjectTypeComposite> expansion) {
    expansion
        .assertThatExpander()
        .isApplicable();
  }

  @Test
  void testBase(TestExpansion<ObjectTypeComposite> expansion) {
    expansion
        .assertThatExpander()
        .baseContent()
        .matchesTemplate();
  }


//  private KindComposite baseModel() {
//    return testModelBuilder.buildModelAndFind(
//        component("ontoUML",
//            kind("PersonKind")),
//        kind("ontoUML::PersonKind")
//    );
//  }
//
//  @Test
//  void testArtifactPath() {
//    assertThat(tester.artifactPath(baseModel()),
//        isPath("C:/COMPONENT_ROOT/some/file.ext"));
//  }

//  @Test
//  void testIsApplicable() {
//    KindComposite model = baseModel();
//
//    tester.testIsApplicable(model);
//  }
//
//  @Test
//  void testBase() {
//    tester.testBase(baseModel());
//  }
//
//  @Test
//  void testImports() {
//    tester.testAnchor("imports", baseModel());
//  }

}