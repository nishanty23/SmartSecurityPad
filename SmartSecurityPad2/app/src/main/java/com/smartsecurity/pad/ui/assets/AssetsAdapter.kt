package com.smartsecurity.pad.ui.assets

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.smartsecurity.pad.R
import com.smartsecurity.pad.data.model.AssetItem
import com.smartsecurity.pad.databinding.ItemAssetBinding

class AssetsAdapter(
    private val items: List<AssetItem>,
    private val onClick: (AssetItem) -> Unit
) : RecyclerView.Adapter<AssetsAdapter.AssetViewHolder>() {

    inner class AssetViewHolder(val binding: ItemAssetBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssetViewHolder {
        val binding = ItemAssetBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AssetViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AssetViewHolder, position: Int) {
        val item = items[position]
        holder.binding.ivAsset.setImageResource(item.imageRes)
        holder.binding.tvAssetName.text = item.name
        holder.binding.tvAssetTime.text = item.lastActivity
        holder.binding.root.setOnClickListener { onClick(item) }

        if (item.status == "SAFE") {
            holder.binding.tvAssetStatus.text = "Safe"
            holder.binding.tvAssetStatus.setTextColor(holder.itemView.context.getColor(R.color.safe_green))
            holder.binding.ivStatusDot.setImageResource(R.drawable.ic_safe_small)
        } else {
            holder.binding.tvAssetStatus.text = "Alert"
            holder.binding.tvAssetStatus.setTextColor(holder.itemView.context.getColor(R.color.alert_red))
            holder.binding.ivStatusDot.setImageResource(R.drawable.ic_alert_small_red)
        }
    }

    override fun getItemCount(): Int = items.size
}