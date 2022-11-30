package by.paramonov.linkcount.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class UrlConstraintValidator implements ConstraintValidator<Url, String> {

    private static final String URL_PATTERN = "/^((http|https|ftp):\\/\\/)?(([A-Z0-9][A-Z0-9_-]*)(\\.[A-Z0-9][A-Z0-9_-]*)+)/i";
    private Pattern pattern;
    @Override
    public void initialize(Url url) {
        pattern = Pattern.compile(URL_PATTERN);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext cvc) {
        if (value == null) {
            return true;
        }
        else {
            return pattern.matcher(value).matches();
        }
    }
}
