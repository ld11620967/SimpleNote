package com.nilin.developgoods

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_details.*
import com.nilin.simplenote.*


class NewActivity : AppCompatActivity() {
    var note: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
    }

    override fun onBackPressed() {
        note = note_details.text.toString()
        val list = Note(null,note)
        App.instance.getNoteDao().insertInTx(list)
        val Intent = Intent(this,MainActivity::class.java)
        startActivity(Intent)
        super.onBackPressed()
    }


}
