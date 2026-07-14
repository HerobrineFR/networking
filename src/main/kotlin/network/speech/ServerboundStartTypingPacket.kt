package fr.herobrine.network.speech

import fr.herobrine.network.AbstractPacket
import fr.herobrine.network.PacketInfo
import fr.herobrine.util.identifier
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.network.codec.StreamCodec

/**
 * Packet envoyé par le client au serveur lorsque celui-ci est en train d'écrire.
 *
 * @property text Texte en cours d'écriture.
 */
data class ServerboundStartTypingPacket(
    val text: String
): AbstractPacket() {

    override fun packetInfo() = PACKET_INFO

    companion object {
        @JvmField
        val PACKET_INFO = PacketInfo(
            identifier = identifier("herobrine:speech/start_typing"),
            streamCodec = StreamCodec.composite(
                ByteBufCodecs.STRING_UTF8, ServerboundStartTypingPacket::text,
                ::ServerboundStartTypingPacket
            )
        )
    }
}