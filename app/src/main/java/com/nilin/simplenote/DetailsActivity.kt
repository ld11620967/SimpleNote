package com.nilin.developgoods

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
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
        note_details.setSelection(note_details.getText().length)
    }

    override fun onBackPressed() {
            note = note_details.text.toString()
            val contrastnote = note_details.text.toString().trim()
        if (contrastnote != "") {
            val list = Note(id, note)
            App.instance.getNoteDao().update(list)
            val Intent = Intent(this, MainActivity::class.java)
            startActivity(Intent)
            finish()
        } else {
            val Intent = Intent(this, MainActivity::class.java)
            val list = Note(id, note)
            App.instance.getNoteDao().delete(list)
            startActivity(Intent)
            finish()
        }
        super.onBackPressed()
    }
}
