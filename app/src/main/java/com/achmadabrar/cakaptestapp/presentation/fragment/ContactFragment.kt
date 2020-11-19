package com.achmadabrar.cakaptestapp.presentation.fragment

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.achmadabrar.cakaptestapp.R
import com.achmadabrar.cakaptestapp.core.base.BaseFragment
import com.achmadabrar.cakaptestapp.data.models.Contact
import com.achmadabrar.cakaptestapp.data.network.NetworkState
import com.achmadabrar.cakaptestapp.presentation.adapter.ContactAdapter
import com.achmadabrar.cakaptestapp.presentation.dialog.PopUpDialog
import com.achmadabrar.cakaptestapp.presentation.viewholder.ContactViewHolder
import com.achmadabrar.cakaptestapp.presentation.viewmodel.ContactViewModel
import kotlinx.android.synthetic.main.fragment_contact.*
import javax.inject.Inject


/**
 * Abrar
 */

private const val NAME = "name"
private const val PHONE = "phone"
private const val EMAIL = "email"
private const val CREATED_AT = "createdAt"

class ContactFragment : BaseFragment(), ContactViewHolder.Listener {

    @Inject lateinit var viewmodel: ContactViewModel

    lateinit var adapter:ContactAdapter
    lateinit var popupDialog: PopUpDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewmodel = ViewModelProviders.of(activity!!, viewModelFactory).get(ContactViewModel::class.java)

        viewmodel.contactLiveData.observe(viewLifecycleOwner, Observer {
            if (!it.isNullOrEmpty()) {
                adapter = ContactAdapter(it, this)
                loadRecycler()
            }
        })

        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).toolbar.setNavigationIcon(R.drawable.ic_baseline_add_24)
        (activity as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(false)
        (activity as AppCompatActivity).toolbar.setNavigationOnClickListener(object: View.OnClickListener{
            override fun onClick(view: View?) {
                val transaction = fragmentManager?.beginTransaction()
                transaction?.replace(R.id.frame_contact, AddContactFragment())
                transaction?.addToBackStack(null)
                transaction?.commit()
            }

        })

        viewmodel.networkLiveData.observe(viewLifecycleOwner, Observer {
            when(it.status) {
                NetworkState.Status.RUNNING -> {
                    shimmer_list.visibility = View.VISIBLE
                    rv_contacts.visibility = View.GONE
                }

                NetworkState.Status.SUCCESS -> {
                    shimmer_list.visibility = View.GONE
                    rv_contacts.visibility = View.VISIBLE
                }

                NetworkState.Status.FAILED -> {
                    viewmodel.contactByNameDsc()
                    rv_contacts.visibility = View.VISIBLE
                    if (viewmodel.contactByNameDsc().isNullOrEmpty()) {
                        shimmer_list.visibility = View.VISIBLE
                        Toast.makeText(requireContext(), "data tidak ada", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })

        tv_sort.setOnClickListener {
            popupDialog = PopUpDialog(requireContext(), object: PopUpDialog.Listener{
                override fun onClickSubmitButton(
                    dialog: PopUpDialog,
                    selectedButton: String,
                    isAsc: Boolean
                ) {
                    when(selectedButton) {
                        NAME -> {
                            if (isAsc) viewmodel.contactByNameAsc() else viewmodel.contactByNameDsc()
                            dialog.cancel()
                        }

                        PHONE -> {
                            if (isAsc) viewmodel.contactByPhoneAsc() else viewmodel.contactByPhoneDsc()
                            dialog.cancel()
                        }

                        EMAIL -> {
                            if (isAsc) viewmodel.contactByEmailAsc() else viewmodel.contactByEmailDsc()
                            dialog.cancel()
                        }

                        CREATED_AT -> {
                            if (isAsc) viewmodel.contactByCreatedatAsc() else viewmodel.contactByCreatedAtDsc()
                            dialog.cancel()
                        }
                    }
                }

            })
            popupDialog.show()
        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.contact_navigations, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    fun loadRecycler() {
        rv_contacts.adapter = adapter
        rv_contacts.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    }

    override fun onClickContatc(contact: Contact) {
        //
    }
}