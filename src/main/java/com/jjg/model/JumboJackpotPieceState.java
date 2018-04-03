package com.jjg.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "jumboJackpotPieceState")
public class JumboJackpotPieceState {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long pieceId;
    private String pieceName;
    private Integer pieceNumber;
    private Long jumboJackpotId;
    private boolean isActive;
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

    public Long getPieceId() { return pieceId; }

    public void setPieceId(Long pieceId) { this.pieceId = pieceId; }

    public String getPieceName() { return pieceName; }

    public void setPieceName(String pieceName) { this.pieceName = pieceName; }

    public Integer getPieceNumber() { return pieceNumber; }

    public void setPieceNumber(Integer pieceNumber) { this.pieceNumber = pieceNumber; }

    public Long getJumboJackpotId() { return jumboJackpotId; }

    public void setJumboJackpotId(Long jumboJackpotId) { this.jumboJackpotId = jumboJackpotId; }

    public boolean isActive() { return isActive; }

    public void setActive(boolean active) { isActive = active; }

    public Date getCreatedDate() { return createdDate; }

    public void setCreatedDate(Date createdDate) { this.createdDate = createdDate; }

    public Date getUpdatedDate() { return updatedDate; }

    public void setUpdatedDate(Date updatedDate) { this.updatedDate = updatedDate; }
}
