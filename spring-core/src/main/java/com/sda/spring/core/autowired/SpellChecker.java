package com.sda.spring.core.autowired;

import org.springframework.stereotype.Component;

@Component
public class SpellChecker {

    public SpellChecker() {
        System.out.println("Spellchecker Constructor Activated!");
    }

    public void checkSpelling(){
        System.out.println("Check thy spelling, pheasant!");
    }
}
