package VoteBankApp.Controller;

import VoteBankApp.Service.Voteservices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vote")
public class VoteController {

    @Autowired
    private Voteservices voteservices;


    @PostMapping
    public ResponseEntity<String> castVote(@RequestParam Long userId,@RequestParam Long candidateId){
        return ResponseEntity.ok(voteservices.castVote(userId,candidateId));
    }
}
