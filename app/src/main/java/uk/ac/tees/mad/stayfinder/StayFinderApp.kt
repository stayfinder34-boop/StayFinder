package uk.ac.tees.mad.stayfinder

import android.app.Application
import uk.ac.tees.mad.stayfinder.utils.Container


/**
 *Application class is a top level class that is alive for the entire lifecycle of
 * application.
 * We are using manual dependency injection so we have to instantiate the object here
 * in application class else will have chance of memory leak which is basically not good
 * by the time it will lead to lag in application which is not a good user ux
 **/

class StayFinderApp : Application(){
    lateinit var container: Container

    override fun onCreate() {
        super.onCreate()
        container = Container(applicationContext)
    }
}


