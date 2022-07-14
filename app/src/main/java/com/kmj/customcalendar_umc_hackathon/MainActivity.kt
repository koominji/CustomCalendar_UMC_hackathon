package com.kmj.customcalendar_umc_hackathon

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.applikeysolutions.cosmocalendar.model.Day
import com.applikeysolutions.cosmocalendar.selection.OnDaySelectedListener
import com.applikeysolutions.cosmocalendar.selection.RangeSelectionManager
import com.kmj.customcalendar_umc_hackathon.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding:ActivityMainBinding
    lateinit var dates: List<Day>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 캘린더 화면으로 전환
        binding.createAddDateCv.setOnClickListener {
            Log.d("clicked", "clicked")
//            val intent = Intent(this,CalendarActivity::class.java)
//            startActivity(intent)
            binding.createContentCl.visibility = View.INVISIBLE
            binding.createCalendarCl.visibility = View.VISIBLE

        }

        // 캘린더에서 기간 설정 후 선택완료 버튼 클릭
        binding.calendarSelectCompleteBtn.setOnClickListener {
            binding.createContentCl.visibility = View.VISIBLE
            binding.createCalendarCl.visibility = View.INVISIBLE
            binding.createAddDateIv.visibility = View.INVISIBLE
            binding.createAddDateCv.setBackgroundColor(Color.TRANSPARENT)


            // 선택된 dates
            dates = binding.calendarView.selectedDays

            // 선택된 날짜 recyclerview
            val selectedDatesRVAdapter = SelectedDatesRVAdapter(dates)
            binding.createSelectedDateRv.adapter = selectedDatesRVAdapter

            val selectedDatesLayoutManager = LinearLayoutManager(this)
            selectedDatesLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
            binding.createSelectedDateRv.layoutManager = selectedDatesLayoutManager

        }

        binding.calendarView.isShowDaysOfWeekTitle = false
        binding.calendarView.selectionManager = RangeSelectionManager(OnDaySelectedListener {
            if (binding.calendarView.selectedDates.size <= 0) return@OnDaySelectedListener
            Log.d("selected dates", "${binding.calendarView.selectedDays}")
        })


    }
}