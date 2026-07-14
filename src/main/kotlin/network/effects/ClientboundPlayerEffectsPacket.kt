package fr.herobrine.network.effects

import fr.herobrine.network.AbstractPacket
import fr.herobrine.network.PacketInfo
import fr.herobrine.util.identifier
import net.minecraft.core.UUIDUtil
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.network.codec.StreamCodec
import net.minecraft.world.effect.MobEffectInstance
import java.util.UUID

/**
 * Packet envoyé au client, contenant le UUID d'un joueur ainsi que la liste de ses effets de potion.
 *
 * @property player [UUID] du joueur dont les effets sont envoyés.
 * @property effects Liste des **effets** du joueur.
 */
data class ClientboundPlayerEffectsPacket(
    val player: UUID,
    val effects: List<MobEffectInstance>
): AbstractPacket() {
    override fun packetInfo() = PACKET_INFO

    companion object {
        @JvmField
        val PACKET_INFO = PacketInfo(
            identifier = identifier("herobrine:player_effects"),
            streamCodec = StreamCodec.composite(
                UUIDUtil.STREAM_CODEC, ClientboundPlayerEffectsPacket::player,
                MobEffectInstance.STREAM_CODEC.apply(ByteBufCodecs.list()), ClientboundPlayerEffectsPacket::effects,
                ::ClientboundPlayerEffectsPacket
            )
        )
    }
}
