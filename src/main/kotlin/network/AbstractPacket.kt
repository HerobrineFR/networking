package fr.herobrine.network

import net.minecraft.network.protocol.common.custom.CustomPacketPayload

/**
 * Type de packet custom.
 *
 * Les packets ont une logique interne très légère, puisque ceux-ci sont transmis
 * par des implémentations de couches réseau.
 */
abstract class AbstractPacket: CustomPacketPayload {
    protected val TYPE: CustomPacketPayload.Type<out CustomPacketPayload> = CustomPacketPayload.Type(packetInfo().identifier)

    override fun type(): CustomPacketPayload.Type<out CustomPacketPayload> {
        return TYPE
    }

    /**
     * Renvoie les informations du packet.
     */
    protected abstract fun packetInfo(): PacketInfo<*>
}