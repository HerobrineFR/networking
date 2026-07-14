package fr.herobrine.network

import fr.herobrine.network.speech.ClientboundStopTypingPayload
import net.minecraft.network.protocol.common.custom.CustomPacketPayload

/**
 * Type de packet custom.
 *
 * Les packets ont une logique interne très légère, puisque ceux-ci sont transmis
 * par des implémentations de couches réseau.
 */
abstract class AbstractPacket: CustomPacketPayload {

    override fun type(): CustomPacketPayload.Type<out CustomPacketPayload> = packetInfo().type

    /**
     * Renvoie les informations du packet.
     */
    abstract fun packetInfo(): PacketInfo<out AbstractPacket>
}