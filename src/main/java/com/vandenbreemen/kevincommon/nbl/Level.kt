package com.vandenbreemen.kevincommon.nbl

enum class Level {
    FATAL,
    ERROR,
    WARN,
    INFO,
    DEBUG,
    TRACE
    ;
    fun isSatisfied(level: Level): Boolean {
        return level.ordinal <= this.ordinal
    }
}