package com.smartsecurity.pad.ui.assets

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.smartsecurity.pad.R
import com.smartsecurity.pad.data.model.AssetItem
import com.smartsecurity.pad.databinding.ActivityAssetsBinding
import com.smartsecurity.pad.ui.add.AddAssetActivity
import com.smartsecurity.pad.ui.dashboard.MainActivity
import com.smartsecurity.pad.ui.profile.ProfileActivity

class AssetsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAssetsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAssetsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val assetList = listOf(
            AssetItem("Laptop", "SAFE", "Last active: 2 min ago", R.drawable.img_laptop),
            AssetItem("Backpack", "ALERT", "Last activity: Movement detected", R.drawable.img_backpack),
            AssetItem("Camera", "SAFE", "Last active: 10 min ago", R.drawable.img_camera)
        )

        binding.rvAssets.layoutManager = LinearLayoutManager(this)
        binding.rvAssets.adapter = AssetsAdapter(assetList) { item ->
            val intent = Intent(this, AssetDetailsActivity::class.java)
            intent.putExtra("asset_name", item.name)
            intent.putExtra("asset_status", item.status)
            intent.putExtra("asset_image", item.imageRes)
            startActivity(intent)
        }

        binding.topBar.ivBack.setOnClickListener { finish() }
        binding.bottomNav.navHome.setOnClickListener { startActivity(Intent(this, MainActivity::class.java)) }
        binding.bottomNav.navAssets.setOnClickListener { }
        binding.bottomNav.navAdd.setOnClickListener { startActivity(Intent(this, AddAssetActivity::class.java)) }
        binding.bottomNav.navProfile.setOnClickListener { startActivity(Intent(this, ProfileActivity::class.java)) }
    }
}