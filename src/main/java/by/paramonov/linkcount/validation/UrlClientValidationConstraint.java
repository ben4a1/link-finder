package by.paramonov.linkcount.validation;

import org.primefaces.validate.bean.AbstractClientValidationConstraint;

public class UrlClientValidationConstraint  extends AbstractClientValidationConstraint {

    public static final String MESSAGE_METADATA = "data-p-url-msg";

    public UrlClientValidationConstraint(){
        super(null, MESSAGE_METADATA);
    }
    @Override
    public String getValidatorId() {
        return Url.class.getSimpleName();
    }
}
