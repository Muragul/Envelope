package com.example.envelope.utils.binding

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

abstract class BindingAdapter<M: Any, V: ViewBinding>(
    private val inflate: (inflater: LayoutInflater, parent: ViewGroup?, attachToParent: Boolean) -> V
) : RecyclerView.Adapter<BindingAdapter.ViewHolder<M, V>>() {

    private var items: List<M> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder<M, V> {
        val view = inflate.invoke(LayoutInflater.from(parent.context), parent, false)
        setViewParams(parent, view)
        return ViewHolder(view, ::bind)
    }

    open fun setViewParams(parent: ViewGroup, view: V) {}

    override fun onBindViewHolder(viewHolder: ViewHolder<M, V>, position: Int) {
        viewHolder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(list: List<M>?) {
        items = list ?: listOf()
        notifyDataSetChanged()
    }

    open fun bind(item: M, binding: V) {}

    class ViewHolder<M: Any, V: ViewBinding>(
        private val binding: V,
        private val bind: (item: M, binding: V) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: M) {
            bind.invoke(item, binding)
        }
    }
}