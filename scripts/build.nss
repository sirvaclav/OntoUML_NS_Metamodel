#!/usr/bin/env nss

// Install methamodel metamodel expansionResource
mvn {
  task("install")
}

// Install methamodel-core and methamodel-test-support
dir("expansions/methamodelMetamodel") {
  mvn {
    task("install")
  }
}
