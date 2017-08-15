package com.nilin.developgoods

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_details.*
import com.nilin.simplenote.*


class DetailsActivity : AppCompatActivity() {
    var note: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        var intent:Intent= getIntent()
        var id:Long?=null
        id=intent.getStringExtra("id").toLong()

        val ss=App.instance.getNoteDao().loadByRowId(id+1).note
        Log.i("1111",id.toString())
        note_details.setText(ss)
    }

    override fun onBackPressed() {
        note = note_details.text.toString()
        var id:Long?=null
        id=intent.getStringExtra("id").toLong()
        val list = Note(id-1,note)
        Log.i("22222",id.toString())
        App.instance.getNoteDao().update(list)
        val Intent = Intent(this,MainActivity::class.java)
        startActivity(Intent)
        super.onBackPressed()
    }

}
