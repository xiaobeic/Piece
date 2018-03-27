package com.jjg.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class JJGToPiece {
    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private Integer jjgId;

    @Column
    private Integer pieceId;

    /// 总共数量
    @Column
    private Integer total;

    /// 已经发出
    @Column
    private Integer used;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getJjgId() {
        return jjgId;
    }

    public void setJjgId(Integer jjgId) {
        this.jjgId = jjgId;
    }

    public Integer getPieceId() {
        return pieceId;
    }

    public void setPieceId(Integer pieceId) {
        this.pieceId = pieceId;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getUsed() {
        return used;
    }

    public void setUsed(Integer used) {
        this.used = used;
    }
}
