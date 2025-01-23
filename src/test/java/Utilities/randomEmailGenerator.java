package Utilities;

import org.apache.commons.lang3.RandomStringUtils;

public class randomEmailGenerator {

    public static String getComplexRandomMail()
    {
        String lowerChar="abcdefghijklmnopqrstuvwxyz";
        String upperChar="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numberChar="0123456789";
        return RandomStringUtils.random(3,lowerChar)+RandomStringUtils.random(3,upperChar)
                +RandomStringUtils.random(3,numberChar)
                +System.currentTimeMillis()
                +"@mail.com";
    }
}
