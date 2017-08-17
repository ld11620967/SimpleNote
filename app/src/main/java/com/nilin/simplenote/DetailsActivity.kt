package com.nilin.developgoods

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_details.*
import com.nilin.simplenote.*


class DetailsActivity : AppCompatActivity() {
    var note: String? = null
    var ss: String? = null
    var id:Long?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        var intent:Intent= getIntent()
        id=intent.getStringExtra("id").toLong()
        ss=intent.getStringExtra("note")
        note_details.setText(ss)
    }

    override fun onBackPressed() {
        note = note_details.text.toString()
        val list = Note(id,note)
        App.instance.getNoteDao().update(list)
        val Intent = Intent(this,MainActivity::class.java)
        startActivity(Intent)
        finish()
        super.onBackPressed()
    }

}
