package com.rentCar.service.impl;

import com.rentCar.dto.CommentDTO;
import com.rentCar.enumerations.ApproveStatus;
import com.rentCar.model.Comment;
import com.rentCar.repository.CommentRepository;
import com.rentCar.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

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
}
