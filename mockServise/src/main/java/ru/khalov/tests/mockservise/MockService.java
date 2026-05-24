package ru.khalov.tests.mockservise;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/response")
public class MockService {

    @GetMapping("/200")
    public ResponseEntity<String> response(){
        return ResponseEntity.ok().body("200");
    }

    @GetMapping("/500")
    public ResponseEntity<String> responseTwo(){
        return ResponseEntity.status(500).body("Some server error");
    }
}
