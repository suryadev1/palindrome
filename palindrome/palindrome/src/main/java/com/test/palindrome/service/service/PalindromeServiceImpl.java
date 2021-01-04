package com.test.palindrome.service.service;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class PalindromeServiceImpl implements PalindromeService {
    static Map<String,String> store = new HashMap<>();
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public String getPalindrome(String palindromeString) {
        if (palindromeString.isEmpty()) {
            log.info("Empty string");
        }
        if(store.get(palindromeString) != null) {
           log.info("already in store");
        }
        String palindrome = findLongestPalindrome(palindromeString);
        log.info("Longest palindrome is : ",palindromeString,palindrome);
        savePalindromeString(palindromeString, palindrome);
        return palindrome;
    }

    @Override
    public void savePalindrome(String palindromeString) {
        String palindrome = findLongestPalindrome(palindromeString);
        savePalindromeString(palindromeString, palindrome);
    }

    private void savePalindromeString(String palindromeString, String palindrome) {
        store.put(palindromeString, palindrome);
    }

    public String findLongestPalindrome(String palindromeString) {
        if (palindromeString.length() == 1) {
            return palindromeString;
        }

        String longest = palindromeString.substring(0, 1);
        for (int i = 0; i < palindromeString.length(); i++) {
            String tmp = helper(palindromeString, i, i);
            if (tmp.length() > longest.length()) {
                longest = tmp;
            }

            tmp = helper(palindromeString, i, i + 1);
            if (tmp.length() > longest.length()) {
                longest = tmp;
            }
        }
        return longest;
    }

    public String helper(String s, int begin, int end) {
        while (begin >= 0 && end <= s.length() - 1 && s.charAt(begin) == s.charAt(end)) {
            begin--;
            end++;
        }
        return s.substring(begin + 1, end);
    }

    private String getPalindromeString(String palindromeString) {
        if(!store.containsKey(palindromeString)){
            log.info("not saved in db", palindromeString);
        }
        return store.get(palindromeString);
    }
}
