package com.discoverme.backend.user.registration;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {PasswordValidator.class})
@ExtendWith(SpringExtension.class)
class PasswordValidatorDiffblueTest {
    @Autowired
    private PasswordValidator passwordValidator;

    /**
     * Method under test: {@link PasswordValidator#test(String)}
     */
    @Test
    void testTest() {
        // Arrange, Act and Assert
        assertFalse(passwordValidator.test("Psw"));
        assertTrue(passwordValidator.test("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$"));
        assertFalse(passwordValidator.test(""));
        assertTrue(passwordValidator.test("U^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$"));
        assertTrue(passwordValidator.test("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$U"));
        assertTrue(passwordValidator
                .test("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?"
                        + "=.*[@#$%^&+=])(?=\\S+$).{8,}$"));
        assertTrue(passwordValidator.test("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$Psw"));
        assertTrue(passwordValidator.test("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$42"));
        assertTrue(passwordValidator.test("Psw^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$"));
        assertTrue(passwordValidator.test("42^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$"));
        assertTrue(passwordValidator.test("UU^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$"));
        assertTrue(passwordValidator.test("U^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$U"));
        assertTrue(passwordValidator
                .test("U^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])("
                        + "?=.*[@#$%^&+=])(?=\\S+$).{8,}$"));
        assertTrue(passwordValidator.test("U^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$Psw"));
        assertTrue(passwordValidator.test("U^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$42"));
        assertTrue(passwordValidator.test("UPsw^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$"));
        assertTrue(passwordValidator.test("U42^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$"));
        assertTrue(passwordValidator.test("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$UU"));
        assertTrue(passwordValidator
                .test("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$U^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])("
                        + "?=.*[@#$%^&+=])(?=\\S+$).{8,}$"));
        assertTrue(passwordValidator.test("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$UPsw"));
        assertTrue(passwordValidator.test("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$U42"));
        assertTrue(passwordValidator
                .test("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?"
                        + "=.*[@#$%^&+=])(?=\\S+$).{8,}$U"));
        assertTrue(passwordValidator
                .test("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?"
                        + "=.*[@#$%^&+=])(?=\\S+$).{8,}$^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$"));
        assertTrue(passwordValidator
                .test("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?"
                        + "=.*[@#$%^&+=])(?=\\S+$).{8,}$Psw"));
        assertTrue(passwordValidator
                .test("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?"
                        + "=.*[@#$%^&+=])(?=\\S+$).{8,}$42"));
        assertTrue(passwordValidator.test("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$PswU"));
        assertTrue(passwordValidator
                .test("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$Psw^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]"
                        + ")(?=.*[@#$%^&+=])(?=\\S+$).{8,}$"));
        assertTrue(passwordValidator.test("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$PswPsw"));
        assertTrue(passwordValidator.test("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$Psw42"));
        assertTrue(passwordValidator.test("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$42U"));
        assertTrue(passwordValidator
                .test("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$42^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])"
                        + "(?=.*[@#$%^&+=])(?=\\S+$).{8,}$"));
        assertTrue(passwordValidator.test("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$42Psw"));
        assertTrue(passwordValidator.test("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$4242"));
        assertTrue(passwordValidator.test("PswU^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$"));
        assertTrue(passwordValidator.test("Psw^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$U"));
        assertTrue(passwordValidator
                .test("Psw^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]"
                        + ")(?=.*[@#$%^&+=])(?=\\S+$).{8,}$"));
        assertTrue(passwordValidator.test("Psw^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$Psw"));
        assertTrue(passwordValidator.test("Psw^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$42"));
        assertTrue(passwordValidator.test("PswPsw^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$"));
        assertTrue(passwordValidator.test("Psw42^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$"));
        assertTrue(passwordValidator.test("42U^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$"));
        assertTrue(passwordValidator.test("42^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$U"));
        assertTrue(passwordValidator
                .test("42^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])"
                        + "(?=.*[@#$%^&+=])(?=\\S+$).{8,}$"));
        assertTrue(passwordValidator.test("42^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$Psw"));
        assertTrue(passwordValidator.test("42^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$42"));
        assertTrue(passwordValidator.test("42Psw^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$"));
        assertTrue(passwordValidator.test("4242^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$"));
    }
}
