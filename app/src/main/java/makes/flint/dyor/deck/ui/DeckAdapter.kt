package makes.flint.dyor.deck.ui

import android.databinding.ObservableArrayList
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import makes.flint.dyor.databinding.DeckItemBinding
import makes.flint.dyor.deck.models.DeckItemViewModel

/**
 * DeckAdapter
 */

 class DeckAdapter(val entries: ObservableArrayList<DeckItemViewModel>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private class ViewHolder(val binding: DeckItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DeckItemViewModel) {
            binding.viewModel = item
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = DeckItemBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return entries.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = entries[position]
        (holder as ViewHolder).bind(item)
    }

}

class DeckBaseAdapter(val entries: ObservableArrayList<DeckItemViewModel>): BaseAdapter() {

    override fun getView(position: Int, convertView: View?, p2: ViewGroup?): View {
        val layoutInflater = LayoutInflater.from(p2?.context)
        val holder : ItemViewHolder
        if(convertView != null) {
            holder = convertView.tag as ItemViewHolder
        } else {
            val binding = DeckItemBinding.inflate(layoutInflater, p2, false)
            holder = ItemViewHolder(binding)
            holder.view().tag = holder
        }
        holder.binding.viewModel = entries[position]
        holder.binding.executePendingBindings()
        return holder.view()
    }

    override fun getItem(p0: Int): Any {
        return entries[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getCount(): Int {
        return entries.size
    }
}

private class ItemViewHolder(val binding: DeckItemBinding) {
    fun view() = binding.root
}