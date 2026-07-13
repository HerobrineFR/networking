package fr.herobrine.network.mods

import fr.herobrine.network.AbstractPacket
import fr.herobrine.network.PacketInfo
import fr.herobrine.util.identifier
import net.minecraft.network.codec.StreamCodec

/**
 * Packet envoyé au serveur par Herobrine tweaks afin de lui annoncer
 * la présence du mod.
 */
class ServerboundHerobrineTweaksHandshakePacket: AbstractPacket() {
    override fun packetInfo(): PacketInfo<*> = PACKET_INFO

    companion object {
        @JvmField
        val PACKET_INFO = PacketInfo(
            identifier = identifier("herobrine:hbtweaks/handshake"),
            streamCodec = StreamCodec.unit(ServerboundHerobrineTweaksHandshakePacket())
        )
    }
}