# Superloop Back-End

## SpringBoot

[Spring Boot](https://projects.spring.io/spring-boot/) is an Opinionated Java Framework for developing production-ready
Spring applications. Spring Boot favours convention over configuration and is designed to get you up and running as
quickly as possible.

## Gradle

[Gradle](https://gradle.org/) is the build tool for this project, and requires Java JDK to be installed. It is not
necessary to install gradle.

### Building the application

This project makes use of [The Gradle Wrapper](https://docs.gradle.org/current/userguide/gradle_wrapper.html). The
following command will run a full build of the project.

    gradlew build

### Running the application

This project makes use of the
[Gradle Spring Boot](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-gradle-plugin.html)
plugin. To start the application locally just run the following command

    gradlew bootRun
