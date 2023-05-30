package com.wtt.composetest

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.wtt.composetest.databinding.MainBinding
import com.wtt.composetest.repository.hilt.SimpleInjectActivity
import com.wtt.composetest.socket.SocketActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: MainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.tvJumpSocket.setOnClickListener {
            startActivity(
                Intent(this, SocketActivity::class.java)
            )
        }
        binding.tvJumpHilt.setOnClickListener {
            startActivity(
                Intent(this, SimpleInjectActivity::class.java)
            )
        }

    }
}
