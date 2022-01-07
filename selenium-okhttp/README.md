# selenium-okhttp

OkHTTP3 relies on a using a logging-interceptor that needs to be created at the time of instanting the HTTPClient Builder class.

To do this we need to create our own HTTPFactory (copied from the Selenium project) but add the interceptor.

This allows all request info including headers, request and response body.

The steps to add this to your project are:

1. Add logging-interceptor as a dependency
```xml
<dependency>
    <groupId>com.squareup.okhttp3</groupId>
    <artifactId>logging-interceptor</artifactId>
    <version>3.11.0</version>
</dependency>
```

1. Copy the contents of HTTPFactory.java

1. Create HTTPFactory object and HTTPCommandExecutor
```java
HTTPFactory factory = new HTTPFactory();
HttpCommandExecutor executor = new HttpCommandExecutor(Collections.emptyMap(), new URL(SAUCE_URL), factory);
```

1. Create Driver object with executor
```java
AppiumDriver driver = new AppiumDriver(executor, caps);
```
