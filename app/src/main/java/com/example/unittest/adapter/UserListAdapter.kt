package com.example.unittest.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.unittest.databinding.UserListBinding
import com.example.unittest.modals.LogInData

/**
 * Created by SARATH on 30-03-2021
 */


class UserListAdapter(private val context: Context) : RecyclerView.Adapter<UserListAdapter.UserListViewHolder>() {

    inner class UserListViewHolder(val binding : UserListBinding) : RecyclerView.ViewHolder(binding.root)


    private val diffCallback = object : DiffUtil.ItemCallback<LogInData>(){
        override fun areItemsTheSame(oldItem: LogInData, newItem: LogInData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: LogInData, newItem: LogInData): Boolean {
            return oldItem == newItem
        }

    }


    private val differ = AsyncListDiffer(this,diffCallback)
    var userListAdapter : MutableList<LogInData>

        get()=differ.currentList
        set(value) {differ.submitList(value)}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListAdapter.UserListViewHolder {
        return UserListViewHolder(UserListBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
        ))
    }

    override fun onBindViewHolder(holder: UserListAdapter.UserListViewHolder, position: Int) {
        val userData  = userListAdapter[position]

        holder.binding.apply {
            tvUserEmail.text = userData.userName
            tvUserPass.text = userData.password
        }

    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


}