# Java EE Packaging Guide

## Overview

Java EE applications can be packaged in different formats, each serving specific purposes and containing different types of components. Understanding these packaging types is crucial for proper application deployment and management.

## Common Packaging Types

### 1. WAR (Web Application Archive)
- **Purpose**: Web applications
- **Extension**: `.war`
- **Contents**:
  - Servlets
  - JSPs
  - Static resources (HTML, CSS, JS)
  - Web deployment descriptors
  - Libraries (in WEB-INF/lib)

#### Directory Structure
```
myapp.war
├── META-INF/
│   └── MANIFEST.MF
├── WEB-INF/
│   ├── web.xml
│   ├── jboss-web.xml
│   ├── classes/
│   └── lib/
├── index.html
└── resources/
    ├── css/
    ├── js/
    └── images/
```

#### Deployment Descriptors
```xml
<!-- web.xml -->
<web-app>
    <servlet>
        <servlet-name>MyServlet</servlet-name>
        <servlet-class>com.example.MyServlet</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>MyServlet</servlet-name>
        <url-pattern>/myservlet</url-pattern>
    </servlet-mapping>
</web-app>

<!-- jboss-web.xml -->
<jboss-web>
    <context-root>/myapp</context-root>
</jboss-web>
```

### 2. JAR (Java Archive)
- **Purpose**: Library or EJB modules
- **Extension**: `.jar`
- **Contents**:
  - Java classes
  - Resources
  - Deployment descriptors

#### Directory Structure
```
mylib.jar
├── META-INF/
│   ├── MANIFEST.MF
│   └── ejb-jar.xml
└── com/
    └── example/
        └── MyClass.class
```

#### EJB Deployment Descriptor
```xml
<!-- ejb-jar.xml -->
<ejb-jar>
    <enterprise-beans>
        <session>
            <ejb-name>MyEJB</ejb-name>
            <ejb-class>com.example.MyEJB</ejb-class>
            <session-type>Stateless</session-type>
        </session>
    </enterprise-beans>
</ejb-jar>
```

### 3. EAR (Enterprise Archive)
- **Purpose**: Complete enterprise applications
- **Extension**: `.ear`
- **Contents**:
  - WAR files
  - EJB JARs
  - Resource adapters
  - Application client modules

#### Directory Structure
```
myapp.ear
├── META-INF/
│   ├── MANIFEST.MF
│   └── application.xml
├── mywebapp.war
├── myejb.jar
└── lib/
    └── common-lib.jar
```

#### Application Descriptor
```xml
<!-- application.xml -->
<application>
    <display-name>My Enterprise App</display-name>
    <module>
        <web>
            <web-uri>mywebapp.war</web-uri>
            <context-root>/myapp</context-root>
        </web>
    </module>
    <module>
        <ejb>myejb.jar</ejb>
    </module>
</application>
```

## Packaging Best Practices

### 1. WAR Packaging
- Keep static resources separate from Java classes
- Use proper directory structure
- Minimize WEB-INF/lib contents
- Externalize configuration
- Use proper context roots

### 2. JAR Packaging
- Follow package naming conventions
- Include proper manifest
- Document public APIs
- Version your libraries
- Minimize dependencies

### 3. EAR Packaging
- Organize modules logically
- Share common libraries
- Use proper class loading
- Configure security
- Manage dependencies

## Maven Packaging Configuration

### 1. WAR Module
```xml
<project>
    <packaging>war</packaging>
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-war-plugin</artifactId>
                <version>3.3.2</version>
                <configuration>
                    <warName>myapp</warName>
                    <webResources>
                        <resource>
                            <directory>src/main/webapp</directory>
                        </resource>
                    </webResources>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

### 2. EJB Module
```xml
<project>
    <packaging>ejb</packaging>
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-ejb-plugin</artifactId>
                <version>3.1.0</version>
                <configuration>
                    <ejbVersion>3.2</ejbVersion>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

### 3. EAR Module
```xml
<project>
    <packaging>ear</packaging>
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-ear-plugin</artifactId>
                <version>3.2.0</version>
                <configuration>
                    <modules>
                        <webModule>
                            <groupId>com.example</groupId>
                            <artifactId>mywebapp</artifactId>
                            <contextRoot>/myapp</contextRoot>
                        </webModule>
                        <ejbModule>
                            <groupId>com.example</groupId>
                            <artifactId>myejb</artifactId>
                        </ejbModule>
                    </modules>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

## Deployment Considerations

### 1. Class Loading
- Understand class loading hierarchy
- Avoid class loading conflicts
- Use proper module dependencies
- Configure class loading isolation

### 2. Resource Management
- Externalize configuration
- Use JNDI for resource lookup
- Configure connection pools
- Manage file system access

### 3. Security
- Configure security domains
- Use proper authentication
- Implement authorization
- Secure sensitive data

## Common Issues and Solutions

### 1. Class Loading Conflicts
- Use proper module dependencies
- Configure class loading isolation
- Avoid duplicate classes
- Use proper versioning

### 2. Resource Access
- Use JNDI for resource lookup
- Configure proper permissions
- Handle resource cleanup
- Implement proper error handling

### 3. Deployment Failures
- Check deployment descriptors
- Verify dependencies
- Review server logs
- Check file permissions

## Resources

- [Java EE 8 Specification](https://javaee.github.io/javaee-spec/)
- [Maven Packaging Types](https://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html#Packaging)
- [WildFly Class Loading](https://docs.wildfly.org/26/Admin_Guide.html#class-loading)
- [Java EE Deployment](https://docs.oracle.com/javaee/8/tutorial/deployment.htm) 