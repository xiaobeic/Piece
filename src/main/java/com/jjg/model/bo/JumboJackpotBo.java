package com.jjg.model.bo;

import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@ApiObject(name = "JumboJackpotBo", description = "jumboJackpot create bo", group = "admin bo")
public class JumboJackpotBo {
    @ApiObjectField(description = "jumbo jackpot id")
    private Long jumboJackpotId;
    @ApiObjectField(description = "jumbo jackpot name")
    private String name;
    @ApiObjectField(description = "jumbo jackpot title")
    private String title;
    @ApiObjectField(description = "jumbo jackpot start date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date formDate;
    @ApiObjectField(description = "jumbo jackpot end date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date toDate;
    @ApiObjectField(description = "jumbo jackpot rules file")
    private MultipartFile rulesFile;
    @ApiObjectField(description = "emails to notify")
    private String emailsToNotify;
    @ApiObjectField(description = "board image")
    private MultipartFile boardImage;
    @ApiObjectField(description = "game thumbnail")
    private MultipartFile gameThumbnail;
    @ApiObjectField(description = "game icon")
    private MultipartFile gameIcon;
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
    private MultipartFile attractVideos;
    @ApiObjectField(description = "game pieces")
    private MultipartFile gamePiecesImages;
    @ApiObjectField(description = "is default")
    private Boolean isDefault;

    public Long getJumboJackpotId() {
        return jumboJackpotId;
    }

    public void setJumboJackpotId(Long jumboJackpotId) {
        this.jumboJackpotId = jumboJackpotId;
    }

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

    public String getEmailsToNotify() {
        return emailsToNotify;
    }

    public void setEmailsToNotify(String emailsToNotify) {
        this.emailsToNotify = emailsToNotify;
    }

    public String getPromotions() {
        return promotions;
    }

    public void setPromotions(String promotions) {
        this.promotions = promotions;
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

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

    public MultipartFile getRulesFile() {
        return rulesFile;
    }

    public void setRulesFile(MultipartFile rulesFile) {
        this.rulesFile = rulesFile;
    }

    public MultipartFile getBoardImage() {
        return boardImage;
    }

    public void setBoardImage(MultipartFile boardImage) {
        this.boardImage = boardImage;
    }

    public MultipartFile getGameThumbnail() {
        return gameThumbnail;
    }

    public void setGameThumbnail(MultipartFile gameThumbnail) {
        this.gameThumbnail = gameThumbnail;
    }

    public MultipartFile getGameIcon() {
        return gameIcon;
    }

    public void setGameIcon(MultipartFile gameIcon) {
        this.gameIcon = gameIcon;
    }

    public MultipartFile getAttractVideos() {
        return attractVideos;
    }

    public void setAttractVideos(MultipartFile attractVideos) {
        this.attractVideos = attractVideos;
    }

    public MultipartFile getGamePiecesImages() {
        return gamePiecesImages;
    }

    public void setGamePiecesImages(MultipartFile gamePiecesImages) {
        this.gamePiecesImages = gamePiecesImages;
    }
}
