package sample.service;

import sample.model.Question;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ManageQuestion {
    private List<Question> questions = new ArrayList<>();

    public ManageQuestion() {
        questions.add(new Question("Tháng này là tháng mấy ?","5","6","7","8","6"));
        questions.add(new Question("Ai có biệt danh ve sầu ?","Chiến","Chương","Tú","Phong","Chiến"));
        questions.add(new Question("Ai code ra ct này ?","Thanh","Cảnh","Chương","Tuyền","Thanh"));
        questions.add(new Question("Ai là lươn chúa","đại ka","vẫn là đại ka","chắc chắn là đại ka","chỉ có thể là đại ka","đại ka"));
        questions.add(new Question("Đệ tử của lươn chúa là ai ???","Hùng","Phương Anh","Cảnh","Phong","Hùng"));
        questions.add(new Question("Who is FUCKBOY","Tú","Chương","Hùng","Phong","Phong"));
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
    public void add(Question question){
        questions.add(question);
    }
    public void delete(int index){
        questions.remove(index);
    }

    public void setQuestion() throws IOException {
        int rand = (int) Math.round(Math.random()*(questions.size()-1)+0);
        for (Question question:questions){
            if(questions.indexOf(question)==rand){
                writeQuestion("question.csv",question);
                writeAnswer("answers.csv",question.getAnswers());
                questions.remove(rand);
                break;
            }
        }
    }





    public void writeQuestion(String path, Question question) throws IOException {
        FileWriter fileWriter = new FileWriter(path);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(question.getQuestion()+","+question.getAnswer1()+","+question.getAnswer2()+","+question.getAnswer3()+","+question.getAnswer4()+","+question.getKey()+"\n");
        bufferedWriter.close();
        fileWriter.close();
    }
    public Question readQuestion(String path) throws IOException {
        FileReader fileReader = new FileReader(path);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line = bufferedReader.readLine();
        while (line!= null){
            String[] arr = line.split(",");
            return new Question(arr[0],arr[1],arr[2],arr[3],arr[4],arr[5]);
        }
        return null;
    }

    public void writeAnswer(String path, List<String> list) throws IOException {
        FileWriter fileWriter = new FileWriter(path);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        for (String str:list){
            bufferedWriter.write(str+"\n");
        }
        bufferedWriter.close();
        fileWriter.close();
    }

    public List<String> readAnswer(String path) throws IOException {
        List<String> list = new ArrayList<>();
        FileReader fileReader = new FileReader(path);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line ;
        while ((line = bufferedReader.readLine())!=null){
            list.add(line);
        }
        return list;
    }

    @Override
    public String toString() {
        return "ManageQuestion{" +
                "questions=" + questions +
                '}';
    }
}
