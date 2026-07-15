package fr.herobrine.network.client.packets

import fr.herobrine.network.AbstractPacket
import fr.herobrine.network.PacketInfo
import fr.herobrine.network.client.ClientInformation
import fr.herobrine.util.identifier
import net.minecraft.core.UUIDUtil
import net.minecraft.network.codec.StreamCodec
import java.util.*

/**
 * Packet permettant d'envoyer à un joueur les [ClientInformation] d'un joueur.
 *
 * @property uuid [UUID] du joueur auquel les informations client appartiennent
 * @property clientInformation Informations client
 */
data class ClientboundClientInformationPacket(
    val uuid: UUID,
    val clientInformation: ClientInformation
): AbstractPacket() {
    override fun packetInfo() = PACKET_INFO

    companion object {
        val PACKET_INFO = PacketInfo(
            identifier("herobrine:client_information"),
            streamCodec = StreamCodec.composite(
                UUIDUtil.STREAM_CODEC, ClientboundClientInformationPacket::uuid,
                ClientInformation.STREAM_CODEC, ClientboundClientInformationPacket::clientInformation,
                ::ClientboundClientInformationPacket
            )
        )
    }
}