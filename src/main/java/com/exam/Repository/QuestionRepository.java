package com.exam.Repository;

import com.exam.models.exam.Question;
import com.exam.models.exam.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface QuestionRepository extends JpaRepository<Question,Long> {

     // custom finder method findByQuiz (camelcase)
    Set<Question> findByQuiz(Quiz quiz);
}
