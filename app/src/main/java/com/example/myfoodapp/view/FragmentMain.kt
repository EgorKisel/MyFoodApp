package com.example.myfoodapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.myfoodapp.KEY_BUNDLE
import com.example.myfoodapp.R
import com.example.myfoodapp.databinding.FragmentMainBinding
import com.example.myfoodapp.viewmodel.AppState
import com.example.myfoodapp.viewmodel.MainViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class FragmentMain : Fragment(), OnItemClickListener {

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding get() = _binding!!
    private val simpleDateFormat = SimpleDateFormat("dd MMMM, yyyy", Locale("ru"))
    private val formattedDate = simpleDateFormat.format(Date())
    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this)[MainViewModel::class.java]
    }
    private val adapter = AdapterMain()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val observer = Observer<AppState> { renderData(it) }
        binding.recyclerMain.adapter = adapter
        adapter.mSetOnClickListener(this)
        binding.tvData.text = formattedDate
        viewModel.apply {
            getLiveData().observe(viewLifecycleOwner, observer)
            getCategories()
        }
    }

    private fun renderData(state: AppState) {
        when (state) {
            is AppState.Error -> {}
            AppState.Loading -> {}
            is AppState.Success -> {
                adapter.setData(state.categoryDTO.categories)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = FragmentMain()
    }

    override fun onItemClick(id: Int) {
        val bundle = Bundle()
        bundle.putInt(KEY_BUNDLE, id)
        requireActivity().supportFragmentManager.beginTransaction()
            .add(R.id.fragmentContainerMain, FragmentMenuList.newInstance(bundle))
            .addToBackStack("").commit()
    }
}