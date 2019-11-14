package com.nilin.developgoods

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_details.*
import com.nilin.simplenote.*


@Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class DetailsActivity : AppCompatActivity() {
    var note: String? = null
    var ss: String? = null
    var id: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        setSupportActionBar(toolbar_details)

        val intent: Intent = getIntent()
        id = intent.getStringExtra("id").toLong()
        ss = intent.getStringExtra("note")
        note_details.setText(ss)
        note_details.setSelection(note_details.getText().length)
    }

    override fun onBackPressed() {
        edit()
        super.onBackPressed()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_edit, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_edit -> {
                edit()
                true
            }
            else ->super.onOptionsItemSelected(item)
        }
    }

    fun edit() {
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
    }
}
