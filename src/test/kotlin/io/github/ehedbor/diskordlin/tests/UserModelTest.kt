package io.github.ehedbor.diskordlin.tests

import com.beust.klaxon.Klaxon
import com.beust.klaxon.KlaxonException
import io.github.ehedbor.diskordlin.models.User
import io.kotlintest.matchers.shouldBe
import io.kotlintest.matchers.shouldNotBe
import io.kotlintest.matchers.shouldThrow
import io.kotlintest.specs.StringSpec

class UserModelTest : StringSpec() {
    init {
        "should parse a user correctly" {
            val user = Klaxon().parse<User>("""
            {
                "id": "80351110224678912",
                "username": "Nelly",
                "discriminator": "1337",
                "avatar": "8342729096ea3675442027381ff50dfe",
                "verified": true,
                "email": "nelly@discordapp.com"
            }
            """)

            user shouldNotBe null
            user?.id shouldBe "80351110224678912"
            user?.username shouldBe "Nelly"
            user?.discriminator shouldBe "1337"
            user?.avatar shouldBe "8342729096ea3675442027381ff50dfe"
            user?.verified shouldBe true
            user?.email shouldBe "nelly@discordapp.com"
        }

        "should create an empty user (all null values)" {
            val empty = Klaxon().parse<User>("{}")

            empty shouldNotBe null
            empty?.id shouldBe null
            empty?.username shouldBe null
            empty?.discriminator shouldBe null
            empty?.avatar shouldBe null
            empty?.verified shouldBe null
            empty?.email shouldBe null
        }

        "should fail to parse" {
            shouldThrow<KlaxonException> {
                Klaxon().parse<User>("""
                {
                    "verified": "I am not a boolean"
                }
                """)
            }
        }
    }
}