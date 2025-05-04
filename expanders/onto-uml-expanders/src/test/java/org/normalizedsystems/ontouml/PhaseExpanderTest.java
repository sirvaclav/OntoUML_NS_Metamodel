package org.normalizedsystems.ontouml;

import net.democritus.elements.DataElementComposite;
import net.democritus.expander.ExpanderTester;
import net.democritus.expander.TestExpansion;
import net.democritus.expander.junit.ExpanderTest;
import net.democritus.expander.junit.TestModel;
import net.democritus.model.common.TestModelBuilder;
import net.democritus.spec.Spec;
import ontoUml.model.ObjectTypeComposite;
import org.junit.jupiter.api.Test;

import static elements.spec.ElementsSpecs.*;
import static net.democritus.spec.CommonSpecs.*;
import static net.democritus.util.AssertUtil.*;
import static net.democritus.testing.Matchers.*;
import static ontoUml.spec.OntoUmlSpecs.*;
import static ontoUml.spec.OntoUmlSpecs.role;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@ExpanderTest
class PhaseExpanderTest {

  @TestModel(select = "ontoUml::LegalPersonPhase")
  Spec baseModel() {
    return component("ontoUml",
        dataElement("LegalPerson"),
        kind("PersonKind"),
        mode("IndustryMode"),
        mode("Mode"),
        quality("Quality1"),
        quality("Quality2"),
        objectType(phase("LegalPersonPhase",
            dataRef("dataElement", "ontoUml::LegalPerson"),
            dataRef("modePropertyDependency", "ontoUml::IndustryMode"),
            dataRef("modePropertyDependency", "ontoUml::Mode"),
            dataRef("qualityPropertyDependency", "ontoUml::Quality1"),
            dataRef("qualityPropertyDependency", "ontoUml::Quality2"),
            dataRef("kindIdentityProvider", "ontoUml::PersonKind")
        ))
    );
  }



  @Test
  void testArtifactPath(TestExpansion<ObjectTypeComposite> expansion) {
    expansion
        .assertThatExpander()
        .hasArtifactPath("C:/MODULE_ROOT/shared/gen/common/src/LegalPersonPhase.java");
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

}