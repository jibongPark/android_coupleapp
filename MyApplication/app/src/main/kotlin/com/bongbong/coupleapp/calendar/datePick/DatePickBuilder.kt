package com.bongbong.coupleapp.calendar.datePick

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
 * Builder for the {@link DatePickScope}.
 *
 * TODO describe this scope's responsibility as a whole.
 */
class DatePickBuilder(dependency: ParentComponent) : ViewBuilder<DatePickView, DatePickRouter, DatePickBuilder.ParentComponent>(dependency) {

  /**
   * Builds a new [DatePickRouter].
   *
   * @param parentViewGroup parent view group that this router's view will be added to.
   * @return a new [DatePickRouter].
   */
  fun build(parentViewGroup: ViewGroup): DatePickRouter {
    val view = createView(parentViewGroup)
    val interactor = DatePickInteractor()
    val component = DaggerDatePickBuilder_Component.builder()
        .parentComponent(dependency)
        .view(view)
        .interactor(interactor)
        .build()
    return component.datepickRouter()
  }

  override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): DatePickView {
    // TODO: Inflate a new view using the provided inflater, or create a new view programatically using the
    // provided context from the parentViewGroup.
    return DatePickView(parentViewGroup.context)
  }

  interface ParentComponent {
    // TODO: Define dependencies required from your parent interactor here.
  }

  @dagger.Module
  abstract class Module {

    @DatePickScope
    @Binds
    internal abstract fun presenter(view: DatePickView): DatePickInteractor.DatePickPresenter

    @dagger.Module
    companion object {

      @DatePickScope
      @Provides
      @JvmStatic
      internal fun router(
          component: Component,
          view: DatePickView,
          interactor: DatePickInteractor): DatePickRouter {
        return DatePickRouter(view, interactor, component)
      }
    }

    // TODO: Create provider methods for dependencies created by this Rib. These should be static.
  }

  @DatePickScope
  @dagger.Component(modules = arrayOf(Module::class), dependencies = arrayOf(ParentComponent::class))
  interface Component : InteractorBaseComponent<DatePickInteractor>, BuilderComponent {

    @dagger.Component.Builder
    interface Builder {
      @BindsInstance
      fun interactor(interactor: DatePickInteractor): Builder

      @BindsInstance
      fun view(view: DatePickView): Builder

      fun parentComponent(component: ParentComponent): Builder
      fun build(): Component
    }
  }

  interface BuilderComponent {
    fun datepickRouter(): DatePickRouter
  }

  @Scope
  @Retention(CLASS)
  internal annotation class DatePickScope

  @Qualifier
  @Retention(CLASS)
  internal annotation class DatePickInternal
}
