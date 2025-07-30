package VoteBankApp.Service;

import VoteBankApp.Entity.Candidate;
import VoteBankApp.Entity.User;
import VoteBankApp.Entity.Vote;
import VoteBankApp.Repository.CandidateRepository;
import VoteBankApp.Repository.UserRepository;
import VoteBankApp.Repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Voteservices {

    @Autowired
    CandidateRepository candidateRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    VoteRepository voteRepository;

    public String castVote(Long userId,Long candidateId){

        User user=userRepository.findById(userId)
                .orElseThrow(()->new RuntimeException("User Not Found"));

        if(user.isHasVoted()) {
            return "You have already voted.";
        }

        Candidate candidate=candidateRepository.findById(candidateId)
                .orElseThrow(()->new RuntimeException("Candidate not found"));

        // Save vote
        Vote vote=new Vote();
        vote.setUser(user);
        vote.setCandidate(candidate);
        voteRepository.save(vote);

        // Update candidate vote count and user status
        candidate.setVoteCount(candidate.getVoteCount()+1);
        candidateRepository.save(candidate);

        user.setHasVoted(true);
        userRepository.save(user);

        return "Vote cast successfully!";
    }
}
