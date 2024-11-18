package com.example.myapplication.rootRib

import android.view.ViewGroup
import com.example.myapplication.logged_out.LoggedOutBuilder
import com.example.myapplication.logged_out.LoggedOutRouter
import com.uber.rib.core.ViewRouter
import com.example.myapplication.rootRib.RootBuilder.Component
import com.uber.rib.core.Router


/**
 * Adds and removes children of {@link RootRibBuilder.RootRibScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class RootRouter(
    view: RootView,
    interactor: RootInteractor,
    component: Component,
    loggedOutBuilder: LoggedOutBuilder
) : ViewRouter<RootView, RootInteractor>(view, interactor, component) {

    private final var loggedOutBuilder = loggedOutBuilder

    private var loggedoutRouter: LoggedOutRouter? = null


    fun attachLoggedOut() {
        loggedoutRouter = loggedOutBuilder.build(view)
        attachChild(loggedoutRouter!!)
        view.addView(loggedoutRouter!!.view)
    }

    fun detachLoggedOut() {
        if(loggedoutRouter != null) {
            detachChild(loggedoutRouter!!)
            view.removeView(loggedoutRouter!!.view)
        }
        loggedoutRouter = null;
    }
}


