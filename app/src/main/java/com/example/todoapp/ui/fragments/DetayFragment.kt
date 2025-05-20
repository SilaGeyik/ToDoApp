package com.example.todoapp.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.todoapp.data.ToDo
import com.example.todoapp.databinding.FragmentDetayBinding
import com.example.todoapp.ui.viewmodel.ToDoViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetayFragment : Fragment() {

    private var _binding: FragmentDetayBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ToDoViewModel by viewModels()
    private lateinit var todo: ToDo

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Safe Args ile ToDo nesnesini al
        arguments?.let {
            todo = DetayFragmentArgs.fromBundle(it).todo
            binding.editTextToDoDetail.setText(todo.name)
        }

        binding.buttonUpdate.setOnClickListener {
            val updatedText = binding.editTextToDoDetail.text.toString().trim()
            if (updatedText.isNotEmpty()) {
                val updatedToDo = todo.copy(name = updatedText)
                viewModel.update(updatedToDo)
                findNavController().popBackStack()
            } else {
                Toast.makeText(requireContext(), "Boş bırakmayın", Toast.LENGTH_SHORT).show()
            }
        }

        binding.buttonDelete.setOnClickListener {
            viewModel.delete(todo)
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
