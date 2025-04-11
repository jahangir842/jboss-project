# JBoss Application Server Project

This project demonstrates the implementation of a modern Java EE application using JBoss Application Server (WildFly). It showcases various enterprise features and best practices for building robust, scalable applications.

## Table of Contents
- [Prerequisites](#prerequisites)
- [Project Structure](#project-structure)
- [Setup and Installation](#setup-and-installation)
- [Building the Application](#building-the-application)
- [Running the Application](#running-the-application)
- [Deployment](#deployment)
- [Understanding the Application](#understanding-the-application)
- [Features](#features)
- [Development Guidelines](#development-guidelines)
- [Troubleshooting](#troubleshooting)

## Prerequisites

- Java Development Kit (JDK) 11 or later
- Maven 3.6 or later
- JBoss Application Server (WildFly) 26 or later
- Git
- A modern web browser

## Project Structure

```
jboss/
├── src/
│   ├── main/
│   │   ├── java/          # Java source files
│   │   ├── resources/     # Configuration files
│   │   └── webapp/        # Web application files
│   └── test/              # Test files
├── target/                # Build output directory
│   ├── classes/           # Compiled Java classes
│   ├── generated-sources/ # Generated source files
│   ├── test-classes/      # Compiled test classes
│   ├── war/               # Unpacked WAR file
│   └── jboss-app.war      # Final WAR artifact
├── docs/                  # Documentation
├── pom.xml                # Maven build configuration
└── README.md             # Project documentation
```

Note: The `target` directory is automatically created during the build process and contains:
- Compiled Java classes
- Generated source files
- Test classes
- Build artifacts (WAR file)
- Temporary build files

This directory is typically excluded from version control (see `.gitignore`).

## Setup and Installation

### 1. Install Maven
```bash
# Download Maven
wget https://dlcdn.apache.org/maven/maven-3/3.9.6/binaries/apache-maven-3.9.6-bin.tar.gz

# Extract the archive
tar -xzf apache-maven-3.9.6-bin.tar.gz

# Move to desired location
sudo mv apache-maven-3.9.6 /opt/maven

# Configure environment variables
echo 'export M2_HOME=/opt/maven' >> ~/.bashrc
echo 'export PATH=$PATH:$M2_HOME/bin' >> ~/.bashrc
source ~/.bashrc

# Verify installation
mvn -version
```

### 2. Install WildFly
```bash
# Download WildFly
wget https://github.com/wildfly/wildfly/releases/download/36.0.0.Final/wildfly-36.0.0.Final.zip

# Extract the archive
unzip wildfly-36.0.0.Final.zip

# Move to desired location
sudo mv wildfly-36.0.0.Final /opt/wildfly
```

### 2. Configure Environment Variables
```bash
# Add to your ~/.bashrc or ~/.zshrc

echo 'export JBOSS_HOME=/opt/wildfly' >> ~/.bashrc
echo 'export PATH=$PATH:$JBOSS_HOME/bin' >> ~/.bashrc
source ~/.bashrc

```

### 3. Start WildFly Server
```bash
# Start in standalone mode
$JBOSS_HOME/bin/standalone.sh
```

### 4. Access Management Console
- URL: http://localhost:9990

## Authentication

By default WildFly is now distributed with security enabled for the management interfaces, this means that before you connect using the administration console or remotely using the CLI you will need to add a new user, this can be achieved simply by using the add-user.sh script in the bin folder.

After starting the script you will be guided through the process to add a new user: -

```bash
$JBOSS_HOME/bin/add-user.sh
```

## Building the Application

### 1. Clone the Repository
```bash
git clone <repository-url>
cd jboss
```

### 2. Build the Application
```bash
# Clean and build
mvn clean package

# Build with tests
mvn clean verify

# Build without tests
mvn clean package -DskipTests
```

### Understanding the Build Process
1. **Clean Phase**: Removes previous build artifacts
2. **Compile Phase**: Compiles Java source files
3. **Test Phase**: Runs unit and integration tests
4. **Package Phase**: Creates WAR file
5. **Install Phase**: Installs artifact in local Maven repository

## Running the Application

### 1. Development Mode
```bash
# Start WildFly in development mode
$JBOSS_HOME/bin/standalone.sh -c standalone-full.xml

# Deploy the application
mvn wildfly:deploy
```

### 2. Production Mode
```bash
# Start WildFly in production mode
$JBOSS_HOME/bin/standalone.sh -c standalone.xml

# Deploy the application
mvn wildfly:deploy
```

## Deployment

### 1. Manual Deployment
```bash
# Copy WAR file to deployment directory
cp target/jboss-app.war $JBOSS_HOME/standalone/deployments/

# Create marker file
touch $JBOSS_HOME/standalone/deployments/jboss-app.war.dodeploy
```

### 2. Automated Deployment
```bash
# Deploy using Maven
mvn wildfly:deploy

# Undeploy
mvn wildfly:undeploy

# Redeploy
mvn wildfly:redeploy
```

## Understanding the Application

### 1. Application Components
- **Controllers**: Handle HTTP requests and responses
- **Services**: Implement business logic
- **DAOs**: Manage data access
- **Entities**: Represent database tables
- **Views**: Present data to users

### 2. Request Flow
1. Client sends HTTP request
2. Controller receives request
3. Service processes business logic
4. DAO accesses database
5. Response returned to client

### 3. Key Files and Their Purpose
- `pom.xml`: Project configuration and dependencies
- `web.xml`: Web application configuration
- `persistence.xml`: Database configuration
- `jboss-web.xml`: JBoss-specific settings

### 4. Database Configuration
```xml
<!-- src/main/resources/META-INF/persistence.xml -->
<persistence-unit name="examplePU">
    <jta-data-source>java:jboss/datasources/ExampleDS</jta-data-source>
    <properties>
        <property name="hibernate.hbm2ddl.auto" value="update"/>
    </properties>
</persistence-unit>
```

### 5. Web Configuration
```xml
<!-- src/main/webapp/WEB-INF/web.xml -->
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
```

## Features

- Enterprise Java Beans (EJB) implementation
- RESTful web services using JAX-RS
- JPA/Hibernate integration
- Security implementation using JAAS
- WebSocket support
- Clustering capabilities
- JMS messaging
- Batch processing

## Development Guidelines

### Code Structure
- Follow Java EE 8 specifications
- Implement proper exception handling
- Use dependency injection
- Follow REST API best practices
- Implement proper logging

### Testing
- Unit tests using JUnit
- Integration tests using Arquillian
- Performance testing using JMeter

### Security Best Practices
- Implement proper authentication and authorization
- Use HTTPS for secure communication
- Implement input validation
- Follow OWASP security guidelines

## Troubleshooting

### Common Issues

1. **Port Conflicts**
   - Check if ports 8080, 9990, and 9999 are available
   - Modify ports in `standalone.xml` if needed

2. **Deployment Failures**
   - Check server logs in `$JBOSS_HOME/standalone/log/server.log`
   - Verify application dependencies
   - Check for configuration errors

3. **Memory Issues**
   - Adjust JVM settings in `standalone.conf`
   - Monitor heap usage
   - Configure garbage collection

### Logging
- Application logs: `$JBOSS_HOME/standalone/log/server.log`
- Access logs: `$JBOSS_HOME/standalone/log/access.log`
- Audit logs: `$JBOSS_HOME/standalone/log/audit.log`

## Additional Resources

- [WildFly Documentation](https://docs.wildfly.org/)
- [Java EE 8 Tutorial](https://javaee.github.io/tutorial/)
- [Maven Documentation](https://maven.apache.org/guides/)
- [Hibernate Documentation](https://hibernate.org/orm/documentation/) 