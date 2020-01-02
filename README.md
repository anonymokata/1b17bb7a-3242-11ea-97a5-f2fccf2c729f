# Kata Pencil
Accenture Coding Kata that accurately simulate the functions of a pencil as defined at https://github.com/PillarTechnology/kata-pencil-durability. 

The purpose of this is to demonstrate Test Driven Development (TDD) by starting with the unit tests and then creating the necessary commponents to make the test pass. This was not always followed strictly in that order because sometimes I needed to sketch out what I'm building first. I did ensure to create unit tests immediately afterwards and make sure that all my code is testable. The project was created using Java on Eclipse Java EE IDE with a Gradle wrapper for running builds and tests. The unit test framework is JUnit 4. 

I chose Java because it was the best-suited language for this Kata of all the ones I am familiar with (Java, NodeJs, Perl, C). The user stories seemed to imply a strong sense of objects based on how it was written. The user stories extended and built upon the previous functionality, reminding me of inheritance. The user stories also seemed very modularized and specific in their purpose, reminding me of interfaces. Although, I am a little bit biased since the job market in my local area has a heavy lean towards Java and C#/.NET.

## Running unit tests
To run JUnit tests, you must have [gradle](https://gradle.org/install/) installed. 

**Command Line Interface (CLI):**

To run tests, use this in your terminal of choice.

``gradle test``

No log level needs to be specified since testLogger is set and defined within the _build.gradle_ file. If you wish to rerun the tests again in its entirety, you will have to force the _test_ task to rerun all tests without using the cache. You can do this by adding _--rerun-tasks_ to the command.

``gradle test --rerun-tasks``

**Eclipse**

1. Open the _Gradle Tasks_ view in your window
	* Window -> Show View -> Other -> Gradle -> _Gradle Tasks_
2. Open the _build_ folder
3. Run the _build_ task in the folder

## Finding the working and testing classes

All the working classes are found in the _src/main/java_ directory from root of project. For the Junit Tests, the junit tests files will be found in the _src/test/java_ directory.



