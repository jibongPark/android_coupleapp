package com.example.myapplication.menu

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
 * Builder for the {@link MenuScope}.
 *
 * TODO describe this scope's responsibility as a whole.
 */
open class MenuBuilder(dependency: ParentComponent) : ViewBuilder<MenuView, MenuRouter, MenuBuilder.ParentComponent>(dependency) {

  /**
   * Builds a new [MenuRouter].
   *
   * @param parentViewGroup parent view group that this router's view will be added to.
   * @return a new [MenuRouter].
   */
  fun build(parentViewGroup: ViewGroup): MenuRouter {
    val view = createView(parentViewGroup)
    val interactor = MenuInteractor()
    val component = DaggerMenuBuilder_Component.builder()
        .parentComponent(dependency)
        .view(view)
        .interactor(interactor)
        .build()
    return component.menuRouter()
  }

  override fun inflateView(inflater: LayoutInflater, parentViewGroup: ViewGroup): MenuView {
    // TODO: Inflate a new view using the provided inflater, or create a new view programatically using the
    // provided context from the parentViewGroup.
    return MenuView(parentViewGroup.context)
  }

  interface ParentComponent {
    // TODO: Define dependencies required from your parent interactor here.
  }

  @dagger.Module
  abstract class Module {

    @MenuScope
    @Binds
    internal abstract fun presenter(view: MenuView): MenuInteractor.MenuPresenter

    @dagger.Module
    companion object {

      @MenuScope
      @Provides
      @JvmStatic
      internal fun router(
          component: Component,
          view: MenuView,
          interactor: MenuInteractor): MenuRouter {
        return MenuRouter(view, interactor, component)
      }
    }

    // TODO: Create provider methods for dependencies created by this Rib. These should be static.
  }

  @MenuScope
  @dagger.Component(modules = arrayOf(Module::class), dependencies = arrayOf(ParentComponent::class))
  interface Component : InteractorBaseComponent<MenuInteractor>, BuilderComponent {

    @dagger.Component.Builder
    interface Builder {
      @BindsInstance
      fun interactor(interactor: MenuInteractor): Builder

      @BindsInstance
      fun view(view: MenuView): Builder

      fun parentComponent(component: ParentComponent): Builder
      fun build(): Component
    }
  }

  interface BuilderComponent {
    fun menuRouter(): MenuRouter
  }

  @Scope
  @Retention(CLASS)
  internal annotation class MenuScope

  @Qualifier
  @Retention(CLASS)
  internal annotation class MenuInternal
}
