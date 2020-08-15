package com.example.booking_room.Auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.booking_room.R
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_chage_password.*

class ChagePassword : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chage_password)
        auth = FirebaseAuth.getInstance()
        myBtnChagePassword.setOnClickListener {
            changePassword()
        }
//        myLoguot.setOnClickListener{
//            Logout()
//        }
    }

//    //Logout
//    private fun Logout(){
//        auth.signOut()
//        startActivity(Intent(this, Login::class.java))
//        finish()
//    }

    private fun changePassword() {
        if (myPassword.text.isNotEmpty() &&
            myNewPassword.text.isNotEmpty() &&
            myConfirmPasswrd.text.isNotEmpty()
        ) {

            if (myNewPassword.text.toString().equals(myConfirmPasswrd.text.toString())) {

                val user = auth.currentUser
                if (user != null && user.email != null) {
                    val credential = EmailAuthProvider
                        .getCredential(user.email!!, myPassword.text.toString())

// Prompt the user to re-provide their sign-in credentials
                    user?.reauthenticate(credential)
                        ?.addOnCompleteListener {
                            if (it.isSuccessful) {
                                Toast.makeText(this, "Xác thực lại thành công.", Toast.LENGTH_SHORT).show()
                                user?.updatePassword(myNewPassword.text.toString())
                                    ?.addOnCompleteListener { task ->
                                        if (task.isSuccessful) {
                                            Toast.makeText(this, "Đã thay đổi mật khẩu thành công.", Toast.LENGTH_SHORT).show()
                                            auth.signOut()
                                            startActivity(Intent(this, Login::class.java))
                                            finish()
                                        } else {
                                            Toast.makeText(this, "Mật khẩu phải có kí tự.", Toast.LENGTH_SHORT).show()
                                        }
                                    }

                            } else {
                                Toast.makeText(this, "Xác thực lại không thành công.", Toast.LENGTH_SHORT).show()
                            }
                        }
                } else {
                    startActivity(Intent(this, Login::class.java))
                    finish()
                }

            } else {
                Toast.makeText(this, "Mật khẩu không khớp.", Toast.LENGTH_SHORT).show()
            }

        } else {
            Toast.makeText(this, "Vui lòng nhập tất cả các trường.", Toast.LENGTH_SHORT).show()
        }

    }

}
