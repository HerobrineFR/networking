package fr.herobrine.network

import net.minecraft.network.protocol.common.custom.CustomPacketPayload

abstract class AbstractPacket: CustomPacketPayload {
    override fun type(): CustomPacketPayload.Type<out CustomPacketPayload> {
        return CustomPacketPayload.Type(packetInfo().identifier)
    }

    abstract protected fun packetInfo(): PacketInfo<*>
}