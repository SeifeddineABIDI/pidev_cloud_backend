package com.picloud.picloud.services.ressources;

import com.picloud.picloud.entities.User;
import com.picloud.picloud.entities.ressources.Notification;
import com.picloud.picloud.repository.ressources.INotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class GestionNotification implements IGestionNotif {

    @Autowired
    private INotificationRepository notificationRepository;

    @Override
    public List<Notification> récupérerNotificationsUtilisateur(User utilisateur) {
        return notificationRepository.findByDestinataire(utilisateur);
    }

    @Override
    public List<Notification> getAll() {
        return notificationRepository.findAll();
    }

    @Override
    public void marquerCommeLue(Notification notification) {
        notification.setEstLue(true);
        notificationRepository.save(notification);
    }


    @Override
    public void envoyerNotification(Notification notification) {
        notificationRepository.save(notification);
        System.out.println("Notification sauvegardée : " + notification.getMessage());
        switch (notification.getType()) {
            case NOUVELLE_RESSOURCE:
                break;
            case COMMENTAIRE:
                break;
            case NOUVELLE_VERSION:
                break;
            default:
                break;
        }
    }


    @Override
    public List<Notification> getNotificationsByUser(User user) {
        return notificationRepository.findByDestinataire(user);
    }

}