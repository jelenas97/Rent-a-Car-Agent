package com.rentCar.service.impl;

import com.rentCar.dto.CommentDTO;
import com.rentCar.enumerations.ApproveStatus;
import com.rentCar.model.Advertisement;
import com.rentCar.model.Comment;
import com.rentCar.model.RentRequest;
import com.rentCar.model.User;
import com.rentCar.repository.AdvertisementRepository;
import com.rentCar.repository.CommentRepository;
import com.rentCar.repository.RentRequestRepository;
import com.rentCar.repository.UserRepository;
import com.rentCar.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private AdvertisementRepository advertisementRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RentRequestRepository rentRequestRepository;

    @Override
    public List<CommentDTO> findUnprocessed() {

        List<Comment> comments = this.commentRepository.findUnprocessed();
        List<CommentDTO> commentDTOS = new ArrayList<>();
        for(Comment com : comments){
            commentDTOS.add(new CommentDTO(com));
        }
        return commentDTOS;
    }

    @Override
    public void changeStatus(CommentDTO comment) {
        Comment com = this.commentRepository.getOne(comment.getId());
        com.setStatus(ApproveStatus.valueOf(comment.getStatus()));
        this.commentRepository.save(com);
    }

    @Override
    public Long addComment(CommentDTO dto) {

        Comment comment = new Comment();
        comment.setStatus(ApproveStatus.UNPROCESSED);
        Advertisement ad = advertisementRepository.find( dto.getAdvertisement_id());
        comment.setAdvertisement(ad);
        comment.setContent(dto.getContent());
        comment.setDate(dto.getDate());
        User user = this.userRepository.findOneById(dto.getCommenter_id());
        comment.setUser(user);
        RentRequest rr = this.rentRequestRepository.find(dto.getRent_request_id());
        comment.setRentRequest(rr);

        Comment c =this.commentRepository.save(comment);

        return c.getId();
    }

    @Override
    public Long addCommentOwner(CommentDTO dto) {

        Comment comment = new Comment();
        comment.setStatus(ApproveStatus.APPROVED);
        Advertisement ad = advertisementRepository.find( dto.getAdvertisement_id());
        comment.setAdvertisement(ad);
        comment.setContent(dto.getContent());
        comment.setDate(dto.getDate());
        User user = this.userRepository.findOneById(dto.getCommenter_id());
        comment.setUser(user);

        Comment c =this.commentRepository.save(comment);

        return c.getId();
    }

    @Override
    public List<CommentDTO> findProcessedAdvertisementComments(long id) {

        List<Comment> comments = this.commentRepository.findByAdvertisementCarIdAndStatus(id, ApproveStatus.APPROVED);
        List<CommentDTO> commentDTOS = new ArrayList<>();
        for(Comment com : comments){
            commentDTOS.add(new CommentDTO(com ,0));
        }
        
        return commentDTOS;
    }

}
