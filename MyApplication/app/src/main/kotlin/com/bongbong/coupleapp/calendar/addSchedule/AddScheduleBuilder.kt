package com.bongbong.coupleapp.calendar.addSchedule

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import com.uber.rib.compose.util.EventStream
import com.uber.rib.compose.util.StateStream
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
 * Builder for the {@link AddScheduleScope}.
 *
 * TODO describe this scope's responsibility as a whole.
 */
class AddScheduleBuilder(dependency: ParentComponent) : ViewBuilder<AddScheduleView, AddScheduleRouter, AddScheduleBuilder.ParentComponent>(dependency) {

  /**
   * Builds a new [AddScheduleRouter].
   *
   * @param parentViewGroup parent view group that this router's view will be added to.
   * @return a new [AddScheduleRouter].
   */
  fun build(parentViewGroup: ViewGroup): AddScheduleRouter {
    val view = createView(parentViewGroup)
    val interactor = AddScheduleInteractor()
    val component = DaggerAddScheduleBuilder_Component.builder()
        .parentComponent(dependency)
        .view(view)
        .interactor(interactor)
        .build()
    return component.addscheduleRouter()
  }

  override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): AddScheduleView {

    return AddScheduleView(parentViewGroup.context)
  }

  interface ParentComponent {
    fun addScheduleListener(): AddScheduleInteractor.Listener
  }

  @dagger.Module
  abstract class Module {

    @AddScheduleScope
    @Binds
    internal abstract fun presenter(view: AddScheduleView): AddScheduleInteractor.AddSchedulePresenter

    @dagger.Module
    companion object {

      @AddScheduleScope
      @Provides
      @JvmStatic
      internal fun router(
          component: Component,
          view: AddScheduleView,
          interactor: AddScheduleInteractor): AddScheduleRouter {
        return AddScheduleRouter(view, interactor, component)
      }

      @AddScheduleScope
      @Provides
      @JvmStatic
      fun stateStream(): StateStream<AddScheduleViewModel> {
        return StateStream(AddScheduleViewModel())
      }

      @AddScheduleScope
      @Provides
      @JvmStatic
      fun eventStream(): EventStream<AddScheduleEvent> {
        return EventStream<AddScheduleEvent>()
      }
    }

    // TODO: Create provider methods for dependencies created by this Rib. These should be static.
  }

  @AddScheduleScope
  @dagger.Component(modules = arrayOf(Module::class), dependencies = arrayOf(ParentComponent::class))
  interface Component : InteractorBaseComponent<AddScheduleInteractor>, BuilderComponent {

    @dagger.Component.Builder
    interface Builder {
      @BindsInstance
      fun interactor(interactor: AddScheduleInteractor): Builder

      @BindsInstance
      fun view(view: AddScheduleView): Builder

      fun parentComponent(component: ParentComponent): Builder
      fun build(): Component
    }
  }

  interface BuilderComponent {
    fun addscheduleRouter(): AddScheduleRouter
  }

  @Scope
  @Retention(CLASS)
  internal annotation class AddScheduleScope

  @Qualifier
  @Retention(CLASS)
  internal annotation class AddScheduleInternal
}
