package com.rentCar.controller;

import com.rentCar.RentCar.wsdl.CommentResponse;
import com.rentCar.dto.CommentDTO;
import com.rentCar.service.CommentService;
import com.rentCar.soap.StatisticsClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "comment")
@CrossOrigin("http://localhost:4200")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @Autowired
    private StatisticsClient statisticsClient;


    @GetMapping(produces="application/json")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
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

    @PostMapping(produces = "application/json", consumes = "application/json")
    @PreAuthorize("hasAuthority('ROLE_CLIENT')")
    public ResponseEntity<?> addComment(@RequestBody CommentDTO dto) {

       try {
           long id = this.commentService.addComment(dto);
           CommentResponse response = statisticsClient.commentResponse(dto);

           return new ResponseEntity(id, HttpStatus.CREATED);
       } catch (Exception e) {
           System.out.println(e);
           return new ResponseEntity(HttpStatus.CONFLICT);
       }
    }

    @PostMapping(value="/owner", produces = "application/json", consumes = "application/json")
    @PreAuthorize("hasAnyAuthority('ROLE_CLIENT','ROLE_AGENT')")
    public ResponseEntity<?> addCommentOwner(@RequestBody CommentDTO dto) {

        try {
            long id = this.commentService.addCommentOwner(dto);
            return new ResponseEntity(id, HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity(HttpStatus.CONFLICT);
        }
    }

    @GetMapping(value="/{id}", produces="application/json")
    @PreAuthorize("hasAnyAuthority('ROLE_CLIENT','ROLE_AGENT')")
    public ResponseEntity<?> getProcessedAdvertisementComments(@PathVariable Long id){

        try {
            List<CommentDTO> comments = statisticsClient.getComments(id);
            return new ResponseEntity(comments, HttpStatus.OK);

        }catch(NullPointerException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error while loading comments");
        }
    }
}
