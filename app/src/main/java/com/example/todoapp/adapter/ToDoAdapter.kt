package com.example.todoapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapp.data.ToDo
import com.example.todoapp.databinding.ItemTodoBinding
import com.example.todoapp.ui.fragments.AnasayfaFragmentDirections

class ToDoAdapter : androidx.recyclerview.widget.ListAdapter<ToDo, ToDoAdapter.ToDoViewHolder>(DiffCallback()) {

    inner class ToDoViewHolder(val binding: ItemTodoBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val binding = ItemTodoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ToDoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        val toDo = getItem(position)

        holder.binding.textViewToDo.text = toDo.name

        // Tıklanırsa detay sayfasına git (bir sonraki adımda işlenecek)
        holder.binding.root.setOnClickListener {
            val action = AnasayfaFragmentDirections.actionAnasayfaFragmentToDetayFragment(toDo)
            it.findNavController().navigate(action)
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<ToDo>() {
        override fun areItemsTheSame(oldItem: ToDo, newItem: ToDo): Boolean = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: ToDo, newItem: ToDo): Boolean = oldItem == newItem
    }
}
