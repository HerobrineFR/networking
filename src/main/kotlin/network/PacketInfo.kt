package fr.herobrine.network

import net.minecraft.network.RegistryFriendlyByteBuf
import net.minecraft.network.codec.StreamCodec
import net.minecraft.resources.Identifier

/**
 * Classe contenant l'**identifiant** ainsi que le **codec** d'un packet.
 *
 * @property identifier Identifiant du packet
 * @property streamCodec Codec du packet
 *
 * @param T Type du packet
 */
@JvmRecord
data class PacketInfo<T: AbstractPacket>(
    val identifier: Identifier,
    val streamCodec: StreamCodec<RegistryFriendlyByteBuf, T>
)
