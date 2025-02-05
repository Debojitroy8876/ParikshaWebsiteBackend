package com.exam.controller;


import com.exam.models.exam.Question;


import com.exam.models.exam.Quiz;
import com.exam.service.QuestionService;
import com.exam.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/question")
@CrossOrigin("*")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuizService quizService;
    // add question
    @PostMapping("/")
    public ResponseEntity<Question> add(@RequestBody Question question){

        return ResponseEntity.ok(this.questionService.addQuestion(question));
    }

    // update question
    @PutMapping("/")
    public ResponseEntity<Question> update(@RequestBody Question question){
        return ResponseEntity.ok(this.questionService.updateQuestion(question));
    }

    // get all question of any quiz ,shown to user after shuffling
    @GetMapping("/quiz/{qId}")
    public ResponseEntity<?> getQuestionOfQuiz(@PathVariable("qId") Long qId)
    {   // Quiz quiz = new Quiz();
        // quiz.setqId(qId);
        // Set<Question> questionOfQuiz = this.questionService.getQuestionOfQuiz(quiz);
       // return ResponseEntity.ok(questionOfQuiz);

       Quiz quiz= this.quizService.getQuiz(qId);
       Set<Question> questions= quiz.getQuestions();
       List list= new ArrayList(questions);
       if(list.size()>Integer.parseInt(quiz.getNumberOfQuestions())){
           list=list.subList(0,Integer.parseInt(quiz.getNumberOfQuestions()+1));
       }
        Collections.shuffle(list);
       return ResponseEntity.ok(list);

    }

    //get all question to admin
    @GetMapping("/quiz/all/{qId}")
    public ResponseEntity<?> getQuestionOfQuizAdmin(@PathVariable("qId") Long qId)
    {    Quiz quiz = new Quiz();
         quiz.setqId(qId);
         Set<Question> questionOfQuiz = this.questionService.getQuestionOfQuiz(quiz);
         return ResponseEntity.ok(questionOfQuiz);


        //return ResponseEntity.ok(list);

    }

    // get single question
    @GetMapping("/{quesId}")
    public Question get(@PathVariable ("quesId") Long quesId)
    {
        return this.questionService.getQuestion(quesId);


    }

    // delete question
    @DeleteMapping("/{quesId}")
    public void delete(@PathVariable("quesId") Long quesId)
    {
        this.questionService.deleteQuestion(quesId);

    }





}
