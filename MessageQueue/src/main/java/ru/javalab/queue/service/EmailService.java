package ru.javalab.queue.service;

public interface EmailService {
    void sendDoc(String to, String subject, String text);
    void sendMeme(String to, String subject);

}
