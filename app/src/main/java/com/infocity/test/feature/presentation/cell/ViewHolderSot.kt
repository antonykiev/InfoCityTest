package com.infocity.test.feature.presentation.cell

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.infocity.test.databinding.ItemSotBinding
import com.infocity.test.feature.data.store.ServiceObjectType

class ViewHolderSot(
    private val list: List<ServiceObjectType>,
    private val onClickListener: (ServiceObjectType) -> Unit
): RecyclerView.Adapter<ViewHolderSot.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinding = ItemSotBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(viewBinding, onClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(list[position])

    override fun getItemCount(): Int = list.size

    class ViewHolder(
        private val binding: ItemSotBinding,
        private val onClickListener: (ServiceObjectType) -> Unit
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(data: ServiceObjectType) {
            binding.tvName.text = data.name

            binding.vCover.setOnClickListener {
                onClickListener(data)
            }

            if (data.hasChildren)
                binding.imageView.visibility = View.VISIBLE
            else
                binding.imageView.visibility = View.INVISIBLE

        }



    }
}