#!/usr/bin/env nss

@file:Import("_version.nss")

var arguments = ctx.script.args

if (arguments.size != 1) {
  println("usage: set-version <version>")
  System.exit(1)
} else {}

var newVersion = arguments[0]
setVersion(newVersion)
