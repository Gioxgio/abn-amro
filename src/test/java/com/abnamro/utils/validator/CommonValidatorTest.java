package com.bank.utils.validator;

import com.bank.utils.MessageHelper;
import com.bank.utils.UnitTestBase;
import jakarta.validation.ConstraintViolation;
import lombok.val;
import org.hibernate.validator.internal.engine.ConstraintViolationImpl;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

class CommonValidatorTest extends UnitTestBase {


    @InjectMocks
    private CommonValidator unitToTest;
    @Mock
    private LocalValidatorFactoryBean localValidatorFactoryBean;
    @Mock
    private MessageHelper messageHelper;

    @Test
    void validate_noViolations_success() {

        when(localValidatorFactoryBean.validate(any())).thenReturn(Set.of());

        unitToTest.validate(null);

        verify(localValidatorFactoryBean).validate(any());

        verifyNoMoreInteractions(localValidatorFactoryBean);
        verifyNoInteractions(messageHelper);
    }

    @Test
    void validate_violations_failure() {

        val message = "message";
        val property = "property";
        Map<String, String> violationsMap = new HashMap<>();

        val violation = getViolation(property, message);
        violationsMap.put(property, message);

        when(localValidatorFactoryBean.validate(any())).thenReturn(Set.of(violation));

        assertThrows(ValidationException.class, () -> unitToTest.validate(null));

        verify(localValidatorFactoryBean).validate(any());
        verify(messageHelper).getMessage("validation.exception", violationsMap);

        verifyNoMoreInteractions(localValidatorFactoryBean);
        verifyNoMoreInteractions(messageHelper);
    }

    private ConstraintViolation<Object> getViolation(String property, String message) {

        val path = PathImpl.createPathFromString(property);

        return ConstraintViolationImpl.forReturnValueValidation(
                null,
                null,
                null,
                message,
                null,
                null,
                null,
                null,
                path,
                null,
                null,
                null);
    }
}