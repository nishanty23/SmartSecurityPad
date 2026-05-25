package com.smartsecurity.pad.ui.dashboard

import android.content.Intent
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.Toast

import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

import com.smartsecurity.pad.R
import com.smartsecurity.pad.databinding.ActivityMainBinding
import com.smartsecurity.pad.ui.add.AddAssetActivity
import com.smartsecurity.pad.ui.assets.AssetsActivity
import com.smartsecurity.pad.ui.auth.LoginActivity
import com.smartsecurity.pad.ui.history.HistoryActivity
import com.smartsecurity.pad.ui.profile.ProfileActivity

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: DashboardViewModel by viewModels()
    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupClicks()
        observeData()
        viewModel.startListening()
    }

    private fun setupClicks() {
        binding.btnHistory.setOnClickListener {
            startActivity(Intent(this, HistoryActivity::class.java))
        }

        binding.btnLogout.setOnClickListener {
            viewModel.logout()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        binding.bottomNav.navHome.setOnClickListener { }

        binding.bottomNav.navAssets.setOnClickListener {
            startActivity(Intent(this, AssetsActivity::class.java))
        }

        binding.bottomNav.navAdd.setOnClickListener {
            startActivity(Intent(this, AddAssetActivity::class.java))
        }

        binding.bottomNav.navProfile.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }
    }

    private fun observeData() {
        viewModel.deviceData.observe(this) { data ->
            binding.tvWeightValue.text = "${data.weight}"
            binding.tvStatusValue.text = data.status
            binding.tvLastUpdatedValue.text = formatTime(data.lastUpdated)

            if (data.status == "SAFE") {
                binding.statusContainer.setBackgroundResource(R.drawable.bg_glass_card)
                binding.ivStatus.setImageResource(R.drawable.img_safe_shield)
                binding.tvStatusBig.text = "Safe"
                binding.tvHeadline.text = "System Status"
                binding.tvSubHeadline.text = "All assets are secure"
            } else {
                binding.statusContainer.setBackgroundResource(R.drawable.bg_glass_card)
                binding.ivStatus.setImageResource(R.drawable.ic_alert_circle)
                binding.tvStatusBig.text = "Alert"
                binding.tvHeadline.text = "System Status"
                binding.tvSubHeadline.text = "Movement detected on one of your assets"
            }
        }
        viewModel.error.observe(this) {
            Toast.makeText(this, it, Toast.LENGTH_SHORT).show()
        }

        viewModel.showAlertEvent.observe(this) { event ->
            event.getContentIfNotHandled()?.let {
                showAlertDialog()
                vibratePhone()
                playAlertSound()
            }
        }
    }
    private fun showAlertDialog() {
        AlertDialog.Builder(this)
            .setTitle("Alert")
            .setMessage("Unauthorized item movement detected!")
            .setPositiveButton("OK", null)
            .show()
    }

    private fun vibratePhone() {
        val vibrator = getSystemService(VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(700, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            @Suppress("DEPRECATION")
            vibrator.vibrate(700)
        }
    }

    private fun playAlertSound() {
        mediaPlayer?.release()
        mediaPlayer = MediaPlayer.create(this, R.raw.alert_tone)
        mediaPlayer?.start()
    }

    private fun formatTime(timestamp: Long): String {
        if (timestamp == 0L) return "--"
        val sdf = SimpleDateFormat("dd MMM yyyy, hh:mm:ss a", Locale.getDefault())
        return sdf.format(Date(timestamp))
    }

    override fun onDestroy() {
        mediaPlayer?.release()
        mediaPlayer = null
        super.onDestroy()
    }
}