package tn.esprit.pidev.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import tn.esprit.pidev.entities.Discussion;
import tn.esprit.pidev.entities.Message;
import tn.esprit.pidev.repository.IDiscussionRepository;
import tn.esprit.pidev.repository.IMessageRepository;
import tn.esprit.pidev.repository.IUserRepository;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GestionMessageImpl implements IGestionMessage {

    @Autowired
    IMessageRepository iMessageRepository;
    @Autowired
    IUserRepository iUserRepository;
    @Autowired
    IDiscussionRepository iDiscussionRepository;

    @Override
    public Message sendMessage(int userSender, Long discussion, String message) {
        Message messageo = new Message();
        messageo.setMessage(message);

        messageo.setDateSent(LocalDateTime.now());
        messageo.setArchived(false);
        messageo.setPinned(false);
        messageo.setUser(iUserRepository.findById(userSender).get());

        Discussion discussiono = iDiscussionRepository.findById(discussion).get();
        messageo.setDiscussion(discussiono);

        return iMessageRepository.save(messageo);
    }

    public Message modifyMessage(Long id, String message) {
        Message messageo = iMessageRepository.findById(id).get();
        messageo.setDateModified(LocalDateTime.now());
        messageo.setMessage(message);

        SecureRandom secureRandom = new SecureRandom();
        messageo.getDiscussion().setUpdating(secureRandom.nextInt(100));
        iDiscussionRepository.save(messageo.getDiscussion());

        return iMessageRepository.save(messageo);
    }

    public boolean deleteMessage(Long id) {
        Message message = iMessageRepository.findById(id).get();
        message.setArchived(true);
        try {
            SecureRandom secureRandom = new SecureRandom();
            message.getDiscussion().setUpdating(secureRandom.nextInt(100));
            iDiscussionRepository.save(message.getDiscussion());

            iMessageRepository.save(message);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Message replyMessage(int userSender, Long discussion,Long message, String reply) {
        Message messageo = new Message();
        messageo.setMessage(reply);
        messageo.setDateSent(LocalDateTime.now());
        messageo.setArchived(false);
        messageo.setUser(iUserRepository.findById(userSender).get());
        messageo.setReply(iMessageRepository.findById(message).get());
        messageo.setPinned(false);

        Discussion discussiono = iDiscussionRepository.findById(discussion).get();
        messageo.setDiscussion(discussiono);

        return iMessageRepository.save(messageo);
    }

    public List<Message> retrieveAllMessages(Long id){
        return iMessageRepository.findAllByDiscussionIdAndArchivedIsFalseOrderByDateSent(id);
    }

    public List<Message> retrieveMessages(Long id, int page, int size){
        PageRequest pageRequest = PageRequest.of(page, size,Sort.by("DateSent").descending());
        return iMessageRepository.findAllByDiscussion_IdAndArchivedIsFalse(id, pageRequest);
    }

    public List<Message> retrieveRecentMessages(Long id, String recentDate)
    {
        LocalDateTime recentDateo = LocalDateTime.parse(recentDate, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS"));
        return iMessageRepository.findByDiscussionIdAndDateSentAfterOrderByDateSent(id, recentDateo);
    }

    public Message pinMessage(Long id) {
        Message messageo = iMessageRepository.findById(id).get();
        messageo.setPinned(!messageo.isPinned());
        return iMessageRepository.save(messageo);
    }


}
