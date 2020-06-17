package com.rentCar.repository;

import com.rentCar.enumerations.ApproveStatus;
import com.rentCar.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(value = "select c from Comment c where c.status = 'UNPROCESSED'")
    List<Comment> findUnprocessed();
    Comment findById(long id);
    List<Comment> findByUserId(long id);
    List<Comment> findByAdvertisementCarIdAndStatus(long id , ApproveStatus a);
}
