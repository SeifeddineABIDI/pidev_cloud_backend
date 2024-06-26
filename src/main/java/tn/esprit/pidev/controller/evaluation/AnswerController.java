package tn.esprit.pidev.controller.evaluation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.pidev.entities.evaluation.Answer;
import tn.esprit.pidev.services.evaluation.IGestionAnswer;

import java.util.List;

@RestController
@RequestMapping("/answer")
@CrossOrigin
public class AnswerController {
    @Autowired
    IGestionAnswer IgResponse;

    @GetMapping("/getAll")
    public List<Answer> retrieveAllReponses(){
        return IgResponse.retrieveAllReponses();
    }

    @GetMapping("/getReponse/{id}")
    public Answer retrieveReponse (@PathVariable("id") Long numReponse){
        return IgResponse.retrieveReponse(numReponse);
    }

    @PutMapping("/updateReponse")
    public Answer updateReponse (@RequestBody Answer reponse){
        return IgResponse.updateReponse(reponse);
    }

    @DeleteMapping("/removeReponse/{id}")
    public void removeReponse(@PathVariable("id") Long numReponse){
        IgResponse.removeReponse(numReponse);
    }

    @PostMapping("addReponseAndAssignToQuestionAndUser/{numUser}/{numQuestion}")
    public Answer addReponseAndAssignToQuestionAndUser(@RequestBody Answer reponse,@PathVariable("numQuestion") Long numQuestion,@PathVariable("numUser") Integer numUser){
        return IgResponse.addReponseAndAssignToQuestionAndUser(reponse,numQuestion,numUser);
    }

    @GetMapping("/getAllByUser/{id}")
    public List<Answer> getAllResponsesByUser (@PathVariable("id") Integer numUser){
        return IgResponse.getAllByUser(numUser);
    }
    @GetMapping("/getAnswersByUserAndQuiz/{numUser}/{numQuiz}")
    public List<Answer> getAnswersByUserAndQuiz (@PathVariable("numUser")Integer numUser,@PathVariable("numQuiz") Long numQuiz) {
        return IgResponse.getAnswersByUserAndQuiz(numUser,numQuiz);
    }
    /******stat***********/
    @GetMapping("/getTotalCorrectAnswersForQuestion/{numq}")
    public int getTotalCorrectAnswersForQuestion (@PathVariable("numq") Long question) {
        return IgResponse.getTotalCorrectAnswersForQuestion(question);
    }

    @GetMapping("/getTotalAnswersForQuestion/{numq}")
    public int getTotalAnswersForQuestion (@PathVariable("numq") Long question) {
        return IgResponse.getTotalAnswersForQuestion(question);
    }
    //int getTotalAnswersForQuestion(Long numquestion)

    @GetMapping("/percentageCorrect")
    public double getPercentageCorrectAnswers() {
        return IgResponse.getPercentageCorrectAnswers();
    }

}
