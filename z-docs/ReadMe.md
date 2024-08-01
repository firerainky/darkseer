The project is built as a multi-module project.

Multi-module reference: https://spring.io/guides/gs/multi-module#scratch

The `application` is the main project and depends on `library` project. While application get running, the library is also running, and the environment is sharing between all projects. So the library can read the config properties defined in application.

In application project, the @ConfigurationProperties is used to represent the properties defined in config. And make the class `ServiceProperties` as the same thing to represent the config.

The `MyServiceTest` class is also interesting. We can assign what we want after annotation `@SpringBootTest`