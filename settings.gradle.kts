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

include(
    "polynomialParametrisationIsAlgebraic",
    "voronoiDiagram",
    "planimetricsComputationExample",
)