package com.example.myapplicationnote

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.asLiveData
import com.example.myapplicationnote.database.MainDb
import com.example.myapplicationnote.database.entity.EntityItem
import com.example.myapplicationnote.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = MainDb.getDb(this)

        db.getDao().getAllItems().asLiveData().observe(this){
            binding.TextViewList.text = ""
            it.forEach{
                val text = "Id: ${it.id} Name: ${it.name} LastName: ${it.lastName}\n"
                binding.TextViewList.append(text)
            }
        }

        binding.saveB.setOnClickListener {
            val item = EntityItem(null,
                binding.editTextName.text.toString(),
                binding.editTextLastName.text.toString()
            )
            Thread{
                db.getDao().insertItem(item)
            }.start()
        }

        binding.clearB.setOnClickListener {

            Thread{
                db.getDao().deleteAll()
            }.start()


        }

    }
}