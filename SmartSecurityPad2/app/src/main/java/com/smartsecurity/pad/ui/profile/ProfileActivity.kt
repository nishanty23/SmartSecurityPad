package com.smartsecurity.pad.ui.profile

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.smartsecurity.pad.databinding.ActivityProfileBinding
import com.smartsecurity.pad.data.repository.FirebaseRepository
import com.smartsecurity.pad.ui.add.AddAssetActivity
import com.smartsecurity.pad.ui.assets.AssetsActivity
import com.smartsecurity.pad.ui.auth.LoginActivity
import com.smartsecurity.pad.ui.dashboard.MainActivity

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private val repository = FirebaseRepository()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvName.text = repository.currentUserName()
        binding.tvEmail.text = repository.currentUserEmail()

        binding.cardSettings.setOnClickListener {
            // add settings screen later
        }

        binding.cardLogout.setOnClickListener {
            repository.logout()
            startActivity(Intent(this, LoginActivity::class.java))
            finishAffinity()
        }

        binding.topBar.ivBack.setOnClickListener { finish() }
        binding.bottomNav.navHome.setOnClickListener { startActivity(Intent(this, MainActivity::class.java)) }
        binding.bottomNav.navAssets.setOnClickListener { startActivity(Intent(this, AssetsActivity::class.java)) }
        binding.bottomNav.navAdd.setOnClickListener { startActivity(Intent(this, AddAssetActivity::class.java)) }
        binding.bottomNav.navProfile.setOnClickListener { }
    }
}
