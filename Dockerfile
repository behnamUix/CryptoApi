# مرحله اول: استفاده مستقیم از گریدل ۸.۱۲ با جاوا ۲۱
FROM gradle:8.12-jdk21 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src

# تنظیم رم گریدل و فورس کردن کاتلین به عدم استفاده از دمون مجزا برای جلوگیری از کرش رم
ENV GRADLE_OPTS="-Xmx512m -Dorg.gradle.jvmargs=-Xmx512m -Dkotlin.compiler.execution.strategy=in-process"
RUN chmod +x gradlew

RUN ./gradlew buildFatJar --no-daemon

# مرحله دوم: اجرا
FROM eclipse-temurin:21-jre-noble
EXPOSE 8080
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/crypto-app.jar /app/ktor-app.jar
ENTRYPOINT ["java", "-jar", "/app/ktor-app.jar"]