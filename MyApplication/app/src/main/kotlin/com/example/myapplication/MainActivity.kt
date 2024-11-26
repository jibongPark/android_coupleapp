package com.example.myapplication

import android.view.ViewGroup
import com.example.myapplication.rootRib.RootBuilder
import com.uber.rib.core.RibActivity
import com.uber.rib.core.ViewRouter

class MainActivity : RibActivity() {
    override fun createRouter(parentViewGroup: ViewGroup): ViewRouter<*, *> {
        supportActionBar?.hide()
        var root = RootBuilder(object : RootBuilder.ParentComponent {})
        return root.build(parentViewGroup);
    }
}