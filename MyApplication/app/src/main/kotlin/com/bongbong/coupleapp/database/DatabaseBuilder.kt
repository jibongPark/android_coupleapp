package com.bongbong.coupleapp.database

import com.uber.rib.core.Builder
import com.uber.rib.core.EmptyPresenter
import com.uber.rib.core.InteractorBaseComponent
import java.lang.annotation.Retention

import javax.inject.Qualifier
import javax.inject.Scope

import dagger.Provides
import dagger.BindsInstance

import java.lang.annotation.RetentionPolicy.CLASS

class DatabaseBuilder(dependency: ParentComponent) : Builder<DatabaseRouter, DatabaseBuilder.ParentComponent>(dependency) {

  /**
   * Builds a new [DatabaseRouter].
   *
   * @return a new [DatabaseRouter].
   */
  fun build(): DatabaseRouter {
    val interactor = DatabaseInteractor()
    val component = DaggerDatabaseBuilder_Component.builder()
        .parentComponent(dependency)
        .interactor(interactor)
        .build()

    return component.databaseRouter()
  }

  interface ParentComponent {
    // TODO: Define dependencies required from your parent interactor here.
  }


  @dagger.Module
  abstract class Module {

    @dagger.Module
    companion object {

      @DatabaseScope
      @Provides
      @JvmStatic
      internal fun presenter(): EmptyPresenter {
        return EmptyPresenter()
      }

      @DatabaseScope
      @Provides
      @JvmStatic
      internal fun router(component: Component, interactor: DatabaseInteractor): DatabaseRouter {
        return DatabaseRouter(interactor, component)
      }

      // TODO: Create provider methods for dependencies created by this Rib. These methods should be static.
    }
  }


  @DatabaseScope
  @dagger.Component(modules = arrayOf(Module::class), dependencies = arrayOf(ParentComponent::class))
  interface Component : InteractorBaseComponent<DatabaseInteractor>, BuilderComponent {

    @dagger.Component.Builder
    interface Builder {
      @BindsInstance
      fun interactor(interactor: DatabaseInteractor): Builder

      fun parentComponent(component: ParentComponent): Builder
      fun build(): Component
    }

  }

  interface BuilderComponent {
    fun databaseRouter(): DatabaseRouter
  }

  @Scope
  @Retention(CLASS)
  internal annotation class DatabaseScope


  @Qualifier
  @Retention(CLASS)
  internal annotation class DatabaseInternal
}
