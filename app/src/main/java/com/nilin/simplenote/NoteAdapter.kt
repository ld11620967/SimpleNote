package com.nilin.developgoods

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.nilin.simplenote.R


class NoteAdapter(layoutId:Int) : BaseQuickAdapter<String, BaseViewHolder>(layoutId) {
    override fun convert(helper: BaseViewHolder?, item: String?) {
        helper!!.setText(R.id.item_note,item)
    }

//    val sdf: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")

}


