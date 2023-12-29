package com.example.suitmediatest.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.suitmediatest.databinding.ItemUserBinding
import com.example.suitmediatest.model.User
import com.squareup.picasso.Picasso

class UserAdapter(
    private val context: Context?,
    private val list: MutableList<User>,
    private val onItemClick: (item: User) -> Unit
) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    class ViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder) {
            with(list[position]) {
                binding.tvUserName.text = "${this.first_name} " + "${this.last_name}"
                binding.tvUserEmail.text = this.email
                Picasso.get().load(this.avatar).into(binding.imageView)

                binding.root.setOnClickListener {
                    onItemClick.invoke(list[position])
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setData(data: List<User>) {
        list.addAll(data)
        notifyDataSetChanged()
    }

}