package com.study.sunnyweather.ui.place

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModelProviders
import com.study.sunnyweather.App
import com.study.sunnyweather.R
import com.study.sunnyweather.data.PlaceRepository
import com.study.sunnyweather.data.model.place.City
import com.study.sunnyweather.data.model.place.County
import com.study.sunnyweather.data.model.place.Province
import com.study.sunnyweather.databinding.FragmentChooseAreaBinding
import com.study.sunnyweather.ui.MainActivity
import com.study.sunnyweather.ui.weather.WeatherActivity
import com.study.sunnyweather.ui.weather.WeatherViewModel
import com.study.sunnyweather.util.InjectorUtil

class ChooseAreaFragment : Fragment() {

    private val placeViewModel:PlaceViewModel by lazy {
        ViewModelProviders.of(this,InjectorUtil.getChooseAreaModelFactory()).get(PlaceViewModel::class.java)
    }
    private lateinit var dialogBuilder: AlertDialog.Builder
    private var dialog: AlertDialog? = null
    private lateinit var adapter:ArrayAdapter<String>
    private var binding: FragmentChooseAreaBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dialogBuilder = AlertDialog.Builder(this.context).apply {
            setCancelable(false)
            setView(R.layout.loading_layout)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_choose_area, container, false)
        binding = DataBindingUtil.bind(view)
        binding?.lifecycleOwner = this
        binding?.viewModel = placeViewModel
        return binding?.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapter = PlaceAdapter(requireContext(),R.layout.simple_item,placeViewModel.dataList)
        binding?.listView?.adapter = adapter
        observe()
    }

    private fun observe(){
        placeViewModel.currentLevel.observe(viewLifecycleOwner){ level ->
            when(level){
                LEVEL_PROVINCE -> {
                    binding?.let {
                        it.titleText.text = "中国"
                        it.btnBack.visibility = View.GONE
                    }
                }
                LEVEL_CITY -> {
                    binding?.let {
                        it.titleText.text = placeViewModel.selectedProvince?.provinceName
                        it.btnBack.visibility = View.VISIBLE
                    }
                }
                LEVEL_COUNTY -> {
                    binding?.let {
                        it.titleText.text = placeViewModel.selectedCity?.cityName
                        it.btnBack.visibility = View.VISIBLE
                    }
                }
            }
        }

        placeViewModel.dataChanged.observe(viewLifecycleOwner){
            adapter.notifyDataSetChanged()
            binding?.listView?.setSelection(0)
            dialog?.dismiss()
        }
        placeViewModel.isLoading.observe(viewLifecycleOwner){ isLoading ->
            if(isLoading) {
                dialog = dialogBuilder.show()
            } else {
                dialog?.dismiss()
            }
        }

        placeViewModel.areaSelected.observe(viewLifecycleOwner){
            if (it && placeViewModel.selectedCounty != null){
                if (activity is MainActivity){
                    val intent = Intent(activity,WeatherActivity::class.java)
                    intent.putExtra("weather_id",placeViewModel.selectedCounty!!.weatherId)
                    startActivity(intent)
                    activity?.finish()
                } else if (activity is WeatherActivity){
                    val weatherActivity = activity as WeatherActivity
                    weatherActivity.binding.drawerLayout.closeDrawers()
                    val weatherViewModel = weatherActivity.binding.viewModel as WeatherViewModel
                    weatherViewModel.weatherId = placeViewModel.selectedCounty!!.weatherId
                    weatherViewModel.refreshWeather()
                }
                placeViewModel._areaSelected.value = false
            }
        }

        if (placeViewModel.dataList.isEmpty()){
            placeViewModel.getProvinces()
        }
    }

    companion object{
        const val LEVEL_PROVINCE = 0
        const val LEVEL_CITY = 1
        const val LEVEL_COUNTY = 2
    }

}