package fr.herobrine.network.client.meta

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder

/**
 * Données liées aux mods d'Herobrine.fr.
 *
 * @property usesHerobrineTweaks Si le joueur utilises `HerobrineTweaks`
 */
data class ModMetadata(
    var usesHerobrineTweaks: Boolean = false
) {
    companion object {
        @JvmField
        val CODEC = RecordCodecBuilder.create { i -> i.group(
            Codec.BOOL.fieldOf("uses_herobrine_tweaks").forGetter(ModMetadata::usesHerobrineTweaks)
        ).apply(i, ::ModMetadata) }
    }
}