tasks.getByName("bootJar") {
    enabled = true
}

tasks.getByName("jar") {
    enabled = true
}

dependencies {
    implementation(project(":playground-core"))
    implementation("org.springframework.boot:spring-boot-starter-web")
}