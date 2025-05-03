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
class CategoryExpanderTest {

  @TestModel(select = "ontoUml::BigCityCategory")
  Spec baseModel() {
    return component("ontoUml",
        dataElement("BigCity"),
        objectType(category("BigCityCategory",
                dataRef("dataElement", "ontoUml::BigCity")
            )
        )
    );
  }

  @Test
  void testArtifactPath(TestExpansion<ObjectTypeComposite> expansion) {
    expansion
        .assertThatExpander()
        .hasArtifactPath("C:/MODULE_ROOT/shared/gen/common/src/BigCityCategory.java");
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