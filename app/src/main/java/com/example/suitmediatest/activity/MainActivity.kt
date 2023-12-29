package com.example.suitmediatest.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.suitmediatest.R
import com.example.suitmediatest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCheck.setOnClickListener(this)
        binding.btnNext.setOnClickListener(this)


    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_check -> {

                val text = binding.etPalindrome.text.toString()

                if (isPalindrome(text)) {
                    Toast.makeText(this, "isPalindrome", Toast.LENGTH_SHORT).show()

                } else {
                    Toast.makeText(this, "not palindrome", Toast.LENGTH_SHORT).show()

                }

            }

            R.id.btn_next -> {

                val text = binding.etNama.text.toString()

                val intent = Intent(this@MainActivity, SecondActivity::class.java)
                intent.putExtra("name", text)
                startActivity(intent)
            }
        }
    }

    private fun isPalindrome(sentence: String): Boolean {
        val clear = sentence.lowercase().replace("\\s".toRegex(), "")
        val reverse = clear.reversed()
        return clear == reverse
    }

}