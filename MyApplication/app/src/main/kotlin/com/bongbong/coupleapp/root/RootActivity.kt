package com.bongbong.coupleapp.root

import android.app.Activity
import android.view.ViewGroup
import com.uber.rib.core.RibActivity
import com.uber.rib.core.ViewRouter
import motif.Creatable
import motif.Expose
import motif.NoDependencies
import motif.ScopeFactory

class RootActivity : RibActivity() {
    override fun createRouter(parentViewGroup: ViewGroup): ViewRouter<*, *> {
        supportActionBar?.hide()

        return ScopeFactory.create(Parent::class.java)
            .rootScope(this, findViewById(android.R.id.content))
            .router()
    }

    @motif.Scope
    interface Parent : Creatable<NoDependencies> {
        fun rootScope(@Expose activity: Activity, @Expose parentViewGroup: ViewGroup): RootScope
    }

}