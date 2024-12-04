package com.bongbong.coupleapp

import android.view.ViewGroup
import com.bongbong.coupleapp.rootRib.RootBuilder
import com.uber.rib.core.RibActivity
import com.uber.rib.core.ViewRouter

class MainActivity : RibActivity() {
    override fun createRouter(parentViewGroup: ViewGroup): ViewRouter<*, *> {
        supportActionBar?.hide()
        var root = RootBuilder(object : RootBuilder.ParentComponent {})
        return root.build(parentViewGroup);
    }
}