# مرحله اول: کامپایل و ساخت فایل JAR پروژه با جاوا ۲۱
FROM gradle:8.7-jdk21 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src

RUN chmod +x gradlew
# اجرای دستور ساخت fatJar بدون نیاز به پس‌زمینه داکر
RUN ./gradlew buildFatJar --no-daemon

# مرحله دوم: اجرای برنامه با ایمیج معتبر و بهینه جاوا ۲۱
FROM eclipse-temurin:21-jre-noble
EXPOSE 8080
RUN mkdir /app

# کپی کردن دقیق فایل خروجی بر اساس نام تعیین شده در گریدل
COPY --from=build /home/gradle/src/build/libs/crypto-app.jar /app/ktor-app.jar

# دستور اجرای برنامه Ktor
ENTRYPOINT ["java", "-jar", "/app/ktor-app.jar"]