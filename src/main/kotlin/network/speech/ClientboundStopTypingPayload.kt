package fr.herobrine.network.speech

import fr.herobrine.network.AbstractPacket
import fr.herobrine.network.PacketInfo
import fr.herobrine.util.identifier
import net.minecraft.core.UUIDUtil
import net.minecraft.network.codec.StreamCodec
import java.util.*

/**
 * Packet envoyé au client afin d'annoncer qu'un joueur a fermé sa session de chat.
 *
 * @property uuid [UUID] du joueur
 */
data class ClientboundStopTypingPayload(
    val player: UUID
): AbstractPacket() {

    override fun packetInfo() = PACKET_INFO

    companion object {
        @JvmField
        val PACKET_INFO = PacketInfo(
            identifier = identifier("herobrine:speech/player_stop_typing"),
            streamCodec = StreamCodec.composite(
                UUIDUtil.STREAM_CODEC, ClientboundStopTypingPayload::player,
                ::ClientboundStopTypingPayload
            )
        )
    }
}
