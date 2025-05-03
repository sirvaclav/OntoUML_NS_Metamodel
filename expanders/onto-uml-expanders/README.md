# onto-uml-expanders

This project contains the expansion resource `onto-uml-expanders`.

## Release procedure

When creating a release of this project, the following steps should be taken:

1. Make sure your local copy is up-to-date with the changes on the server. You should at least have the latest version
   of the `master` and `develop` branch.
2. Switch to the `develop` branch.
3. Make sure that your `CHANGELOG.md` file contains all changes on the `develop` branch since the last release under
   a section labeled with the intended release version number. For example, if you are preparing release `1.2.3`, this
   could look like:
   ```markdown
   ## 1.2.3

   ### Fixed

   - Bug X which caused problem Y was resolved.
   - Behavior X no longer occurs.

   ### Added

   - New feature X was added which allows you to do Y.

   ### Changed

   - Feature X now does Y instead of Z.

   ### Removed

   - Functionality X was removed from the application.
   ```
   > Take note that all releases which have a date behind the section are already released, a pending release should
   > not have a date, as this will be added by the release script automatically.

4. Commit any last changes made to the changelog or elsewhere.
5. Call the release script as `nss scripts/release.nss <versionNumber>`, where you replace `<versionNumber>` with the
   intended version of the release. (`1.2.3` in the changelog example)

The script will now update the version in the project, finalize the changelog by adding a release date, create release
commits, a release tag and push everything to the remote server.
