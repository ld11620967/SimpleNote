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
import java.nio.file.Files.size
import kotlin.coroutines.experimental.EmptyCoroutineContext.plus


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
        val ss = App.instance.getNoteDao().loadAll()
        for (i in 0..ss.size - 1) {
            var dd= ArrayList<String>()
            dd.add(ss.get(i).note)
            adapter!!.setNewData(dd)
        }

//            adapter!!.addData(list)



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
