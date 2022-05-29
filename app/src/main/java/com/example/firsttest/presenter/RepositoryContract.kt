package com.example.firsttest.presenter

import com.example.firsttest.model.SearchResponse
import com.example.firsttest.repository.RepositoryCallback
import io.reactivex.Observable


interface RepositoryContract {

    fun searchGithub(
        query: String,
        callback: RepositoryCallback
    )

    fun searchGithub(
        query: String
    ): Observable<SearchResponse>

    suspend fun searchGithubAsync(
        query: String
    ): SearchResponse
}