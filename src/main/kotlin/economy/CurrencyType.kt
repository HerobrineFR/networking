package fr.herobrine.economy

import fr.herobrine.util.identifier
import fr.herobrine.util.itemSprite
import fr.herobrine.util.literalComponent
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.contents.objects.AtlasSprite
import net.minecraft.util.StringRepresentable

/**
 * Monnaie, en laquelle il est possible d'échanger.
 *
 * @property displayedName Nom d'affichage de la monnaie
 * @property sprite Texture de la monnaie
 */
enum class CurrencyType(
    val dataName: String,
    val displayedName: Component,
    val sprite: AtlasSprite,
): StringRepresentable {
    /**
     * Émeraudes, utilisées lors des échanges avec les Nezloyans.
     */
    EMERALD(
        dataName = "emerald",
        displayedName = literalComponent("Émeraude"),
        sprite = itemSprite(identifier("minecraft:item/emerald"))
    ),

    /**
     * Monnaie utilisée dans le cadre du jeu avec Massilia et, plus globalement, du RP Artisanat.
     */
    ATHARINE(
        dataName = "atharine",
        displayedName = literalComponent("Atharine"),
        sprite = itemSprite(identifier("herobrine:item/atharine"))
    ),

    /**
     * Boussoles de radiante.
     */
    COMPASS(
        dataName = "redstone_compass",
        displayedName = literalComponent("Boussole"),
        sprite = itemSprite(identifier("minecraft:item/vanilla/compass/compass_03")),
    ),

    /**
     * Cristaux d'énergie. Monnaie difficilement accessible
     */
    ENERGY_CRYSTAL(
        dataName = "energy_crystal",
        displayedName = literalComponent("Cristal d'énergie"),
        sprite = itemSprite(identifier("minecraft:item/experience_bottle"))
    );

    /**
     * Effectue le rendu du [sprite][CurrencyType.sprite] sous la forme de [Component].
     */
    fun sprite() = Component.`object`(sprite)
    override fun getSerializedName() = dataName

    companion object {
        @JvmField
        val CODEC = StringRepresentable.fromEnum { entries.toTypedArray() }
    }
}