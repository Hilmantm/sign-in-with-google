package com.example.loginwithgoogle

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions


class SecondActivity : AppCompatActivity() {

    private lateinit var image: ImageView
    private lateinit var name: TextView
    private lateinit var email: TextView
    private lateinit var id: TextView
    private lateinit var signOut: Button

    private lateinit var mGoogleSignInClient: GoogleSignInClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        image = findViewById(R.id.image)
        name = findViewById(R.id.name)
        email = findViewById(R.id.email)
        id = findViewById(R.id.id)
        signOut = findViewById(R.id.signOut)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        val acct = GoogleSignIn.getLastSignedInAccount(this)
        if(acct != null) {
            name.text = acct.displayName
            email.text = acct.email
            id.text = acct.id
            Glide.with(this).load(acct.photoUrl).into(image)
            Log.d("SecondActivity", acct.account.toString())
            Log.d("SecondActivity", "Token: ${acct.idToken.toString()}")
        }

        signOut.setOnClickListener {
            signOut()
        }
    }

    private fun signOut() {
        mGoogleSignInClient.signOut().addOnCompleteListener {
            Toast.makeText(this, "Sign Out Success", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}