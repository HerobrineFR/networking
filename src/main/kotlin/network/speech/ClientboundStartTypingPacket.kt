package fr.herobrine.network.speech

import fr.herobrine.network.AbstractPacket
import fr.herobrine.network.PacketInfo
import fr.herobrine.util.identifier
import net.minecraft.core.UUIDUtil
import net.minecraft.network.codec.StreamCodec
import java.util.*

/**
 * Packet envoyé au client lorsqu'un joueur est en train d'écrire.
 *
 * @property uuid UUID du joueur concerné.
 */
data class ClientboundStartTypingPacket(
    val player: UUID
): AbstractPacket() {
    override fun packetInfo() = PACKET_INFO

    companion object {
        @JvmField
        val PACKET_INFO = PacketInfo(
            identifier = identifier("herobrine:speech/player_start_typing"),
            streamCodec = StreamCodec.composite(
                UUIDUtil.STREAM_CODEC, ClientboundStartTypingPacket::player,
                ::ClientboundStartTypingPacket
            )
        )
    }
}