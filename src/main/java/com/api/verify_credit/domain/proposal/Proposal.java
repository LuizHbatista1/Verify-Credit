package com.api.verify_credit.domain.proposal;

import com.api.verify_credit.DTOS.proposal.ProposalDTO;
import com.api.verify_credit.domain.user.User;
import jakarta.persistence.*;

@Entity(name = "proposals")
@Table(name = "proposals")
public class Proposal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @JoinColumn(name = "user_id")
    @ManyToOne
    private User client;
    private Double value;
    private Integer duration;
    private String description;
    private String verification;

    public Proposal(){


    }

    public Proposal(User client, Double value, Integer duration, String description , String verification) {
        this.client = client;
        this.value = value;
        this.duration = duration;
        this.description = description;
        this.verification = verification;
    }

    public Proposal(ProposalDTO data){

        this.client = client;
        this.value = data.value();
        this.duration = data.duration();
        this.description = data.description();
        this.verification = data.verification();

    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getClient() {
        return client;
    }

    public void setClient(User client) {
        this.client = client;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getVerification() {
        return verification;
    }

    public void setVerification(String verification) {
        this.verification = verification;
    }
}
