package unittests

import com.github.salomonbrys.kotson.fromJson
import com.google.gson.Gson
import com.google.gson.JsonParseException
import io.github.ehedbor.diskordlin.entities.user.User
import io.kotlintest.matchers.shouldBe
import io.kotlintest.matchers.shouldThrow
import io.kotlintest.specs.StringSpec

class UserModelTest : StringSpec() {
    init {
        "should parse a user correctly" {
            val user = Gson().fromJson<User>("""
            {
                "id": "80351110224678912",
                "username": "Nelly",
                "discriminator": "1337",
                "avatar": "8342729096ea3675442027381ff50dfe",
                "verified": true,
                "email": "nelly@discordapp.com"
            }
            """)

            user.id shouldBe "80351110224678912"
            user.username shouldBe "Nelly"
            user.discriminator shouldBe "1337"
            user.avatar shouldBe "8342729096ea3675442027381ff50dfe"
            user.verified shouldBe true
            user.email shouldBe "nelly@discordapp.com"
        }

        "should create an 'empty' user (all null values)" {
            val empty = Gson().fromJson<User>("{}")

            empty.id shouldBe null
            empty.username shouldBe null
            empty.discriminator shouldBe null
            empty.avatar shouldBe null
            empty.verified shouldBe null
            empty.email shouldBe null
        }

        "should fail to parse" {
            shouldThrow<JsonParseException> {
                Gson().fromJson<User>("""
                {
                    "verified": "I am not a boolean"
                }
                """)
            }
        }
        
    }
}