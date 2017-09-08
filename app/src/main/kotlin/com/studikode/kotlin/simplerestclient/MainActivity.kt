package com.studikode.kotlin.simplerestclient

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.studikode.kotlin.github.GithubApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    var disposable: Disposable? = null
    val apiService by lazy {
        GithubApiService.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var tvHello = findViewById(R.id.result) as TextView
        var btnCallApi = findViewById(R.id.callApi) as Button

        toast("Welcome to hackday")

        disposable = apiService.search("mojombo")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { result -> tvHello.setText(result.items[0].htmlUrl) },
                { error -> tvHello.setText(error.message) }
            )
    }
}
