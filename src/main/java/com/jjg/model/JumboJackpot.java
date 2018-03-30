package com.jjg.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "jumboJackpot")
public class JumboJackpot {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long jumboJackpotId;
    private String name;
    private Date formDate;
    private Date toDate;
    private String rulesFilePath;
    private String emailsToNotify;
    private String boardImagePath;
    private String gameThumbnail;
    private String gameIconPath;
    private String distributions;
    private String mobileLocation;
    private String kioskLocation;
    private Integer totalPieces;
    private String racePieces;
    private String oddsOfWinning;
    private int pieceType;
    private int raceRatio;
    private String value;
    private String attractVideos;
    private Boolean isDefault;
    private Long createUser;
    private Integer status;
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

    public Long getJumboJackpotId() { return jumboJackpotId; }

    public void setJumboJackpotId(Long jumboJackpotId) { this.jumboJackpotId = jumboJackpotId; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public Date getFormDate() { return formDate; }

    public void setFormDate(Date formDate) { this.formDate = formDate; }

    public Date getToDate() { return toDate; }

    public void setToDate(Date toDate) { this.toDate = toDate; }

    public String getRulesFilePath() { return rulesFilePath; }

    public void setRulesFilePath(String rulesFilePath) { this.rulesFilePath = rulesFilePath; }

    public String getEmailsToNotify() { return emailsToNotify; }

    public void setEmailsToNotify(String emailsToNotify) { this.emailsToNotify = emailsToNotify; }

    public String getBoardImagePath() { return boardImagePath; }

    public void setBoardImagePath(String boardImagePath) { this.boardImagePath = boardImagePath; }

    public String getGameThumbnail() { return gameThumbnail; }

    public void setGameThumbnail(String gameThumbnail) { this.gameThumbnail = gameThumbnail; }

    public String getGameIconPath() { return gameIconPath; }

    public void setGameIconPath(String gameIconPath) { this.gameIconPath = gameIconPath; }

    public String getDistributions() { return distributions; }

    public void setDistributions(String distributions) { this.distributions = distributions; }

    public String getMobileLocation() { return mobileLocation; }

    public void setMobileLocation(String mobileLocation) { this.mobileLocation = mobileLocation; }

    public String getKioskLocation() { return kioskLocation; }

    public void setKioskLocation(String kioskLocation) { this.kioskLocation = kioskLocation; }

    public Integer getTotalPieces() { return totalPieces; }

    public void setTotalPieces(Integer totalPieces) { this.totalPieces = totalPieces; }

    public String getRacePieces() { return racePieces; }

    public void setRacePieces(String racePieces) { this.racePieces = racePieces; }

    public String getOddsOfWinning() { return oddsOfWinning; }

    public void setOddsOfWinning(String oddsOfWinning) { this.oddsOfWinning = oddsOfWinning; }

    public int getPieceType() { return pieceType; }

    public void setPieceType(int pieceType) { this.pieceType = pieceType; }

    public int getRaceRatio() { return raceRatio; }

    public void setRaceRatio(int raceRatio) { this.raceRatio = raceRatio; }

    public String getValue() { return value; }

    public void setValue(String value) { this.value = value; }

    public String getAttractVideos() { return attractVideos; }

    public void setAttractVideos(String attractVideos) { this.attractVideos = attractVideos; }

    public Boolean getDefault() { return isDefault; }

    public void setDefault(Boolean aDefault) { isDefault = aDefault; }

    public Long getCreateUser() { return createUser; }

    public void setCreateUser(Long createUser) { this.createUser = createUser; }

    public Integer getStatus() { return status; }

    public void setStatus(Integer status) { this.status = status; }

    public Date getCreatedDate() { return createdDate; }

    public void setCreatedDate(Date createdDate) { this.createdDate = createdDate; }

    public Date getUpdatedDate() { return updatedDate; }

    public void setUpdatedDate(Date updatedDate) { this.updatedDate = updatedDate; }
}
