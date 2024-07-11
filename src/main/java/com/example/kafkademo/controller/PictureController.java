package com.example.kafkademo.controller;

import static com.example.kafkademo.config.KafkaTopicConfiguration.PICTURE_EVENTS;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class PictureController {
    private final KafkaTemplate<String, byte[]> kafkaTemplate;



    @PostMapping("/upload")
    public void upload(@RequestParam("file")MultipartFile pic) throws IOException {
        kafkaTemplate.send(PICTURE_EVENTS, "some_file_name", pic.getBytes());
    }
}
