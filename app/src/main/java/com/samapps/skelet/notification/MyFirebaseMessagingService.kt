package com.samapps.skelet.notification

import android.annotation.TargetApi
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.TaskStackBuilder
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.samapps.skelet.AppApplication
import com.samapps.skelet.R
import com.samapps.skelet.ui.main.activities.main.MainActivity
import com.samapps.skelet.utils.Constants
import com.samapps.skelet.utils.UiUtils
import timber.log.Timber


class MyFirebaseMessagingService : FirebaseMessagingService() {

    val TEXT_KEY = "gcm.notification.body"
    val GROUP_KEY = "com.adviscent.jpa.develop.group"
    //    val GROUP_KEY = "0|com.adviscent.jpa.develop|0|FCM-Notification:265119115|10303"
    private val APP_CHANEL = MyFirebaseMessagingService::class.java.canonicalName
    private var NOTIFICATION_ID = 18

//    private var bageCount: BadgeCount? = null


    init {
        AppApplication.component.inject(this)
    }


    override fun onMessageReceived(p0: RemoteMessage?) {
        val data = p0?.data?.keys
        Timber.d("Notification keys $data")
    }

//    override fun handleIntent(intent: Intent?) {
//        if (intent?.extras != null) {
//            Timber.d("Notification keys ${intent.extras.keySet()}")
//            updateCounter(intent.extras)
//            if (!isAppForeground()) {
//                showNotification(intent.extras)
//            }
//        }
//
//    }

//    private fun updateCounter(bundle: Bundle) {
//        if (!bundle.containsKey(Constants.PUSH_ID_KEY)) return
//        val ids = bundle.getString(Constants.PUSH_ID_KEY)
//
//        if (!TextUtils.isEmpty(ids)) {
//            updateCount(1)
//        }
//    }

//    private fun updateCount(i: Int) {
//        val realm = Realm.getDefaultInstance()
//        bageCount = realm.where(BadgeCount::class.java).findFirst()
//        var counter = 0
//        if (bageCount != null) {
//            counter = bageCount!!.count?.plus(i)!!
//            Handler(mainLooper).post(Runnable {
//                ShortcutBadger.applyCount(this, counter)
//            })
//            realm.executeTransaction { realmEx ->
//                bageCount!!.count = counter
//                realmEx.copyToRealmOrUpdate(bageCount!!)
//            }
//            realm.close()
//        } else {
//            counter = i
//
//            Handler(mainLooper).post(Runnable {
//                ShortcutBadger.applyCount(this, counter)
//            })
//
//            realm.executeTransaction { it ->
//                val badge = BadgeCount()
//                badge.count = counter
//                it.copyToRealmOrUpdate(badge)
//            }
//        }
//        realm.close()
//    }

//    @TargetApi(Build.VERSION_CODES.M)
//    private fun showNotification(bundle: Bundle?) {
////        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//        val notificationManager = systemService<NotificationManager>()
//        var text = bundle!!.getString(TEXT_KEY)
//        if (TextUtils.isEmpty(text)) {
//            text = getString(R.string.notification_text)
//        } else if (text!!.indexOf('"') != -1) {
//            text = text.substring(text.indexOf('"'), text.length - 1)
//        }
//
//        if (UiUtils.isCompatWithO) {
//            val notificationChannel = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//                NotificationChannel(APP_CHANEL, getString(R.string.notification_title), NotificationManager.IMPORTANCE_DEFAULT)
//            } else {
//                NotificationChannel(APP_CHANEL, getString(R.string.notification_title), NotificationManager.IMPORTANCE_DEFAULT)
//            }
//            with(notificationChannel) {
//                description = bundle.getString("gcm.notification.title")
//                setShowBadge(true)
//                enableLights(true)
//                lightColor = Color.BLUE
//                enableVibration(true)
//                vibrationPattern = longArrayOf(0, 1000, 500, 1000)
//            }
//            notificationManager.createNotificationChannel(notificationChannel)
//        }
//
//        val builder = NotificationCompat.Builder(applicationContext, APP_CHANEL)
//                .setSmallIcon(R.mipmap.ic_launcher_round)
//                .setLargeIcon(BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher))
//                .setBadgeIconType(NotificationCompat.BADGE_ICON_LARGE)
//                .setContentTitle(bundle.getString("gcm.notification.title"))
//                .setContentText(text)
//                .setDefaults(Notification.DEFAULT_ALL)
//                .setAutoCancel(true)
//                .setGroup(GROUP_KEY)
//                .setGroupSummary(true)
//
//
//        val resultIntent = Intent(this, MainActivity::class.java).setAction(bundle.get("ProductId").toString()).putExtras(bundle)
//        val stackBuilder = TaskStackBuilder.create(this)
//        stackBuilder.addParentStack(MainActivity::class.java)
//        stackBuilder.addNextIntent(resultIntent)
//
//        val resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
//        builder.setContentIntent(resultPendingIntent)
//
//        NOTIFICATION_ID += 1
//
//        notificationManager.notify(bundle.get("ProductId").toString().toInt(), builder.build())
//    }


//    @RequiresApi(manager = 23)
//    private fun getPreviousNotifications(newText: String): NotificationCompat.InboxStyle? {
////        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//        val notificationManager = systemService<NotificationManager>()
//        var counter = 0
//        val inbox = NotificationCompat.InboxStyle()
//        inbox.addLine(newText)
//        val previousNotifications = notificationManager.activeNotifications
//        previousNotifications.size
//        if (previousNotifications?.isNotEmpty()!!) {
//
//            previousNotifications
//                    .mapNotNull { it.notification?.extras?.get(NotificationCompat.EXTRA_TEXT_LINES) as? Array<CharSequence> }
//                    .filter { it.isNotEmpty() }
//                    .forEach {
//                        it.forEach { line ->
//                            inbox.addLine(line)
//                            counter++
//                        }
//                    }
//        }
//        inbox.setBigContentTitle(getString(R.string.notification_title))
//        if (counter > 0) {
//            inbox.setSummaryText(String.format(getString(R.string.new_articles), counter + 1))
//        }
//        return inbox
//    }
//
//    private fun isAppForeground(): Boolean {
//        val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
//        return if (activityManager.runningAppProcesses != null) {
//            activityManager.runningAppProcesses.any { it.uid == applicationInfo.uid && it.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND }
//        } else {
//            false
//        }
//    }
}

