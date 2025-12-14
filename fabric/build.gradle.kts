plugins {
    id("hexxyplanes.platform")
}

architectury {
    fabric()
}

fabricApi {
    configureDataGeneration {
        outputDirectory = file("src/generated/resources")
        modId = providers.gradleProperty("modId")
        strictValidation = true
        addToResources = false
    }
}

loom {
    runs {
        named("datagen") {
            property("hexxyplanes.apply-datagen-mixin", "true")
        }
        loom.runs["client"].programArgs += listOf("--username", "Player", "--uuid", "bd346dd5-ac1c-427d-87e8-73bdd4bf3e13")
    }
}

repositories {
    maven {
        url = uri("https://pkgs.dev.azure.com/hexxy-media/artifacts/_packaging/community/maven/v1")
        name = "HexxyMaven"
    }
    maven {
        url = uri("https://repo.sleeping.town")
        name = "Unascribed"
    }
    maven {
        url = uri("https://dl.cloudsmith.io/public/geckolib3/geckolib/maven/")
    }
}

hexxyplanesModDependencies {
    // expand versions in fabric.mod.json
    filesMatching.add("fabric.mod.json")

    // transform Gradle version ranges into a Fabric-compatible format
    anyVersion = "*"
    mapVersions {
        replace(",", " ")
        replace(Regex("""\s+"""), " ")
        replace(Regex("""\[(\S+)"""), ">=$1")
        replace(Regex("""(\S+)\]"""), "<=$1")
        replace(Regex("""\](\S+)"""), ">$1")
        replace(Regex("""(\S+)\["""), "<$1")
    }

    // CurseForge/Modrinth mod dependency metadata
    requires("architectury-api")
    requires("cloth-config")
    requires(curseforge = "hexcasting", modrinth = "hex-casting")
    requires("fabric-api")
    requires("fabric-language-kotlin")
    optional("modmenu")
}

dependencies {
    modApi(libs.fabric.api)
    modImplementation(libs.fabric.loader)

    modImplementation(libs.kotlin.fabric)
    modApi(libs.cardinalComponents)

    modApi(libs.architectury.fabric) {
        // Fix for the "two fabric loaders" loading crash
        exclude(group = "net.fabricmc", module = "fabric-loader")
    }

    modApi(libs.hexcasting.fabric) {
        // If not excluded here, calls a nonexistent method and crashes the dev client
        exclude(module = "phosphor")
    }
    // Hex Casting dependencies
    // we use modLocalRuntime to add these to the development runtime, but not at compile time or for consumers of this project
    modLocalRuntime(libs.paucal.fabric)
    modLocalRuntime(libs.patchouli.fabric)
    modLocalRuntime(libs.serializationHooks)
    modLocalRuntime(libs.trinkets)
    modLocalRuntime(libs.inline.fabric) { isTransitive = false }

    libs.mixinExtras.fabric.also {
        localRuntime(it)
        include(it)
        annotationProcessor(it)
    }

    modCompileOnly(files("../libs/oneironaut-fabric-1.20.1-0.5.0-ad811dc.jar"))

    modLocalRuntime(files("../libs/oneironaut-fabric-1.20.1-0.5.0-ad811dc.jar"))
    modLocalRuntime(libs.hexal.fabric)

    modApi(libs.clothConfig.fabric) {
        exclude(group = "net.fabricmc.fabric-api")
    }
    modImplementation(libs.modMenu)
}
