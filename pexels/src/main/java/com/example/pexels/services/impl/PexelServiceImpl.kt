package com.example.pexels.services.impl

import com.example.pexels.api.PexelApi
import com.example.pexels.api.PexelCollection
import com.example.pexels.api.PexelPhoto
import com.example.pexels.services.PexelService
import java.util.Locale

class PexelServiceImpl internal constructor(
    private val api: PexelApi,
): PexelService{

    private val supportLocal = arrayOf(
        "en-Us",
        "pt-BR",
        "es-ES",
        "ca-ES",
        "de-DE",
        "it-IT",
        "fr-FR",
        "sv-SE",
        "id-ID",
        "pl-PL",
        "ja-JP",
        "zh-TW",
        "zh-CN",
        "ko-KR",
        "th-TH",
        "nl-NL",
        "hu-HU",
        "vi-VN",
        "cs-CZ",
        "da-DK",
        "fi-FI",
        "uk-UA",
        "el-GR",
        "ro-RO",
        "nb-NO",
        "sk-SK",
        "tr-TR",
        "ru-RU"
    )

    override suspend fun searchPhotos(
        query: String,
        orientation: PexelService.Orientation?,
        size: PexelService.PhotoSize?,
        color: Int?,
        locale: Locale?,
        page: Int?,
        perPage: Int?
    ): Result<List<PexelPhoto>> {
        return kotlin.runCatching {
            api.searchPhotos(
                query,
                orientation.toQueryValue(),
                size.toQueryValue(),
                color.toColorValue(),
                locale.toQueryValue(),
                page,
                perPage
            )
        }.map {
            it.photos
        }
    }

    override suspend fun featurePhotos(page: Int?, perPage: Int?): Result<List<PexelPhoto>> {
        return kotlin.runCatching {
            api.featuredPhoto(page, perPage)
        }.map {
            it.photos
        }
    }

    override suspend fun collections(page: Int?, perPage: Int?): Result<List<PexelCollection>> {
        return kotlin.runCatching {
            api.collections(page, perPage)
        }.map {
            it.collection.filter { collection ->
                collection.photos_count > 0
            }
        }
    }

    override suspend fun collectionPhotos(
        collection: PexelCollection,
        page: Int?,
        perPage: Int?
    ): Result<List<PexelPhoto>> {
        return kotlin.runCatching {
            api.collectionMedias(
                collection.id, "photos",
                page,
                perPage
            )
        }.map {
            it.meida.filterIsInstance(PexelPhoto::class.java)
        }
    }

    private fun PexelService.Orientation?.toQueryValue(): String? {
        return when (this) {
            PexelService.Orientation.Landscape -> "landscape"
            PexelService.Orientation.Protrait -> "portrait"
            PexelService.Orientation.Square -> "portrait"
            else -> null
        }
    }

    private fun PexelService.PhotoSize?.toQueryValue(): String? {
        return if (this == null) {
            null
        } else {
            "${sizeInMb}MP"
        }
    }

    private fun Locale?.toQueryValue(): String? {
        return if (this == null) {
            return null
        } else {
            val tag = toLanguageTag()
            if (supportLocal.contains(tag)) {
                tag
            } else {
                null
            }
        }
    }

    private fun Int?.toColorValue(): String? {
        if (this == null) {
            return null
        }
        return "#${this.toUInt().toString(16)}"
    }
}