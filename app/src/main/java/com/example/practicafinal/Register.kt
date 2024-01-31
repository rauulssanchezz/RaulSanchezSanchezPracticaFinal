package com.example.practicafinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class Register : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private var user: FirebaseUser?=null
    private var email:String=""
    private var password:String=""
    private lateinit var emailEdit: TextInputEditText
    private lateinit var passwordEdit: TextInputEditText
    private lateinit var confirmPasswordEdit: TextInputEditText
    private lateinit var save: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        inicializarVariables()
        save()
    }

    private fun inicializarVariables(){
        auth=FirebaseAuth.getInstance()
        user=auth.currentUser
        emailEdit=findViewById<TextInputEditText>(R.id.user)
        passwordEdit=findViewById<TextInputEditText>(R.id.password)
        confirmPasswordEdit=findViewById<TextInputEditText>(R.id.confirmpassword)
        save=findViewById<Button>(R.id.save)
    }

    private fun save() {
        save.setOnClickListener {

            if (emailEdit.text.isNullOrBlank() || passwordEdit.text.isNullOrBlank() || confirmPasswordEdit.text.isNullOrBlank()) {
                if (emailEdit.text.isNullOrBlank()) {
                    emailEdit.setError("Este campo es obligatorio")
                }
                if (passwordEdit.text.isNullOrBlank()) {
                    passwordEdit.setError("Este campo es obligatorio")
                }
                if (confirmPasswordEdit.text.isNullOrBlank()) {
                    confirmPasswordEdit.setError("Este campo es obligatorio")
                }
            } else {

                if (!passwordEdit.text.toString().equals(confirmPasswordEdit.text.toString())) {
                    passwordEdit.setError("Las contraseñas no coinciden")
                    confirmPasswordEdit.setError("Las contraseñas no coinciden")
                }else{
                    email = emailEdit.text.toString()
                    password = passwordEdit.text.toString()
                    auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this) { task ->
                            if (task.isSuccessful) {
                                user = auth.currentUser
                                finish()
                            } else {
                                Toast.makeText(this, "Error en el registro", Toast.LENGTH_SHORT).show()
                            }
                        }
                }
            }
        }
    }
}