package com.example.myapplication.rootRib

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.myapplication.databinding.RootRibBinding
import com.example.myapplication.calendar.CalendarBuilder
import com.example.myapplication.calendar.schedule.ScheduleBuilder
import com.example.myapplication.menu.MenuBuilder
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
 * Builder for the {@link RootRibScope}.
 *
 * TODO describe this scope's responsibility as a whole.
 */
class RootBuilder(dependency: ParentComponent) : ViewBuilder<RootView, RootRouter, RootBuilder.ParentComponent>(dependency) {

  /**
   * Builds a new [RootRouter].
   *
   * @param parentViewGroup parent view group that this router's view will be added to.
   * @return a new [RootRouter].
   */
  fun build(parentViewGroup: ViewGroup): RootRouter {
    val view = createView(parentViewGroup)
    val interactor = RootInteractor()
    val component = DaggerRootBuilder_Component.builder()
        .parentComponent(dependency)
        .view(view)
        .interactor(interactor)
        .build()
    return component.rootribRouter()
  }

  override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): RootView {

    return RootRibBinding.inflate(inflater, parentViewGroup, false).root as RootView
  }

  interface ParentComponent {
    // TODO: Define dependencies required from your parent interactor here.
  }

  @dagger.Module
  abstract class Module {

    @RootRibScope
    @Binds
    internal abstract fun presenter(view: RootView): RootInteractor.RootRibPresenter

    @dagger.Module
    companion object {

      @RootRibScope
      @Provides
      @JvmStatic
      internal fun router(
        component: Component,
        view: RootView,
        interactor: RootInteractor): RootRouter {
        return RootRouter(view, interactor, component,
          CalendarBuilder(component), ScheduleBuilder(component), MenuBuilder(component))
      }
    }

    // TODO: Create provider methods for dependencies created by this Rib. These should be static.
  }

  @RootRibScope
  @dagger.Component(modules = arrayOf(Module::class), dependencies = arrayOf(ParentComponent::class))
  interface Component : InteractorBaseComponent<RootInteractor>,
    CalendarBuilder.ParentComponent,
    MenuBuilder.ParentComponent,
    ScheduleBuilder.ParentComponent,
    BuilderComponent {

    @dagger.Component.Builder
    interface Builder {
      @BindsInstance
      fun interactor(interactor: RootInteractor): Builder

      @BindsInstance
      fun view(view: RootView): Builder

      fun parentComponent(component: ParentComponent): Builder
      fun build(): Component
    }
  }

  interface BuilderComponent {
    fun rootribRouter(): RootRouter
  }

  @Scope
  @Retention(CLASS)
  internal annotation class RootRibScope

  @Qualifier
  @Retention(CLASS)
  internal annotation class RootRibInternal
}
