package com.api.verify_credit.controller;


import com.api.verify_credit.DTOS.proposal.ProposalDTO;
import com.api.verify_credit.DTOS.proposal.ProposalResponseDTO;
import com.api.verify_credit.domain.proposal.Proposal;
import com.api.verify_credit.service.proposal.ProposalRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping
public class ProposalController {

    private ProposalRequestService proposalRequestService;


    @Autowired
    public ProposalController(ProposalRequestService proposalRequestService) {
        this.proposalRequestService = proposalRequestService;
    }

    @PostMapping("/proposal")
    public ResponseEntity<Proposal>createProposal(@RequestBody ProposalDTO proposalDTO) throws Exception{

        Proposal newProposal = proposalRequestService.createProposal(proposalDTO);
        return new ResponseEntity<>(newProposal, HttpStatus.CREATED);

    }

    @GetMapping("/{clientId}/proposal")
    public ResponseEntity<List<ProposalResponseDTO>> getProposal(@PathVariable Long clientId){

        List<Proposal>proposals = proposalRequestService.getProposalsById(clientId);

        List<ProposalResponseDTO> responseDTOS = proposals.stream()
                .map(proposal -> new ProposalResponseDTO(

                        proposal.getDescription(),
                        proposal.getValue(),
                        proposal.getDuration(),
                        proposal.getVerification()

                )).collect(Collectors.toList());

        return new ResponseEntity<>(responseDTOS , HttpStatus.OK);

    }

    @GetMapping("/{clientId}/response")
    public ResponseEntity<String>verifyProposal(@PathVariable Long clientId){

        List<Proposal> proposals = proposalRequestService.getProposalsById(clientId);
        Proposal proposal = proposals.get(0);
        String result = proposalRequestService.proposalApprovedOrNot(proposal);
        return ResponseEntity.ok(result);



    }



}
