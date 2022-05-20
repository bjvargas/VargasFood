FROM openjdk
EXPOSE 8080
ADD target/vargas-food.jar vargas-food.jar
ENTRYPOINT ["java", "-jar", "/vargas-food.jar"]
