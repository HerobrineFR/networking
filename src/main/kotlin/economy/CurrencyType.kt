package fr.herobrine.economy

import fr.herobrine.util.identifier
import fr.herobrine.util.itemSprite
import fr.herobrine.util.literalComponent
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.contents.objects.AtlasSprite
import net.minecraft.util.StringRepresentable

enum class CurrencyType(
    val dataName: String,
    val displayedName: Component,
    val sprite: AtlasSprite,
): StringRepresentable {
    EMERALD(
        dataName = "emerald",
        displayedName = literalComponent("Émeraude"),
        sprite = itemSprite(identifier("minecraft:item/emerald"))
    ),

    ATHARINE(
        dataName = "atharine",
        displayedName = literalComponent("Atharine"),
        sprite = itemSprite(identifier("herobrine:item/atharine"))
    ),

    COMPASS(
        dataName = "redstone_compass",
        displayedName = literalComponent("Boussole"),
        sprite = itemSprite(identifier("minecraft:item/vanilla/compass/compass_03")),
    ),

    ENERGY_CRYSTAL(
        dataName = "energy_crystal",
        displayedName = literalComponent("Cristal d'énergie"),
        sprite = itemSprite(identifier("minecraft:item/experience_bottle"))
    );

    fun sprite() = Component.`object`(sprite)
    override fun getSerializedName() = dataName

    companion object {
        @JvmField
        val CODEC = StringRepresentable.fromEnum { entries.toTypedArray() }
    }
}