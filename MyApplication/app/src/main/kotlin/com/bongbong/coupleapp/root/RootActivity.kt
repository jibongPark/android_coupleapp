package com.bongbong.coupleapp.root

import android.app.Activity
import android.view.ViewGroup
import com.bongbong.coupleapp.rootRib.RootBuilder
import com.uber.rib.core.RibActivity
import com.uber.rib.core.ViewRouter
import motif.Creatable
import motif.Expose
import motif.NoDependencies
import motif.ScopeFactory

class RootActivity : RibActivity() {
    override fun createRouter(parentViewGroup: ViewGroup): ViewRouter<*, *> {
        supportActionBar?.hide()

        ScopeFactory.create(Parent::class.java)
            .rootScope(this, findViewById(android.R.id.content))
            .router()

        var root = RootBuilder(object : RootBuilder.ParentComponent {})
        return root.build(parentViewGroup);
    }

    @motif.Scope
    interface Parent : Creatable<NoDependencies> {
        fun rootScope(@Expose activity: Activity, parentViewGroup: ViewGroup): RootScope
    }

}