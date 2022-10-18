rootProject.name = "math-kotlin-experiments"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        mavenLocal()
        maven("https://repo.kotlin.link")
    }

    versionCatalogs {
        create("kone").from("com.lounres.kone:versionCatalog:0.0.0-dev-1")
    }
}

include(
    "polynomialParametrisationIsAlgebraic",
    "voronoiDiagram",
    "planimetricsComputationExample",
)