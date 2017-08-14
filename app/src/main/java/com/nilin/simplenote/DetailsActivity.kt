package com.nilin.developgoods

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.nilin.simplenote.gen.DaoMaster
import com.nilin.simplenote.gen.NoteDao
import kotlinx.android.synthetic.main.activity_details.*
import com.nilin.simplenote.*


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
        val ss = App.instance.getNoteDao().queryBuilder().where(NoteDao.Properties.Id.eq(1)).list()
        Log.i("111111", ss.get(0).note)

        if (ss.get(0).note !== note) {
            App.instance.getNoteDao().update(list)
        }
//        App.instance.getNoteDao().insertInTx(list)


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
