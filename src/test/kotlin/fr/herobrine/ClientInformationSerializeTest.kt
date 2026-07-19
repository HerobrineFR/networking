package fr.herobrine

import fr.herobrine.network.client.ClientInformation
import fr.herobrine.network.client.ClientProperty
import fr.herobrine.network.client.meta.ModMetadata
import net.minecraft.nbt.NbtOps
import kotlin.test.Test
import kotlin.test.assertTrue
import kotlin.test.fail

class ClientInformationSerializeTest {
    @Test
    fun `verify clientinfo serialization`() {
        val info = ClientInformation()

        info.setProperty(ClientProperty.MOD_METADATA, ModMetadata(
                usesHerobrineTweaks = true
            ))

        ClientInformation.CODEC.encodeStart(NbtOps.INSTANCE, info).ifSuccess { tag ->
            ClientInformation.CODEC.decode(NbtOps.INSTANCE, tag).ifSuccess { pair ->
                assertTrue { pair.first.getProperty(ClientProperty.MOD_METADATA).usesHerobrineTweaks }
            }.ifError { error ->
                fail(error.message())
            }
        }.ifError { error ->
            fail(error.message())
        }
    }
}