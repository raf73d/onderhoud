package be.vdab.onderhoud.users;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserBestaatNietException extends RuntimeException{
    UserBestaatNietException(){
        super("User bestaat niet");
    }
}
