package com.nilin.simplenote

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.SimpleItemAnimator
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import android.view.Menu
import android.view.MenuItem
import com.chad.library.adapter.base.BaseQuickAdapter
import com.nilin.developgoods.DetailsActivity
import com.nilin.developgoods.NewActivity
import com.nilin.developgoods.NoteAdapter

import kotlinx.android.synthetic.main.activity_main.*
import android.widget.Toast
import android.content.DialogInterface
import androidx.appcompat.app.AlertDialog


class MainActivity : AppCompatActivity() {
    var adapter: NoteAdapter? = null
    val ss = App.instance.getNoteDao().loadAll()
    var id = ArrayList<Long>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        recyclerview.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        val simpleAnimator: SimpleItemAnimator = recyclerview.itemAnimator as SimpleItemAnimator
        simpleAnimator.supportsChangeAnimations = false
        adapter = NoteAdapter(R.layout.item_note)
        recyclerview.adapter = adapter
        adapter!!.onItemClickListener = BaseQuickAdapter.OnItemClickListener {
            adapter, view, position ->
            val intent = Intent(this, DetailsActivity::class.java)
            intent.putExtra("id", id[position].toString())
            intent.putExtra("note", ss.get(position).note)
            startActivity(intent)
            finish()
        }

        adapter!!.onItemLongClickListener = BaseQuickAdapter.OnItemLongClickListener listener@ {
            adapter, view, position ->
            AlertDialog.Builder(this).setTitle("提示")
                    .setMessage("确定要删除吗？")
                    .setNegativeButton("取消", null)
                    .setPositiveButton("确定", DialogInterface.OnClickListener { dialog, which ->
                        App.instance.getNoteDao().deleteByKey(id[position])
                        ss.removeAt(position)
                        adapter.remove(position)
                        Toast.makeText(this, "删除成功", Toast.LENGTH_SHORT).show()
                    }).show()
            return@listener false
        }

        updata()

        fab.setOnClickListener { view ->
            val intent = Intent(this, NewActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> {
                Toast.makeText(this, "简约笔记", Toast.LENGTH_SHORT).show()
                true
            }
            else ->super.onOptionsItemSelected(item)
        }
    }

    fun updata() {
        for (i in 0..ss.size - 1) {
            val dd = ArrayList<String>()
            dd.add(ss.get(i).note)
            id.add(ss.get(i).id)
            adapter!!.addData(dd)
        }
    }
}
