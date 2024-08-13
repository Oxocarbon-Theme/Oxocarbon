import org.jetbrains.changelog.Changelog
import org.jetbrains.changelog.date
import org.jetbrains.intellij.platform.gradle.tasks.VerifyPluginTask

fun properties(key: String) = providers.gradleProperty(key)
fun projectProperties(key: String) = project.findProperty(key).toString()

plugins {
    // Java Support
    id("java")
    // Kotlin support
    id("org.jetbrains.kotlin.jvm") version "2.0.20-RC2"
    // Gradle IntelliJ Plugin
    id("org.jetbrains.intellij.platform") version "2.0.1"
    // Gradle Changelog Plugin
    id("org.jetbrains.changelog") version "2.0.0"
}

group = projectProperties("pluginGroup")
version = projectProperties("pluginVersion")

repositories {
    mavenCentral()

    intellijPlatform {
        defaultRepositories()
        intellijDependencies()
    }
}

kotlin {
    jvmToolchain {
        this.languageVersion.set(JavaLanguageVersion.of(projectProperties("javaVersion")))
    }
}

// Configure Gradle IntelliJ Plugin
// Read more: https://plugins.jetbrains.com/docs/intellij/tools-gradle-intellij-plugin.html
intellijPlatform {
    pluginConfiguration {
        id = projectProperties("pluginId")
        name = projectProperties("pluginName")
        version = projectProperties("pluginVersion")
        changeNotes.set(provider {
            changelog.renderItem(changelog.getLatest(), Changelog.OutputType.HTML)
        })
        description = projectProperties("pluginDescription")

        ideaVersion {
            sinceBuild.set(projectProperties("pluginSinceBuild"))
            untilBuild.set(projectProperties("pluginUntilBuild"))
        }

        vendor {
            name.set(projectProperties("vendorName"))
            email.set(projectProperties("vendorEmail"))
            url.set(projectProperties("pluginUrl"))
        }
    }

    pluginVerification {
        failureLevel = listOf(VerifyPluginTask.FailureLevel.COMPATIBILITY_PROBLEMS)

        ides {
            recommended()
        }
    }

    publishing {
        token.set(System.getenv("PUBLISH_TOKEN"))
    }

    signing {
        certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
        privateKey.set(System.getenv("PRIVATE_KEY"))
        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
    }


}

dependencies {
    intellijPlatform {
        instrumentationTools()
        intellijIdeaCommunity("2024.2")
        pluginVerifier()

        bundledPlugins(properties("platformBundledPlugins").map { it.split(',') })
    }
}

// Configure Gradle Changelog Plugin - read more: https://github.com/JetBrains/gradle-changelog-plugin
changelog {
    version.set(projectProperties("pluginVersion"))
    path.set(file(".github/CHANGELOG.md").canonicalPath)
    header.set(provider { "v${version.get()} - ${date()}" })
    headerParserRegex.set("""(v\d\.\d\.\d)""".toRegex())
    itemPrefix.set("-")
    keepUnreleasedSection.set(true)
    unreleasedTerm.set("[Unreleased]")
    groups.set(listOf("🚀 Features", "📝 Changes", "🪦 Removed", "🐛 Fixed"))
}

tasks {
    // Set the JVM compatibility versions
    projectProperties("javaVersion").let {
        withType<JavaCompile> {
            sourceCompatibility = it
            targetCompatibility = it
        }

    }
}
