# مرحله ۱: بیلد کردن پروژه (با استفاده از ایمیج گریدل)
FROM gradle:8.5-jdk17 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
# اجرای دستور گریدل برای ساخت Fat JAR بدون نیاز به اجرای دیمون
RUN ./gradlew fatJar --no-daemon

# مرحله ۲: محیط اجرای بسیار سبک جاوا برای اجرای سرور
FROM openjdk:17-slim
EXPOSE 8080
RUN mkdir /app

# کپی کردن فایل Jar تولید شده از مرحله قبل به محیط اصلی
COPY --from=build /home/gradle/src/build/libs/crypto-app.jar /app/crypto-app.jar

# دستور نهایی برای اجرای سرور Ktor
ENTRYPOINT ["java", "-jar", "/app/crypto-app.jar"]