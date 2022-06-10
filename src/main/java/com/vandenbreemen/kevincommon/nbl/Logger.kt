package com.vandenbreemen.kevincommon.nbl

import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*


class Logger(private val name: String) {

    companion object {

        val initialized by lazy {
            println("""
                       XXXXXXXX
   /|             XXXXXXXX|\XXXXXXXXX
 /*/         XXXXXXXXXXXXXX\*\XXXXXXXXXXXX
|**\       X _____XXXXXXXXX/**|XXXXXXXXXXXXX
|***\    X_/       \_     /***|___XXXXXXXXXXXX
 \*******             *******/   XXXXX \\XXXXXXX
   \****    /     \    *****/  XXXXX     \\XXXXXXX
    XXXX|   0     0   |      XXXXX          \XXXXXXX
   XXXXX |           |     XXXXX             \XXXXXXX
  XXXXXX  \         /    XXXXX                |________//
   XXXXXX  \       /   XXXXX                  |XXXXXX
     XXXXXX | O_O |  XXXXX                   ||XXXXX
       XXXXX \ _ / XXXXX                      \XXX
         XXXX| : |XXXX                 /\      \    _
           XXX\_/XXX |\__\      _____/ \  \     )  |_|
             XXXXXX< |    | |         XX| |X\_  |     _
               XXX/  |X   <_>      XXXX/  |  |  |    |_|
                 |___|XXXX| |XXXXXXXXX|___|  |   \
                   XXXXXX/   \XXXXXXXX       |____|
                        |_____|XX
            """.trimIndent())
            println("No Bullshit Logging has been activated.  Happy logging!")
        }

        fun getLogger(name: String): Logger {
            initialized
            return Logger(name)
        }

        fun getLogger(clazz: Class<*>): Logger {
            initialized
            return getLogger(clazz.canonicalName)
        }
    }

    private var level: Level = Level.INFO

    private fun doLog(message: () -> String, level: Level) {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS")
        val str = "${sdf.format(Date())}\t${name} - ${level.name}\t${message()}"
        System.out.println(str)
    }

    private fun log(message: () -> String, level: Level, throwable: Throwable? = null, ) {
        if(this.level.isSatisfied(level)){
            throwable?.let { thrbl->
                doLog(
                    {
                        "${message()}\n${thrbl.stackTraceToString()}"
                    }, level
                )
                return
            }
            doLog(message, level)
        }
    }

    fun fatal(message: () -> String)  = log(message, Level.FATAL)
    fun error(message: () -> String)  = log(message, Level.ERROR)
    fun warn(message: () -> String)  = log(message, Level.WARN)
    fun info(message: () -> String)  = log(message, Level.INFO)
    fun debug(message: () -> String)  = log(message, Level.DEBUG)
    fun trace(message: ()->String) = log(message, Level.TRACE)

    fun fatal(throwable: Throwable, message: () -> String) = log(message, Level.FATAL, throwable)
    fun error(throwable: Throwable, message: () -> String) = log(message, Level.ERROR, throwable)
    fun warn(throwable: Throwable, message: () -> String) = log(message, Level.WARN, throwable)
    fun info(throwable: Throwable, message: () -> String) = log(message, Level.INFO, throwable)
    fun debug(throwable: Throwable, message: () -> String) = log(message, Level.DEBUG, throwable)
    fun trace(throwable: Throwable, message: () -> String) = log(message, Level.TRACE, throwable)

}

fun main() {
    with(Logger.getLogger(Level::class.java)) {
        info { "INFO" }
        debug { "DEBUG" }
        trace { "TRACE" }
        warn { "WARN" }
        error { "ERROR" }
        fatal { "FATAL" }
        fatal (Throwable()){ "FATAL ERROR OCCURRED AND MUST NOW EXIT" }
    }

}