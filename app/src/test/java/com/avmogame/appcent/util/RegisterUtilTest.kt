package com.avmogame.appcent.util


import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class RegisterUtilTest{

    lateinit var RU : RegisterUtil

    @Before
    fun setUp(){
        RU = RegisterUtil
    }

    @Test
    fun `empty username returns false`(){
        val result = RU.validateRegistrationInput(
            "",
            "123",
            "123"
        )
        assertFalse(result)
    }

    @Test
    fun `empty password returns false`(){
        val result = RU.validateRegistrationInput(
            "jacket",
            "",
            ""
        )
        assertFalse(result)
    }
    @Test
    fun `less than 2 digit password returns false`(){
        val result = RU.validateRegistrationInput(
            "tom",
            "1",
            "1"
        )
        assertFalse(result)
    }
    @Test
    fun `username already exist returns false`(){
        val result = RU.validateRegistrationInput(
            "jack",
            "123",
            "123"
        )
        assertFalse(result)
    }

}