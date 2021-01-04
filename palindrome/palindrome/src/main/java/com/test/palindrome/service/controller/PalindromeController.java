package com.test.palindrome.service.controller;

import com.test.palindrome.service.service.PalindromeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/")
public class PalindromeController {

    private final PalindromeService service;

    public PalindromeController(PalindromeService service){
        this.service = service;
    }

    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(method = RequestMethod.GET, path = "/getPalindrome/{palindromeString}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public String getPalindrome(@PathVariable("palindromeString") String palindromeString) {
        return service.getPalindrome(palindromeString);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(method = RequestMethod.POST, path = "/savePalindrome/{palindromeString}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public void savePalindrome(@PathVariable("palindromeString") String palindromeString) {
        service.savePalindrome(palindromeString);
    }

}
