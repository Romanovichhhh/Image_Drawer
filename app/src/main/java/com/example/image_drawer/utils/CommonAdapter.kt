package com.example.image_drawer.utils

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView


open class CommonAdapter<T : ItemVM>(
    private val createViewHolderDelegate: (parent: ViewGroup, viewType: Int) -> ViewHolder<T>
    ) : RecyclerView.Adapter<ViewHolder<T>>() {

        private val items = mutableListOf<T>()

        fun setItems(items: List<T>) {
            this.items.clear()
            this.items.addAll(items)

            notifyDataSetChanged()
        }

        fun getItems(): List<T> {
            return items
        }

        fun getItemByPos(pos: Int): T? {
            if (pos < 0 || pos > items.size - 1) {
                return null
            }

            return items[pos]
        }

        override fun getItemViewType(position: Int): Int {
            return items[position].type
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<T> {
            return createViewHolderDelegate(parent, viewType)
        }

        override fun onBindViewHolder(holder: ViewHolder<T>, position: Int) {
            holder.bind(position, items[position])
        }

        override fun getItemCount(): Int {
            return items.size
        }
}


interface ItemVM {
    val type: Int
}

class ViewHolder<T : ItemVM>(
    private val binding: ViewDataBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(position: Int, VM: ItemVM) {
        binding.setVariable(1, VM as T)
    }
}