rootProject.name = "math-kotlin-experiments"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        maven("https://repo.kotlin.link")
    }
}

include(
    "polynomialParametrisationIsAlgebraic",
    "voronoiDiagram",
    "kosholka",
    "arc",
)