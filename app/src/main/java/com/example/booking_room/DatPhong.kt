package com.example.booking_room

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_dat_phong.*
import java.text.SimpleDateFormat
import java.util.*

class DatPhong : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private var khu = arrayOf("A", "B", "D", "S")
    private var toa = arrayOf("A1", "A2", "A3", "A4", "A5", "A6")
    private var tang = arrayOf("1", "2", "3", "4", "5")
    private var phong = arrayOf("101", "102", "103", "104")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dat_phong)

        /* Khu */
        val khu: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_spinner_item, khu)
        khu.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        mySpKhu!!.adapter = khu

        mySpKhu!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, osition: Int, id: Long) {
                Toast.makeText(this@DatPhong, "Khu ", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        /* *********************** */

        /* toa */
        val toa: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_spinner_item, toa)
        toa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        mySpToa!!.adapter = toa

        mySpToa!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, osition: Int, id: Long) {
                Toast.makeText(this@DatPhong, "toa ", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        /* *********************** */

        /* Tang */
        val tang: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_spinner_item, tang)
        tang.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        mySpTang!!.adapter = tang

        mySpTang!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, osition: Int, id: Long) {
                Toast.makeText(this@DatPhong, "tang ", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        /* *********************** */

        /* Phong */
        val phong: ArrayAdapter<String> = ArrayAdapter(this, android.R.layout.simple_spinner_item, phong)
        phong.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        mySpPhong!!.adapter = phong

        mySpPhong!!.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, osition: Int, id: Long) {
                Toast.makeText(this@DatPhong, "phong ", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        /* *********************** */


        /* Ngày giờ */
        val myNgay = findViewById<ImageButton>(R.id.myBtnNgay)
        val myTu = findViewById<ImageButton>(R.id.myBtnTu)
        val myDen = findViewById<ImageButton>(R.id.myBtnDen)

        val textNgay = findViewById<TextView>(R.id.myTextNgay)
        val textTu = findViewById<TextView>(R.id.myTextTu)
        val textDen = findViewById<TextView>(R.id.myTextDen)


        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        myNgay.setOnClickListener {

            val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                // Display Selected date in TextView
                textNgay.setText("Ngày    " + dayOfMonth + " " + month + ", " + year)
            }, year, month, day)
            dpd.show()

        }

        myTu.setOnClickListener {
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)
                textTu.text = SimpleDateFormat("HH:mm").format(cal.time)
            }
            TimePickerDialog(this, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
        }

        myDen.setOnClickListener {
            val cal = Calendar.getInstance()
            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
                cal.set(Calendar.HOUR_OF_DAY, hour)
                cal.set(Calendar.MINUTE, minute)
                textDen.text = SimpleDateFormat("HH:mm").format(cal.time)
            }
            TimePickerDialog(this, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
        }
        /* *********************** */
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
