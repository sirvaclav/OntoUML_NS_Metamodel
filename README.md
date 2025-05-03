# methamodelMetamodel

## Structure

This project contains a metamodel.

- metamodel/methamodelMetamodel.xml contains the root of the metamodel, this links to imported ontologies and ontologies in the project.
- ontologies/ contains the descriptions of the metamodel, which are grouped in Ontologies.

The scripts directory contains a few scripts to help during development:

- scripts/expand.nss: Expands the metamodel to create a metamodel project.
- scripts/harvest.nss: Harvests the changes of the converted metamodel.
- scripts/build.nss: Build the expanded metamodel project. This creates a `methamodel-core.jar` and `methamodel-expanders.jar`.

