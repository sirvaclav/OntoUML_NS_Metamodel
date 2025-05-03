package org.normalizedsystems.ontouml;

import net.democritus.elements.DataElementComposite;
import net.democritus.expander.ExpanderTester;
import net.democritus.expander.TestExpansion;
import net.democritus.expander.junit.ExpanderTest;
import net.democritus.expander.junit.TestModel;
import net.democritus.model.common.TestModelBuilder;
import net.democritus.spec.Spec;
import ontoUml.model.AntiRigidSortalComposite;
import ontoUml.model.CategoryComposite;
import ontoUml.model.ObjectTypeComposite;
import org.junit.jupiter.api.Test;

import static elements.spec.ElementsSpecs.*;
import static net.democritus.spec.CommonSpecs.*;
import static net.democritus.util.AssertUtil.*;
import static net.democritus.testing.Matchers.*;
import static ontoUml.spec.OntoUmlSpecs.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@ExpanderTest
class RoleExpanderTest {

  @TestModel(select = "ontoUml::LegalPersonRole")
  Spec baseModel() {
    return component("ontoUml",
        dataElement("LegalPerson"),
        kind("PersonKind"),
        relator("IndustryRelator"),
        relator("RegistrationRelator"),
        objectType(role("LegalPersonRole",
            dataRef("dataElement", "ontoUml::LegalPerson"),
            dataRef("roleRelationalDependency", "ontoUml::IndustryRelator"),
            dataRef("roleRelationalDependency", "ontoUml::RegistrationRelator")
//            dataRef("antiRigidIdentityInheritance", "ontoUml::PersonKind")
        ))
    );
  }


  @Test
  void testArtifactPath(TestExpansion<ObjectTypeComposite> expansion) {
    expansion
        .assertThatExpander()
        .hasArtifactPath("C:/MODULE_ROOT/shared/gen/common/src/LegalPersonRole.java");
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