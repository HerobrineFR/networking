package fr.herobrine.network.animation

import fr.herobrine.emote.PlayerEmote
import fr.herobrine.network.AbstractPacket
import fr.herobrine.network.PacketInfo
import fr.herobrine.util.identifier
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.network.codec.StreamCodec

/**
 * Packet envoyé au client, contenant la liste des emotes du serveur.
 *
 * @property emotes Liste d'emotes
 */
data class ClientboundListPlayerEmotesPacket(
    val emotes: List<PlayerEmote>
): AbstractPacket() {
    override fun packetInfo() = PACKET_INFO

    companion object {
        @JvmField
        val PACKET_INFO = PacketInfo(
            identifier("herobrine:player_emotes/send_list"),
            streamCodec = StreamCodec.composite(
                PlayerEmote.STREAM_CODEC.apply(ByteBufCodecs.list()), ClientboundListPlayerEmotesPacket::emotes,
                ::ClientboundListPlayerEmotesPacket
            )
        )
    }
}