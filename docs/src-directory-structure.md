# Source Directory Structure Guide

## Overview

The `src` directory is the heart of any Java EE project, containing all source code, resources, and configuration files. Understanding its structure and organization is crucial for effective development and maintenance.

## Standard Directory Structure

```
src/
├── main/
│   ├── java/              # Java source files
│   ├── resources/         # Configuration and resource files
│   ├── webapp/           # Web application files
│   └── webapp/WEB-INF/   # Web deployment descriptors
└── test/
    ├── java/             # Test source files
    ├── resources/        # Test resources
    └── webapp/          # Test web files
```

## Detailed Breakdown

### 1. Main Source Directory (`src/main`)

#### Java Source Files (`src/main/java`)
```
src/main/java/
└── com/
    └── example/
        ├── controller/    # MVC controllers
        ├── service/       # Business logic
        ├── dao/          # Data access objects
        ├── model/        # Entity classes
        ├── util/         # Utility classes
        └── config/       # Configuration classes
```

- **Controller Layer**
  - Handles HTTP requests
  - Manages user interactions
  - Returns appropriate responses
  ```java
  @Path("/api")
  public class UserController {
      @GET
      @Path("/users")
      public List<User> getUsers() {
          // Implementation
      }
  }
  ```

- **Service Layer**
  - Contains business logic
  - Manages transactions
  - Coordinates between controllers and DAOs
  ```java
  @Stateless
  public class UserService {
      @Inject
      private UserDAO userDAO;
      
      public User createUser(User user) {
          // Business logic
      }
  }
  ```

- **DAO Layer**
  - Handles database operations
  - Implements data access patterns
  - Uses JPA/Hibernate
  ```java
  @Stateless
  public class UserDAO {
      @PersistenceContext
      private EntityManager em;
      
      public User findById(Long id) {
          return em.find(User.class, id);
      }
  }
  ```

#### Resources Directory (`src/main/resources`)
```
src/main/resources/
├── META-INF/
│   ├── persistence.xml   # JPA configuration
│   └── beans.xml        # CDI configuration
├── application.properties
└── log4j2.xml
```

- **Configuration Files**
  - Database settings
  - Logging configuration
  - Application properties
  - Security settings

#### Web Application Directory (`src/main/webapp`)
```
src/main/webapp/
├── WEB-INF/
│   ├── web.xml          # Web deployment descriptor
│   ├── jboss-web.xml    # JBoss specific configuration
│   └── faces-config.xml # JSF configuration
├── index.html
├── css/
├── js/
└── images/
```

- **Static Resources**
  - HTML files
  - CSS stylesheets
  - JavaScript files
  - Images and media

### 2. Test Directory (`src/test`)

#### Test Source Files (`src/test/java`)
```
src/test/java/
└── com/
    └── example/
        ├── controller/
        ├── service/
        ├── dao/
        └── integration/
```

- **Unit Tests**
  - Test individual components
  - Use mocking frameworks
  - Verify business logic

- **Integration Tests**
  - Test component interactions
  - Verify database operations
  - Test REST endpoints

#### Test Resources (`src/test/resources`)
```
src/test/resources/
├── test-persistence.xml
├── test-application.properties
└── test-data.sql
```

- **Test Configuration**
  - Test-specific settings
  - Test database configuration
  - Test data setup

## Working with Source Files

### 1. Package Organization

#### Best Practices
- Use meaningful package names
- Follow domain-driven design
- Separate concerns
- Maintain consistent structure

#### Example Package Structure
```java
com.example.project
├── api          // REST endpoints
├── business     // Business logic
├── persistence  // Data access
├── model        // Entity classes
└── util         // Utilities
```

### 2. Resource Management

#### Configuration Files
- Externalize configuration
- Use environment-specific properties
- Implement proper logging
- Manage database connections

#### Example Configuration
```properties
# application.properties
database.url=jdbc:mysql://localhost:3306/mydb
database.username=user
database.password=pass
logging.level=INFO
```

### 3. Web Resources

#### Static Content
- Organize by type (css, js, images)
- Use versioning for caching
- Implement proper minification
- Follow naming conventions

#### Example Structure
```
webapp/
├── assets/
│   ├── css/
│   │   ├── main.css
│   │   └── vendor.css
│   ├── js/
│   │   ├── app.js
│   │   └── vendor.js
│   └── images/
│       ├── logo.png
│       └── icons/
└── WEB-INF/
    └── templates/
        ├── header.xhtml
        └── footer.xhtml
```

## Build and Deployment

### 1. Maven Integration

#### Source Directory Configuration
```xml
<build>
    <sourceDirectory>src/main/java</sourceDirectory>
    <testSourceDirectory>src/test/java</testSourceDirectory>
    <resources>
        <resource>
            <directory>src/main/resources</directory>
        </resource>
    </resources>
    <testResources>
        <testResource>
            <directory>src/test/resources</directory>
        </testResource>
    </testResources>
</build>
```

### 2. IDE Configuration

#### Eclipse/IntelliJ Setup
- Configure source folders
- Set up resource folders
- Configure build path
- Set up test configurations

## Best Practices

1. **Code Organization**
   - Follow consistent naming conventions
   - Use proper package structure
   - Implement separation of concerns
   - Maintain clean architecture

2. **Resource Management**
   - Externalize configuration
   - Use proper resource loading
   - Implement caching strategies
   - Manage file system access

3. **Testing**
   - Write comprehensive unit tests
   - Implement integration tests
   - Use proper test data
   - Follow testing best practices

4. **Documentation**
   - Document package structure
   - Maintain README files
   - Use proper JavaDoc
   - Document configuration

## Common Issues and Solutions

1. **Classpath Issues**
   - Verify source directory configuration
   - Check resource loading
   - Validate build path
   - Review dependency management

2. **Resource Loading**
   - Use proper resource paths
   - Implement proper error handling
   - Verify file permissions
   - Check resource availability

3. **Build Problems**
   - Validate directory structure
   - Check Maven configuration
   - Verify IDE settings
   - Review build logs

## Resources

- [Maven Standard Directory Layout](https://maven.apache.org/guides/introduction/introduction-to-the-standard-directory-layout.html)
- [Java EE Project Structure](https://docs.oracle.com/javaee/8/tutorial/projectstructure.htm)
- [IntelliJ Project Structure](https://www.jetbrains.com/help/idea/project-structure.html)
- [Eclipse Project Structure](https://help.eclipse.org/latest/index.jsp?topic=%2Forg.eclipse.jdt.doc.user%2Freference%2Fref-properties-build-path.htm) 