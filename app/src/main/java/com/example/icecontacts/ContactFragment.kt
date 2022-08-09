package com.example.icecontacts

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.icecontacts.databinding.ActivityMainBinding
import com.example.icecontacts.databinding.FragmentContactBinding

class ContactFragment : Fragment() {

    companion object{
        val NAME = "name"
        val SURNAME = "surname"
    }
    private var _binding: FragmentContactBinding? = null

    private val binding get() = _binding!!

    private lateinit var nameId: String
    private lateinit var surnameId: String



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            nameId = it.getString(NAME).toString()
            surnameId = it.getString(SURNAME).toString()
            Log.i("Text","${nameId}")
            Log.i("Text","${surnameId}")


        }

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentContactBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvName.text = "Name: " + nameId
        binding.tvSurname.text = "Surname: " + surnameId
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}