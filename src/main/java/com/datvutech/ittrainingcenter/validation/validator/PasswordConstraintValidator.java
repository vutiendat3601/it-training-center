package com.datvutech.ittrainingcenter.validation.validator;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.passay.LengthRule;
import org.passay.MessageResolver;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.PropertiesMessageResolver;
import org.passay.RuleResult;
import org.springframework.util.ResourceUtils;

import com.datvutech.ittrainingcenter.validation.annotation.ValidPassword;

public class PasswordConstraintValidator implements ConstraintValidator<ValidPassword, String> {

    @Override
    public boolean isValid(String password, ConstraintValidatorContext context) {
        // customizing validation messages
        Properties props = new Properties();
        Reader reader;
        try {
            reader = new FileReader(ResourceUtils.getFile("classpath:validation/password-validation.properties"));
            props.load(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        MessageResolver resolver = new PropertiesMessageResolver(props);
        PasswordValidator validator = new PasswordValidator(resolver, Arrays.asList(
                // length between 6 and 16 characters
                new LengthRule(6, 16)
        // at least one upper-case character
        // new CharacterRule(EnglishCharacterData.UpperCase, 1),
        // at least one lower-case character
        // new CharacterRule(EnglishCharacterData.LowerCase, 1),
        // at least one digit character
        // new CharacterRule(EnglishCharacterData.Digit, 1),
        // at least one symbol (special character)
        // new CharacterRule(EnglishCharacterData.Special, 1),
        // no whitespace
        // new WhitespaceRule()
        // rejects passwords that contain a sequence of >= 5 characters alphabetical
        // (e.g. abcdef)
        // new IllegalSequenceRule(EnglishSequenceData.Alphabetical, 5, false),
        // rejects passwords that contain a sequence of >= 5 characters numerical (e.g.
        // 12345)
        // new IllegalSequenceRule(EnglishSequenceData.Numerical, 5, false)
        ));
        RuleResult result = validator.validate(new PasswordData(password));
        if (result.isValid()) {
            return true;
        }
        List<String> messages = validator.getMessages(result);
        String messageTemplate = String.join(",",
                messages);
        context.buildConstraintViolationWithTemplate(messageTemplate).addConstraintViolation()
                .disableDefaultConstraintViolation();
        return false;
    }

}
