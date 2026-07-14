package fr.herobrine.network.client

import com.mojang.serialization.Codec
import com.mojang.serialization.DataResult
import fr.herobrine.network.client.meta.ModMetadata
import fr.herobrine.util.identifier
import net.minecraft.resources.Identifier
import java.util.Optional

/**
 * Propriété du client de jeu.
 *
 * @property id Identifiant de la propriété.
 * @property defaultValue Valeur par défaut
 *
 * @param T Type associé à la propriété
 */
@JvmRecord
data class ClientProperty<T>(
    val id: Identifier,
    val defaultValue: T,
    val codec: Codec<T>
) {
    companion object {
        private val PROPERTIES = mutableMapOf<Identifier, ClientProperty<*>>()

        /**
         * Données concernant les mods du client.
         */
        @JvmField
        val MOD_METADATA = registerProperty(
            ClientProperty(
                identifier("herobrine:mod_metadata"),
                defaultValue = ModMetadata(),
                codec = ModMetadata.CODEC
            )
        )

        /**
         * Codec permettant d'obtenir une [ClientProperty] à partir de son [id][Identifier].
         */
        @JvmField
        val BY_NAME_CODEC: Codec<ClientProperty<*>> = Identifier.CODEC.comapFlatMap({ id ->
            when(val property = PROPERTIES[id]) {
                null -> DataResult.error { "Property '${id}' doesn't exist." }
                else -> DataResult.success(property)
            }
        }, { dataResult ->
            dataResult.id
        })

        @JvmField
        val PERSISTENT_CODEC = BY_NAME_CODEC.validate({property ->
            DataResult.success(property)
        })
        /**
         * Codec utilisant [BY_NAME_CODEC].
         *
         * Permet de créer le **codec** de [ClientInformation].
         * @see ClientInformation.CODEC
         */
        @JvmField
        val VALUE_MAP_CODEC = Codec.dispatchedMap(PERSISTENT_CODEC, {p -> p.codec})

        /**
         * Trouve une [ClientProperty] à partir de son [id][Identifier].
         */
        @JvmStatic
        fun findProperty(id: Identifier): Optional<ClientProperty<*>> {
            return when(PROPERTIES.contains(id)) {
                true -> Optional.of(PROPERTIES.get(id) as ClientProperty<*>)
                false -> Optional.empty<ClientProperty<*>>()
            }
        }

        @JvmStatic
        private fun <T> registerProperty(property: ClientProperty<T>): ClientProperty<T> {
            PROPERTIES[property.id] = property

            return property
        }
    }
}