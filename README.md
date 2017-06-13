# KSAFE

GUI for SAFE implemented in Kotlin.

## Quick Start

* Checkout project from git

* Beause of bug https://youtrack.jetbrains.com/issue/KT-4078 change absolute path in /gui/build.gradle.

* Build by command:
...
gradle build
...

* Install (or unzip) Apache Tomcat.

* Add two contexts to Apache Tocat (xml files in conf/catalina/localhost tomcat directory)
...
<Context docBase="...project path to ksafe...\www\build\libs\www-1.0-SNAPSHOT.war" path="/ksafe-www" />
<Context docBase="...project path to ksafe...\gui\build\libs\gui-1.0-SNAPSHOT.war" path="/ksafe-www" />
...

* Run Tomcat

* Open http://localhost:8080/ksafe-gui/ in the browser, Log in as admin/admin.

* Use IDEA IntelliJ to open project in the IDE

* See also the project ksafe/example with example implementation.