package com.api.verify_credit.service.proposal;

import com.api.verify_credit.DTOS.proposal.ProposalResponseDTO;
import com.api.verify_credit.domain.proposal.Proposal;
import com.api.verify_credit.repositories.ProposalRepository;
import org.springframework.stereotype.Service;

@Service
public class ProposalResponse {

    private ProposalRepository proposalRepository;



    public ProposalResponseDTO createResponse(Proposal proposal){


       return null;

    }

    public void saveProposalResponse(Proposal proposal){

        this.proposalRepository.save(proposal);

    }







}
