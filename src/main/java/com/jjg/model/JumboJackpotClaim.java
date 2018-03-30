package com.jjg.model;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "jumboJackpotClaim")
public class JumboJackpotClaim {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long claimId;
    private Long playerId;
    private long adminId;
    private Long jumboJackpotId;
    private Boolean isNotify;
    private Integer status;
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

    public Long getClaimId() { return claimId; }

    public void setClaimId(Long claimId) { this.claimId = claimId; }

    public Long getPlayerId() { return playerId; }

    public void setPlayerId(Long playerId) { this.playerId = playerId; }

    public long getAdminId() { return adminId; }

    public void setAdminId(long adminId) { this.adminId = adminId; }

    public Long getJumboJackpotId() { return jumboJackpotId; }

    public void setJumboJackpotId(Long jumboJackpotId) { this.jumboJackpotId = jumboJackpotId; }

    public Boolean getNotify() { return isNotify; }

    public void setNotify(Boolean notify) { isNotify = notify; }

    public Integer getStatus() { return status; }

    public void setStatus(Integer status) { this.status = status; }

    public Date getCreatedDate() { return createdDate; }

    public void setCreatedDate(Date createdDate) { this.createdDate = createdDate; }

    public Date getUpdatedDate() { return updatedDate; }

    public void setUpdatedDate(Date updatedDate) { this.updatedDate = updatedDate; }
}
