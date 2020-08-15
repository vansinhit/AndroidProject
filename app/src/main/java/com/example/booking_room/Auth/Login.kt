package com.example.booking_room.Auth

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Patterns
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.booking_room.MainActivity
import com.example.booking_room.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.myBtnRegister

class Login : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        auth = FirebaseAuth.getInstance()


        // chuyển qua form Register
        myBtnRegister.setOnClickListener {
            val intent : Intent = Intent(this@Login, Register::class.java)
            startActivity(intent)
            finish()
        }
        //Login
        myBtnLogin.setOnClickListener {
            doLogin()
        }

        //ResetPassword
        myReset.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setTitle("Quên mật khẩu")
            val view = layoutInflater.inflate(R.layout.reset_password,null)
            val username = view.findViewById<EditText>(R.id.myResetEmail)
            builder.setView(view)
            builder.setPositiveButton("Đổi", DialogInterface.OnClickListener { _, _ ->
                forgotPassword(username)
            })
            builder.setNegativeButton("thoát", DialogInterface.OnClickListener { _, _ ->  })
            builder.show()
        }

    }
    private fun forgotPassword(username : EditText){
        if (username.text.toString().isEmpty()) {
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(username.text.toString()).matches()) {
            return
        }
        auth.sendPasswordResetEmail(username.text.toString())
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this,"Đả gửi Email.",Toast.LENGTH_SHORT).show()
                }
            }

    }


    // kiểm tra đăng nhập
    private fun doLogin() {
        if (myEmailLogin.text.toString().isEmpty()) {
            myEmailLogin.error = "vui lòng nhập Email."
            myEmailLogin.requestFocus()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(myEmailLogin.text.toString()).matches()) {
            myEmailLogin.error = "Email không hợp lệ."
            myEmailLogin.requestFocus()
            return
        }

        if (myPassLogin.text.toString().isEmpty()) {
            myPassLogin.error = "Vui lòng nhập mật khẩu"
            myPassLogin.requestFocus()
            return
        }

        auth.signInWithEmailAndPassword(myEmailLogin.text.toString(), myPassLogin.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    updateUI(user)
                } else {

                    updateUI(null)
                }
            }
    }
    //
    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

   private fun updateUI(currentUser: FirebaseUser?) {
       if (currentUser != null) {
           if (currentUser.isEmailVerified) {
               startActivity(Intent(this, MainActivity::class.java))
               finish()
           } else {
               Toast.makeText(
                   baseContext, "Email chưa xác thực.",
                   Toast.LENGTH_SHORT
               ).show()
           }
       } else {
           Toast.makeText(baseContext, "Sai mật khẩu.", Toast.LENGTH_SHORT).show()
       }
   }


}
