import org.apache.tools.ant.util.JavaEnvUtils.VERSION_11
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.sqlDelight)
    alias(libs.plugins.kotlinxSerialization)
}

sqldelight {
    databases {
        create("PizzaDatabase") {
            packageName.set("td.numres.pizzaapp.data")
        }
    }
}

tasks.withType<Copy>().configureEach {
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
}

configurations.all {
    exclude(group = "com.intellij", module = "annotations")
}

repositories {
    google()
    mavenCentral()
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
    jvm("desktop")

    
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        moduleName = "composeApp"
        browser {
            val rootDirPath = project.rootDir.path
            val projectDirPath = project.projectDir.path
            commonWebpackConfig {
                outputFileName = "composeApp.js"
                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                    static = (static ?: mutableListOf()).apply {
                        // Serve sources to debug inside browser
                        add(rootDirPath)
                        add(projectDirPath)
                    }
                }
            }
        }
        binaries.executable()
    }
    
    sourceSets {
        val commonMain by getting {
            resources.srcDirs("src/commonMain/resources")
            dependencies {
                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material)
                implementation(compose.ui)
                implementation(libs.kotlinx.coroutines.core)
                implementation(libs.sqlite.driver)
                implementation(compose.components.resources)
                implementation(libs.kotlinx.serialization.json)
                implementation(compose.material3)
                api(libs.koin.core)
                implementation(libs.koin.compose)
                implementation(libs.koin.composeVM)
                implementation(libs.androidx.lifecycle.viewmodel)
            }
        }

        val androidMain by getting {
            resources.srcDirs("src/androidMain/res")
            dependencies {
                implementation(compose.preview)
                implementation(libs.androidx.activity.ktx)
                implementation(libs.androidx.activity.compose)

                implementation(libs.sqlite.driver)
                implementation(libs.compose.preview)
                implementation(libs.android.driver)
                implementation ("androidx.room:room-runtime:2.6.0")
                implementation ("androidx.room:room-compiler:2.6.0")
                implementation(libs.koin.android)
                implementation(libs.koin.androidx.compose)

            }
        }

        val desktopMain by getting {
            resources.srcDirs("src/wasmJsMain/resources")
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation(libs.kotlinx.coroutines.swing)
                implementation(libs.sqlite.driver)
                implementation(libs.compose.preview)
                implementation(libs.koin.core)
                implementation(libs.koin.compose)
                implementation(libs.kotlinx.serialization.json)
            }
        }

        val wasmJsMain by getting {
            resources.srcDirs("src/commonMain/resources")
            dependencies {
                // Exclure SQLDelight pour WASM
                configurations["wasmJsMainImplementation"].exclude(group = "app.cash.sqldelight")
                configurations["wasmJsMainImplementation"].exclude(group = "org.jetbrains.compose.ui", module = "ui-tooling-preview")
                implementation(libs.koin.core)
                implementation(libs.koin.compose)

            }
        }
    }
}

android {
    packagingOptions {
        resources {
            excludes += "META-INF/gradle/incremental.annotation.processors"
            excludes += "META-INF/LICENSE.md"
            excludes += "META-INF/LICENSE"
            excludes += "META-INF/LICENSE.txt"
            excludes += "META-INF/NOTICE"
            excludes += "META-INF/NOTICE.md"
            excludes += "META-INF/NOTICE.txt"
            excludes += "META-INF/DEPENDENCIES"
            excludes += "META-INF/services/javax.annotation.processing.Processor"
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    namespace = "td.numres.pizzaapp"
    compileSdk = libs.versions.android.targetSdk.get().toInt()

    defaultConfig {
        applicationId = "td.numres.pizzaapp"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation(libs.androidx.activity.ktx)
    implementation(libs.androidx.ui.tooling.preview.android)
    implementation(libs.androidx.lifecycle.viewmodel.android)
    implementation(libs.androidx.room.common)
    implementation(libs.androidx.databinding.compiler)
    implementation(libs.androidx.ui.android)
    debugImplementation(compose.uiTooling)
}

compose.desktop {
    application {
        mainClass = "td.numres.pizzaapp.MainKt"

        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "td.numres.pizzaapp"
            packageVersion = "1.0.0"
        }
    }
}
