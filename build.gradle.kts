import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension

@Suppress("DSL_SCOPE_VIOLATION")
plugins {
    with(libs.plugins) {
        alias(kotlin.jvm) apply false
    }
}

allprojects {
    repositories {
        mavenLocal()
        mavenCentral()
        maven("https://repo.kotlin.link")
//        maven("https://oss.sonatype.org/content/repositories/snapshots")
    }
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    configure<KotlinJvmProjectExtension> {
        target.compilations.all {
            kotlinOptions {
                freeCompilerArgs += listOf(
                    "-Xcontext-receivers",
//                    "-Xuse-k2",
                )
                jvmTarget = properties["jvmTarget"] as String
            }
        }

        sourceSets {
            all {
                languageSettings {
                    progressiveMode = true
                }
            }
            val test by getting {
                dependencies {
                    implementation(kotlin("test"))
                }
            }
        }
    }
    tasks.named<Test>("test") {
        useJUnitPlatform()
    }
}