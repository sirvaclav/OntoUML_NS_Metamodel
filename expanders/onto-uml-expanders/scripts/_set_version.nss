#!/usr/bin/env nss

@file:MinVersion("1.9.0")

fun ScriptScope.updateVersion(newVersion: String) {
  dir(ctx.script.path.parent) {
    // Set version
    mvn {
      task("versions:set")
      property("newVersion", newVersion)
    }
  }
}
