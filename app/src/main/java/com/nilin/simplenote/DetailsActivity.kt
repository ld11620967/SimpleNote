package com.nilin.developgoods

import android.app.Application
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.nilin.simplenote.gen.DaoMaster
import com.nilin.simplenote.gen.NoteDao
import kotlinx.android.synthetic.main.activity_details.*
import com.nilin.simplenote.*
import com.nilin.simplenote.R.id.note_details


class DetailsActivity : AppCompatActivity() {
    var mNoteDao: NoteDao? = null
    var note: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        //        initDbHelp()
}



    override fun onBackPressed() {
        note = note_details.text.toString()
        val list = Note(1, note)
//        GreenDaoManager.instance.newSession.noteDao.update(list)
//        mNoteDao!!.insertInTx(list)
//        App.instance.mNoteDao!!.insertInTx(list)

        App.instance.getNoteDao().insertInTx(list)
//        mNoteDao!!.update(list)
        super.onBackPressed()
    }

    private fun initDbHelp() {
        val helper = DaoMaster.DevOpenHelper(this, "note.db", null)
        val db = helper.writableDatabase
        val daoMaster = DaoMaster(db)
        val daoSession = daoMaster.newSession()
        mNoteDao = daoSession.noteDao
    }


//     fun loadUsers() {
//        val qb = mNoteDao!!.queryBuilder()
//        val list = qb.list()
//        if (list.isEmpty() || list.size <= 0)  {
//            for (i in 0..5) {
//                val user:User = User()
//                user.name = "aaa$i"
//                user.password = i.toString()
//                list.add(user)
//            }
//            mNoteDao!!.insertInTx(list)
//        }
//        processUsers(list)
//    }

}
