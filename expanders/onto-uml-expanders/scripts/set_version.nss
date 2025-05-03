#!/usr/bin/env nss

@file:MinVersion("1.9.0")
@file:Import("_set_version.nss")

var arguments = ctx.script.args

if (arguments.size != 1) {
  println("usage: set_version <version>")
  exit(1)
}

var version: String = arguments[0]

if (!version.matches(Regex("\\d+\\.\\d+\\.\\d+(?:-[a-zA-Z0-9]+)?(?:-SNAPSHOT)?"))) {
  println("Unexpected version format")
  exit(1)
}

updateVersion(version)
