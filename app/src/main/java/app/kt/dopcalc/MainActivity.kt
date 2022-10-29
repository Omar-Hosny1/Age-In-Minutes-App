package app.kt.dopcalc

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModel
import app.kt.dopcalc.R.id.tvSelectedDate
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private var tvSelectedDate: TextView? = null
    private var tvAgeInMinutes: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePrimary: Button = findViewById(R.id.btnDataPicker);
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvAgeInMinutes = findViewById(R.id.tvAgeInMinutes)
        btnDatePrimary.setOnClickListener {
            clickDatePicker()
        }
    }

    private fun clickDatePicker(){
        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this,
            {_, selectedYear, selectedMonth, selectedDay ->
                Toast.makeText(this,"ADDED",Toast.LENGTH_LONG).show()
                val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear"
                tvSelectedDate?.text = selectedDate;

                val SDF = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

                val theDate = SDF.parse(selectedDate)
                val selectedDayInMinutes = selectedDateInMinutes(theDate)
                tvAgeInMinutes?.text = selectedDayInMinutes.toString();

            },
            year,
            month,
            day
        )
        dpd.datePicker.maxDate  = System.currentTimeMillis() - 86400000
        dpd.show()
    }

    fun selectedDateInMinutes(date: Date): Long {
        val selectedDateInMinutes = date.time / 60000
        val SDF = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
        val currentDate = SDF.parse(SDF.format(System.currentTimeMillis()))
        val currentDateInMins = currentDate.time / 60000
        val diffrenceInMins = currentDateInMins - selectedDateInMinutes
        return diffrenceInMins
    }
}