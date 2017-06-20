# KSAFE

GUI for SAFE implemented in Kotlin.

## Quick Start

1. Checkout project from git

2. Because of bug https://youtrack.jetbrains.com/issue/KT-4078 change absolute path in /gui/build.gradle.

3. Built by command:

```
gradle build
```

4. Install (or unzip) Apache Tomcat.

5. Add two contexts to Apache Tocat (xml files in conf/catalina/localhost tomcat directory)
```
<Context docBase="...project path to ksafe...\www\build\libs\ksafe-www-1.0-SNAPSHOT.war" path="/ksafe-www" />
<Context docBase="...project path to ksafe...\gui\build\libs\ksafe-gui-1.0-SNAPSHOT.war" path="/ksafe-www" />
```

6. Run Tomcat

7. Open http://localhost:8080/ksafe-gui/ in the browser, Log in as admin/admin.

Use IDEA IntelliJ to open project in the IDE

See also the project ksafe/example with example implementation.