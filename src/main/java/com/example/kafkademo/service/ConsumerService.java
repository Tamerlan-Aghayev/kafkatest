package com.example.kafkademo.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static com.example.kafkademo.config.KafkaTopicConfiguration.PICTURE_EVENTS;

@Service
public class ConsumerService {
    @KafkaListener(groupId = "pictures", topics = PICTURE_EVENTS)
    public void listen(byte[] message) {
        writeToFile(message);
    }
    public void writeToFile(byte[] pic){
        String folderPath = "C:\\Users\\TamerlanAg\\Desktop\\kafkatest\\kafkatest\\src\\main\\resources\\images";
        String fileName = "image.jpg";

        File file = new File(folderPath, fileName);

        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(pic);
            System.out.println("File saved successfully: " + file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
