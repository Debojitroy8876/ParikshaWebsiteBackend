package com.exam.Repository;

import com.exam.models.exam.Category;
import com.exam.models.exam.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizRepository extends JpaRepository<Quiz,Long> {
    public List<Quiz> findByCategory(Category category);

    // find only ACTIVE QUIZ ,these are custom finder method
    public List<Quiz> findByActive(Boolean b);

    // find category wise ACTIVE QUIZ,these are custom finder method
    public List<Quiz> findByCategoryAndActive(Category c,Boolean b);
}
