package tn.esprit.pidev.controller;

import tn.esprit.pidev.entities.Forum;
import tn.esprit.pidev.services.ForumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class ForumController {

    private final ForumService forumService;

    @Autowired
    public ForumController(ForumService forumService) {
        this.forumService = forumService;
    }

    @PostMapping("/forums")
    public ResponseEntity<Forum> createForum(@RequestBody Forum forum) {
        Forum savedForum = forumService.saveForum(forum);
        return new ResponseEntity<>(savedForum, HttpStatus.CREATED);
    }

    // Ajoutez d'autres méthodes pour gérer les requêtes HTTP selon vos besoins
}
