package fr.herobrine

import com.google.gson.JsonPrimitive
import com.mojang.serialization.JsonOps
import fr.herobrine.emote.PlayerEmote
import fr.herobrine.util.asLiteral
import fr.herobrine.util.identifier
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail
import kotlin.test.assertEquals

class FindKneelEmoteTest {

    @Test
    fun `check if name codec is able to find emotes`() {
        val kneelEmote = PlayerEmote(
            "kneel",
            "Kneel".asLiteral(),
            identifier("herobrine:ui/emote/kneel")
        )

        val emotes = listOf(
            kneelEmote,
            PlayerEmote(
                "pray",
                "Pray".asLiteral(),
                identifier("herobrine:ui/emote/pray")
            )
        )

        val nameCodec = PlayerEmote.byNameCodec(emotes)

        // Check if it's able to find the emote from it's name
        nameCodec.decode(JsonOps.INSTANCE, JsonPrimitive("kneel")).ifSuccess { pair ->
            println("Successfully found emote '${pair.first.id}' (${pair.first.itemModel.toString()})")
        }.ifError { error ->
            fail { error.message() }
        }

        // Check if it's able to encode the emote to it's name
        nameCodec.encodeStart(JsonOps.INSTANCE, kneelEmote).ifSuccess { element ->
            assertEquals("kneel", element.asString, "Serialization result should match 'kneel'. '${element.asString}' returned instead")
        }.ifError { error ->
            fail { error.message() }
        }
    }
}