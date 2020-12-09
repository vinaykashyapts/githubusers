package com.ulp.ym.data

import kotlinx.serialization.Serializable

@Serializable
data class Users(
        val incomplete_results: Boolean,
        val items: List<Item>,
        val total_count: Int
)