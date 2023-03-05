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

val jvmTargetVersion: String by properties

featuresManagement {
    features {
        on("kotlin jvm") {
            apply(plugin = "org.jetbrains.kotlin.jvm")
            configure<KotlinJvmProjectExtension> {
                target.compilations.all {
                    kotlinOptions {
                        jvmTarget = jvmTargetVersion
                    }
                    compileTaskProvider.apply {
                        // TODO: Check if really is necessary
                        kotlinOptions {
                            jvmTarget = jvmTargetVersion
                        }
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
        on("kotlin common settings") {
            configure<org.jetbrains.kotlin.gradle.dsl.KotlinProjectExtension> {
                sourceSets {
                    all {
                        languageSettings {
                            progressiveMode = true
                            optIn("kotlin.contracts.ExperimentalContracts")
                        }
                    }
                }
            }
            pluginManager.withPlugin("org.gradle.java") {
                configure<JavaPluginExtension> {
                    targetCompatibility = JavaVersion.toVersion(jvmTargetVersion)
                }
                tasks.withType<Test> {
                    useJUnitPlatform()
                }
            }
        }
    }
}