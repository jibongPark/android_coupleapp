package com.bongbong.coupleapp.calendar

import android.view.LayoutInflater
import android.view.ViewGroup
import com.uber.rib.core.InteractorBaseComponent
import com.uber.rib.core.ViewBuilder
import dagger.Binds
import dagger.BindsInstance
import dagger.Provides
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy.CLASS
import javax.inject.Qualifier
import javax.inject.Scope

/**
 * Builder for the {@link LoggedOutScope}.
 *
 * TODO describe this scope's responsibility as a whole.
 */
open class CalendarBuilder(dependency: ParentComponent) : ViewBuilder<CalendarView, CalendarRouter, CalendarBuilder.ParentComponent>(dependency) {

  /**
   * Builds a new [CalendarRouter].
   *
   * @param parentViewGroup parent view group that this router's view will be added to.
   * @return a new [CalendarRouter].
   */
  fun build(parentViewGroup: ViewGroup): CalendarRouter {
    val view = createView(parentViewGroup)
    val interactor = CalendarInteractor()
    val component = DaggerCalendarBuilder_Component.builder()
        .parentComponent(dependency)
        .view(view)
        .interactor(interactor)
        .build()
    return component.loggedoutRouter()
  }

  override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): CalendarView {
    // TODO: Inflate a new view using the provided inflater, or create a new view programatically using the
    // provided context from the parentViewGroup.
    return CalendarView(parentViewGroup.context)
//    return inflater.inflate(R.layout.logged_out_rib, parentViewGroup, false) as LoggedOutView
  }

  interface ParentComponent {
    // TODO: Define dependencies required from your parent interactor here.
  }

  @dagger.Module
  abstract class Module {

    @LoggedOutScope
    @Binds
    internal abstract fun presenter(view: CalendarView): CalendarInteractor.CalendarPresenter

    @dagger.Module
    companion object {

      @LoggedOutScope
      @Provides
      @JvmStatic
      internal fun router(
        component: Component,
        view: CalendarView,
        interactor: CalendarInteractor): CalendarRouter {
        return CalendarRouter(view, interactor, component)
      }
    }

    // TODO: Create provider methods for dependencies created by this Rib. These should be static.
  }

  @LoggedOutScope
  @dagger.Component(modules = arrayOf(Module::class), dependencies = arrayOf(ParentComponent::class))
  interface Component : InteractorBaseComponent<CalendarInteractor>, BuilderComponent {

    @dagger.Component.Builder
    interface Builder {
      @BindsInstance
      fun interactor(interactor: CalendarInteractor): Builder

      @BindsInstance
      fun view(view: CalendarView): Builder

      fun parentComponent(component: ParentComponent): Builder
      fun build(): Component
    }
  }

  interface BuilderComponent {
    fun loggedoutRouter(): CalendarRouter
  }

  @Scope
  @Retention(CLASS)
  internal annotation class LoggedOutScope

  @Qualifier
  @Retention(CLASS)
  internal annotation class LoggedOutInternal
}
