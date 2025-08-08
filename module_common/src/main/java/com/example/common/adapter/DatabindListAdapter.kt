package com.example.common.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.common.model.BindListData
import com.example.module_utils.R
import com.example.module_utils.databinding.ItemDataBindListBinding

class DatabindListAdapter(var dataList: ArrayList<BindListData>) :
    RecyclerView.Adapter<DatabindListAdapter.DatabindListViewHolder>() {

    class DatabindListViewHolder(viewDataBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(viewDataBinding.root) {
        val dataBing: ViewDataBinding?

        init {
            this.dataBing = viewDataBinding
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DatabindListViewHolder {
        val binding = DataBindingUtil.inflate<ItemDataBindListBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_data_bind_list,
            parent,
            false
        )
        return DatabindListViewHolder(
            binding
        )
    }

    override fun onBindViewHolder(holder: DatabindListViewHolder, position: Int) {
        val data = dataList[position]
        val bing = holder.dataBing as ItemDataBindListBinding
        bing.bindDataInfo=data
        bing.executePendingBindings()
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}