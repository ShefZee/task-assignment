# task-assignment
The solution to the task can be found in the test classes: FileWriterTest & StringWriterTest

For the design, I've used Strategy, Chain of Responsibility & Singleton design patterns.

For the implementation, I've used Maven & java 11.

#Design details

With the help of Strategy pattern, it is easier to implement a new writer, say SocketWriter in future.
Chain of Responsibility pattern will help to implement more future features and clients can use various combinations in order with ease.
SIngleton pattern ensures that there is only one instance of the Writer object and a long running writer thread can be interfered in another thread(using open & close methods).

#Unit Tests & Code Coverage

I've used jacoco to print the coverage report. Please run mvn clean jacoco:prepare-agent install jacoco:report for generating report
