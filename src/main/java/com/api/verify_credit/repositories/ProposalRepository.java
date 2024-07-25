package com.api.verify_credit.repositories;

import com.api.verify_credit.domain.proposal.Proposal;
import com.api.verify_credit.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProposalRepository extends JpaRepository<Proposal, Long> {


    List<Proposal> findProposalByClient(User clientId);


}
