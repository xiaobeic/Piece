package com.jjg.model;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "playerPiece")
public class PlayerPiece {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long playerPieceId;
    private Long playerId;
    private String pieceName;
    private Long jumboJackpotId;
    private Boolean isLatest;
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    public Long getPlayerPieceId() { return playerPieceId; }

    public void setPlayerPieceId(Long playerPieceId) { this.playerPieceId = playerPieceId; }

    public Long getPlayerId() { return playerId; }

    public void setPlayerId(Long playerId) { this.playerId = playerId; }

    public String getPieceName() { return pieceName; }

    public void setPieceName(String pieceName) { this.pieceName = pieceName; }

    public Long getJumboJackpotId() { return jumboJackpotId; }

    public void setJumboJackpotId(Long jumboJackpotId) { this.jumboJackpotId = jumboJackpotId; }

    public Boolean getLatest() { return isLatest; }

    public void setLatest(Boolean latest) { isLatest = latest; }

    public Date getCreatedDate() { return createdDate; }

    public void setCreatedDate(Date createdDate) { this.createdDate = createdDate; }
}
