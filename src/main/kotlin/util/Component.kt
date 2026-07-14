package fr.herobrine.util

import net.minecraft.data.AtlasIds
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.contents.objects.AtlasSprite
import net.minecraft.resources.Identifier

fun literalComponent(literal: String) = Component.literal(literal)
fun translatableComponent(translatable: String) = Component.translatable(translatable)
fun itemSprite(sprite: Identifier) = AtlasSprite(AtlasIds.ITEMS, sprite)
fun emptyComponent() = Component.empty()

fun String.asLiteral() = literalComponent(this)

fun Component.append(vararg components: Component): Component {
    var newComponent = this.copy()

    components.forEach { sibling ->
        newComponent.siblings.add(sibling)
    }

    return  newComponent
}