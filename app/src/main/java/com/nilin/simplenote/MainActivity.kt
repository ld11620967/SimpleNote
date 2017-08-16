package com.nilin.simplenote

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.SimpleItemAnimator
import android.support.v7.widget.StaggeredGridLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.chad.library.adapter.base.BaseQuickAdapter
import com.nilin.developgoods.DetailsActivity
import com.nilin.developgoods.NewActivity
import com.nilin.developgoods.NoteAdapter

import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    var adapter: NoteAdapter? = null
    private val noteList: List<Note>? = null
    val ss = App.instance.getNoteDao().loadAll()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        recyclerview.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        var simpleAnimator: SimpleItemAnimator = recyclerview.itemAnimator as SimpleItemAnimator
        simpleAnimator.supportsChangeAnimations = false
        adapter = NoteAdapter(R.layout.item_note)
        recyclerview.adapter = adapter
        adapter!!.onItemClickListener = BaseQuickAdapter.OnItemClickListener {
            adapter, view, position ->
            val intent = Intent(this, DetailsActivity::class.java)
//            intent.putExtra("note", position.toString())
            intent.putExtra("note", ss.get(position).note)
            startActivity(intent)
        }

//        adapter!!.onItemLongClickListener = BaseQuickAdapter.OnItemLongClickListener {
//            adapter, view, position ->
//            App.instance.getNoteDao().deleteByKey(position.toLong())
//        }


        for (i in 0..ss.size - 1) {
            var dd= ArrayList<String>()
            dd.add(ss.get(i).note)
            adapter!!.addData(dd)
//            adapter!!.setNewData(dd)
        }

        fab.setOnClickListener { view ->
            val intent = Intent(this, NewActivity::class.java)
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
