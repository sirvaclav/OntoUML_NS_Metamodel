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
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@ExpanderTest
class CollectiveExpanderTest {

  @TestModel(select = "ontoUml::CitiesCollective")
  Spec baseModel() {
    return component("ontoUml",
        dataElement("Cities"),
        collective("Places"),
        collective("MapPoints"),
        objectType(collective("CitiesCollective",
                dataRef("dataElement", "ontoUml::Cities"),
                dataRef("subCollectionOf", "ontoUml::Places"),
                dataRef("subCollectionOf", "ontoUml::MapPoints")
            )
        )
    );
  }

  @Test
  void testArtifactPath(TestExpansion<ObjectTypeComposite> expansion) {
    expansion
        .assertThatExpander()
        .hasArtifactPath("C:/MODULE_ROOT/shared/gen/common/src/CitiesCollective.java");
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