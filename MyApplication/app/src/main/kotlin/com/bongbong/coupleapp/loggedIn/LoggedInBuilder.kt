package com.bongbong.coupleapp.loggedIn

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import com.bongbong.coupleapp.calendar.CalendarBuilder
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
 * Builder for the {@link LoggedInScope}.
 *
 * TODO describe this scope's responsibility as a whole.
 */
class LoggedInBuilder(dependency: ParentComponent) : ViewBuilder<LoggedInView, LoggedInRouter, LoggedInBuilder.ParentComponent>(dependency) {

  /**
   * Builds a new [LoggedInRouter].
   *
   * @param parentViewGroup parent view group that this router's view will be added to.
   * @return a new [LoggedInRouter].
   */
  fun build(parentViewGroup: ViewGroup): LoggedInRouter {
    val view = createView(parentViewGroup)
    val interactor = LoggedInInteractor()
    val component = DaggerLoggedInBuilder_Component.builder()
        .parentComponent(dependency)
        .view(view)
        .interactor(interactor)
        .build()
    return component.loggedinRouter()
  }

  override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): LoggedInView {
    // TODO: Inflate a new view using the provided inflater, or create a new view programatically using the
    // provided context from the parentViewGroup.
    return LoggedInView(parentViewGroup.context)
  }

  interface ParentComponent {
    // TODO: Define dependencies required from your parent interactor here.
  }

  @dagger.Module
  abstract class Module {

    @LoggedInScope
    @Binds
    internal abstract fun presenter(view: LoggedInView): LoggedInInteractor.LoggedInPresenter

    @dagger.Module
    companion object {

      @LoggedInScope
      @Provides
      @JvmStatic
      internal fun router(
        component: Component,
        view: ComposeView,
        interactor: LoggedInInteractor): LoggedInRouter {
        return LoggedInRouter(view, interactor, CalendarBuilder(component), component)
      }
    }
  }

  @LoggedInScope
  @dagger.Component(modules = arrayOf(Module::class), dependencies = arrayOf(ParentComponent::class))
  interface Component : InteractorBaseComponent<LoggedInInteractor>,
    CalendarBuilder.ParentComponent,
    BuilderComponent {

    @dagger.Component.Builder
    interface Builder {
      @BindsInstance
      fun interactor(interactor: LoggedInInteractor): Builder

      @BindsInstance
      fun view(view: LoggedInView): Builder

      fun parentComponent(component: ParentComponent): Builder
      fun build(): Component
    }
  }

  interface BuilderComponent {
    fun loggedinRouter(): LoggedInRouter
  }

  @Scope
  @Retention(CLASS)
  internal annotation class LoggedInScope

  @Qualifier
  @Retention(CLASS)
  internal annotation class LoggedInInternal
}
