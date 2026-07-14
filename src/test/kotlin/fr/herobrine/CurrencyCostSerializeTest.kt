package fr.herobrine

import fr.herobrine.economy.CurrencyCost
import fr.herobrine.economy.CurrencyType
import net.minecraft.nbt.NbtOps
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail
import kotlin.test.assertEquals

class CurrencyCostSerializeTest {

    @Test
    fun `test currency serialization`() {
        val cost = CurrencyCost(
            value = 17,
            costType = CurrencyType.EMERALD
        )

        CurrencyCost.CODEC.encodeStart(NbtOps.INSTANCE, cost).ifSuccess { tag ->
            println("Serialized tag: $tag")

            tag.asCompound().ifPresentOrElse({ compound ->
                assertEquals(
                    17,
                    compound.getInt("value").get(),
                    "'value' was expected to match 17"
                )
            }, {
                fail("Serialized tag is not a compound")
            })
        }.ifError { error ->
            fail(error.message())
        }
    }
}
