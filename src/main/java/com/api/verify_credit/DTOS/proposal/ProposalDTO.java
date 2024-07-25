package com.api.verify_credit.DTOS.proposal;

import com.api.verify_credit.domain.user.User;

public record ProposalDTO(Long clientId, Double value, Integer duration, String description , String verification) {
}
