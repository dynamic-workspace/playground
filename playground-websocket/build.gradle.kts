val openFeignVersion: String by rootProject

dependencies {
    implementation(project(":playground-core"))

    implementation("org.springframework.boot:spring-boot-starter-websocket")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.kafka:spring-kafka")
    implementation("io.github.openfeign:feign-core:$openFeignVersion")
    implementation("io.github.openfeign:feign-jackson:$openFeignVersion")
}