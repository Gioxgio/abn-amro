package com.bank.utils.validator;

import com.bank.api.request.CustomerRegisterRequest;
import com.bank.config.BusinessConfig;
import com.bank.data.repository.CustomerRepository;
import com.bank.utils.MessageHelper;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import java.time.LocalDate;

@Component
public class CustomerRequestValidator extends CommonValidator {

    private final BusinessConfig businessConfig;
    private final CustomerRepository customerRepository;

    public CustomerRequestValidator(
            @Autowired final BusinessConfig businessConfig,
            @Autowired final CustomerRepository customerRepository,
            @Autowired final LocalValidatorFactoryBean localValidatorFactoryBean,
            @Autowired final MessageHelper messageHelper) {

        super(localValidatorFactoryBean, messageHelper);

        this.businessConfig = businessConfig;
        this.customerRepository = customerRepository;
    }

    public void validate(final CustomerRegisterRequest request) throws ValidationException {

        super.validate(request);

        val country = request.getCountry();
        val dob = request.getDob();
        val username = request.getUsername();

        checkAllowedCountry(country);
        checkUnderage(dob);
        checkUsername(username);
    }

    private void checkAllowedCountry(final String country) {

        val allowedCountries = businessConfig.getAllowedCountries();

        if (!allowedCountries.contains(country)) {

            throwValidationException("validation.customer.register.not_allowed_country", country);
        }
    }

    private void checkUnderage(final LocalDate dob) {

        val lastAvailableDob = LocalDate.now().minusYears(18);

        if (dob.isAfter(lastAvailableDob)) {

            throwValidationException("validation.customer.register.underage");
        }
    }

    private void checkUsername(final String username) {

        if (customerRepository.existsByUsername(username)) {

            throwValidationException("validation.customer.register.username_unavailable", username);
        }
    }
}
