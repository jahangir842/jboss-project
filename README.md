# JBoss Application Server Project

This project demonstrates the implementation of a modern Java EE application using JBoss Application Server (WildFly). It showcases various enterprise features and best practices for building robust, scalable applications.

## Table of Contents
- [Prerequisites](#prerequisites)
- [Project Structure](#project-structure)
- [Setup and Installation](#setup-and-installation)
- [Configuration](#configuration)
- [Deployment](#deployment)
- [Features](#features)
- [Development Guidelines](#development-guidelines)
- [Troubleshooting](#troubleshooting)

## Prerequisites

- Java Development Kit (JDK) 11 or later
- Maven 3.6 or later
- JBoss Application Server (WildFly) 26 or later
- Git

## Project Structure

```
jboss/
├── src/
│   ├── main/
│   │   ├── java/          # Java source files
│   │   ├── resources/     # Configuration files
│   │   └── webapp/        # Web application files
│   └── test/              # Test files
├── pom.xml                # Maven build configuration
└── README.md             # Project documentation
```

## Setup and Installation

1. **Install WildFly**
   ```bash
   # Download WildFly
   wget https://github.com/wildfly/wildfly/releases/download/26.1.2.Final/wildfly-26.1.2.Final.zip
   
   # Extract the archive
   unzip wildfly-26.1.2.Final.zip
   
   # Move to desired location
   mv wildfly-26.1.2.Final /opt/wildfly
   ```

2. **Configure Environment Variables**
   ```bash
   export JBOSS_HOME=/opt/wildfly
   export PATH=$PATH:$JBOSS_HOME/bin
   ```

3. **Start WildFly Server**
   ```bash
   # Start in standalone mode
   $JBOSS_HOME/bin/standalone.sh
   ```

4. **Access Management Console**
   - URL: http://localhost:9990
   - Default credentials: admin/admin

## Configuration

### Database Configuration
1. Create a datasource in `standalone.xml`:
   ```xml
   <datasource jndi-name="java:jboss/datasources/ExampleDS" 
               pool-name="ExampleDS" 
               enabled="true">
       <connection-url>jdbc:h2:mem:test;DB_CLOSE_DELAY=-1</connection-url>
       <driver>h2</driver>
       <security>
           <user-name>sa</user-name>
           <password>sa</password>
       </security>
   </datasource>
   ```

### Security Configuration
1. Add security domain in `standalone.xml`:
   ```xml
   <security-domain name="example-security" cache-type="default">
       <authentication>
           <login-module code="Database" flag="required">
               <module-option name="dsJndiName" value="java:jboss/datasources/ExampleDS"/>
               <module-option name="principalsQuery" value="SELECT password FROM users WHERE username=?"/>
               <module-option name="rolesQuery" value="SELECT role, 'Roles' FROM user_roles WHERE username=?"/>
           </login-module>
       </authentication>
   </security-domain>
   ```

## Deployment

1. **Build the Application**
   ```bash
   mvn clean package
   ```

2. **Deploy to WildFly**
   ```bash
   # Using CLI
   $JBOSS_HOME/bin/jboss-cli.sh --connect --command="deploy target/your-application.war"
   
   # Or copy to deployment directory
   cp target/your-application.war $JBOSS_HOME/standalone/deployments/
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