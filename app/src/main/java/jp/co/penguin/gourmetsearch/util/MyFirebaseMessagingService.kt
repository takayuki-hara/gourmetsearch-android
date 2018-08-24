package jp.co.penguin.gourmetsearch.util

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {
    override fun onNewToken(token: String?) {
        Log.d("FCM", "Refreshed token: " + token)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.d("FCM", "From: " + remoteMessage.getFrom())

        // 通知メッセージの受信.
        if (remoteMessage.getNotification() != null) {
            Log.d("FCM", "Message Notification Body: " + remoteMessage.getNotification()!!.getBody())
        }

        // データメッセージの受信
        if (remoteMessage.getData().size > 0) {
            Log.d("FCM", "Message data payload: " + remoteMessage.getData())

            //if (/* Check if data needs to be processed by long running job */ true) {
            //    // For long-running tasks (10 seconds or more) use Firebase Job Dispatcher.
            //    scheduleJob();
            //} else {
            //    // Handle message within 10 seconds
            //    handleNow();
            //}
        }

    }

}
