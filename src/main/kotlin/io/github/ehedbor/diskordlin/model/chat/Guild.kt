package io.github.ehedbor.diskordlin.model.chat

import com.google.gson.annotations.SerializedName
import io.github.ehedbor.diskordlin.model.Snowflake
import io.github.ehedbor.diskordlin.model.user.Role

data class Guild(
    val id: Snowflake,
    val name: String,
    val icon: String? = null,
    val splash: String? = null,
    val owner: Boolean? = null,
    @SerializedName("owner_id")
    val ownerId: Snowflake,
    val permissions: Int? = null,
    val region: String,
    @SerializedName("afk_channel_id")
    val afkChannelId: Snowflake,
    @SerializedName("afk_timeout")
    val afkTimeout: Int,
    @SerializedName("embed_enabled")
    val embedEnabled: Boolean? = null,
    @SerializedName("embed_channel_id")
    val embedChannelId: Snowflake,
    @SerializedName("verification_level")
    val verificationLevel: Int,
    @SerializedName("default_message_notifications")
    val defaultMessageNotifications: Int,
    @SerializedName("explicit_content_filter")
    val explicitContentFilter: Int,
    val roles: List<Role> = emptyList(),
    val emojis: List<Emoji> = emptyList(),
    val features: List<String> = emptyList(),
    @SerializedName("mfa_level")
    val mfaLevel: Int,
    @SerializedName("application_id")
    val applicationId: Snowflake? = null,
    @SerializedName("widget_enabled")
    val widgetEnabled: Boolean? = null,
    @SerializedName("widget_channel_id")
    val widgetChannelId: Snowflake? = null,
    @SerializedName("system_channel_id")
    val systemChannelId: Snowflake? = null,
    @SerializedName("joined_at")
    val joinedAt: String? = null,
    @SerializedName("large")
    val isLarge: Boolean? = null,
    val unavailable: Boolean? = null,
    @SerializedName("member_count")
    val memberCount: Int? = null,

    // TODO: Implement these classes
    //@SerializedName("voice_states")
    //val voiceStates: List<VoiceState> = emptyList(),
    //val members: List<GuildMember> = emptyList(),
    val channels: List<Channel> = emptyList()
    //val presences: List<PresenceUpdate> = emptyList()
)