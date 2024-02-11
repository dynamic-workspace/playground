tasks.getByName("jar") {
    enabled = true
}

tasks.getByName("bootJar") {
    enabled = false
}

dependencies {
    compileOnly("org.springframework:spring-context")
    runtimeOnly("com.h2database:h2")
//    implementation("mysql:mysql-connector-java:8.0.33")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
}