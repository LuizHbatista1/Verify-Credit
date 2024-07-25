package com.api.verify_credit.infra.exceptions;

public class ProposalAlreadyExist extends RuntimeException{

    public ProposalAlreadyExist(){

        super("Proposal Already exist for this client!");

    }

}
