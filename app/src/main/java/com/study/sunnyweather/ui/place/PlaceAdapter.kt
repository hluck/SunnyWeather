package com.study.sunnyweather.ui.place

import android.content.Context
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import com.study.sunnyweather.R
import com.study.sunnyweather.databinding.SimpleItemBinding

/**
 *
 * @Author： LJH
 * @Time： 2023/7/5
 * @description：
 */
class PlaceAdapter(context: Context,private val resId:Int,private val dataList:List<String>):ArrayAdapter<String>(context,resId,dataList) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val itemBinding:SimpleItemBinding?
        val view = if (convertView == null){
            val v = LayoutInflater.from(context).inflate(R.layout.simple_item,parent,false)
            itemBinding = DataBindingUtil.bind(v)
            v.tag = itemBinding
            v
        } else {
            itemBinding = convertView.tag as SimpleItemBinding
            convertView
        }
        itemBinding?.data = dataList[position]

        return view
    }
}