package com.alvarozarza94.simpsons.common.utils;

import org.apache.commons.lang3.RandomStringUtils;

public class RandomStringFactory {
    public static String createRandomString(){
      return RandomStringUtils.randomAlphabetic(24);
    };
}
