package fr.herobrine.emote

import com.mojang.serialization.Codec
import com.mojang.serialization.DataResult
import com.mojang.serialization.codecs.RecordCodecBuilder
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.ComponentSerialization
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.resources.Identifier

/**
 * Emote utilisable par un joueur.
 *
 * @property id Identifiant de l'emote
 * @property displayName Nom affiché de l'emote
 * @property itemModel Modèle d'item servant d'icône à l'emote
 */
@JvmRecord
data class PlayerEmote(
    val id: String,
    val displayName: Component,
    val itemModel: Identifier,
) {

    companion object {
        @JvmField
        val CODEC = RecordCodecBuilder.create { i -> i.group(
            Codec.STRING.fieldOf("id").forGetter(PlayerEmote::id),
            ComponentSerialization.CODEC.fieldOf("display_name").forGetter(PlayerEmote::displayName),
            Identifier.CODEC.fieldOf("item_model").forGetter(PlayerEmote::itemModel)
        ).apply(i, ::PlayerEmote) }

        @JvmField
        val STREAM_CODEC = ByteBufCodecs.fromCodecWithRegistries(CODEC)

        /**
         * Créé un codec permettant d'obtenir un emote à partir de son [PlayerEmote.id].
         *
         * @param knownEmotes Liste des [PlayerEmote] connues.
         */
        @JvmStatic
        fun byNameCodec(knownEmotes: List<PlayerEmote>): Codec<PlayerEmote> {
            return Codec.STRING.comapFlatMap(
                { id ->
                    val foundEmote = knownEmotes.firstOrNull { emote -> emote.id == id }

                    return@comapFlatMap when(foundEmote) {
                        null -> DataResult.error { "emote '$id' not found." }
                        else -> DataResult.success(foundEmote)
                    }
                },
                (PlayerEmote::id)
            )
        }
    }
}
