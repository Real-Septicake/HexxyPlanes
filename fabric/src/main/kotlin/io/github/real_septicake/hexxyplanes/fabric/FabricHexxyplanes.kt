package io.github.real_septicake.hexxyplanes.fabric

import dev.architectury.platform.Platform
import io.github.real_septicake.hexxyplanes.Hexxyplanes
import io.github.real_septicake.hexxyplanes.HexxyplanesCommands
import io.github.real_septicake.hexxyplanes.HexxyplanesDimension
import io.github.real_septicake.hexxyplanes.fabric.interop.oneironaut.OneironautCompat
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents

object FabricHexxyplanes : ModInitializer {
    init {
        if(Platform.isModLoaded("oneironaut"))
            OneironautCompat.load()
    }

    override fun onInitialize() {
        Hexxyplanes.init()
        CommandRegistrationCallback.EVENT.register { dp, _, _ -> HexxyplanesCommands.register(dp) }
        ServerPlayConnectionEvents.JOIN.register { handler, _, server ->
            if(HexxyplanesDimension.WORLD_KEY == handler.player.level().dimension()) {
                HexxyplanesDimension.sendToPlane(server.getLevel(HexxyplanesDimension.WORLD_KEY)!!, handler.player, handler.player.uuid)
            }
        }
    }
}
