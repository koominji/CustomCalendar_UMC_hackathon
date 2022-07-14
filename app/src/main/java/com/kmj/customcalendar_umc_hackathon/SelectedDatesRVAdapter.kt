package com.kmj.customcalendar_umc_hackathon

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.applikeysolutions.cosmocalendar.model.Day
import com.kmj.customcalendar_umc_hackathon.databinding.CreateSelectedDateRvItemBinding
import java.util.*
import java.util.Calendar.*

class SelectedDatesRVAdapter(val dates:List<Day>) : RecyclerView.Adapter<SelectedDatesRVAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: CreateSelectedDateRvItemBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(dates: Day){

            val year = dates.calendar.get(YEAR)
            val month = dates.calendar.get(MONTH)+1
            val day = dates.calendar.get(DAY_OF_MONTH)
            val dayOfWeek = dates.calendar.getDisplayName(DAY_OF_WEEK, LONG_FORMAT, Locale.KOREAN)

            binding.selectedDateMonth.text= month.toString()+"월" //월
            binding.selectedDateDay.text= day.toString() //일
            binding.selectedDateDayOfWeek.text= dayOfWeek //요일
        }
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val binding:CreateSelectedDateRvItemBinding= CreateSelectedDateRvItemBinding.inflate(LayoutInflater.from(viewGroup.context),viewGroup,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val binding = (holder as SelectedDatesRVAdapter.ViewHolder).binding
        holder.bind(dates[position])
        binding.selectedDateRvRoot.setOnClickListener {
            Log.d("clicked","${position}")
        }
    }

    override fun getItemCount(): Int = dates.size
}