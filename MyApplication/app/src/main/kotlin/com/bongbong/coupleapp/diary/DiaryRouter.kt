package com.bongbong.coupleapp.diary

import com.uber.rib.core.ViewRouter

/**
 * Adds and removes children of {@link DiaryBuilder.DiaryScope}.
 *
 * TODO describe the possible child configurations of this scope.
 */
class DiaryRouter(
    view: DiaryView,
    interactor: DiaryInteractor,
    component: DiaryBuilder.Component) : ViewRouter<DiaryView, DiaryInteractor>(view, interactor, component)
