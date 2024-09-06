package com.github.idan.koblik;

/**
 * Thrown when unsupported operation being performed on game model record
 */
/* package-private */ class UnsupportedGameModelException extends RuntimeException {

    public UnsupportedGameModelException(String reason) {
        super(reason);
    }

}
