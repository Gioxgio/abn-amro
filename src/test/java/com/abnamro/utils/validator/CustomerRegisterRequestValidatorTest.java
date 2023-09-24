package com.abnamro.utils.validator;

import com.abnamro.api.request.CustomerRegisterRequest;
import com.abnamro.config.BusinessConfig;
import com.abnamro.data.repository.CustomerRepository;
import com.abnamro.utils.MessageHelper;
import com.abnamro.utils.UnitTestBase;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

class CustomerRegisterRequestValidatorTest extends UnitTestBase {

    @InjectMocks
    private CustomerRegisterRequestValidator unitToTest;

    @Mock
    private BusinessConfig businessConfig;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private LocalValidatorFactoryBean localValidatorFactoryBean;
    @Mock
    private MessageHelper messageHelper;

    @Test
    void validate_commonValidationFails_failure() {

        doThrow(new ValidationException("")).when(localValidatorFactoryBean).validate(any());

        assertThrows(ValidationException.class, () -> unitToTest.validate(null));

        verify(localValidatorFactoryBean).validate(any());

        verifyNoMoreInteractions(localValidatorFactoryBean);
        verifyNoInteractions(businessConfig);
        verifyNoInteractions(customerRepository);
        verifyNoInteractions(messageHelper);
    }

    @Test
    void validate_countryNotAllowed_failure() {

        val country = "NL";
        val customer = new CustomerRegisterRequest(null, null, null, null, null, country, null);

        when(businessConfig.getAllowedCountries()).thenReturn(Set.of("IT"));

        assertThrows(ValidationException.class, () -> unitToTest.validate(customer));

        verify(localValidatorFactoryBean).validate(any());
        verify(businessConfig).getAllowedCountries();
        verify(messageHelper).getMessage("validation.customer.register.not_allowed_country", country);

        verifyNoMoreInteractions(localValidatorFactoryBean);
        verifyNoMoreInteractions(businessConfig);
        verifyNoMoreInteractions(messageHelper);
        verifyNoInteractions(customerRepository);
    }

    @Test
    void validate_underage_failure() {

        val country = "NL";
        val customer = new CustomerRegisterRequest(null, null, LocalDate.now(), null, null, country, null);

        when(businessConfig.getAllowedCountries()).thenReturn(Set.of(country));

        assertThrows(ValidationException.class, () -> unitToTest.validate(customer));

        verify(localValidatorFactoryBean).validate(any());
        verify(businessConfig).getAllowedCountries();
        verify(messageHelper).getMessage("validation.customer.register.underage");

        verifyNoMoreInteractions(localValidatorFactoryBean);
        verifyNoMoreInteractions(businessConfig);
        verifyNoMoreInteractions(messageHelper);
        verifyNoInteractions(customerRepository);
    }

    @Test
    void validate_usernameUnavailable_failure() {

        val country = "NL";
        val dob = LocalDate.now().minusYears(19);
        val username = "username";

        val customer = new CustomerRegisterRequest(null, null, dob, null, username, country, null);

        when(businessConfig.getAllowedCountries()).thenReturn(Set.of(country));
        when(customerRepository.existsByUsername(any())).thenReturn(true);

        assertThrows(ValidationException.class, () -> unitToTest.validate(customer));

        verify(localValidatorFactoryBean).validate(any());
        verify(businessConfig).getAllowedCountries();
        verify(customerRepository).existsByUsername(username);
        verify(messageHelper).getMessage("validation.customer.register.username_unavailable", username);

        verifyNoMoreInteractions(localValidatorFactoryBean);
        verifyNoMoreInteractions(businessConfig);
        verifyNoMoreInteractions(messageHelper);
        verifyNoMoreInteractions(customerRepository);
    }

    @Test
    void validate_usernameUnavailable_success() {

        val country = "NL";
        val dob = LocalDate.now().minusYears(19);
        val username = "username";

        val customer = new CustomerRegisterRequest(null, null, dob, null, username, country, null);

        when(businessConfig.getAllowedCountries()).thenReturn(Set.of(country));
        when(customerRepository.existsByUsername(any())).thenReturn(false);

        unitToTest.validate(customer);

        verify(localValidatorFactoryBean).validate(any());
        verify(businessConfig).getAllowedCountries();
        verify(customerRepository).existsByUsername(username);

        verifyNoMoreInteractions(localValidatorFactoryBean);
        verifyNoMoreInteractions(businessConfig);
        verifyNoMoreInteractions(customerRepository);
        verifyNoInteractions(messageHelper);
    }
}