package com.example.firsttest.presenter

import com.example.firsttest.repository.RepositoryCallback


internal interface RepositoryContract {
    fun searchGithub(
        query: String,
        callback: RepositoryCallback
    )
}