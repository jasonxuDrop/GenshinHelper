package com.team11.genshinhelper

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.*
import android.util.Log
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import java.util.concurrent.TimeUnit
import kotlin.math.roundToInt

class ResinTimerActivity : AppCompatActivity() {

    val TIMER_DURATION = 76800000.toLong() // exact millisecond for the timer to complete
    var startTime : Long = 0 // read ONLY / do NOT SET value directly
    var endTime : Long = 0 // read ONLY / do NOT SET value directly
    lateinit var countDownTimer : CountDownTimer
    var timerInitialized = false

    private val CHANNEL_ID = "channel_id_example_01"
    lateinit var resinAmount: TextView
    lateinit var notificationButton: ImageButton
    lateinit var progressBar: ProgressBar
    var setNotification = false
    var doSendNotification = true

    lateinit var notificationManager : NotificationManager
    lateinit var sp : SharedPreferences

    private val editResinCount = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == RESULT_OK) {
            val editedAmount = it.data?.getStringExtra("ResinFromEdit")
            if (editedAmount != null) {
                amountToStartTime(editedAmount)
                Log.d("get result from act", "$editedAmount currently, timer starts at $startTime")

                sp.edit()
                    .putLong("startTime", startTime)
                    .apply()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resin_timer)

        // user prefs
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        sp = getSharedPreferences("pref", Context.MODE_PRIVATE)

        // set the resin amount
        resinAmount = findViewById(R.id.resinAmount)
        progressBar = findViewById(R.id.progress_bar)

        findViewById<ImageButton>(R.id.EditButton).setOnClickListener{
            val intent = Intent(this, ResinTimerEditActivity::class.java)
            val currentAmount = resinAmount.text.toString()
            intent.putExtra("SOME_KEY2", currentAmount)
            editResinCount.launch(intent)
        }

        findViewById<LinearLayout>(R.id.SearchNav).setOnClickListener{
            val intent = Intent(this, ListActivityMain::class.java)
            startActivity(intent)
            if (timerInitialized)
                countDownTimer.cancel()
            finish()
        }

        notificationButton = findViewById(R.id.btn_button)
        updateNotification(sp.getBoolean("notification", true))
        notificationButton.setOnClickListener{
            updateNotification (doSendNotification)
            sp.edit()
                .putBoolean("notification", setNotification)
                .apply()
        }

        loadStartTime()
        createNotificationChannel()
    }



    fun countDown() {

        val timerDuration = endTime - System.currentTimeMillis()
        if (timerInitialized)
            countDownTimer.cancel()
        else
            timerInitialized = true

        countDownTimer = object : CountDownTimer(timerDuration, 1000) {
            var resinTimer = findViewById<TextView>(R.id.textView3)
            //resinAmount

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

                println("Time : $hms")
                resinTimer.text = hms

                val currentResinCount = (System.currentTimeMillis() - startTime)/(60000 * 8)
                println("currentResinCount: $currentResinCount")
                resinAmount.text = currentResinCount.toString()
                progressBar.progress = (currentResinCount / 1.60f).roundToInt()
            }

            override fun onFinish() {
                resinTimer.text = "FULL"
                resinAmount.text = "160"
                progressBar.progress = 100
                println("Available")
            }
        }
        countDownTimer.start()
    }



    private fun createNotificationChannel(){
        val name = "Notification Title"
        val descriptionText = "Notification Description"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(CHANNEL_ID,name,importance).apply {
            description = descriptionText
        }
        notificationManager.createNotificationChannel(channel)
    }

    private fun sendNotification(delay : Long){
        Handler(Looper.getMainLooper()).removeCallbacksAndMessages(null)

        val notification = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle("Resin Timer")
            .setContentText("You are able to get more Resin!!")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        Handler(Looper.getMainLooper()).postDelayed({
            notificationManager.notify(1, notification)
        }, delay)
    }

    private fun updateNotification(b:Boolean){
        if (b) {
            doSendNotification = false
            setNotification = true
            notificationButton.setImageResource(R.drawable.button_notification_on)
        }
        else {
            doSendNotification = true
            setNotification = false
            notificationButton.setImageResource(R.drawable.button_notification_off)
        }
    }



    private fun amountToStartTime(resinAmount: String?): Long {
        if (resinAmount.isNullOrEmpty())
            return 0

        val timeSince0: Long =
            (resinAmount.toInt() * 60000 * 8).toLong() // 60000ms / 1m * 8 minutes per resin
        val currentTime: Long = System.currentTimeMillis()
        updateTime(currentTime - timeSince0)

        val timeUntilFull = endTime - System.currentTimeMillis()
        if (setNotification && timeUntilFull > 0) {
            sendNotification(timeUntilFull)
        }
        return startTime
    }

    private fun loadStartTime(): Long {
        val startTimeFromPref = sp.getLong("startTime", 0)
        if (startTimeFromPref == 0.toLong()) {
            updateTime(System.currentTimeMillis() - TIMER_DURATION)
            resinAmount.text = "160"
        }
        else {
            updateTime(startTimeFromPref)
        }

        return startTime
    }

    private fun updateTime(_startTime: Long) {
        startTime = _startTime
        endTime = startTime + TIMER_DURATION
        countDown()
    }
}
