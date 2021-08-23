package com.example.envelope.utils.navigation

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.envelope.ui.ContainerActivity
import com.example.envelope.ui.services.ServicesFragment

object FragmentNavigator {

    fun openFragment(
        activity: ContainerActivity,
        screen: Screen,
        data: Bundle? = null,
        tag: String? = null,
        targetFragment: Fragment? = null,
        requestCode: Int? = null
    ) {
        val fragment = when (screen) {
            Screen.EXPENSES -> {
                //todo remove
                ServicesFragment.newInstance(bundle = data)
            }
            Screen.PAYMENT -> {
                //todo remove
                ServicesFragment.newInstance(bundle = data)
            }
            Screen.SERVICES -> {
                ServicesFragment.newInstance(bundle = data)
            }
        }
        activity.showFragment(
            fragment = fragment,
            tag = tag
        )
    }

}