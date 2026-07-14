package fr.herobrine.network.animation

import fr.herobrine.emote.PlayerAction
import fr.herobrine.network.AbstractPacket
import fr.herobrine.network.PacketInfo
import fr.herobrine.util.identifier
import net.minecraft.network.codec.StreamCodec

/**
 * Packet envoyé par le **client afin** d'annoncer au **serveur** que celui-ci souhaite lancer une [PlayerAction].
 *
 * @property playerAction Action que le client souhaite lancer
 */
data class ServerboundPlayerActionPacket(
    val playerAction: PlayerAction
): AbstractPacket() {
    override fun packetInfo() = PACKET_INFO

    companion object {
        @JvmField
        val PACKET_INFO = PacketInfo(
            identifier("herobrine:player_emotes/action"),
            streamCodec = StreamCodec.composite(
                PlayerAction.STREAM_CODEC, ServerboundPlayerActionPacket::playerAction,
                ::ServerboundPlayerActionPacket
            )
        )
    }
}