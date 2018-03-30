package com.jjg.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "jumboJackpotPromotion")
public class JumboJackpotPromotion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long jumboJackpotPromotionId;
    private Long promotionId;
    private Long jumboJackpotId;

    public Long getJumboJackpotPromotionId() { return jumboJackpotPromotionId; }

    public void setJumboJackpotPromotionId(Long jumboJackpotPromotionId) { this.jumboJackpotPromotionId = jumboJackpotPromotionId; }

    public Long getPromotionId() { return promotionId; }

    public void setPromotionId(Long promotionId) { this.promotionId = promotionId; }

    public Long getJumboJackpotId() { return jumboJackpotId; }

    public void setJumboJackpotId(Long jumboJackpotId) { this.jumboJackpotId = jumboJackpotId; }
}
