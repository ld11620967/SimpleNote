package com.nilin.simplenote

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SimpleItemAnimator
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.nilin.developgoods.DetailsActivity
import com.nilin.developgoods.NoteAdapter
import com.nilin.developgoods.Notes
import com.nilin.simplenote.gen.NoteDao
import com.nilin.simplenote.gen.NoteDao.Properties.*
import kotlinx.android.synthetic.main.activity_details.*

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var adapter: NoteAdapter? = null
    var noteDao: NoteDao?=null

    var note:Notes?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        recyclerview.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        var simpleAnimator: SimpleItemAnimator = recyclerview.itemAnimator as SimpleItemAnimator
        simpleAnimator.supportsChangeAnimations = false
        adapter = NoteAdapter(R.layout.item_note)
        recyclerview.adapter = adapter


//        if (noteDao!=null) {
//        for (x in 1..9) {
//        val qb = noteDao!!.queryBuilder()
//            val list = noteDao!!.queryBuilder().where(NoteDao.Properties.Id.eq(1)).list() as ArrayList<String>

//            var list= ArrayList<String>()
//            list.add(x.toString())
// var list= ArrayList<Notes>()
//            list.add(Notes(x.toString()))
//            val mUser = Note(2,"anye3")
//        val mUserDao = MyApplication.instances?.daoSession?.noteDao
//        mUserDao!!.insert(mUser);

//            list.add(list.get(1))
//            adapter!!.addData(list)
//            Log.i("33333",x.toString())
//        }
//        }


        fab.setOnClickListener { view ->
            val intent = Intent(this, DetailsActivity::class.java)
//            intent.putExtra("url", article.url)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
