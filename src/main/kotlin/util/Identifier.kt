package fr.herobrine.util

import net.minecraft.resources.Identifier

/**
 * Créé un [Identifier] à partir d'une chaîne de caractères contenant le `namespace` et le `path`.
 */
fun identifier(text: String): Identifier {
    return Identifier.parse(text)
}