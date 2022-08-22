kotlin {
    sourceSets {
        main {
            dependencies {
                implementation(libs.bundles.kmath.polynomial)
                implementation(libs.bundles.kone.polynomial)
            }
        }
    }
}