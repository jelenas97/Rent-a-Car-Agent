package com.rentCar.controller;

import com.rentCar.dto.CommentDTO;
import com.rentCar.model.Comment;
import com.rentCar.service.CommentService;
import com.rentCar.service.impl.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "comments")
@CrossOrigin("http://localhost:4200")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping(produces="application/json")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getUnComments(){

        try {
            List<CommentDTO> users = this.commentService.findUnprocessed();

            return new ResponseEntity(users, HttpStatus.OK);

        }catch(NullPointerException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error while loading users");
        }
    }

    @PutMapping(produces = "application/json", consumes = "application/json")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity changeStatus(@RequestBody CommentDTO commentDTO) {

        try {
            this.commentService.changeStatus(commentDTO);
            return new ResponseEntity(HttpStatus.OK);

        } catch (NullPointerException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
