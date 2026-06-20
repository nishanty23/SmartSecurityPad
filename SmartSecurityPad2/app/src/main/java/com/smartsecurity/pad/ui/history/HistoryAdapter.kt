package com.smartsecurity.pad.ui.history



class HistoryAdapter(private val items: List<HistoryItem>) : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    inner class HistoryViewHolder(val binding: ItemHistoryBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val binding = ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val item = items[position]
        holder.binding.tvTitle.text = item.title
        holder.binding.tvTime.text = item.time
        holder.binding.tvSubtitle.text = item.subtitle

        if (item.status == "SAFE") {
            holder.binding.ivIcon.setImageResource(R.drawable.ic_safe)
            holder.binding.tvTitle.setTextColor(holder.itemView.context.getColor(R.color.colorSafe))
        } else {
            holder.binding.ivIcon.setImageResource(R.drawable.ic_warning)
            holder.binding.tvTitle.setTextColor(holder.itemView.context.getColor(R.color.colorAlert))
        }
    }

    override fun getItemCount(): Int = items.size
}
