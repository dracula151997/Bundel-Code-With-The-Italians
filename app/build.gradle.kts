import com.android.build.gradle.internal.lint.AndroidLintTask
import io.gitlab.arturbosch.detekt.Detekt

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("kotlin-kapt")
    id("io.gitlab.arturbosch.detekt")
    id("org.jmailen.kotlinter")

}

android {
    namespace = "com.dracula.bundelcodewiththeitalians"
    compileSdk = 33

    defaultConfig {
        applicationId = "com.dracula.bundelcodewiththeitalians"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        named("release") {
            isMinifyEnabled = false
            setProguardFiles(listOf(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"))
        }
    }
    java {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "1.8"

    }
    buildFeatures {
        compose =  true
        aidl =  true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.3.2"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    lint{
        lintConfig = file("build-config/lint.xml")
        sarifReport = true
    }
}

detekt {
    source.setFrom("src/main/java", "src/main/kotlin")
    config.setFrom(rootProject.file("build-config/detekt.yml"))
    buildUponDefaultConfig = true
}

tasks{
    withType<Detekt>{
        this.jvmTarget = "1.8"
    }

    @Suppress("UNUSED_VARIABLE")
    val collectSarifReports by registering(Sync::class) {
        val detektRelease by getting(Detekt::class)
        val androidLintRelease = named<AndroidLintTask>("lintRelease")


        dependsOn(detekt, detektRelease, androidLintRelease)
        from(detektRelease.sarifReportFile) {
            rename { "detekt-release.sarif" }
        }
        from(detekt.get().sarifReportFile) {
            rename { "detekt.sarif" }
        }
        from(androidLintRelease.get().sarifReportOutputFile) {
            rename { "android-lint.sarif" }
        }

        into("$buildDir/reports/sarif")
    }

    register("staticAnalysis") {
        val detektRelease by getting(Detekt::class)
        val androidLintRelease = named<com.android.build.gradle.internal.lint.AndroidLintTask>("lintRelease")

        dependsOn(detekt, detektRelease, androidLintRelease, lintKotlin)
        finalizedBy(collectSarifReports)
    }
}

dependencies {
    val navVersion = "2.6.0"
    implementation("androidx.navigation:navigation-compose:$navVersion")
    implementation("com.google.dagger:hilt-android:2.44")
    implementation("androidx.activity:activity-compose:1.7.2")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.hilt:hilt-navigation-compose:1.0.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")
    implementation("com.jakewharton.timber:timber:5.0.1")
    implementation(platform("androidx.compose:compose-bom:2023.06.01"))
    implementation(platform("org.jetbrains.kotlin:kotlin-bom:1.8.0"))
    implementation("androidx.compose.material:material-icons-extended:1.4.3")
    kapt("com.google.dagger:hilt-compiler:2.44")
    detektPlugins("io.gitlab.arturbosch.detekt:detekt-formatting:1.23.0")

}
kapt {
    correctErrorTypes = true
}
