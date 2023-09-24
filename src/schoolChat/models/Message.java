package schoolChat.models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message implements Serializable {
    private String author;
    private LocalDateTime date;
    //private String topic;
    private String content;

    public Message(String author, String content) {
        this.author = author;
        this.date = LocalDateTime.now();
        //this.topic = topic;
        this.content = content;
    }

    public String toChatFormat() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = this.date.format(dateFormatter);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        String formattedTime = this.date.format(timeFormatter);
        return "(" + formattedDate + ")" +
                " [" + formattedTime + "] " +
                this.author + ": " +
                this.content;
    }

    @Override
    public String toString() {
        return "schoolChat.models.Message{" +
                "author='" + author + '\'' +
                ", date=" + date +
                ", content='" + content + '\'' +
                '}';
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
