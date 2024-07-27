package com.api.verify_credit.infra.rabbitmq;


import com.api.verify_credit.DTOS.proposal.ProposalDTO;
import com.api.verify_credit.domain.proposal.Proposal;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

@Component
public class ProposalPendentListener {


    @RabbitListener
    public void listen(Message<ProposalDTO> messageConverter){



    }


}
