package com.abnamro.mapper;

import com.abnamro.data.entity.Account;
import lombok.val;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class AccountMapper {

    public Account fromScratch(final int idCustomer, final String type) {

        val number = generateRandomNumber(10);

        return Account.builder()
                      .number(number)
                      .iban(generateIBAN(number))
                      .balance(generateRandomBigDecimal(BigDecimal.ZERO, BigDecimal.valueOf(1000000)))
                      .currency("€")
                      .type(type)
                      .id_customer(idCustomer)
                      .build();
    }

    private String generateIBAN(final String number) {

        val country = "NL";
        val checksum = generateRandomNumber(2);
        val bic = "ABNA";

        return country + checksum + bic + number;
    }

    private String generateRandomNumber(final int length) {

        return RandomStringUtils.random(length, false, true);
    }

    public static BigDecimal generateRandomBigDecimal(BigDecimal min, BigDecimal max) {

        BigDecimal randomBigDecimal = min.add(BigDecimal.valueOf(Math.random()).multiply(max.subtract(min)));
        return randomBigDecimal.setScale(2, RoundingMode.HALF_UP);
    }
}
