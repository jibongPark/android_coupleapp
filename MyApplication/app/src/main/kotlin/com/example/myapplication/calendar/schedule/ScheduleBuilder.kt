package com.example.myapplication.calendar.schedule

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
 * Builder for the {@link ScheduleScope}.
 *
 * TODO describe this scope's responsibility as a whole.
 */
class ScheduleBuilder(dependency: ParentComponent) : ViewBuilder<ScheduleView, ScheduleRouter, ScheduleBuilder.ParentComponent>(dependency) {

  /**
   * Builds a new [ScheduleRouter].
   *
   * @param parentViewGroup parent view group that this router's view will be added to.
   * @return a new [ScheduleRouter].
   */
  fun build(parentViewGroup: ViewGroup): ScheduleRouter {
    val view = createView(parentViewGroup)
    val interactor = ScheduleInteractor()
    val component = DaggerScheduleBuilder_Component.builder()
        .parentComponent(dependency)
        .view(view)
        .interactor(interactor)
        .build()
    return component.scheduleRouter()
  }

  override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): ScheduleView {
    return ScheduleView(parentViewGroup.context)
  }

  interface ParentComponent {
    // TODO: Define dependencies required from your parent interactor here.
  }

  @dagger.Module
  abstract class Module {

    @ScheduleScope
    @Binds
    internal abstract fun presenter(view: ScheduleView): ScheduleInteractor.SchedulePresenter

    @dagger.Module
    companion object {

      @ScheduleScope
      @Provides
      @JvmStatic
      internal fun router(
          component: Component,
          view: ScheduleView,
          interactor: ScheduleInteractor): ScheduleRouter {
        return ScheduleRouter(view, interactor, component)
      }
    }

    // TODO: Create provider methods for dependencies created by this Rib. These should be static.
  }

  @ScheduleScope
  @dagger.Component(modules = arrayOf(Module::class), dependencies = arrayOf(ParentComponent::class))
  interface Component : InteractorBaseComponent<ScheduleInteractor>, BuilderComponent {

    @dagger.Component.Builder
    interface Builder {
      @BindsInstance
      fun interactor(interactor: ScheduleInteractor): Builder

      @BindsInstance
      fun view(view: ScheduleView): Builder

      fun parentComponent(component: ParentComponent): Builder
      fun build(): Component
    }
  }

  interface BuilderComponent {
    fun scheduleRouter(): ScheduleRouter
  }

  @Scope
  @Retention(CLASS)
  internal annotation class ScheduleScope

  @Qualifier
  @Retention(CLASS)
  internal annotation class ScheduleInternal
}
