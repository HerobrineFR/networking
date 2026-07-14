package fr.herobrine.network.profile

import fr.herobrine.network.AbstractPacket
import fr.herobrine.network.PacketInfo
import fr.herobrine.util.identifier
import net.minecraft.network.codec.StreamCodec
import net.minecraft.world.entity.player.PlayerSkin

/**
 * Packet envoyé au serveur, permettant d'appliquer un **changement de skin**. (Non implémenté)
 *
 * @property skinPatch Nouvelles données du skin
 */
data class ServerboundUpdateSkinPacket(
    val skinPatch: PlayerSkin.Patch
): AbstractPacket() {
    override fun packetInfo(): PacketInfo<out AbstractPacket> {
        return PACKET_INFO
    }

    companion object {
        @JvmField
        val PACKET_INFO = PacketInfo(
            identifier("herobrine:update_skin"),
            streamCodec = StreamCodec.composite(
                PlayerSkin.Patch.STREAM_CODEC, ServerboundUpdateSkinPacket::skinPatch,
                ::ServerboundUpdateSkinPacket
            )
        )
    }
}