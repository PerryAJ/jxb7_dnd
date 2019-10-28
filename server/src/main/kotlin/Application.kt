package es.perryjon.purdom


import io.javalin.Javalin
import io.javalin.http.staticfiles.Location
import org.slf4j.Logger
import org.slf4j.LoggerFactory


val LOG: Logger = LoggerFactory.getLogger("main")

fun main(args: Array<String>) {

    LOG.info("Initializing application...")
    val port = 8099

    // create the web server
    val app: Javalin = Javalin.create { config ->
        LOG.info("Configuring server, handling requests at port $port...")
        config.addStaticFiles("static", Location.EXTERNAL)
    }.start(port)
}
