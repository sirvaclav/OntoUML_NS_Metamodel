package org.normalizedsystems.ontouml;

import net.democritus.elements.DataElementComposite;
import net.democritus.expander.ExpanderTester;
import net.democritus.expander.TestExpansion;
import net.democritus.expander.junit.ExpanderTest;
import net.democritus.expander.junit.ModelTest;
import net.democritus.expander.junit.TestModel;
import net.democritus.model.common.TestModelBuilder;
import net.democritus.spec.Spec;
import ontoUml.model.KindComposite;
import ontoUml.model.ObjectTypeComposite;
import ontoUml.model.SubKindComposite;
import org.junit.jupiter.api.Test;

import static elements.spec.ElementsSpecs.*;
import static net.democritus.spec.CommonSpecs.*;
import static net.democritus.util.AssertUtil.*;
import static net.democritus.testing.Matchers.*;
import static ontoUml.spec.OntoUmlSpecs.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@ExpanderTest
class SubKindExpanderTest {

  @TestModel(select = "ontoUml::BigCitySubKind")
  Spec baseModel() {
    return component("ontoUml",
        dataElement("BigCity"),
        kind("City"),
        objectType(subKind("BigCitySubKind",
            dataRef("dataElement", "ontoUml::BigCity"),
            dataRef("subKindIdentityInheritance", "ontoUml::City")
            )
        )
    );
  }

  @Test
  void testArtifactPath(TestExpansion<ObjectTypeComposite> expansion) {
    expansion
        .assertThatExpander()
        .hasArtifactPath("C:/MODULE_ROOT/shared/gen/common/src/BigCitySubKind.java");
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