package VoteBankApp.Controller;


import VoteBankApp.Entity.Candidate;
import VoteBankApp.Repository.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/candidates")
public class CandidateController {

    @Autowired
    CandidateRepository candidateRepository;

    @PostMapping("/createCandidate")
    public  Candidate createCandidate(@RequestBody Candidate candidate){
        Candidate candidate1=candidateRepository.save(candidate);
        return ResponseEntity.ok(candidate1).getBody();
    }

    @GetMapping
    public List<Candidate> getAllCandidates(){
        return candidateRepository.findAll();
    }

    @GetMapping("/results")
    public List<Candidate> getResults(){
        return candidateRepository.findAll(Sort.by( Sort.Direction.DESC,"voteCount"));
    }


}
