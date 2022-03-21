package com.futuremind.loyaltyrewards.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.futuremind.loyaltyrewards.R
import com.futuremind.loyaltyrewards.databinding.ActivityMainBinding
import com.futuremind.loyaltyrewards.view.dashboard.DashboardViewModel
import com.futuremind.loyaltyrewards.view.dashboard.adapter.DashboardAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: DashboardViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    private val dashboardAdapter by lazy { DashboardAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setBinding()
        setBackNavigation()
    }

    private fun setBinding() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        binding.viewmodel = viewModel
        binding.adapter = dashboardAdapter
    }

    private fun setBackNavigation() {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}
