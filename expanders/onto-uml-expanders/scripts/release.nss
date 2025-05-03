#!/usr/bin/env nss

@file:MinVersion("1.9.0")
@file:Import("_set_version.nss")

import java.nio.file.Files
import java.time.LocalDate
import java.time.format.DateTimeFormatter

var arguments = ctx.script.args

if (arguments.size != 1) {
  println("usage: release <version>")
  exit(1)
}

var releaseVersion: String = arguments[0]

if (!releaseVersion.matches(Regex("\\d+\\.\\d+\\.\\d+"))) {
  println("Unexpected version format")
  exit(1)
}

dir(ctx.script.path.parent) {
  println("Releasing ${releaseVersion}")
  git {
    flow {
      release {
        start {
          version(releaseVersion)
        }
      }
    }
  }

  updateVersion(newVersion = releaseVersion)
  updateChangelog(releaseVersion)

  git {
    commit {
      files(".")
      message("Updated version to ${releaseVersion}")
    }
    flow {
      release {
        finish {
          message("Release")
          push()
        }
      }
    }
  }

  val nextVersion = getNextVersion(releaseVersion)
  println("Next version $nextVersion")

  updateVersion(newVersion = nextVersion)

  git {
    commit {
      files(".")
      message("Updated version to ${nextVersion}")
    }
    push { }
  }
}

fun ScriptScope.getNextVersion(version: String): String {
  val versionNumbers = version.split(".")
  val minorNumber = Integer.parseInt(versionNumbers[1]) + 1
  return "${versionNumbers[0]}.${minorNumber}.0-SNAPSHOT"
}

fun ScriptScope.updateChangelog(version: String) {
  val changelog = ctx.path.resolve("CHANGELOG.md")
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
