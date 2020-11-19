package com.achmadabrar.cakaptestapp.presentation.fragment

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.achmadabrar.cakaptestapp.R
import com.achmadabrar.cakaptestapp.core.base.BaseFragment
import com.achmadabrar.cakaptestapp.data.network.NetworkState
import com.achmadabrar.cakaptestapp.presentation.viewmodel.ContactViewModel
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_add_contact.*
import javax.inject.Inject


/**
 * Abrar
 */
class AddContactFragment : BaseFragment() {

    @Inject lateinit var viewModel: ContactViewModel

    private val textWatcher = object: TextWatcher{
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            //
        }

        override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
            val name = et_name.text.toString().trim()
            val email = et_email.text.toString().trim()
            val phone = et_phone.text.toString().trim()
            val createdAt = et_created_at.text.toString().trim()

            if (name.length >  5 && !email.isEmpty() && phone.length > 7 && !createdAt.isEmpty()) {
                btn_submit_contact.isEnabled = true
                btn_submit_contact.isClickable = true
            } else {
                btn_submit_contact.isEnabled = false
                btn_submit_contact.isClickable = false
                btn_submit_contact.setBackgroundColor(Color.parseColor("#017064"))
            }
        }

        override fun afterTextChanged(p0: Editable?) {
            //
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_contact, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(activity!!, viewModelFactory).get(ContactViewModel::class.java)

        handleButton()
        btn_submit_contact.setOnClickListener {
            viewModel.insertNewContact(
                et_name.text.toString(),
                et_email.text.toString(),
                et_phone.text.toString().toLong(),
                et_created_at.text.toString().toLong()
            )
        }

        viewModel.networkInsertLiveData.observe(viewLifecycleOwner, Observer {
            if (it.status.equals(NetworkState.Status.RUNNING)) {
                iv_loading.visibility = View.VISIBLE
                Glide.with(requireContext()).asGif().load(R.raw.loading).into(iv_loading)
            } else if (it.status.equals(NetworkState.Status.FAILED)){
                Toast.makeText(requireContext(), "error: ${NetworkState.FAILED.msg}", Toast.LENGTH_SHORT).show()
            } else if (it.status.equals(NetworkState.Status.SUCCESS)) {
                fragmentManager?.popBackStack()
                Toast.makeText(requireContext(), viewModel.successMessage, Toast.LENGTH_SHORT).show()
                iv_loading.visibility = View.GONE
            }
        })

    }

    private fun handleButton() {
        et_name.addTextChangedListener(textWatcher)
        et_phone.addTextChangedListener(textWatcher)
        et_created_at.addTextChangedListener(textWatcher)
        et_email.addTextChangedListener(textWatcher)
    }

}