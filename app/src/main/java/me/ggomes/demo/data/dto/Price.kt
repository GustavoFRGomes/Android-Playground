package me.ggomes.demo.data.dto

import com.google.gson.annotations.SerializedName

data class Price(
    val type: String?,

) {
    inner class Grs(
        val amount: Long?,
        val currency: String?,
        val localized: String?
    )
}