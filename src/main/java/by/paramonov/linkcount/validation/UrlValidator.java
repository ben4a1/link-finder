package by.paramonov.linkcount.validation;

import org.primefaces.validate.ClientValidator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.executable.ExecutableValidator;
import javax.validation.metadata.BeanDescriptor;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

public class UrlValidator implements Validator, ClientValidator {

    private static final String URL_PATTERN = "/^((http|https|ftp):\\/\\/)?(([A-Z0-9][A-Z0-9_-]*)(\\.[A-Z0-9][A-Z0-9_-]*)+)/i";

    private Pattern pattern;

    public UrlValidator() {
        pattern = Pattern.compile(URL_PATTERN);
    }


    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException{
        if (value == null) {
            return;
        }
        if (!pattern.matcher(value.toString()).matches()){
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Validation Error",
                    value + " is not a valid email;"));
        }
    }

    @Override
    public <T> Set<ConstraintViolation<T>> validate(T t, Class<?>... classes) {
        return null;
    }

    @Override
    public <T> Set<ConstraintViolation<T>> validateProperty(T t, String s, Class<?>... classes) {
        return null;
    }

    @Override
    public <T> Set<ConstraintViolation<T>> validateValue(Class<T> aClass, String s, Object o, Class<?>... classes) {
        return null;
    }

    @Override
    public BeanDescriptor getConstraintsForClass(Class<?> aClass) {
        return null;
    }

    @Override
    public <T> T unwrap(Class<T> aClass) {
        return null;
    }

    @Override
    public ExecutableValidator forExecutables() {
        return null;
    }

    @Override
    public Map<String, Object> getMetadata() {
        return null;
    }

    @Override
    public String getValidatorId() {
        return "custom.urlValidator";
    }
}
