# Test Objects from File

This is a Spring-Boot project consisting of almost all of the examples provided in this [post](http://blog.acari.io/java/2017/05/01/Hazelcast-Performance-Serialization.html).
Which covers how to write Java objects to a file and how is this useful.

To run the sample you will need:
 - Internet Connection (At least the first time it is run)
 - [Java 8 runtime](http://blog.acari.io/jvm/2017/05/05/Gradle-Install.html)
 - [Gradle 2.3+ ](http://blog.acari.io/jvm/2017/05/05/Gradle-Install.html)
 
Once the repository is on your machine, in order to boot up the server do the following.

1. Open up a command window and make the current working directory the root of the hazelcast-serialization repository
1. Run the command

    gradle test
        
        
The gradle will output a index.html file in the build/reports/tests/test/ of the repository. 
Somewhere in there you should find the standard outputs of the tests run.


Enjoy!

## -Alex
