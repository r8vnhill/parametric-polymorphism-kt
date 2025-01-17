plugins {
    id("bounds")
    id("jvm.conventions")
    alias(libs.plugins.detekt)
}

val projectGroup = extra["generic-programming.group"]!! // Throws an exception if the property is not found
val projectVersion: String = libs.versions.generic.programming.get()
val detektId: String = libs.plugins.detekt.get().pluginId
val detektFormattingModule = libs.detekt.formatting.get().module.toString()
val detektFormattingVersion = libs.detekt.formatting.get().version
val kotestBundle = libs.bundles.kotest

allprojects {
    group = projectGroup
    version = projectVersion
}

subprojects {
    apply(plugin = "jvm.conventions")
    apply(plugin = detektId)

    dependencies {
        detektPlugins("$detektFormattingModule:$detektFormattingVersion")
        implementation(kotestBundle)
    }
}
