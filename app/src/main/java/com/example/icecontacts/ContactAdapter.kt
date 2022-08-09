package com.example.icecontacts

import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.accessibility.AccessibilityNodeInfo
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.icecontacts.databinding.ContactItemBinding

val contact1 = Contact("Alexey","Mihailov",R.drawable.ic_account_box)
val contact2 = Contact("Kot","",R.drawable.ic_account_box)
val contact3 = Contact("Petr","",R.drawable.ic_account_box)
val contact4 = Contact("Tom","",R.drawable.ic_account_box)
val contact5 = Contact("Anna","",R.drawable.ic_account_box)
val contacts: List<Contact> = listOf(contact1,contact2,contact3,contact4,contact5)
class ContactAdapter : RecyclerView.Adapter<ContactAdapter.ContactHolder>() {


    class ContactHolder(item: View) : RecyclerView.ViewHolder(item){

        val binding = ContactItemBinding.bind(item)
        fun bind(contact: Contact){
            binding.tvContact.text = contact.firstName
            binding.tvSur.text = contact.lastName
            binding.imageView.setImageResource(contact.avatarResourceId)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.contact_item,parent,false)
        view.accessibilityDelegate = Accessibility
        return ContactHolder(view)
    }

    override fun onBindViewHolder(holder: ContactHolder, position: Int) {
        holder.bind(contacts[position])
        holder.itemView.setOnClickListener {
            val action = ContactsFragmentDirections.actionContactsFragmentToContactFragment(
                name = holder.binding.tvContact.text.toString(),
                surname = holder.binding.tvSur.text.toString()
            )
            holder.itemView.findNavController().navigate(action)
        }

    }

    override fun getItemCount(): Int {
        return contacts.size
    }

    companion object Accessibility : View.AccessibilityDelegate() {
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        override fun onInitializeAccessibilityNodeInfo(
            host: View?,
            info: AccessibilityNodeInfo?
        ) {
            super.onInitializeAccessibilityNodeInfo(host, info)
            // With `null` as the second argument to [AccessibilityAction], the
            // accessibility service announces "double tap to activate".
            // If a custom string is provided,
            // it announces "double tap to <custom string>".
            val customString = "Show Stored Words"
            val customClick =
                AccessibilityNodeInfo.AccessibilityAction(
                    AccessibilityNodeInfo.ACTION_CLICK,
                    customString
                )
            info?.addAction(customClick)
        }
    }

}