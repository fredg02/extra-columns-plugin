# Extra-Columns plugin for Jenkins

## Note
Older versions of this plugin may not be safe to use. Please review the following warnings before using an older version:
* [Stored XSS vulnerability](https://jenkins.io/security/advisory/2016-04-11/)

## Purpose

This is a plugin for the [Jenkins CI server](https://jenkins.io) which provides additional columns in the main UI screen.
It's using the [listview-column extension](https://wiki.jenkins-ci.org/display/JENKINS/Extension+points#Extensionpoints-hudson.views.ListViewColumn).
Since additional columns do not require a lot of code, the intention is to bundle multiple columns in one plugin instead of having a separate plugin for each column.

It currently provides the following columns:

* Build description
* Build duration
* Build parameters
* Configure build button
* Disable/enable Project button/icon
* Job type
* Last build
* Last build node
* Last/current build console
* Last project configuration modification
* Periodic build trigger
* Project description
* SCM type
* Agent or label restriction
* Test result
* User name
* Workspace link

Some columns have been adapted from existing plugins that in some cases are not actively maintained anymore, e.g.

* [Build node plugin](https://wiki.jenkins-ci.org/display/JENKINS/Build+Node+Column+Plugin)
* [Configure job column plugin](https://wiki.jenkins-ci.org/display/JENKINS/Configure+Job+Column+Plugin)
* [Console column plugin](https://wiki.jenkins-ci.org/display/JENKINS/Console+Column+Plugin)
* [Description column plugin](https://wiki.jenkins-ci.org/display/JENKINS/Description+Column+Plugin)
* [Job type column plugin](https://wiki.jenkins-ci.org/display/JENKINS/Job+Type+Column+Plugin)

## Changelog & Releases

See [GitHub releases](https://github.com/jenkinsci/extra-columns-plugin/releases)


## Usage


### How to add a column

* Create a new view by clicking on the "+" tab above the list of jobs, select "list view" as type and give it a name. At the bottom of the configuration page of the new view you can add more columns and change their order.
* If you want to add columns to the All view, follow the guide on the [Editing or Replacing the All View wiki page](https://wiki.jenkins-ci.org/display/JENKINS/Editing+or+Replacing+the+All+View).

### How to configure a column

Columns can be configured on the "Edit view" page. Please note, not all columns can be configured.

## Support


[Please create an issue in the Jenkins JIRA on the "extra-columns-plugin" component](https://issues.jenkins-ci.org/secure/CreateIssueDetails!init.jspa?pid=10172&issuetype=1&components=15943).
__Please do not use GitHub issues!__

## Contributions

Since the column API is quite simple, it's easy to get started. 
Contributions are more than welcome. :)

### You have an idea for a new column or want to improve an existing column?
The fastest way to get this done, is to fork the GitHub project, take a look at the existing code, copy and/or modify a column and create a pull-request.


