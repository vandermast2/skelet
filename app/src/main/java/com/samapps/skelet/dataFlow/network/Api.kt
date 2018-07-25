package com.samapps.skelet.dataFlow.network

import com.samapps.skelet.dataFlow.models.apiModels.*
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import kotlinx.coroutines.experimental.Deferred
import okhttp3.ResponseBody
import retrofit2.http.*


interface Api{
    //***********Alerts
    @GET("api/ManageAlerts")
    fun getAlerts(): Flowable<List<AlertsModel>>

    @POST("api/Alerts/{productId}")
    fun sendAlerts(@Path("productId") productId: Int, @Body alertSendModel: List<AlertSendModel>): Flowable<ResponseBody>

    @DELETE("api/Alerts/{productId}")
    fun deleteAlerts(@Path("productId") productId: String): Flowable<ResponseBody>

    @GET("api/Alerts/{productId}")
    fun getAlert(@Path("productId") id: String): Flowable<List<AlertItemModel>>?

    @DELETE("api/Alerts")
    fun deleteAllAlerts(): Flowable<ResponseBody>

    //***********File
    @GET("api/File")
    fun getFile(@Query("fileHash") fileHash:String, @Query ("code") code:String ): Flowable<List<CandleStickModel>>

    //***********Indices
    @GET("api/Indices/SMI")
    fun getSmiIndex(): Deferred<List<Index>>

    @GET("api/Indices/MidCap")
    fun getMidCapIndex(): Flowable<List<Index>>

    @GET("api/Indices/external")
    fun getExternalIndex(): Flowable<List<Index>>

    @GET("api/Index/{id}")
    fun getIndex(@Path("id") id: String): Flowable<Index>

    //***********Login
    @GET("api/Logout")
    fun logout(): Flowable<ResponseBody>

    @FormUrlEncoded
    @POST("token")
    fun registration(@Field("email") email: String?, @Field("client_id") installationId: String, @Field("grant_type") grant_type: String, @Field("device_token") device_token: String?): Deferred<RegistrationModel>//

    //***********News
    @GET("api/News")
    fun getNews(): Flowable<List<NewsModel>>

    @GET("api/News/{id}")
    fun getNewsById(@Path("id") id: Long): Flowable<NewsModel>

    @GET("api/News")
    fun getNewsForUnderluing(@Query("underlyingId") id: Int): Flowable<List<NewsModel>>

    @GET("api/News")
    fun getNewsByUnderluingId(@Query("filter.productIds") id: Array<Int>): Flowable<List<NewsModel>>

    //***********PerformanceSeries
    @GET("api/PerformanceSeries/{id}")
    fun getChartDataResult(@Path("id") id: String, @Query("period") period: String): Flowable<ChartData>

    @GET("api/CandlesticksGraph/Smi")
    fun getCandleSMI(): Deferred<List<CandleStickModel>>

    @GET("api/CandlesticksGraph/MidCap")
    fun getCandleMidCap(): Flowable<List<CandleStickModel>>

    //***********Promotion
    @GET("api/Promotion")
    fun getPromotionInfo(): Observable<PromotionInfoModel>

    //***********Staticstics
    @POST()
    fun saveStaticstics(@Body statistics: StatisticsModel)

    //***********Underlying
    @GET("api/Underlyings/SMI")
    fun getSMIUnderlyings(): Deferred<List<JBSMIModel>>//

    @GET("api/Underlyings")
    fun getAllUnderlyings(): Flowable<List<JBSMIModel>>//

    @GET("api/Underlyings/MidCap")
    fun getMidCapUnderlyings(): Flowable<List<JBMidCapModel>>//

    @GET("api/Underlying/{id}")
    fun getUnderlyingsById(@Path("id") id: String?): Flowable<UnderlyingById>

    //***********UserProfile
    @GET("api/UserProfile")
    fun getUserProfile(): Flowable<UserInfoModel>

    @PUT("api/UserProfile")
    fun putUserProfile(@Body userInfoModel: UserInfoModel): Flowable<ResponseBody>

    //***********Warrant
    @GET("api/Warrants")
    fun getWarrants(
            @Query("warrantsView") warrantsView: Boolean?,
            @Query("filter.underlyingId") id: String?,
            @Query("filter.contractOption") contractOption: String?,
            @Query("filter.maturityStartDate") maturityStartDate: String?,
            @Query("filter.maturityEndDate") maturityEndDate: String?,
            @Query("filter.strikePriceMin") strikePriceMin: String?,
            @Query("filter.strikePriceMax") strikePriceMax: String?,
            @Query("filter.topOnly") topOnly: String?,
            @Query("pagination.start") paginationStart: String?,
            @Query("pagination.count") paginationCount: String?): Flowable<List<Warrant>>//

    @GET("api/ExtremeWarrantsFilterValues")
    fun getExtremeWarrantValues(): Single<ExtremeWarrantModel>//

    @GET("api/Warrant/{id}")
    fun getWarrantById(@Path("id") id: String): Flowable<DetailWarrant>

    //***********WatchListItem
    @POST("api/WatchListItem")
    fun addWatchListItem(@Query("id") id: String): Flowable<ResponseBody>

    @GET("api/WatchListInfo")
    fun getWatchList(): Flowable<WatchlistInfoModel>

    @DELETE("api/WatchListItem")
    fun deleteWatchListItem(@Query("id") id: String): Flowable<ResponseBody>
}