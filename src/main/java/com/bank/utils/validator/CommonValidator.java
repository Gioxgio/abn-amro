package com.bank.utils.validator;

import com.bank.utils.MessageHelper;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class CommonValidator {

    private final LocalValidatorFactoryBean localValidatorFactoryBean;
    private final MessageHelper messageHelper;

    public void validate(final Object obj) throws ValidationException {

        val violations = localValidatorFactoryBean.validate(obj);
        if (CollectionUtils.isEmpty(violations)) {
            return;
        }

        final Map<String, String> errors = new HashMap<>();
        for (val violation : violations) {

            errors.put(((PathImpl) violation.getPropertyPath()).getLeafNode()
                                                               .getName(),
                       violation.getMessage());
        }

        throwValidationException("validation.exception", errors);
    }

    protected void throwValidationException(final String messageCode, final Object... args) {

        throw new ValidationException(messageHelper.getMessage(messageCode, args));
    }
}
