package com.nilin.simplenote

import android.app.Application
import com.nilin.simplenote.gen.DaoMaster
import com.nilin.simplenote.gen.DaoSession
import com.nilin.simplenote.gen.NoteDao

import kotlin.properties.Delegates

/**
 * Created by liangd on 2017/8/14.
 */
class App : Application() {
    var mNoteDao: NoteDao? = null
    private var daoSession: DaoSession? = null

    companion object {
        var instance: App by Delegates.notNull()
//        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        initDao()
    }

    fun initDao() {
//        val helper = DaoMaster.DevOpenHelper(this, "user")
//        val db = helper.writableDb
//        daoSession = DaoMaster(db).newSession()
        val helper = DaoMaster.DevOpenHelper(this, "test.db", null)
        val db = helper.writableDatabase
        val daoMaster = DaoMaster(db)
        val daoSession = daoMaster.newSession()
        mNoteDao = daoSession.noteDao
    }

    open fun getDaoSession(): DaoSession {
        return daoSession!!
    }

    open fun getNoteDao(): NoteDao {
        return mNoteDao!!
    }
}
