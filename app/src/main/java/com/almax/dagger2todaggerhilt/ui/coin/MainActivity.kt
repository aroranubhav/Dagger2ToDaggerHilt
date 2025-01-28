package com.almax.dagger2todaggerhilt.ui.coin

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.almax.dagger2todaggerhilt.CoinApplication
import com.almax.dagger2todaggerhilt.databinding.ActivityMainBinding
import com.almax.dagger2todaggerhilt.di.component.CoinComponent
import com.almax.dagger2todaggerhilt.di.component.DaggerCoinComponent
import com.almax.dagger2todaggerhilt.di.module.CoinModule
import com.almax.dagger2todaggerhilt.ui.base.UiState
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var component: CoinComponent

    @Inject
    lateinit var viewModel: MainViewModel

    @Inject
    lateinit var adapter: MainAdapter

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpUi()
        observeDataAndUpdateUi()
    }

    private fun setUpUi() {
        binding.apply {
            rvCoins.apply {
                layoutManager = LinearLayoutManager(this@MainActivity)
                setHasFixedSize(true)
                adapter = this@MainActivity.adapter
            }
        }
    }

    private fun observeDataAndUpdateUi() {
        lifecycleScope.launch {
            viewModel.uiState.collect {
                when (it) {
                    is UiState.Success -> {
                        updateProgressBarVisibility(false)
                        adapter.setData(it.result)
                    }

                    is UiState.Error -> {
                        updateProgressBarVisibility(false)
                        Snackbar.make(
                            binding.root,
                            it.error,
                            Snackbar.LENGTH_SHORT
                        ).show()
                    }

                    is UiState.Loading -> {
                        updateProgressBarVisibility(true)
                    }
                }
            }
        }
    }

    private fun updateProgressBarVisibility(isVisible: Boolean) {
        binding.pbCoins.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    private fun injectDependencies() {
        component = DaggerCoinComponent
            .builder()
            .applicationComponent((application as CoinApplication).component)
            .coinModule(CoinModule(this@MainActivity))
            .build()
        component.inject(this@MainActivity)
    }
}