plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kotlin.serialization)
    id("io.ktor.plugin") version "3.0.3"
}
ktor {
    fatJar {
        archiveFileName.set("crypto-app.jar")
    }
}
group = "com.behnamdev"
version = "1.0.0-SNAPSHOT"

application {
    mainClass = "io.ktor.server.cio.EngineMain"
}

kotlin {
    jvmToolchain(21)
    // فورس کردن کامپایلر به استفاده از لایه پایدار قدیمی برای حل باگ کرش خط ۸ Routing
    compilerOptions {
        freeCompilerArgs.add("-Xuse-k2=false")
    }
}

dependencies {
    // ۱. هسته و افزونه‌های سرور Ktor (نسخه سازگار 2.4.3)
    implementation("io.ktor:ktor-server-core:2.4.3")
    implementation("io.ktor:ktor-server-cio:2.4.3")
    implementation("io.ktor:ktor-server-config-yaml:2.4.3")

    // ۲. مدیریت محتوا و JSON در سمت سرور
    implementation("io.ktor:ktor-server-content-negotiation:2.4.3")
    implementation("io.ktor:ktor-serialization-kotlinx-json:2.4.3")
    implementation(libs.kotlinx.serialization.json)

    // ۳. کلاینت Ktor (برای درخواست زدن به API خارجی freecryptoapi)
    implementation("io.ktor:ktor-client-core:2.4.3")
    implementation("io.ktor:ktor-client-cio:2.4.3")
    implementation("io.ktor:ktor-client-content-negotiation:2.4.3")

    // ۴. دیتابیس JetBrains Exposed و درایورها
    implementation("org.jetbrains.exposed:exposed-core:0.57.0")
    implementation("org.jetbrains.exposed:exposed-dao:0.57.0")
    implementation("org.jetbrains.exposed:exposed-jdbc:0.57.0")
    implementation("org.postgresql:postgresql:42.7.4")
    implementation("com.zaxxer:HikariCP:6.2.1")

    // ۵. احراز هویت سرور (در صورت نیاز در آینده)
    implementation("io.ktor:ktor-server-auth:2.4.3")
    implementation("io.ktor:ktor-server-auth-jwt:2.4.3")
    implementation(libs.ktor.client.okHttp)

    // ۶. تزریق وابستگی Koin و ابزار لاگین
    implementation(libs.logback.classic)
    implementation(libs.koin.ktor)
    implementation(libs.koin.logger)
    implementation("io.ktor:ktor-client-okhttp-jvm:3.0.3")

    // ۷. تست‌ها
    testImplementation(kotlin("test"))
    testImplementation("io.ktor:ktor-server-test-host:2.4.3")
}