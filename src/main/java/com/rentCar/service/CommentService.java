package com.rentCar.service;

import com.rentCar.dto.CommentDTO;
import com.rentCar.model.Comment;

import java.util.List;

public interface CommentService {
    List<CommentDTO> findUnprocessed();
    void changeStatus(CommentDTO comment);

}
