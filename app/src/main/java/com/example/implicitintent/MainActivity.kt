package com.example.implicitintent

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDial = findViewById<Button>(R.id.btnDail)
        val btnMsg = findViewById<Button>(R.id.btnMsg)
        val btnEmail = findViewById<Button>(R.id.btnEmail)
        val btnShare = findViewById<Button>(R.id.btnShare)

        btnDial.setOnClickListener {
            val dial = Intent(Intent.ACTION_DIAL)
            dial.setData(Uri.parse("tel:911234567890"))
            startActivity(dial)
        }

        btnMsg.setOnClickListener {
            val msg = Intent(Intent.ACTION_SENDTO)
            // sending message to a particular number
            msg.setData(Uri.parse("smsto:" + Uri.encode("tel:+911234567890")))
            // sending the message content to the particular number
            msg.putExtra("sms_body", "Good Morning")
            startActivity(msg)
        }

        btnEmail.setOnClickListener {
            val email = Intent(Intent.ACTION_SEND)
            email.setType("message/rfc822")
            // receiver email address
            email.putExtra(Intent.EXTRA_EMAIL, arrayOf("example1@gmail.com", "example2@gmail.com"))
            // email subject
            email.putExtra(Intent.EXTRA_SUBJECT, "Dummy Subject")
            // email content body
            email.putExtra(Intent.EXTRA_TEXT, "Dummy mail content body")
            // if you want to send email by default email app
//            startActivity(email)
            // if you want to give the user to select a particular app to send the email
            startActivity(Intent.createChooser(email, "Choose the app"))
        }

        btnShare.setOnClickListener {
            val share = Intent(Intent.ACTION_SEND)
            share.type = "text/plain"
            share.putExtra(
                Intent.EXTRA_TEXT,
                "https://google.com"
            )
            startActivity(Intent.createChooser(share, ""))
        }
    }
}