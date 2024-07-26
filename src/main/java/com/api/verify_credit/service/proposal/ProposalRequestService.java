package com.api.verify_credit.service.proposal;

import com.api.verify_credit.DTOS.proposal.ProposalDTO;
import com.api.verify_credit.domain.proposal.Proposal;
import com.api.verify_credit.domain.user.User;
import com.api.verify_credit.infra.exceptions.IdNotFoundException;
import com.api.verify_credit.infra.exceptions.ProposalAlreadyExist;
import com.api.verify_credit.repositories.ProposalRepository;
import com.api.verify_credit.repositories.UserRepository;
import com.api.verify_credit.service.proposal.constants.VerifyNameConstants;
import com.api.verify_credit.service.proposal.simulatorScore.SimulatorScoreService;
import com.api.verify_credit.service.rabbitmq.RabbitMqService;
import com.api.verify_credit.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProposalRequestService {

    private ProposalRepository proposalRepository;
    private UserRepository userRepository;
    private SimulatorScoreService simulatorScoreService;
    private PointSystemService pointSystemService;
    private UserService userService;
    private RabbitMqService rabbitMqService;
    @Value("${rabbitmq.exchange}")
    private String exchange;

    @Autowired
    public ProposalRequestService(ProposalRepository proposalRepository, UserRepository userRepository, SimulatorScoreService simulatorScoreService, PointSystemService pointSystemService, UserService userService, RabbitMqService rabbitMqService) {
        this.proposalRepository = proposalRepository;
        this.userRepository = userRepository;
        this.simulatorScoreService = simulatorScoreService;
        this.pointSystemService = pointSystemService;
        this.userService = userService;
        this.rabbitMqService = rabbitMqService;
    }

    public Proposal findProposalById(Long id){

        return proposalRepository.findById(id).orElseThrow(()-> new IdNotFoundException(id));

    }

    public Proposal createProposal(ProposalDTO proposalDTO) throws Exception{

        User sender = userService.findUserById(proposalDTO.clientId());
        Proposal newProposal = new Proposal(proposalDTO);
        newProposal.setClient(sender);
        newProposal.setValue(newProposal.getValue());
        newProposal.setDuration(newProposal.getDuration());
        newProposal.setDescription(newProposal.getDescription());
        newProposal.setVerification(verifyNegativeName());
        activeProposals(proposalDTO);
        this.saveProposal(newProposal);
        rabbitMqService.notificationQueue(exchange , newProposal);
        return newProposal;

    }

    public void saveProposal(Proposal proposal){

        this.proposalRepository.save(proposal);

    }

    public String verifyNegativeName(){

        boolean verification = simulatorScoreService.negativeName();
        if(verification){

            return VerifyNameConstants.NEGATIVE_NAME_TRUE;

        }

        return VerifyNameConstants.NEGATIVE_NAME_FALSE;

    }

    public void activeProposals(ProposalDTO proposal){

        if (proposalRepository.existsById(proposal.clientId())){

            throw new ProposalAlreadyExist();

        }

    }

    public List<Proposal> getProposalsById (Long clientId){

        User client = userRepository.findById(clientId).orElseThrow(()->new IdNotFoundException(clientId));
        return proposalRepository.findProposalByClient(client);

    }

}
