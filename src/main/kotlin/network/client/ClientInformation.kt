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

    fun <T> setProperty(property: ClientProperty<T>, value: T) {
        properties[property] = value as Any
    }
}