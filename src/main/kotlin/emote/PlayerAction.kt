package fr.herobrine.emote

import fr.herobrine.util.asLiteral
import net.minecraft.network.chat.Component
import net.minecraft.network.codec.ByteBufCodecs
import net.minecraft.network.codec.StreamCodec
import net.minecraft.util.ByIdMap
import net.minecraft.util.StringRepresentable

/**
 * Action pouvant être lancée par le joueur.
 *
 * @property displayName Nom affiché dans l'interface
 */
enum class PlayerAction(
    val dataName: String,
    val displayName: Component,
): StringRepresentable {
    /**
     * Action lancée lorsque le joueur essaie de s'assoir.
     */
    SIT(
        dataName = "sit",
        displayName = "S'assoir".asLiteral()
    ),

    /**
     * Action lancée lorsque le joueur essaie de se coucher.
     */
    LAY(
        dataName = "lay",
        displayName = "Se coucher".asLiteral()
    );

    override fun getSerializedName() = dataName

    companion object {
        @JvmField
        val CODEC = StringRepresentable.fromEnum { entries.toTypedArray() }

        @JvmField
        val STREAM_CODEC = ByteBufCodecs.fromCodec(CODEC)
    }
}