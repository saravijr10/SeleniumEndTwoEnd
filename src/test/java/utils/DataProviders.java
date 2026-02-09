package utils;

import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(name = "validLoginData")
    public Object[][] validLoginData() {
        return ExcelUtils.readLoginDataByType(
                "src/test/resources/testdata/loginData.xlsx",
                "LoginData",
                "valid"
        );
    }

    @DataProvider(name = "invalidLoginData")
    public Object[][] invalidLoginData() {
        return ExcelUtils.readLoginDataByType(
                "src/test/resources/testdata/loginData.xlsx",
                "LoginData",
                "invalid"
        );
    }

}
