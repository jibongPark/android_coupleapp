package com.example.myapplication.loggedIn

import android.view.View

import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link LoggedInBuilder.LoggedInScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class LoggedInRouter(
    view: LoggedInView,
    interactor: LoggedInInteractor,
    component: LoggedInBuilder.Component) : ViewRouter<LoggedInView, LoggedInInteractor>(view, interactor, component)
