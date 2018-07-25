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
    @GET("manager/ManageAlerts")
    fun getAlerts(): Flowable<List<AlertsModel>>

    @POST("manager/Alerts/{productId}")
    fun sendAlerts(@Path("productId") productId: Int, @Body alertSendModel: List<AlertSendModel>): Flowable<ResponseBody>

    @DELETE("manager/Alerts/{productId}")
    fun deleteAlerts(@Path("productId") productId: String): Flowable<ResponseBody>

    @GET("manager/Alerts/{productId}")
    fun getAlert(@Path("productId") id: String): Flowable<List<AlertItemModel>>?

    @DELETE("manager/Alerts")
    fun deleteAllAlerts(): Flowable<ResponseBody>

    //***********File
    @GET("manager/File")
    fun getFile(@Query("fileHash") fileHash:String, @Query ("code") code:String ): Flowable<List<CandleStickModel>>

    //***********Indices
    @GET("manager/Indices/SMI")
    fun getSmiIndex(): Flowable<List<Index>>

    @GET("manager/Indices/MidCap")
    fun getMidCapIndex(): Flowable<List<Index>>

    @GET("manager/Indices/external")
    fun getExternalIndex(): Flowable<List<Index>>

    @GET("manager/Index/{id}")
    fun getIndex(@Path("id") id: String): Flowable<Index>

    //***********Login
    @GET("manager/Logout")
    fun logout(): Flowable<ResponseBody>

    @FormUrlEncoded
    @POST("token")
    fun registration(@Field("email") email: String?, @Field("client_id") installationId: String, @Field("grant_type") grant_type: String, @Field("device_token") device_token: String?): Deferred<RegistrationModel>//

    //***********News
    @GET("manager/News")
    fun getNews(): Flowable<List<NewsModel>>

    @GET("manager/News/{id}")
    fun getNewsById(@Path("id") id: Long): Flowable<NewsModel>

    @GET("manager/News")
    fun getNewsForUnderluing(@Query("underlyingId") id: Int): Flowable<List<NewsModel>>

    @GET("manager/News")
    fun getNewsByUnderluingId(@Query("filter.productIds") id: Array<Int>): Flowable<List<NewsModel>>

    //***********PerformanceSeries
    @GET("manager/PerformanceSeries/{id}")
    fun getChartDataResult(@Path("id") id: String, @Query("period") period: String): Flowable<ChartData>

    @GET("manager/CandlesticksGraph/Smi")
    fun getCandleSMI(): Flowable<List<CandleStickModel>>

    @GET("manager/CandlesticksGraph/MidCap")
    fun getCandleMidCap(): Flowable<List<CandleStickModel>>

    //***********Promotion
    @GET("manager/Promotion")
    fun getPromotionInfo(): Observable<PromotionInfoModel>

    //***********Staticstics
    @POST()
    fun saveStaticstics(@Body statistics: StatisticsModel)

    //***********Underlying
    @GET("manager/Underlyings/SMI")
    fun getSMIUnderlyings(): Flowable<List<JBSMIModel>>//

    @GET("manager/Underlyings")
    fun getAllUnderlyings(): Flowable<List<JBSMIModel>>//

    @GET("manager/Underlyings/MidCap")
    fun getMidCapUnderlyings(): Flowable<List<JBMidCapModel>>//

    @GET("manager/Underlying/{id}")
    fun getUnderlyingsById(@Path("id") id: String?): Flowable<UnderlyingById>

    //***********UserProfile
    @GET("manager/UserProfile")
    fun getUserProfile(): Flowable<UserInfoModel>

    @PUT("manager/UserProfile")
    fun putUserProfile(@Body userInfoModel: UserInfoModel): Flowable<ResponseBody>

    //***********Warrant
    @GET("manager/Warrants")
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

    @GET("manager/ExtremeWarrantsFilterValues")
    fun getExtremeWarrantValues(): Single<ExtremeWarrantModel>//

    @GET("manager/Warrant/{id}")
    fun getWarrantById(@Path("id") id: String): Flowable<DetailWarrant>

    //***********WatchListItem
    @POST("manager/WatchListItem")
    fun addWatchListItem(@Query("id") id: String): Flowable<ResponseBody>

    @GET("manager/WatchListInfo")
    fun getWatchList(): Flowable<WatchlistInfoModel>

    @DELETE("manager/WatchListItem")
    fun deleteWatchListItem(@Query("id") id: String): Flowable<ResponseBody>
}