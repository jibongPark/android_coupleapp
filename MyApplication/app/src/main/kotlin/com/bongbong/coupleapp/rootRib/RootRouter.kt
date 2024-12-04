package com.bongbong.coupleapp.rootRib

import com.bongbong.coupleapp.loggedIn.LoggedInBuilder
import com.bongbong.coupleapp.loggedIn.LoggedInRouter
import com.bongbong.coupleapp.menu.MenuBuilder
import com.bongbong.coupleapp.menu.MenuRouter
import com.uber.rib.core.ViewRouter
import com.bongbong.coupleapp.rootRib.RootBuilder.Component


/**
 * Adds and removes children of {@link RootRibBuilder.RootRibScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class RootRouter(
    view: RootView,
    interactor: RootInteractor,
    component: Component,
    loggedInBuilder: LoggedInBuilder,
    menuBuilder: MenuBuilder
) : ViewRouter<RootView, RootInteractor>(view, interactor, component) {

    private var menuBuilder = menuBuilder
    private var loggedInBuilder = loggedInBuilder

    private var menuRouter: MenuRouter? = null
    private var loggedInRouter : LoggedInRouter? = null


    fun attachLoggedIn() {
        loggedInRouter = loggedInBuilder.build(view)
        attachChild(loggedInRouter!!)
        view.addViewToMain(loggedInRouter!!.view)
    }

    fun attachMenu() {
        menuRouter = menuBuilder.build(view)
        attachChild(menuRouter!!)
        view.addViewToMenu(menuRouter!!.view)
    }

    fun detachMenu() {
        if(menuRouter != null) {
            detachChild(menuRouter!!)
            view.removeViewToMenu(menuRouter!!.view)
        }

        menuRouter = null;
    }
}



