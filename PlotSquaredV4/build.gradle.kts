/*
 * This file was generated by the Gradle 'init' task.
 */

plugins {
    id("de.Ste3et_C0st.furniture.java-conventions")
}

repositories {
    mavenCentral()
    maven {
        name = "IntellectualSites Public"
        url = uri("https://mvn.intellectualsites.com/content/groups/public/")
    }
    maven {
        name = "enginehub"
        url = uri("https://maven.enginehub.org/repo/")
    }
    maven {
        name = "OSS Sonatype"
        url = uri("https://oss.sonatype.org/content/repositories/snapshots/")
    }
}

dependencies {
    implementation(project(":Core"))
    compileOnly("com.github.intellectualsites.plotsquared:PlotSquared-API:4.514")
    compileOnly("com.github.intellectualsites.plotsquared:PlotSquared-BukkitAPI:4.514")
    compileOnly("com.sk89q:worldedit:6.0.0-SNAPSHOT")
    compileOnly("com.sk89q:worldguard:6.1.1-SNAPSHOT")
}

the<JavaPluginExtension>().toolchain {
    languageVersion.set(JavaLanguageVersion.of(8))
}
description = "PlotSquaredV4"
