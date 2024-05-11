FROM openjdk:17

#ENV DATABASE_URL jdbc:mysql://localhost:3306/users
#ENV DATABASE_USERNAME admin
#ENV DATABASE_PASSWORD 1234
#ENV DATABASE_PLATFORM org.hibernate.dialect.MySQL8Dialect
#ENV DATABASE_DRIVER com.mysql.jdbc.Driver

EXPOSE 8090

ADD target/login-0.0.1-SNAPSHOT.jar login-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/login-0.0.1-SNAPSHOT.jar"]