
Jar classloader that allow you to load jar packed in jar.

With it, you can deliver your application in a single jar or war file.

features : 
- can load jar in jar in jar ... 
- can load jar in war ...
- support native lib loading
- allow runnable war from container or from cli with only one war file
- maven sample configuration (2 plugin to configure)
- define your real main class with a simple manifest entry
- configurable path scanned to exclude some jar 


Associated with jetty, you can produce a single war, able to be deployed in a container or run from the command line.
See the samples for more informations.