rootProject.name = "math-kotlin-experiments"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

val projectProperties = java.util.Properties()
file("gradle.properties").inputStream().use {
    projectProperties.load(it)
}

val koneVersion : String by projectProperties

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        mavenLocal()
        maven("https://repo.kotlin.link")
    }

    versionCatalogs {
        create("kone").from("com.lounres:kone.versionCatalog:$koneVersion")
    }
}

pluginManagement {
    repositories {
        gradlePluginPortal()
        mavenLocal()
    }
}

plugins {
    id("com.lounres.gradle.stal") version "1.0.0"
}

stal {
    structure {
        "polynomialParametrisationIsAlgebraic"("kotlin jvm")
        "voronoiDiagram"("kotlin jvm")
        "planimetricsComputationExample"("kotlin jvm")
        "olympiads" {
            "mathFest" {
                "y2023" {
                    "g7vp2"("kotlin jvm")
                }
            }
        }
    }

    tag {
        "kotlin common settings" since { hasAnyOf("kotlin jvm", "kotlin multiplatform") }
    }
}