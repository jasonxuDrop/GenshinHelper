package com.team11.genshinhelper

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.icu.util.CurrencyAmount
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import java.time.temporal.Temporal
import java.util.concurrent.TimeUnit

class ResinTimerActivity : AppCompatActivity() {

    private val CHANNEL_ID = "channel_id_example_01"
    private val notificationId = 101


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resin_timer)

        val door = 3
        val resinAmmount = findViewById<TextView>(R.id.textView2)


        findViewById<ImageButton>(R.id.EditButton).setOnClickListener{
            val intent = Intent(this, ResinTimerEditActivity::class.java)
            startActivity(intent)
            saveText2()
        }

        findViewById<ImageButton>(R.id.SearchNav).setOnClickListener{
            val intent = Intent(this, ListActivityMain::class.java)
            startActivity(intent)
        }

        val textToDisplay = intent.getStringExtra("SOME_KEY")
        resinAmmount.text = textToDisplay

        createNotificationChannel()

        val btn_button = findViewById<ImageButton>(R.id.btn_button)
        btn_button.setOnClickListener{
            sendNotification()
        }

        countDown()

}

    fun saveText2(){
        var textView2 = findViewById<TextView>(R.id.textView2)
        var textToPass2 = textView2.text.toString()

        var intent = Intent(this, ResinTimerEditActivity::class.java)
        intent.putExtra("SOME_KEY2", textToPass2)
        startActivity(intent)
    }

    fun conversionForTimer(): Long {
        var resinAmmount = findViewById<TextView>(R.id.textView2)
        var converted = Integer.parseInt(resinAmmount.getText().toString());

        val convertedNum = ((160 - converted) * 8) * 60000

        return convertedNum.toLong()
    }

   fun countDown() {

       val countDownTimer = object : CountDownTimer(65000000.toLong(), 1000) {
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
                resinTimer.setText("Count down completed");
                System.out.println("Time up")
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
