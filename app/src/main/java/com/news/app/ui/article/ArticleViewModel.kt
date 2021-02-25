package com.news.app.ui.article

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.news.app.BuildConfig
import com.news.app.repository.ArticleRepository
import com.news.app.shared.AppPreferences
import com.news.ext.date.convertDatePattern
import com.news.ext.fromJsonTyped
import com.news.ext.toJson
import com.news.model.Article
import com.news.model.Multimedia
import com.news.model.State
import kotlinx.coroutines.launch
import java.net.UnknownHostException
import javax.inject.Inject

class ArticleViewModel @Inject constructor(private val repository: ArticleRepository, val appPreferences: AppPreferences) : ViewModel() {

    enum class UiRequest { TRY_AGAIN, CLOSE_TOOLTIP, DETAIL_ARTICLE, OFFLINE_MODE }
    enum class UiMode { INITIATE, ON_PROGRESS, SUCCESS, ERROR, TOOLTIP }

    data class Parcel(val article: Article? = null)

    private val mUiRequest = MutableLiveData<Pair<UiRequest, Parcel>>()
    val uiRequest: LiveData<Pair<UiRequest, Parcel>> = mUiRequest

    private val mUiMode = MutableLiveData(UiMode.INITIATE)
    val uiMode: LiveData<UiMode> = mUiMode

    private val mArticles = MutableLiveData<State<List<Article>>>()
    val articles: LiveData<State<List<Article>>> = mArticles

    init {
        if (!appPreferences.tooltipSwipeSeen) mUiMode.postValue(UiMode.TOOLTIP)
    }

    fun fetchArticles(page: Int) {
        viewModelScope.launch scope@{
            mUiMode.postValue(UiMode.ON_PROGRESS)
            try {
                repository.findArticles(page).run {
                    if (page == 1) appPreferences.articlesJson = result.docs.toJson()
                    mArticles.postValue(State.Success(result.docs))
                    mUiMode.postValue(UiMode.SUCCESS)
                }
            } catch (e: Exception) {
                mArticles.postValue(when (e) {
                    is UnknownHostException -> State.Failure("Please check your internet connection and try again.")
                    else -> State.Failure(e.message)
                })
                mUiMode.postValue(UiMode.ERROR)
                e.printStackTrace()
            }
        }
    }

    fun fetchArticlesFromLocalStorage() {
        mArticles.postValue(State.Success(appPreferences.articlesJson.fromJsonTyped()))
        mUiRequest.postValue(UiRequest.OFFLINE_MODE to Parcel())
        mUiMode.postValue(UiMode.SUCCESS)
    }

    fun formatTime(data: String?): String? {
        return data?.convertDatePattern("yyyy-MM-dd'T'HH:mm:ssZ", "yyyy/MM/dd")
    }

    fun formatImgUrl(list: List<Multimedia>?): String {
        return BuildConfig.URL_IMAGE + list?.firstOrNull { it.subtype == "popup" }?.url
    }

    fun clickArticle(data: Article?) {
        mUiRequest.postValue(UiRequest.DETAIL_ARTICLE to Parcel(article = data))
    }

    fun clickTryAgain() {
        mUiRequest.postValue(UiRequest.TRY_AGAIN to Parcel())
    }

    fun clickCloseTooltip() {
        mUiRequest.postValue(UiRequest.CLOSE_TOOLTIP to Parcel())
    }

}