package com.almax.dagger2todaggerhilt.ui.coin

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.almax.dagger2todaggerhilt.R
import com.almax.dagger2todaggerhilt.data.model.CoinResponse
import com.almax.dagger2todaggerhilt.databinding.ItemCoinBinding

class MainAdapter(
    private val coins: ArrayList<CoinResponse>
) : RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    inner class MainViewHolder(private val binding: ItemCoinBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(coin: CoinResponse) {
            binding.apply {
                coin.apply {
                    val coinTitle = "$name : $symbol"
                    tvName.text = coinTitle
                    val rank = "Rank: $rank Active: $isActive"
                    tvRank.text = rank
                    if (isActive) {
                        cvCoin.setBackgroundDrawable(
                            ContextCompat.getDrawable(
                                itemView.context,
                                R.drawable.card_border_active
                            )
                        )
                    } else {
                        Log.d("xamla", "onBind: $name")
                        cvCoin.setBackgroundDrawable(
                            ContextCompat.getDrawable(
                                itemView.context,
                                R.drawable.card_border_inactive
                            )
                        )
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val holder = ItemCoinBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(holder)
    }

    override fun getItemCount(): Int =
        coins.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.onBind(coins[position])
    }

    fun setData(coins: List<CoinResponse>) {
        this.coins.clear()
        this.coins.addAll(coins)
        notifyDataSetChanged()
    }
}