plugins {
    id("hexdebug.conventions.architectury")
    id("hexdebug.conventions.dokka")
}

architectury {
    common("fabric", "forge")
}

dependencies {
    implementation(libs.kotlin.stdlib)
    implementation(kotlin("reflect"))

    // We depend on fabric loader here to use the fabric EnvType class and get the mixin dependencies
    // Do NOT use other classes from fabric loader
    modImplementation(libs.fabric.loader)
    modApi(libs.architectury)

    modApi(libs.hexcasting.common)

    modApi(libs.clothConfig.common)

    implementation(libs.mixinExtras)

    implementation(libs.bundles.lsp4j)

    implementation(libs.bundles.ktor)

    api(project(":hexdebug-core-common", "namedElements"))
}
