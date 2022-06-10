package com.vandenbreemen.kevincommon.nbl

/**
 * Configuration for the No Bullshit Logging
 */
data class NBLConfiguration(var level: Level) {
    fun level(level: Level): NBLConfiguration {
        this.level = level
        return this
    }
}