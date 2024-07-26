package com.api.verify_credit.service.rabbitmq;

import com.api.verify_credit.domain.proposal.Proposal;
import com.api.verify_credit.service.proposal.constants.VerifyNameConstants;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMqService {

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public RabbitMqService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void notificationQueue(String exchange , Proposal proposal) throws Exception {


        if(proposal.getVerification() == VerifyNameConstants.NEGATIVE_NAME_TRUE){

            return;
        }

        rabbitTemplate.convertAndSend(exchange , "",proposal);

    }

}
