package com.nilin.developgoods

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.InputMethodManager
import kotlinx.android.synthetic.main.activity_details.*
import com.nilin.simplenote.*


class NewActivity : AppCompatActivity() {
    var note: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        setSupportActionBar(toolbar_details)

        note_details.setFocusable(true)//自动获得焦点
        note_details.setFocusableInTouchMode(true)//自动获得焦点
        note_details.requestFocus()//自动获得焦点
        Handler().postDelayed({ showInputMethod() }, 100)
    }

    override fun onBackPressed() {
        edit()
        super.onBackPressed()
    }

    private fun showInputMethod() {
        //自动弹出键盘
        val inputManager = baseContext.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS)
        //强制隐藏Android输入法窗口
        // inputManager.hideSoftInputFromWindow(edit.getWindowToken(),0);
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
        if (note != "") {
            val list = Note(null, note)
            App.instance.getNoteDao().insertInTx(list)
            val Intent = Intent(this, MainActivity::class.java)
            startActivity(Intent)
            finish()
        } else {
            val Intent = Intent(this, MainActivity::class.java)
            startActivity(Intent)
            finish()
        }
    }

}
