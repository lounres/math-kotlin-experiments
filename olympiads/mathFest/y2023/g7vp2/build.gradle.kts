kotlin {
    sourceSets {
        main {
            dependencies {
                implementation(kone.numberTheory)
                implementation(kone.enumerativeCombinatorics)
                implementation(kone.util.collectionOperations)
            }
        }
    }
}