package sample.control;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import sample.model.CreateAlert;
import sample.model.Question;
import sample.service.ManageQuestion;

import java.io.IOException;
import java.util.List;

public class Controller {
    @FXML
    TextArea question;
    @FXML
    Button answer1;
    @FXML
    Button answer2;
    @FXML
    Button answer3;
    @FXML
    Button answer4;
    @FXML
    Label score;
    ManageQuestion manageQuestion = new ManageQuestion();
    CreateAlert createAlert = new CreateAlert();
    int myScore = 0;
    public void initialize() throws IOException {
        score.setText(String.valueOf(myScore));
        next();
    }

    public void setAnswer(List<String> list,Button button) throws IOException {
        int rand = (int) Math.round(Math.random()*(list.size()-1)+0);
        for (String str: list){
            if(list.indexOf(str)==rand){
                button.setText(str);
                list.remove(rand);
                break;
            }
        }
        manageQuestion.writeAnswer("answers.csv",list);
    }

    public void next() throws IOException {
        manageQuestion.setQuestion();
        question.setText(manageQuestion.readQuestion("question.csv").getQuestion());
        setAnswer(manageQuestion.readAnswer("answers.csv"),answer1);
        setAnswer(manageQuestion.readAnswer("answers.csv"),answer2);
        setAnswer(manageQuestion.readAnswer("answers.csv"),answer3);
        setAnswer(manageQuestion.readAnswer("answers.csv"),answer4);
    }
    public void check1() throws IOException {
       Question question = manageQuestion.readQuestion("question.csv");
       if (answer1.getText().equals(question.getKey())){
           pointUp();
           System.out.println("Đúng");
           createAlert.showAlert("Congratulation","Chính xác",CreateAlert.INFORMATION);
       }else {
           System.out.println("Sai");
           createAlert.showAlert("Haizzz", " Sai rồi, kết quả là :"+question.getKey(),CreateAlert.WARNING);
       }
       next();
    }
    public void check2() throws IOException {
        Question question = manageQuestion.readQuestion("question.csv");
        if (answer2.getText().equals(question.getKey())){
            pointUp();
            System.out.println("Đúng");
            createAlert.showAlert("Congratulation","Chính xác",CreateAlert.INFORMATION);
        }else {
            System.out.println("Sai");
            createAlert.showAlert("Haizzz", " Sai rồi, kết quả là :"+question.getKey(),CreateAlert.WARNING);

        }
        next();
    }
    public void check3() throws IOException {
        Question question = manageQuestion.readQuestion("question.csv");
        if (answer3.getText().equals(question.getKey())){
            pointUp();
            System.out.println("Đúng");
            createAlert.showAlert("Congratulation","Chính xác",CreateAlert.INFORMATION);
        }else {
            System.out.println("Sai");
            createAlert.showAlert("Haizzz", " Sai rồi, kết quả là :"+question.getKey(),CreateAlert.WARNING);

        }
        next();
    }
    public void check4() throws IOException {
        pointUp();
        Question question = manageQuestion.readQuestion("question.csv");
        if (answer4.getText().equals(question.getKey())){
            System.out.println("Đúng");
            createAlert.showAlert("Congratulation","Chính xác",CreateAlert.INFORMATION);
        }else {
            System.out.println("Sai");
            createAlert.showAlert("Haizzz", " Sai rồi, kết quả là :"+question.getKey(),CreateAlert.WARNING);
        }
        next();
    }
    public void pointUp(){
        myScore+=10;
        score.setText(String.valueOf(myScore));
    }

}
