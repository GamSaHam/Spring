package springbook.spring.service;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.validation.ValidationUtils;
import springbook.spring.dao.User;

public class UserRegistValidator implements Validator {


    public boolean supports(Class<?> aClass) {
        return false;
    }

    public void validate(Object o, Errors errors) {
        User user = (User)o;

        if(user.getId() == null || "".equals(user.getId())){
            errors.reject("id", "required");
        }


        /*ValidationUtils.rejectIfEmptyOrWhitespace(errors,"password", "required");
*/

    }
}
