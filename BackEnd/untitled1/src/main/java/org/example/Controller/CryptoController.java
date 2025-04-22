package org.example.Controller;

import org.example.Util.CryptoUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class CryptoController {

    private static final String SECRET_KEY = "SaiKiran12345678"; // 16-char AES key
    private static final String SALT = "Sai Kiran";

    @PostMapping("/encrypt")
    public ResponseEntity<String> encrypt(@RequestBody String plainText) {
        try {
            String encrypted = CryptoUtil.encrypt(plainText, SECRET_KEY, SALT);
            return ResponseEntity.ok(encrypted);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Encryption Error");
        }
    }

    @PostMapping("/decrypt")
    public ResponseEntity<String> decrypt(@RequestBody String encryptedText) {
        try {
            String decrypted = CryptoUtil.decrypt(encryptedText, SECRET_KEY, SALT);
            return ResponseEntity.ok(decrypted);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Decryption Error");
        }
    }
}
