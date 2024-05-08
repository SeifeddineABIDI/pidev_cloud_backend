package com.example.picloud.services;


import java.util.List;

public interface StageService {
    List<Stage> getAllStages();
    Stage getStageById(int id);
    void addStage(Stage stage);
    void updateStage(int id, Stage stage);


    List<Stage> searchStageByTitre(String title);

    void deleteStage(int id);

}
