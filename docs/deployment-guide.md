# JBoss Application Deployment Guide

## Deployment Methods

### 1. Manual Deployment

#### Using CLI
```bash
# Connect to CLI
$JBOSS_HOME/bin/jboss-cli.sh --connect

# Deploy application
deploy /path/to/application.war

# Undeploy application
undeploy application.war
```

#### Using Deployment Scanner
1. Copy WAR file to deployment directory:
```bash
cp application.war $JBOSS_HOME/standalone/deployments/
```

2. Create marker file:
```bash
touch $JBOSS_HOME/standalone/deployments/application.war.dodeploy
```

### 2. Automated Deployment

#### Using Maven
```xml
<plugin>
    <groupId>org.wildfly.plugins</groupId>
    <artifactId>wildfly-maven-plugin</artifactId>
    <version>4.0.0.Final</version>
    <configuration>
        <hostname>localhost</hostname>
        <port>9990</port>
        <username>admin</username>
        <password>admin</password>
    </configuration>
</plugin>
```

Deploy using:
```bash
mvn wildfly:deploy
```

## Deployment Configuration

### 1. Deployment Descriptors

#### web.xml
```xml
<web-app>
    <display-name>My Application</display-name>
    <context-param>
        <param-name>paramName</param-name>
        <param-value>paramValue</param-value>
    </context-param>
</web-app>
```

#### jboss-web.xml
```xml
<jboss-web>
    <context-root>/myapp</context-root>
    <security-domain>my-security-domain</security-domain>
</jboss-web>
```

### 2. Deployment Scanner Configuration

In `standalone.xml`:
```xml
<subsystem xmlns="urn:jboss:domain:deployment-scanner:2.0">
    <deployment-scanner 
        path="deployments" 
        relative-to="jboss.server.base.dir"
        scan-interval="5000"
        auto-deploy-zipped="true"
        auto-deploy-exploded="false"/>
</subsystem>
```

## Deployment Strategies

### 1. Rolling Deployment
1. Deploy new version to subset of servers
2. Verify functionality
3. Gradually deploy to remaining servers

### 2. Blue-Green Deployment
1. Deploy new version to separate environment
2. Switch traffic to new environment
3. Keep old environment as fallback

### 3. Canary Deployment
1. Deploy to small percentage of users
2. Monitor performance and errors
3. Gradually increase deployment scope

## Deployment Best Practices

### 1. Pre-Deployment Checklist
- [ ] Verify application dependencies
- [ ] Check database schema compatibility
- [ ] Validate configuration files
- [ ] Test in staging environment
- [ ] Backup current deployment

### 2. Configuration Management
- Use environment-specific configurations
- Externalize configuration properties
- Implement proper logging
- Set up monitoring

### 3. Security Considerations
- Validate security constraints
- Check SSL/TLS configuration
- Verify authentication mechanisms
- Review access controls

## Monitoring Deployment

### 1. Deployment Status
```bash
# Check deployment status
$JBOSS_HOME/bin/jboss-cli.sh --connect --command="deploy -l"

# View deployment details
$JBOSS_HOME/bin/jboss-cli.sh --connect --command="deployment-info --name=application.war"
```

### 2. Log Monitoring
```bash
# View server logs
tail -f $JBOSS_HOME/standalone/log/server.log

# View deployment logs
tail -f $JBOSS_HOME/standalone/log/deployment.log
```

### 3. Performance Monitoring
- Monitor JVM metrics
- Track request response times
- Check resource utilization
- Monitor error rates

## Rollback Procedures

### 1. Manual Rollback
```bash
# Undeploy current version
$JBOSS_HOME/bin/jboss-cli.sh --connect --command="undeploy application.war"

# Deploy previous version
$JBOSS_HOME/bin/jboss-cli.sh --connect --command="deploy /path/to/previous/application.war"
```

### 2. Automated Rollback
- Implement health checks
- Set up monitoring alerts
- Configure automatic rollback triggers
- Maintain deployment history

## Troubleshooting Deployment

### 1. Common Issues

#### Deployment Failures
- Check server logs
- Verify file permissions
- Validate configuration
- Check resource availability

#### Application Errors
- Review application logs
- Check database connectivity
- Verify external service connections
- Monitor memory usage

### 2. Diagnostic Tools

#### CLI Commands
```bash
# Check server status
$JBOSS_HOME/bin/jboss-cli.sh --connect --command=":read-attribute(name=server-state)"

# View subsystem status
$JBOSS_HOME/bin/jboss-cli.sh --connect --command="/subsystem=web:read-resource"
```

#### Log Analysis
- Use log aggregation tools
- Implement log parsing
- Set up alerting
- Monitor error patterns

## Deployment Automation

### 1. CI/CD Integration

#### Jenkins Pipeline
```groovy
pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                sh 'mvn clean package'
            }
        }
        stage('Deploy') {
            steps {
                sh 'mvn wildfly:deploy'
            }
        }
    }
}
```

### 2. Infrastructure as Code

#### Ansible Playbook
```yaml
- name: Deploy application
  hosts: jboss_servers
  tasks:
    - name: Copy WAR file
      copy:
        src: /path/to/application.war
        dest: /opt/wildfly/standalone/deployments/
    - name: Create marker file
      file:
        path: /opt/wildfly/standalone/deployments/application.war.dodeploy
        state: touch
```

## Resources

- [WildFly Deployment Guide](https://docs.wildfly.org/26/Admin_Guide.html#deployment)
- [Maven WildFly Plugin](https://docs.wildfly.org/26/Admin_Guide.html#wildfly-maven-plugin)
- [JBoss CLI Guide](https://docs.wildfly.org/26/Admin_Guide.html#command-line-interface)
- [Deployment Best Practices](https://docs.wildfly.org/26/Admin_Guide.html#deployment-best-practices) 