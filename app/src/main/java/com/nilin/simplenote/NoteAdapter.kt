package com.nilin.developgoods

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.nilin.simplenote.R
import java.text.SimpleDateFormat


class NoteAdapter(layoutId:Int) : BaseQuickAdapter<String, BaseViewHolder>(layoutId) {
//    val sdf: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")


    override fun convert(helper: BaseViewHolder?, item: String) {
            helper!!.setText(R.id.item_note,item)
//            helper!!.setText(R.id.item_time,date)
    }

}


