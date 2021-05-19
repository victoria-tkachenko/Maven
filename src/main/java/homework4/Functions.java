package homework4;


import io.qameta.allure.Step;

public class Functions {

    public boolean isNumberEven(Integer number) {
        return number % 2 == 0;
    }

    public boolean isNumberPositive(Integer number) {
        return number > 0;
    }

    public boolean isPrime(Integer number) {
        if (number < 0) {
            return false;
        } else if (number == 1) {
            return true;
        } else {
            for (int i = 2; i < number - 1; i++) {
                if (number % i == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isPalindrome(String word) {
        if (word.length() < 2) {
            return true;
        }
        if (word.charAt(0) != word.charAt(word.length() - 1)) {
            return false;
        }
        return isPalindrome(word.substring(1, word.length() - 1));
    }

}
