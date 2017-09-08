package com.studikode.kotlin.github

class SearchRepository(val apiService: GithubApiService) {

    fun searchUsers(location: String, language: String): io.reactivex.Observable<GithubResult> {
        return apiService.search(query = "location:$location language:$language")
    }

}