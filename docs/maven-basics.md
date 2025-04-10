# Maven Basics

## What is Maven?

Maven is a powerful project management and comprehension tool that provides a complete build lifecycle framework. It is primarily used for Java projects but can be used for other languages as well.

### Key Features
- Project Object Model (POM)
- Dependency Management
- Build Lifecycle
- Standard Directory Structure
- Plugin Architecture

## Maven Directory Structure

```
project/
├── src/
│   ├── main/
│   │   ├── java/          # Java source files
│   │   ├── resources/     # Resource files
│   │   └── webapp/        # Web application files
│   └── test/              # Test files
├── target/                # Compiled files and build output
└── pom.xml                # Project configuration
```

## Maven Lifecycle

Maven has three built-in build lifecycles:
1. **default**: handles project deployment
2. **clean**: handles project cleaning
3. **site**: handles the creation of project's site documentation

### Common Maven Commands

```bash
# Clean and build
mvn clean install

# Run tests
mvn test

# Create project documentation
mvn site

# Skip tests
mvn install -DskipTests

# Build with specific profile
mvn install -P production
```

## Dependency Management

Maven manages project dependencies through the POM file. Dependencies are automatically downloaded from repositories.

### Dependency Scope
- **compile**: default scope, available in all classpaths
- **provided**: expected to be provided by JDK or container
- **runtime**: required for execution but not compilation
- **test**: only available for test compilation and execution
- **system**: similar to provided but requires explicit path
- **import**: only used in dependencyManagement section

## Maven Repositories

1. **Local Repository**: Located in `~/.m2/repository`
2. **Central Repository**: Maven's default repository
3. **Remote Repository**: Custom repositories defined in POM

## Maven Plugins

Maven plugins provide additional functionality:
- **Compiler Plugin**: Compiles Java source files
- **Surefire Plugin**: Runs unit tests
- **War Plugin**: Creates WAR files
- **Javadoc Plugin**: Generates Javadoc documentation

## Best Practices

1. **Use Specific Versions**
   - Always specify exact versions for dependencies
   - Avoid using LATEST or RELEASE

2. **Organize Dependencies**
   - Group related dependencies
   - Use dependencyManagement for version control

3. **Use Properties**
   - Define versions as properties
   - Makes version management easier

4. **Keep POM Clean**
   - Remove unused dependencies
   - Use inheritance when possible

5. **Use Profiles**
   - Separate development and production configurations
   - Handle different environments

## Common Issues and Solutions

1. **Dependency Conflicts**
   - Use `mvn dependency:tree` to analyze
   - Use `mvn dependency:analyze` to find unused dependencies

2. **Build Failures**
   - Check Maven version compatibility
   - Verify network connectivity to repositories

3. **Slow Builds**
   - Use `-o` for offline mode
   - Configure mirror repositories

## Resources

- [Maven Official Documentation](https://maven.apache.org/guides/)
- [Maven Central Repository](https://search.maven.org/)
- [Maven Plugin Documentation](https://maven.apache.org/plugins/) 