repositories.mavenCentral()

plugins {
    id("org.jetbrains.kotlin.jvm")
}

tasks.getByName<JavaCompile>("compileJava").also {
    it.targetCompatibility = Version.jvmTarget
}

val compileKotlin: org.jetbrains.kotlin.gradle.tasks.KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = Version.jvmTarget
    freeCompilerArgs = freeCompilerArgs + setOf("-module-name", Maven.groupId + ":" + Maven.artifactId)
}

"Snapshot".also { variant ->
    val versionName = Version.name + "-" + variant.toUpperCase()
    task<Jar>("assemble${variant}Jar") {
        dependsOn(compileKotlin)
        archiveBaseName.set(Maven.artifactId)
        archiveVersion.set(versionName)
        from(compileKotlin.destinationDirectory.asFileTree)
    }
    task<Jar>("assemble${variant}Source") {
        archiveBaseName.set(Maven.artifactId)
        archiveVersion.set(versionName)
        archiveClassifier.set("sources")
        from("src/main")
    }
    task("assemble${variant}Pom") {
        doLast {
            val parent = File(buildDir, "libs")
            if (!parent.exists()) parent.mkdirs()
            val file = File(parent, "${Maven.artifactId}-${versionName}.pom")
            if (file.exists()) file.delete()
            val text = MavenUtil.pom(
                modelVersion = "4.0.0",
                groupId = Maven.groupId,
                artifactId = Maven.artifactId,
                version = versionName,
                packaging = "jar"
            )
            file.writeText(text)
        }
    }
}
