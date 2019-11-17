package com.redbox.mirumon.main.presentation.common.software

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.redbox.mirumon.R
import com.redbox.mirumon.main.extensions.applyLoadingState
import com.redbox.mirumon.main.extensions.applySuccessState
import kotlinx.android.synthetic.main.fragment_software.*
import org.koin.android.viewmodel.ext.android.viewModel

class SoftwareFragment : Fragment() {

    private val vm: SoftwareViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_software, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        software_list_btn.setOnClickListener {
            if (software_list_rv.visibility == View.GONE) {
                software_list_rv.visibility = View.VISIBLE
                software_list_btn.setCompoundDrawablesWithIntrinsicBounds(
                    resources.getDrawable(
                        R.drawable.ic_programs,
                        context?.theme
                    ), null, resources.getDrawable(
                        R.drawable.ic_open,
                        context?.theme
                    ), null
                )
            } else {
                software_list_rv.visibility = View.GONE
                software_list_btn.setCompoundDrawablesWithIntrinsicBounds(
                    resources.getDrawable(
                        R.drawable.ic_programs,
                        context?.theme
                    ), null, resources.getDrawable(
                        R.drawable.ic_close,
                        context?.theme
                    ), null
                )
            }
        }

        software_list_rv.layoutManager = LinearLayoutManager(this.context)

        vm.state.observe(this, Observer {
            when (it) {
                is SoftwareState.Initial -> {
                    vm.getSoftware(true)
                }
                is SoftwareState.Loading -> {
                    applyLoadingState(software_list_rv, software_pv)
                }
                is SoftwareState.Success -> {
                    applySuccessState(software_pv)
                    software_list_rv.adapter = SoftwareListAdapter()
                }
                is SoftwareState.Error -> {
                    Toast
                        .makeText(this.context, getText(R.string.error_message), Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })
    }
}
