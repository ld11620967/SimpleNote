package com.nilin.simplenote;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by nilin on 2017/8/12.
 */
@Entity
public class Note {
    @Id(autoincrement = true)
    private Long id;
    private String note;
    @Transient
    private int tempUsageCount; // not persisted
    @Generated(hash = 1462355924)
    public Note(Long id, String note) {
        this.id = id;
        this.note = note;
    }
    @Generated(hash = 1272611929)
    public Note() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNote() {
        return this.note;
    }
    public void setNote(String note) {
        this.note = note;
    }


}
