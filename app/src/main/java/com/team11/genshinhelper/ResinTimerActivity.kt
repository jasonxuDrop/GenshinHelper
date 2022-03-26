package com.team11.genshinhelper

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import java.util.concurrent.TimeUnit

class ResinTimerActivity : AppCompatActivity() {

    private val CHANNEL_ID = "channel_id_example_01"
    private val notificationId = 101
    lateinit var resinAmmount: TextView
    lateinit var notificatonButton: ImageButton
    var setNotification = false
    var doSendNotification = true // TODO save this notification on/off bool && load the value in on create instead of defaulting to true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resin_timer)

        val door = 3
        resinAmmount = findViewById<TextView>(R.id.resinAmount)
        resinAmmount.text = "0"                                                        // TODO load resinAmmount value from save
        Log.d("test", resinAmmount.text.toString())

        findViewById<ImageButton>(R.id.EditButton).setOnClickListener{
            val intent = Intent(this, ResinTimerEditActivity::class.java)
            startActivity(intent)
            saveText2()
        }

        findViewById<LinearLayout>(R.id.SearchNav).setOnClickListener{
            val intent = Intent(this, ListActivityMain::class.java)
            startActivity(intent)
        }

        val textToDisplay = intent.getStringExtra("SOME_KEY")
        if (!textToDisplay.isNullOrEmpty())
            resinAmmount.text = textToDisplay

        createNotificationChannel()

        notificatonButton = findViewById(R.id.btn_button)
        notificatonButton.setOnClickListener(){
            if (doSendNotification) {
                doSendNotification = false
                setNotification = true
                notificatonButton.setImageResource(R.drawable.button_notification_on)
            }
            else {
                doSendNotification = true
                notificatonButton.setImageResource(R.drawable.button_notification_off)
                setNotification = false
            }
        }

        countDown()

}

    fun saveText2(){
        var textView2 = findViewById<TextView>(R.id.resinAmount)
        var textToPass2 = textView2.text.toString()

        var intent = Intent(this, ResinTimerEditActivity::class.java)
        intent.putExtra("SOME_KEY2", textToPass2)
        startActivity(intent)
    }

    fun conversionForTimer(): Long {
        Log.d("test", "resinAmmount.getText().toString() = " + resinAmmount.text.toString())
        var converted = Integer.parseInt(resinAmmount.text.toString());
        findViewById<ProgressBar>(R.id.progress_bar).progress = (((converted.toFloat()/160f)*100).toInt())

        val convertedNum = ((160 - converted) * 8) * 60000

        return convertedNum.toLong()
    }

   fun countDown() {

       val countDownTimer = object : CountDownTimer(conversionForTimer(), 1000) {
            var resinTimer = findViewById<TextView>(R.id.textView3)

            override fun onTick(p0: Long) {
                val millis: Long = p0
                val hms = String.format(
                    "%02d:%02d:%02d",

                    (TimeUnit.MILLISECONDS.toHours(millis) - TimeUnit.DAYS.toHours(
                        TimeUnit.MILLISECONDS.toDays(
                            millis
                        )
                    )),
                    (TimeUnit.MILLISECONDS.toMinutes(millis) -
                            TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis))),
                    (TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(
                        TimeUnit.MILLISECONDS.toMinutes(millis)
                    ))
                )

                System.out.println("Time : " + hms)
                resinTimer.setText(hms);//set text
            }

            override fun onFinish() {
                /*clearing all fields and displaying countdown finished message          */
                resinTimer.setText("RESIN");
                System.out.println("Available")
                if (setNotification == true) {
                    sendNotification()
                }
            }
        }
        countDownTimer.start()
    }

    private fun createNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name = "Notification Title"
            val descriptionText = "Notification Description"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID,name,importance).apply {
            description = descriptionText
             }
            val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun sendNotification(){
        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Resin Timer")
            .setContentText("You are able to get more Resin!!")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(this)){
            notify(notificationId, builder.build())
        }
    }

}
