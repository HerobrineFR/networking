package fr.herobrine.network.client

import com.mojang.serialization.Codec
import com.mojang.serialization.DataResult
import net.minecraft.network.codec.ByteBufCodecs

/**
 * Informations d'un client, contenant une liste de [ClientProperty].
 *
 * @property properties Liste des [ClientProperty] du client.
 */
data class ClientInformation(
    val properties: MutableMap<ClientProperty<*>, Any?> = mutableMapOf()
) {

    companion object {
        /**
         * Codec **décrivant** une map **property** vers **valeur**.
         *
         * ```json
         * {
         *  "herobrine:mod_metadata": {
         *      "uses_herobrine_tweaks": true
         *  }
         * }
         * ```
         */
        @JvmField
        val CODEC = codecFromMap(ClientProperty.VALUE_MAP_CODEC)

        @JvmField
        val STREAM_CODEC = ByteBufCodecs.fromCodec(CODEC)

        private fun codecFromMap(codec: Codec<Map<ClientProperty<*>, Any?>>): Codec<ClientInformation> {
            return codec.comapFlatMap({ map ->
                DataResult.success(ClientInformation(map.toMutableMap()))
            }, { info ->
                info.properties
            })
        }
    }

    /**
     * Obtient une **valeur** à partir d'une **property**.
     *
     * @param property [ClientProperty] à obtenir.
     * @param T Type de la propriété
     */
    inline fun <reified T> getProperty(property: ClientProperty<T>): T {
        try {
            val storedProperty = properties.getValue(property)

            if(storedProperty is T) {
                return storedProperty
            }
        }catch (_: NoSuchElementException) {
        }

        val defaultValue = property.defaultValue
        properties[property] = defaultValue as Any
        return defaultValue
    }

    /**
     * Définit la valeur d'une propriété.
     *
     * @param property Propriété à assigner
     * @param value Nouvelle valeur
     *
     * @param T Type de la propriété
     */
    fun <T> setProperty(property: ClientProperty<T>, value: T) {
        properties[property] = value as Any
    }
}