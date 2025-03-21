package com.almax.dagger2todaggerhilt.presentation.gif

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.almax.dagger2todaggerhilt.GifApplication
import com.almax.dagger2todaggerhilt.R
import com.almax.dagger2todaggerhilt.databinding.ActivityGifBinding
import com.almax.dagger2todaggerhilt.di.component.DaggerGifComponent
import com.almax.dagger2todaggerhilt.di.component.GifComponent
import com.almax.dagger2todaggerhilt.di.module.GifModule
import com.almax.dagger2todaggerhilt.presentation.base.UiState
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import javax.inject.Inject

class GifActivity : AppCompatActivity() {

    private lateinit var component: GifComponent

    private lateinit var binding: ActivityGifBinding

    @Inject
    lateinit var viewModel: GifViewModel

    @Inject
    lateinit var adapter: GifAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        injectDependencies()
        super.onCreate(savedInstanceState)
        binding = ActivityGifBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupUi()
    }

    private fun setupUi() {
        binding.apply {
            rvGif.apply {
                layoutManager = LinearLayoutManager(this@GifActivity)
                setHasFixedSize(true)
                adapter = this@GifActivity.adapter
                addItemDecoration(
                    DividerItemDecoration(
                        this@GifActivity,
                        (layoutManager as LinearLayoutManager).orientation
                    )
                )
            }
        }
        observeDataAndUpdateUi()
    }

    private fun observeDataAndUpdateUi() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    when (it) {
                        is UiState.Success -> {
                            updateProgressBarVisibility(false)
                            adapter.setData(it.data)
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
    }

    private fun updateProgressBarVisibility(isVisible: Boolean) {
        binding.pbGif.visibility = if (isVisible) View.VISIBLE else View.GONE
    }

    private fun injectDependencies() {
        component = DaggerGifComponent
            .builder()
            .applicationComponent((application as GifApplication).component)
            .gifModule(GifModule(this@GifActivity))
            .build()
        component.inject(this@GifActivity)
    }
}