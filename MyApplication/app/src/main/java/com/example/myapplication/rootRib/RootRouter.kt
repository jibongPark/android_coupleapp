package com.example.myapplication.rootRib

import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link RootRibBuilder.RootRibScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class RootRouter(
    view: RootRibView,
    interactor: RootInteractor,
    component: RootBuilder.Component) : ViewRouter<RootRibView, RootInteractor>(view, interactor, component)
