package com.bongbong.coupleapp.database

import com.uber.rib.core.Router

/**
 * Adds and removes children of {@link DatabaseBuilder.DatabaseScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class DatabaseRouter(
    interactor: DatabaseInteractor,
    component: DatabaseBuilder.Component) : Router<DatabaseInteractor>(interactor, component)
