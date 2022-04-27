package com.example.firsttest.presenter.search

import com.example.firsttest.presenter.PresenterContract

internal interface PresenterSearchContract : PresenterContract {
    fun searchGitHub(searchQuery: String)
}