package fr.herobrine.util

import net.minecraft.data.AtlasIds
import net.minecraft.network.chat.Component
import net.minecraft.network.chat.contents.objects.AtlasSprite
import net.minecraft.resources.Identifier

/**
 * Créé un [Component] texture à l'aide d'une chaîne de caractères.
 *
 * @param literal Texte à convertir
 */
fun literalComponent(literal: String) = Component.literal(literal)

/**
 * Créé un [Component] à partir de son id de traduction.
 *
 * ```json
 * "item.herobrine.yellow_fish": "Poisson jaune"
 * ```
 */
fun translatableComponent(translatable: String) = Component.translatable(translatable)

/**
 * Créé un [AtlasSprite] à partir d'une **texture d'item**.
 *
 * @param sprite [Identifier] de la texture
 * @return Sprite situé dans l'atlas [AtlasIds.ITEMS]
 */
fun itemSprite(sprite: Identifier) = AtlasSprite(AtlasIds.ITEMS, sprite)

/**
 * Créé un [Component] vide.
 */
fun emptyComponent() = Component.empty()

/**
 * Transforme une chaîne de caractères en [Component].
 *
 * @receiver Chaîne de caractères à convertir
 */
fun String.asLiteral() = literalComponent(this)

/**
 * Ajoute un ou plusieurs [components][Component] à un [Component].
 *
 * @receiver [Component] parent
 * @param components Liste de [Component]
 */
fun Component.append(vararg components: Component): Component {
    var newComponent = this.copy()

    components.forEach { sibling ->
        newComponent.siblings.add(sibling)
    }

    return  newComponent
}