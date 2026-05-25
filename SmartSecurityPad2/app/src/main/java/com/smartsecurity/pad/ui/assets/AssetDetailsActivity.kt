package com.smartsecurity.pad.ui.assets

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.smartsecurity.pad.R
import com.smartsecurity.pad.data.model.HistoryItem
import com.smartsecurity.pad.databinding.ActivityAssetDetailsBinding
import com.smartsecurity.pad.ui.add.AddAssetActivity
import com.smartsecurity.pad.ui.dashboard.MainActivity
import com.smartsecurity.pad.ui.history.HistoryAdapter
import com.smartsecurity.pad.ui.profile.ProfileActivity

class AssetDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAssetDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAssetDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val assetName = intent.getStringExtra("asset_name") ?: "Laptop"
        val assetStatus = intent.getStringExtra("asset_status") ?: "ALERT"
        val assetImage = intent.getIntExtra("asset_image", R.drawable.img_laptop)

        binding.tvAssetName.text = assetName
        binding.ivAsset.setImageResource(assetImage)

        if (assetStatus == "SAFE") {
            binding.tvLiveTitle.text = "Safe"
            binding.tvLiveTitle.setTextColor(getColor(R.color.safe_green))
            binding.ivLiveIcon.setImageResource(R.drawable.ic_safe_small)
            binding.tvLiveSub.text = "Object is securely placed on the pad"
            binding.tvTopCardTitle.text = "Safe"
            binding.tvTopCardTitle.setTextColor(getColor(R.color.safe_green))
            binding.tvTopCardTime.text = "Detected at 10:45 AM"
            binding.tvTopCardSub.text = "Exact item could not be determined due to similar weight profile"
        } else {
            binding.tvLiveTitle.text = "Movement Detected"
            binding.tvLiveTitle.setTextColor(getColor(R.color.warning_yellow))
            binding.ivLiveIcon.setImageResource(R.drawable.ic_alert_circle)
            binding.tvLiveSub.text = "Exact item could not be determined due to similar weight profile."
            binding.tvTopCardTitle.text = "Movement Detected"
            binding.tvTopCardTitle.setTextColor(getColor(R.color.warning_yellow))
            binding.tvTopCardTime.text = "Detected at 10:45 AM"
            binding.tvTopCardSub.text = "Exact item could not be determined due to similar weight profile"
        }

        val history = listOf(
            HistoryItem("Movement Detected", "10:45 AM", "", "ALERT"),
            HistoryItem("Safe", "09:10 AM", "", "SAFE"),
            HistoryItem("Safe", "07:25 AM", "", "SAFE")
        )

        binding.rvHistory.layoutManager = LinearLayoutManager(this)
        binding.rvHistory.adapter = HistoryAdapter(history)

        binding.topBar.ivBack.setOnClickListener { finish() }
        binding.bottomNav.navHome.setOnClickListener { startActivity(Intent(this, MainActivity::class.java)) }
        binding.bottomNav.navAssets.setOnClickListener { startActivity(Intent(this, AssetsActivity::class.java)) }
        binding.bottomNav.navAdd.setOnClickListener { startActivity(Intent(this, AddAssetActivity::class.java)) }
        binding.bottomNav.navProfile.setOnClickListener { startActivity(Intent(this, ProfileActivity::class.java)) }
    }
}