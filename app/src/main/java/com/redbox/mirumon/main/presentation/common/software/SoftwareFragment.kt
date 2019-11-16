package com.redbox.mirumon.main.presentation.common.software

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.redbox.mirumon.R
import kotlinx.android.synthetic.main.fragment_software.*

class SoftwareFragment : Fragment() {

    private lateinit var viewModel: SoftwareViewModel
    private val adapter = SoftwareListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(SoftwareViewModel::class.java)
        lifecycle.addObserver(viewModel)
        return inflater.inflate(R.layout.fragment_software, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.notifyWebsocket()

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

        viewModel.observeSoftware(this) {
            adapter.softwareList = it
            software_list_rv.adapter = adapter.apply { notifyDataSetChanged() }
        }

        software_list_rv.layoutManager = LinearLayoutManager(this.context)
    }
}
