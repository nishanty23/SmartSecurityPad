package com.smartsecurity.pad.ui.history

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.smartsecurity.pad.R
import com.smartsecurity.pad.data.model.HistoryItem
import com.smartsecurity.pad.databinding.ActivityHistoryBinding
import com.smartsecurity.pad.ui.add.AddAssetActivity
import com.smartsecurity.pad.ui.assets.AssetsActivity
import com.smartsecurity.pad.ui.dashboard.MainActivity
import com.smartsecurity.pad.ui.profile.ProfileActivity

class HistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val items = listOf(
            HistoryItem("Movement Detected", "10:45 AM", "Exact item could not be determined due to similar weight profile.", "ALERT"),
            HistoryItem("Safe", "09:10 AM", "Object securely placed on pad.", "SAFE"),
            HistoryItem("Safe", "07:25 AM", "Object securely placed on pad.", "SAFE")
        )

        binding.rvHistory.layoutManager = LinearLayoutManager(this)
        binding.rvHistory.adapter = HistoryAdapter(items)

        binding.topBar.ivBack.setOnClickListener { finish() }
        binding.bottomNav.navHome.setOnClickListener { startActivity(Intent(this, MainActivity::class.java)) }
        binding.bottomNav.navAssets.setOnClickListener { startActivity(Intent(this, AssetsActivity::class.java)) }
        binding.bottomNav.navAdd.setOnClickListener { startActivity(Intent(this, AddAssetActivity::class.java)) }
        binding.bottomNav.navProfile.setOnClickListener { startActivity(Intent(this, ProfileActivity::class.java)) }
    }
}
