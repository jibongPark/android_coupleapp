package com.example.myapplication.rootRib

import android.view.ViewGroup
import com.example.myapplication.logged_out.LoggedOutBuilder
import com.example.myapplication.logged_out.LoggedOutRouter
import com.example.myapplication.menu.MenuBuilder
import com.example.myapplication.menu.MenuRouter
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
    loggedOutBuilder: LoggedOutBuilder,
    menuBuilder: MenuBuilder
) : ViewRouter<RootView, RootInteractor>(view, interactor, component) {

    private var loggedOutBuilder = loggedOutBuilder
    private var menuBuilder = menuBuilder

    private var loggedoutRouter: LoggedOutRouter? = null
    private var menuRouter: MenuRouter? = null


    fun attachLoggedOut() {
        loggedoutRouter = loggedOutBuilder.build(view)
        attachChild(loggedoutRouter!!)
        view.addViewToMain(loggedoutRouter!!.view)
    }

    fun detachLoggedOut() {
        if(loggedoutRouter != null) {
            detachChild(loggedoutRouter!!)
            view.removeView(loggedoutRouter!!.view)
        }
        loggedoutRouter = null;
    }

    fun attachMenu() {
        menuRouter = menuBuilder.build(view)
        attachChild(menuRouter!!)
        view.addViewToMenu(menuRouter!!.view)
    }
}



