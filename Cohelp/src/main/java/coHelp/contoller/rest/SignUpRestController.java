package coHelp.contoller.rest;

import coHelp.dto.SignUpDto;
import coHelp.service.SignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignUpRestController {


    @Autowired
    private SignUpService signUpService;

        @PreAuthorize("isAuthenticated().not")
        @PostMapping(value = "/rest/signUp")
        public ResponseEntity<Boolean> signIn(@RequestBody SignUpDto signUpData) {
            return ResponseEntity.ok(signUpService.signUp(signUpData));
        }

}
