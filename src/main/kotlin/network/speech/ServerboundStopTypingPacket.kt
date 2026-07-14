package fr.herobrine.network.speech

import fr.herobrine.network.AbstractPacket
import fr.herobrine.network.PacketInfo
import fr.herobrine.util.identifier
import net.minecraft.network.codec.StreamCodec

/**
 * Packet envoyé au serveur lorsque le joueur interrompt la session de chat.
 */
object ServerboundStopTypingPacket: AbstractPacket() {
    override fun packetInfo(): PacketInfo<*> = PACKET_INFO

    @JvmField
    val PACKET_INFO = PacketInfo(
        identifier = identifier("herobrine:speech/stop_typing"),
        streamCodec = StreamCodec.unit(ServerboundStopTypingPacket)
    )
}