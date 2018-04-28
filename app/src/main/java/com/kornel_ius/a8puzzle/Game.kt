package com.kornel_ius.a8puzzle

/**
 * Created by Kornel_ius.
 */
class Game {

    var playerName: String? = null
    var time: Int = 0

    constructor (playerName: String, time: Int) {
        this.playerName = playerName
        this.time = time
    }
}