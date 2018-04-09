package com.jjg.model.bo;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;

import java.util.Date;

@ApiObject(name = "JumboJackpotBo", description = "jumboJackpot create bo", group = "admin bo")
public class JumboJackpotBo {
    @ApiObjectField(description = "jumbo jackpot name")
    private String name;
    @ApiObjectField(description = "jumbo jackpot title")
    private String title;
    @ApiObjectField(description = "jumbo jackpot start date")
    private Date formDate;
    @ApiObjectField(description = "jumbo jackpot end date")
    private Date toDate;
    @ApiObjectField(description = "jumbo jackpot rules file")
    private String rulesFile;
    @ApiObjectField(description = "emails to notify")
    private String emailsToNotify;
    @ApiObjectField(description = "board image")
    private String boardImage;
    @ApiObjectField(description = "game thumbnail")
    private String gameThumbnail;
    @ApiObjectField(description = "game icon")
    private String gameIcon;
    @ApiObjectField(description = "promotions id")
    private String promotions;
    @ApiObjectField(description = "jumbo jackpot distributions")
    private String distributions;
    @ApiObjectField(description = "total pieces")
    private Integer totalPieces;
    @ApiObjectField(description = "race pieces")
    private String racePieces;
    @ApiObjectField(description = "piece type")
    private Integer pieceType;
    @ApiObjectField(description = "race piece ratio")
    private Integer raceRatio;
    @ApiObjectField(description = "value")
    private Integer value;
    @ApiObjectField(description = "attract videos")
    private String attractVideos;
    @ApiObjectField(description = "game pieces")
    private String gamePiecesImages;
    @ApiObjectField(description = "is default")
    private Boolean isDefault;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getFormDate() {
        return formDate;
    }

    public void setFormDate(Date formDate) {
        this.formDate = formDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public String getRulesFile() {
        return rulesFile;
    }

    public void setRulesFile(String rulesFile) {
        this.rulesFile = rulesFile;
    }

    public String getEmailsToNotify() {
        return emailsToNotify;
    }

    public void setEmailsToNotify(String emailsToNotify) {
        this.emailsToNotify = emailsToNotify;
    }

    public String getBoardImage() {
        return boardImage;
    }

    public void setBoardImage(String boardImage) {
        this.boardImage = boardImage;
    }

    public String getGameThumbnail() {
        return gameThumbnail;
    }

    public void setGameThumbnail(String gameThumbnail) {
        this.gameThumbnail = gameThumbnail;
    }

    public String getGameIcon() {
        return gameIcon;
    }

    public String getPromotions() {
        return promotions;
    }

    public void setPromotions(String promotions) {
        this.promotions = promotions;
    }

    public void setGameIcon(String gameIcon) {
        this.gameIcon = gameIcon;
    }

    public String getDistributions() {
        return distributions;
    }

    public void setDistributions(String distributions) {
        this.distributions = distributions;
    }

    public Integer getTotalPieces() {
        return totalPieces;
    }

    public void setTotalPieces(Integer totalPieces) {
        this.totalPieces = totalPieces;
    }

    public String getRacePieces() {
        return racePieces;
    }

    public void setRacePieces(String racePieces) {
        this.racePieces = racePieces;
    }

    public Integer getPieceType() {
        return pieceType;
    }

    public void setPieceType(Integer pieceType) {
        this.pieceType = pieceType;
    }

    public Integer getRaceRatio() {
        return raceRatio;
    }

    public void setRaceRatio(Integer raceRatio) {
        this.raceRatio = raceRatio;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public String getAttractVideos() {
        return attractVideos;
    }

    public void setAttractVideos(String attractVideos) {
        this.attractVideos = attractVideos;
    }

    public String getGamePiecesImages() {
        return gamePiecesImages;
    }

    public void setGamePiecesImages(String gamePiecesImages) {
        this.gamePiecesImages = gamePiecesImages;
    }

    public Boolean getDefault() {
        return isDefault;
    }

    public void setDefault(Boolean aDefault) {
        isDefault = aDefault;
    }
}
