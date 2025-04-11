# Changing Project Identity in Maven Projects

This guide explains how to properly change your project's identification details in a Maven project and the necessary steps to ensure everything works correctly afterward.

## Project Identification Elements

The main project identification elements in `pom.xml` are:

```xml
<groupId>com.example</groupId>        <!-- Organization identifier -->
<artifactId>project-name</artifactId> <!-- Project name -->
<version>1.0.0</version>             <!-- Version number -->
<packaging>war</packaging>           <!-- Package type -->
```

### Understanding Each Element

1. **groupId**: 
   - Identifies your organization/company
   - Usually follows reverse domain name convention
   - Examples: `com.yourcompany`, `org.yourorganization`
   - Affects package structure in your source code

2. **artifactId**:
   - The name of your project
   - Should be lowercase with hyphens
   - Examples: `employee-management`, `inventory-system`
   - Affects the final build artifact name

3. **version**:
   - Your project's version number
   - Follows semantic versioning (major.minor.patch)
   - Examples: `1.0.0`, `2.1.0-SNAPSHOT`

4. **packaging**:
   - Type of project artifact
   - Common types: `war`, `jar`, `ear`
   - Affects build process and final artifact

## Steps to Change Project Identity

1. **Backup Your Project**
   ```bash
   cp -r your-project your-project-backup
   ```

2. **Update POM File**
   - Modify the project identification elements in `pom.xml`
   - Ensure version numbers are appropriate
   - Update any references to the old identity in properties or configurations

3. **Update Package Structure**
   If changing `groupId`:
   ```bash
   # Create new package structure
   mkdir -p src/main/java/com/newgroup/{domain1,domain2}
   
   # Move files to new structure
   mv src/main/java/com/oldgroup/* src/main/java/com/newgroup/
   
   # Remove old structure
   rm -rf src/main/java/com/oldgroup
   ```

4. **Update Source Files**
   - Update package declarations in all Java files
   - Update import statements referencing old packages
   - Update any configuration files referencing the old identity

5. **Clean and Rebuild**
   ```bash
   mvn clean           # Clean previous builds
   mvn compile         # Compile to verify changes
   mvn clean package   # Create new artifact
   ```

## Common Issues and Solutions

1. **Package Declaration Mismatch**
   - Error: "The declared package does not match the expected package"
   - Solution: Ensure package declarations in Java files match the new directory structure

2. **Import Resolution Problems**
   - Error: "Cannot resolve symbol" or "Package does not exist"
   - Solution: Update import statements to use new package names

3. **Build Path Issues**
   - Error: "Source folder not found" or "Resource not found"
   - Solution: Update IDE project settings and rebuild project

4. **Dependency Resolution**
   - Error: "Could not resolve dependencies"
   - Solution: Update dependency references if they used old project identity

## Best Practices

1. **Version Control**
   - Commit changes before starting
   - Make changes in a new branch
   - Review changes before committing

2. **Testing**
   - Run all tests after changes
   - Verify application deployment
   - Check all external references

3. **Documentation**
   - Update README files
   - Update configuration guides
   - Update deployment documentation

4. **IDE Support**
   - Refresh/reload project in IDE
   - Clean IDE caches if needed
   - Update IDE-specific configurations

## Example Workflow

```bash
# 1. Backup project
cp -r my-project my-project-backup

# 2. Update pom.xml
# Edit groupId, artifactId, version as needed

# 3. Create new package structure
mkdir -p src/main/java/com/newcompany/module

# 4. Move and update files
mv src/main/java/com/oldcompany/module/* src/main/java/com/newcompany/module/

# 5. Clean old structure
rm -rf src/main/java/com/oldcompany

# 6. Update package declarations in source files
# Use IDE refactoring tools or sed for bulk updates

# 7. Clean and rebuild
mvn clean
mvn compile

# 8. Run tests
mvn test

# 9. Package application
mvn package
```

## Additional Considerations

1. **Deployment Scripts**
   - Update deployment configurations
   - Update CI/CD pipelines
   - Update server configurations

2. **External References**
   - Update client applications
   - Update service references
   - Update documentation links

3. **Database Changes**
   - Update persistence configurations
   - Update database scripts if needed
   - Update connection properties

Remember to always test thoroughly after making identity changes, as they can affect many aspects of your application's functionality and deployment. 