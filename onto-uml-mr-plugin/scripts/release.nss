#!/usr/bin/env nss

@file:Import("_version.nss")

import java.nio.file.Files
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

val arguments = ctx.script.args
val sourceBasePath = ctx.script.path.parent

if (arguments.size != 1) {
    println("usage: release <version>")
    exit(1)
}

var releaseVersion: String = arguments[0]

if (!releaseVersion.matches(Regex("\d+\.\d+\.\d+"))) {
    println("Unexpected version format")
    exit(1)
}

println("Releasing ${releaseVersion}")
setVersion(releaseVersion)

updateChangelog(releaseVersion)

dir(sourceBasePath) {
  git {
    commit {
      files(".")
      message("release v${releaseVersion}")
      tag("v${releaseVersion}")
    }
  }
}

val nextVersion = getNextVersion(releaseVersion)
println("Next version $nextVersion")
setVersion(nextVersion)

dir(sourceBasePath) {
  git {
    commit {
      files(".")
      message("v${nextVersion}")
    }
    push {
      tags()
    }
  }
}

fun getNextVersion(version: String): String {
    val versionNumbers = version.split(".")
    val patchNumber = Integer.parseInt(versionNumbers[2]) + 1
    return "${versionNumbers[0]}.${versionNumbers[1]}.${patchNumber}-SNAPSHOT"
}

fun updateChangelog(version: String) {
    val changelog = ctx.script.path.parent.resolve("CHANGELOG.md")
    val lines = Files.readAllLines(changelog)
    val index = lines.indexOfFirst { it.startsWith("## ${version}") }
    if (index == -1) {
        println("Missing version ${version} in CHANGELOG.md")
        exit(1)
    }

    val timestamp = LocalDate.now().format(DateTimeFormatter.ISO_DATE)
    lines.set(index, "## ${version} (${timestamp})")

    println("Updated CHANGELOG.md, timestamp=${timestamp}")
    Files.write(changelog, lines)
}
