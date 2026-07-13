package fr.herobrine.network.effects

import fr.herobrine.network.AbstractPacket
import fr.herobrine.network.PacketInfo
import fr.herobrine.util.identifier
import net.minecraft.core.UUIDUtil
import net.minecraft.network.codec.StreamCodec
import java.util.UUID

/**
 * Packet envoyé au serveur afin de demander les effets de potion d'un joueur.
 *
 * @property player [UUID] du joueur dont les effets sont demandés.
 */
data class ServerboundRequestEffectsPacket(
    val player: UUID
): AbstractPacket() {

    override fun packetInfo(): PacketInfo<*> = PACKET_INFO

    companion object {
        @JvmField
        val PACKET_INFO = PacketInfo(
            identifier = identifier("herobrine:request_player_effects"),
            streamCodec = StreamCodec.composite(
                UUIDUtil.STREAM_CODEC, ServerboundRequestEffectsPacket::player,
                ::ServerboundRequestEffectsPacket
            )
        )
    }
}