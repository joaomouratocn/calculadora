package br.com.programadorjm.calculator

enum class KeyValues(val value: Char){
    ZERO('0'),
    ONE('1'),
    TWO('2'),
    THREE('3'),
    FOUR('4'),
    FIVE('5'),
    SIX('6'),
    SEVEN('7'),
    EIGHT('8'),
    NINE('9'),
    SUM('+'),
    SUBTRACT('-'),
    MULTIPLY('×'),
    DIVIDE('÷'),
    DOT('.'),
    AC('r'), //reset
    BS('b'), //backspace
    EQUAL('=')
}