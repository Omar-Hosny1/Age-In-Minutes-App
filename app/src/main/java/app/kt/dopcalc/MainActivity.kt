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
    private var tvSelectedDate: TextView? = null;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePrimary: Button = findViewById(R.id.btnDataPicker);
        tvSelectedDate = findViewById(R.id.tvSelectedDate)

        btnDatePrimary.setOnClickListener {
            clickDatePicker();
        }
    }

    fun clickDatePicker(){
        val myCalender = Calendar.getInstance();
        val year = myCalender.get(Calendar.YEAR);
        val month = myCalender.get(Calendar.MONTH);
        val day = myCalender.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog(this,
            {view, selectedYear, selectedMonth, selectedDay ->
                Toast.makeText(this,"ADDED",Toast.LENGTH_LONG).show()
                val selectedDate = "$selectedDay/${selectedMonth + 1}/$selectedYear";
                tvSelectedDate?.text = selectedDate;

                val SDF = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

                val theDate = SDF.parse(selectedDate)
            },
            year,
            month,
            day
        ).show()
    }
}