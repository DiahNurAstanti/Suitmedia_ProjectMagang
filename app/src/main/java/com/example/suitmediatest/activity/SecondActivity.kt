package com.example.suitmediatest.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.suitmediatest.R
import com.example.suitmediatest.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivitySecondBinding
    companion object{
        const val REQUEST_CODE = 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showUserName()
        binding.ivBack.setOnClickListener(this)
        binding.btnChoose.setOnClickListener(this)
    }

    private fun showUserName(){
        val name = intent.extras?.getString("name")
        binding.tvName.text = name
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.iv_back -> {
                val intent = Intent(this@SecondActivity, MainActivity::class.java)
                startActivity(intent)
            }
            R.id.btn_choose -> {
                val intent = Intent(this@SecondActivity, ThirdActivity::class.java)
                startActivityForResult(intent, REQUEST_CODE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK){
            val selectedName = data?.getStringExtra("selected_name")
            if (selectedName != null) {
                binding.tvSelectedUser.text = selectedName
            }
        }
    }
}