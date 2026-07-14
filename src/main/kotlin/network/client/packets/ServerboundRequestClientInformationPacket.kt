package fr.herobrine.network.client.packets

import fr.herobrine.network.AbstractPacket
import fr.herobrine.network.PacketInfo
import fr.herobrine.util.identifier
import net.minecraft.core.UUIDUtil
import net.minecraft.network.codec.StreamCodec
import java.util.UUID

/**
 * Packet permettant au client de demander au serveur les
 * [fr.herobrine.network.client.ClientInformation] d'un joueur.
 *
 * Le serveur répond ensuite avec un [ClientboundClientInformationPacket].
 */
data class ServerboundRequestClientInformationPacket(
    val uuid: UUID
): AbstractPacket() {

    override fun packetInfo() = PACKET_INFO

    companion object {
        @JvmField
        val PACKET_INFO = PacketInfo(
            identifier("herobrine:request_client_information"),
            streamCodec = StreamCodec.composite(
                UUIDUtil.STREAM_CODEC, ServerboundRequestClientInformationPacket::uuid,
                ::ServerboundRequestClientInformationPacket
            )
        )
    }
}
