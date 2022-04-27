package com.example.firsttest.view.details

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.firsttest.R
import com.example.firsttest.databinding.ActivityDetailsBinding
import com.example.firsttest.presenter.details.DetailsPresenter
import com.example.firsttest.presenter.details.PresenterDetailsContract
import java.util.*

class DetailsActivity : AppCompatActivity(), ViewDetailsContract {
    private lateinit var binding:ActivityDetailsBinding
    private val presenter: PresenterDetailsContract = DetailsPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setUI()
    }

    private fun setUI() {
        val count = intent.getIntExtra(TOTAL_COUNT_EXTRA, 0)
        presenter.setCounter(count)
        setCountText(count)
        binding.decrementButton.setOnClickListener { presenter.onDecrement() }
        binding.incrementButton.setOnClickListener { presenter.onIncrement() }
    }

    override fun setCount(count: Int) {
        setCountText(count)
    }

    private fun setCountText(count: Int) {
        binding.totalCountTextView.text =
            String.format(Locale.getDefault(), getString(R.string.results_count), count)
    }

    companion object {

        const val TOTAL_COUNT_EXTRA = "TOTAL_COUNT_EXTRA"

        fun getIntent(context: Context, totalCount: Int): Intent {
            return Intent(context, DetailsActivity::class.java).apply {
                putExtra(TOTAL_COUNT_EXTRA, totalCount)
            }
        }
    }
}