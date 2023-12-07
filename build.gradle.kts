import com.vanniktech.maven.publish.MavenPublishBaseExtension
import com.vanniktech.maven.publish.SonatypeHost

plugins {
    kotlin("multiplatform") apply false
    kotlin("android") apply false
    id("com.android.application") apply false
    id("com.android.library") apply false
    id("org.jetbrains.compose") apply false
    id("com.vanniktech.maven.publish.base") apply false
}

allprojects {
    repositories {
        mavenLocal()
        google()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://www.jitpack.io")
    }

    // Credentials must be added to ~/.gradle/gradle.properties per
    // https://vanniktech.github.io/gradle-maven-publish-plugin/central/#secrets
    plugins.withId("com.vanniktech.maven.publish.base") {
        configure<PublishingExtension> {
            repositories {
                maven {
                    name = "testMaven"
                    url = file("${rootProject.buildDir}/testMaven").toURI()
                }
            }
        }
        @Suppress("UnstableApiUsage")
        configure<MavenPublishBaseExtension> {
            publishToMavenCentral(SonatypeHost.S01)
            signAllPublications()
            pom {
                name.set("ONVIF Camera Kotlin")
                description.set("A Kotlin library to interact with ONVIF cameras.")
                url.set("https://github.com/sproctor/ONVIF-Camera-Kotlin/")
                licenses {
                    license {
                        name.set("MIT")
                        url.set("https://github.com/sproctor/ONVIFCameraAndroid/blob/master/LICENSE")
                    }
                }
                developers {
                    developer {
                        id.set("sproctor")
                        name.set("Sean Proctor")
                        email.set("sproctor@gmail.com")
                    }
                }
                scm {
                    url.set("https://github.com/sproctor/ONVIF-Camera-Kotlin/tree/master")
                }
            }
        }
    }
}

tasks.wrapper {
    gradleVersion = "8.5"
}
