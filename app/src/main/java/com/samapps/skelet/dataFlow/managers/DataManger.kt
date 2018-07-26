package com.samapps.skelet.dataFlow.managers

import com.google.firebase.iid.FirebaseInstanceId
import com.samapps.skelet.dataFlow.models.FilterModel
import com.samapps.skelet.dataFlow.models.apiModels.*
import com.samapps.skelet.dataFlow.network.Api
import com.samapps.skelet.dataFlow.storage.IUserStorage
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import kotlinx.coroutines.experimental.Deferred
import okhttp3.ResponseBody
import java.util.*
import javax.inject.Inject

class DataManger @Inject constructor(private val api: Api, private val storage: IUserStorage) : IDataManager {
    override fun saveStaticstics(statistics: StatisticsModel) {

    }

    override fun getFile(fileHash: String, code: String): Flowable<List<CandleStickModel>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteAllAlerts(): Flowable<ResponseBody> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveFilter(filter: FilterModel?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getFilter(): FilterModel {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun savePhoneNumber(number: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPhoneNumber(): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getEmail(): String? = storage.getEmail()

    override fun saveEmail(email: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveTokenRole(role: String) {
        storage.saveTokenRole(role)
    }

    override fun getTokenRole(): String = storage.getTokenRole()

    override fun setIsConfirmed(confirmed: Boolean) {
        storage.setIsConfirmed(confirmed)
    }

    override fun isConfirmed(): Boolean = storage.isConfirmed()

    override fun clearFilter() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun setAlphabetic(toBoolean: Boolean) {
        storage.setAlphabetic(toBoolean)
    }

    override fun setTop(toBoolean: Boolean) {
        storage.setTop(toBoolean)
    }

    override fun setBoxes(toBoolean: Boolean) {
        storage.setBoxes(toBoolean)
    }

    override fun setCandles(toBoolean: Boolean) {
        storage.setCandles(toBoolean)
    }

    override fun getAlphabetic(): Boolean = storage.getAlphabetic()

    override fun getTop(): Boolean = storage.getTop()

    override fun getBoxes(): Boolean = storage.getBoxes()

    override fun getCandles(): Boolean = storage.getCandles()

    override fun setUserId(deviceId: String) {
        storage.setUserId(deviceId)
    }

    override fun getUserID(): String = if (storage.getUserID() == "") {
        val userId: String = UUID.randomUUID().toString()
        setUserId(userId)
        userId
    } else {
        storage.getUserID()
    }

    override fun savePublicKey(publicKey: String) {
        storage.savePublicKey(publicKey)
    }

    override fun getPublicKey(): String = storage.getPublicKey()

    override fun getCandleSMI(): Deferred<List<CandleStickModel>> = api.getCandleSMI()

    override fun getCandleMidCap(): Flowable<List<CandleStickModel>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAlerts(): Flowable<List<AlertsModel>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun sendAlerts(productId: Int, alertSendModel: List<AlertSendModel>): Flowable<ResponseBody> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteAlerts(productId: String): Flowable<ResponseBody> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getSMIUnderlyings(): Deferred<List<JBSMIModel>> = api.getSMIUnderlyings()

    override fun getAllUnderlyings(): Flowable<List<JBSMIModel>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getMidCapUnderlyings(): Flowable<List<JBMidCapModel>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getNews(): Flowable<List<NewsModel>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getNewsForUnderluing(id: Int): Flowable<List<NewsModel>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getNewsById(id: Long): Flowable<NewsModel> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getNewsByUnderluingId(id: Array<Int>): Flowable<List<NewsModel>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun registration(email: String?, installationId: String, grant_type: String, device_token: String?): Deferred<RegistrationModel> = api.registration(email, getUserID(), "identity", FirebaseInstanceId.getInstance().token)

    override fun getUnderlyingsById(id: String?): Flowable<UnderlyingById> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getUserProfile(): Flowable<UserInfoModel> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun putUserProfile(userInfoModel: UserInfoModel): Flowable<ResponseBody> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getWarrants(warrantsView: Boolean?, id: String?, contractOption: String?, maturityStartDate: String?, maturityEndDate: String?, strikePriceMin: String?, strikePriceMax: String?, topOnly: String?, paginationStart: String?, paginationCount: String?): Flowable<List<Warrant>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getExtremeWarrantValues(): Single<ExtremeWarrantModel> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getWarrantById(id: String): Flowable<DetailWarrant> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getSmiIndex(): Deferred<List<Index>> = api.getSmiIndex()

    override fun getMidCapIndex(): Flowable<List<Index>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getExternalIndex(): Flowable<List<Index>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun addWatchListItem(id: String): Flowable<ResponseBody> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getWatchList(): Flowable<WatchlistInfoModel> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteWatchListItem(id: String): Flowable<ResponseBody> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getChartDataResult(id: String, period: String): Flowable<ChartData> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getIndex(id: String): Flowable<Index> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAlert(id: String): Flowable<List<AlertItemModel>>? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun logout(): Flowable<ResponseBody> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPromotionInfo(): Observable<PromotionInfoModel> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveToken(token: String) {
        storage.saveToken(token)
    }

    override fun getToken(): String  = storage.getToken()

    override fun setFirstTimeLoading(firstTime: Boolean) {
        storage.setFirstTimeLoading(firstTime)
    }

    override fun getFirstTimeLoading(): Boolean = storage.getFirstTimeLoading()


}