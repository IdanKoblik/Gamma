package com.github.idan.koblik;

/**
 * @hidden
 */
/* package-private */ class UnsupportedGameModel extends RuntimeException {

    public UnsupportedGameModel(String reason) {
        super(reason);
    }

}
