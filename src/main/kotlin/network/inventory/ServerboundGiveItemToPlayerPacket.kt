package fr.herobrine.network.inventory

import fr.herobrine.network.AbstractPacket
import fr.herobrine.network.PacketInfo
import fr.herobrine.util.identifier
import net.minecraft.core.UUIDUtil
import net.minecraft.network.codec.StreamCodec
import java.util.UUID

/**
 * Packet envoyé au serveur, indiquant à celui-ci que le client souhaite donner l'item qu'il a en main à un autre joueur.
 *
 * @property uuid [UUID] du joueur visé
 */
data class ServerboundGiveItemToPlayerPacket(
    val uuid: UUID,
): AbstractPacket() {
    override fun packetInfo(): PacketInfo<ServerboundGiveItemToPlayerPacket> = PACKET_INFO

    companion object {
        @JvmField
        val PACKET_INFO = PacketInfo(
            identifier = identifier("herobrine:give_item_to_player"),
            streamCodec = StreamCodec.composite(
                UUIDUtil.STREAM_CODEC, ServerboundGiveItemToPlayerPacket::uuid,
                ::ServerboundGiveItemToPlayerPacket
            )
        )
    }
}
