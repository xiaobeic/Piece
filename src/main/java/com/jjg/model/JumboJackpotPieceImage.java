package com.jjg.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "jumboJackpotPieceImage")
public class JumboJackpotPieceImage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long pieceIamgeId;
    private String imageName;
    private String imagePath;
    private Long jumboJackpotId;

    public Long getPieceIamgeId() { return pieceIamgeId; }

    public void setPieceIamgeId(Long pieceIamgeId) { this.pieceIamgeId = pieceIamgeId; }

    public String getImageName() { return imageName; }

    public void setImageName(String imageName) { this.imageName = imageName; }

    public String getImagePath() { return imagePath; }

    public void setImagePath(String imagePath) { this.imagePath = imagePath; }

    public Long getJumboJackpotId() { return jumboJackpotId; }

    public void setJumboJackpotId(Long jumboJackpotId) { this.jumboJackpotId = jumboJackpotId; }
}
