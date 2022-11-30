package by.paramonov.linkcount.validation;

import lombok.Data;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

@Named
@RequestScoped
@Data
public class CustomValidationView {
    private String text;

    @Url(message = "must be a valid URL")
    private String url;
}
