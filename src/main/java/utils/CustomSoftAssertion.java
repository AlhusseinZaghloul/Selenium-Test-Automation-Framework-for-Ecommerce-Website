package utils;

import org.testng.asserts.SoftAssert;

/**
 * CustomSoftAssertion is a utility class that extends SoftAssert to provide custom assertion functionality.
 * It allows for soft assertions in test cases, enabling the collection of multiple assertion failures
 * without stopping the test execution immediately.
 */
public class CustomSoftAssertion extends SoftAssert {

    public static CustomSoftAssertion softAssertion = new CustomSoftAssertion();

    public static void customAssertAll() {
        try {
            softAssertion.assertAll("Custom Soft Assertion");
        } catch (Exception e) {
            System.out.println("Custom Soft Assertion Failed");
        }
    }



}
