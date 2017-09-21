# Usage

This project can be started using gradle or maven

## Gradle

Build and start application

    ./gradlew clean build --info && java -jar $(find build/libs -name "*.jar" -type f | xargs ls -tr | tail -1)

Build with gradle

    ./gradlew clean build --info 
    
Generate pom.xml (Use gradle-maven plugin)

    ./gradlew createPom
    
Start application (require a database)

    java -jar $(find build/libs -name "*.jar" -type f | xargs ls -tr | tail -1)
    
## Maven

Build and start application

    ./mvnw clean install && java -jar $(find target -name "*.jar" -type f | xargs ls -tr | tail -1)

Build with maven

    ./mvnw clean install
    
Start application (require a database)

    java -jar $(find target -name "*.jar" -type f | xargs ls -tr | tail -1)
    