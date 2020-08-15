package com.example.booking_room.Auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import com.example.booking_room.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.myBtnRegister
import kotlinx.android.synthetic.main.activity_register.*

class Register : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        auth = FirebaseAuth.getInstance()

        myBtnRegister.setOnClickListener{
            signUpUser()
        }
    }
  private  fun signUpUser() {
        if (myEmailSinUp.text.toString().isEmpty()) {
            myEmailSinUp.error = "Vui lòng nhập mail"
            myEmailSinUp.requestFocus()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(myEmailSinUp.text.toString()).matches()) {
            myEmailSinUp.error = "Email không hợp lệ"
            myEmailSinUp.requestFocus()
            return
        }
        if (myPassSinUp.text.toString().isEmpty()) {
            myPassSinUp.error = "Vui lòng nhập mật khẩu"
            myPassSinUp.requestFocus()
            return
        }
      auth.createUserWithEmailAndPassword(myEmailSinUp.text.toString(),myPassSinUp.text.toString())
          .addOnCompleteListener(this) { task ->
              if (task.isSuccessful) {
                  val user = auth.currentUser
                  user?.sendEmailVerification()
                      ?.addOnCompleteListener { task ->
                          if (task.isSuccessful) {
                              startActivity(Intent(this, Login::class.java))
                              finish()
                          }
                      }
              } else {
                  Toast.makeText(baseContext, "Đăng ký thất bại vui lòng thử lại.",
                      Toast.LENGTH_SHORT).show()
              }
          }
    }
}
