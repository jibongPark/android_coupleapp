package com.bongbong.coupleapp.diary

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
 * Builder for the {@link DiaryScope}.
 *
 * TODO describe this scope's responsibility as a whole.
 */
class DiaryBuilder(dependency: ParentComponent) : ViewBuilder<DiaryView, DiaryRouter, DiaryBuilder.ParentComponent>(dependency) {

  /**
   * Builds a new [DiaryRouter].
   *
   * @param parentViewGroup parent view group that this router's view will be added to.
   * @return a new [DiaryRouter].
   */
  fun build(parentViewGroup: ViewGroup): DiaryRouter {
    val view = createView(parentViewGroup)
    val interactor = DiaryInteractor()
    val component = DaggerDiaryBuilder_Component.builder()
        .parentComponent(dependency)
        .view(view)
        .interactor(interactor)
        .build()
    return component.diaryRouter()
  }

  override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): DiaryView {
    // TODO: Inflate a new view using the provided inflater, or create a new view programatically using the
    // provided context from the parentViewGroup.
    return DiaryView(parentViewGroup.context)
  }

  interface ParentComponent {
    // TODO: Define dependencies required from your parent interactor here.
  }

  @dagger.Module
  abstract class Module {

    @DiaryScope
    @Binds
    internal abstract fun presenter(view: DiaryView): DiaryInteractor.DiaryPresenter

    @dagger.Module
    companion object {

      @DiaryScope
      @Provides
      @JvmStatic
      internal fun router(
          component: Component,
          view: DiaryView,
          interactor: DiaryInteractor): DiaryRouter {
        return DiaryRouter(view, interactor, component)
      }
    }

    // TODO: Create provider methods for dependencies created by this Rib. These should be static.
  }

  @DiaryScope
  @dagger.Component(modules = arrayOf(Module::class), dependencies = arrayOf(ParentComponent::class))
  interface Component : InteractorBaseComponent<DiaryInteractor>, BuilderComponent {

    @dagger.Component.Builder
    interface Builder {
      @BindsInstance
      fun interactor(interactor: DiaryInteractor): Builder

      @BindsInstance
      fun view(view: DiaryView): Builder

      fun parentComponent(component: ParentComponent): Builder
      fun build(): Component
    }
  }

  interface BuilderComponent {
    fun diaryRouter(): DiaryRouter
  }

  @Scope
  @Retention(CLASS)
  internal annotation class DiaryScope

  @Qualifier
  @Retention(CLASS)
  internal annotation class DiaryInternal
}
