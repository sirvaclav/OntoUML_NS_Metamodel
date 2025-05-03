fun setVersion(version: String) {
  dir("${ctx.script.path.parent}") {
    mvn {
      task("versions:set")
      property("newVersion", version)
    }
    mvn {
      task("expanders:set-model-version")
    }
  }
}
