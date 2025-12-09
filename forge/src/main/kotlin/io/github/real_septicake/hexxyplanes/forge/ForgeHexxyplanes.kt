package io.github.real_septicake.hexxyplanes.forge

import dev.architectury.platform.forge.EventBuses
import io.github.real_septicake.hexxyplanes.Hexxyplanes
import io.github.real_septicake.hexxyplanes.HexxyplanesCommands
import io.github.real_septicake.hexxyplanes.HexxyplanesDimension
import io.github.real_septicake.hexxyplanes.forge.capabilities.HexplaneExitAttacher
import io.github.real_septicake.hexxyplanes.forge.capabilities.HexplaneExitCapability
import net.minecraftforge.event.entity.player.PlayerEvent
import io.github.real_septicake.hexxyplanes.forge.capabilities.IHexplaneExit
import io.github.real_septicake.hexxyplanes.forge.datagen.ForgeHexxyplanesDatagen
import net.minecraft.server.level.ServerPlayer
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.player.Player
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.common.capabilities.Capability
import net.minecraftforge.common.capabilities.CapabilityManager
import net.minecraftforge.common.capabilities.CapabilityToken
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent
import net.minecraftforge.event.AttachCapabilitiesEvent
import net.minecraftforge.event.RegisterCommandsEvent
import net.minecraftforge.event.entity.player.PlayerEvent.PlayerLoggedInEvent
import net.minecraftforge.fml.common.Mod
import thedarkcolour.kotlinforforge.forge.MOD_BUS

@Mod(Hexxyplanes.MODID)
class ForgeHexxyplanes {
    companion object {
        val HEXPLANE_EXIT_CAPABILITY: Capability<IHexplaneExit> = CapabilityManager.get(object : CapabilityToken<IHexplaneExit>() {})
    }

    init {
        MOD_BUS.apply {
            EventBuses.registerModEventBus(Hexxyplanes.MODID, this)
            addListener(ForgeHexxyplanesClient::init)
            addListener(ForgeHexxyplanesDatagen::init)
            addListener(ForgeHexxyplanesServer::init)
            addListener { evt: RegisterCapabilitiesEvent ->
                evt.register(IHexplaneExit::class.java)
            }
        }
        MinecraftForge.EVENT_BUS.apply {
            addListener { evt: RegisterCommandsEvent -> HexxyplanesCommands.register(evt.dispatcher) }
            addListener { evt: PlayerLoggedInEvent ->
                if(HexxyplanesDimension.WORLD_KEY == evt.entity.level().dimension()) {
                    HexxyplanesDimension.sendToPlane(evt.entity.server!!.getLevel(HexxyplanesDimension.WORLD_KEY)!!, evt.entity!! as ServerPlayer, evt.entity.uuid)
                }
            }
            addGenericListener(Entity::class.java) { evt: AttachCapabilitiesEvent<Entity> ->
                if(evt.`object` is Player)
                    evt.addCapability(
                        HexplaneExitAttacher.IDENTIFIER,
                        HexplaneExitAttacher.HexplaneExitProvider()
                    )
            }
            addListener { evt: PlayerEvent.Clone ->
                if(evt.isWasDeath) {
                    evt.original.reviveCaps()
                    evt.entity.getCapability(HEXPLANE_EXIT_CAPABILITY)
                        .resolve().orElse(HexplaneExitCapability())
                        .exit = evt.original.getCapability(HEXPLANE_EXIT_CAPABILITY)
                        .resolve().orElse(HexplaneExitCapability()).exit
                }
            }
        }
        Hexxyplanes.init()
    }
}
