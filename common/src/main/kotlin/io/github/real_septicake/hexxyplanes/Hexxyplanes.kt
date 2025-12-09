package io.github.real_septicake.hexxyplanes

import at.petrak.hexcasting.api.casting.eval.CastingEnvironment
import net.minecraft.resources.ResourceLocation
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger
import io.github.real_septicake.hexxyplanes.config.HexxyplanesServerConfig
import io.github.real_septicake.hexxyplanes.networking.HexxyplanesNetworking
import io.github.real_septicake.hexxyplanes.registry.HexxyplanesActions
import io.github.real_septicake.hexxyplanes.registry.HexxyplanesBlocks
import io.github.real_septicake.hexxyplanes.registry.HexxyplanesIotas
import io.github.real_septicake.hexxyplanes.registry.HexxyplanesItems
import net.minecraft.world.level.ChunkPos
import java.util.UUID

object Hexxyplanes {
    const val MODID = "hexxyplanes"

    @JvmField
    val LOGGER: Logger = LogManager.getLogger(MODID)

    @JvmStatic
    fun id(path: String) = ResourceLocation(MODID, path)

    @JvmStatic
    fun chunkFromUUID(uuid: UUID): ChunkPos {
        return ChunkPos((uuid.leastSignificantBits % 257).toInt(), (uuid.mostSignificantBits % 257).toInt())
    }

    fun init() {
        CastingEnvironment.addCreateEventListener { t, _ ->
            t.addExtension(HexplaneComponent(t))
        }
        HexxyplanesServerConfig.init()
        initRegistries(
            HexxyplanesActions,
            HexxyplanesBlocks,
            HexxyplanesItems,
            HexxyplanesIotas
        )
        HexxyplanesNetworking.init()
    }

    fun initServer() {
        HexxyplanesServerConfig.initServer()
    }
}
