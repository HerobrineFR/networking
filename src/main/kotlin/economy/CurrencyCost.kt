package fr.herobrine.economy

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import fr.herobrine.util.append
import fr.herobrine.util.asLiteral
import fr.herobrine.util.emptyComponent
import net.minecraft.network.chat.Component
import net.minecraft.network.codec.ByteBufCodecs

/**
 * Représente un coût.
 *
 * @param value Valeur du coût.
 * @param costType Monnaie associée au coût.
 */
data class CurrencyCost(
    var value: Int,
    var costType: CurrencyType
) {
    /**
     * Affiche l'étiquette du coup.
     */
    fun render(): Component {
        return emptyComponent().append(
            costType.sprite(),
            " ".asLiteral(),
            "$value".asLiteral()
        )
    }

    companion object {
        @JvmField
        val CODEC = RecordCodecBuilder.create { i -> i.group(
                Codec.INT.fieldOf("value").forGetter(CurrencyCost::value),
                CurrencyType.CODEC.fieldOf("cost_type").forGetter(CurrencyCost::costType)
            ).apply(i, ::CurrencyCost) }

        @JvmField
        val STREAM_CODEC = ByteBufCodecs.fromCodec(CODEC)
    }
}
