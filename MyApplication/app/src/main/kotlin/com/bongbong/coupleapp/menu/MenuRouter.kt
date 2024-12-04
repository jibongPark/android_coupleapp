package com.bongbong.coupleapp.menu

import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link MenuBuilder.MenuScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class MenuRouter(
    view: MenuView,
    interactor: MenuInteractor,
    component: MenuBuilder.Component) : ViewRouter<MenuView, MenuInteractor>(view, interactor, component)
