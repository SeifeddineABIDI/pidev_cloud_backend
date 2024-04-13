package tn.esprit.pidev.services.evaluation;


import tn.esprit.pidev.entities.User;
import tn.esprit.pidev.entities.evaluation.Quiz;
import tn.esprit.pidev.entities.evaluation.Reponse;
import tn.esprit.pidev.entities.evaluation.Score;

import java.util.List;

public interface IGestionScore {
    List<Score> retrieveAllScores();
    Score retrieveScore (Long numScore);
    Score calculateScore(Long numQuiz, Long numUser);

}
