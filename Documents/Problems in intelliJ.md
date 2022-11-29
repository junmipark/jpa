---

    title : Problems in intelliJ 
    date : 2022-11-29
    last-modified-date : 2022-11-29
    author : junmipark

---

# Problems in intelliJ

## Index

- [Problems in intelliJ](#problems-in-intellij)
  - [Index](#index)
  - [Create Project](#create-project)
    - [Create Webapp Project](#create-webapp-project)
  - [Add Library in Webapp Project](#add-library-in-webapp-project)
    - [JPA, Hibernate](#jpa-hibernate)
    - [H2](#h2)
    - [Notes](#notes)
      - [IDE Version](#ide-version)

## Create Project

### Create Webapp Project

    1. Select File -> New -> Project...
    2. Select Option "Maven Archetype" 
    3. Change Option "Archtype" to "org.apache.maven.archetypes:maven-archetype-webapp"

## Add Library in Webapp Project

### JPA, Hibernate
    
    1. Open "pom.xml" in project
    2. Add dependency "hibernate-entitymanager"

    ```
        <!-- https://mvnrepository.com/artifact/org.hibernate/hibernate-entitymanager -->
        <dependency>
            <groupId>org.hibernate</groupId>
            <artifactId>hibernate-entitymanager</artifactId>
            <version>5.6.14.Final</version>
        </dependency>
    ```

### H2

    1. Open "pom.xml" in proejct
    2. Add dependency "h2"

    ```
        <!-- https://mvnrepository.com/artifact/com.h2database/h2 -->
        <dependency>
            <groupId>com.h2database</groupId>
            <artifactId>h2</artifactId>
            <version>1.4.200</version>
            <scope>test</scope>
        </dependency>
    ```

<!--  -->

### Notes

#### IDE Version
    IntelliJ IDEA Community Edition 2022.2.4