package com.nilin.developgoods

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.nilin.simplenote.gen.DaoMaster
import com.nilin.simplenote.gen.NoteDao
import kotlinx.android.synthetic.main.activity_details.*
import com.nilin.simplenote.*


class DetailsActivity : AppCompatActivity() {
    var mUoteDao: NoteDao? = null
    var note: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        initDbHelp()
    }

    override fun onBackPressed() {
        note = note_details.text.toString()
        val list = Note(1, note)
        GreenDaoManager.instance.newSession.noteDao.update(list)
        super.onBackPressed()
    }

    private fun initDbHelp() {
        val helper = DaoMaster.DevOpenHelper(this, "note.db", null)
        val db = helper.writableDatabase
        val daoMaster = DaoMaster(db)
        val daoSession = daoMaster.newSession()
        mUoteDao = daoSession.noteDao
    }
}
