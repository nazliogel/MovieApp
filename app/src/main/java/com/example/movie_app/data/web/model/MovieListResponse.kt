package com.example.movie_app.data.web.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class MovieListResponse(
    @Json(name = "page")
    val page: Int?,
    @Json(name = "results")
    val results: List<Result>?,
    @Json(name = "total_pages")
    val totalPages: Int?,
    @Json(name = "total_results")
    val totalResults: Int?
) {
    @JsonClass(generateAdapter = true)
    data class Result(
        @Json(name = "adult")
        val adult: Boolean?,
        @Json(name = "backdrop_path")
        val backdrop_path: String?,  // Nullable
        @Json(name = "genre_ids")
        val genre_ids: List<Int>?,   // Nullable
        @Json(name = "id")
        val id: Int?,
        @Json(name = "original_language")
        val original_language: String?,  // Nullable
        @Json(name = "original_title")
        val original_title: String?,
        @Json(name = "overview")
        val overview: String?,
        @Json(name = "popularity")
        val popularity: Double?,
        @Json(name = "poster_path")
        val posterPath: String?,  // Nullable
        @Json(name = "release_date")
        val release_date: String?,  // Nullable
        @Json(name = "title")
        val title: String?,
        @Json(name = "video")
        val video: Boolean,
        @Json(name = "vote_average")
        val vote_average: Double?,  // Nullable
        @Json(name = "vote_count")
        val voteCount: Int?  // Nullable
    )
}
