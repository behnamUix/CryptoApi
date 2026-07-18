# مرحله اول: کامپایل و ساخت فایل JAR پروژه
FROM gradle:8.5-jdk17 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src

RUN ./gradlew buildFatJar --no-daemon


FROM openjdk:17-slim
EXPOSE 8080
RUN mkdir /app

COPY --from=build /home/gradle/src/build/libs/*all.jar /app/ktor-app.jar
# دستور اجرای برنامه Ktor
ENTRYPOINT ["java", "-jar", "/app/ktor-app.jar"]