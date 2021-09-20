package com.example.envelope.ui.payment.detail

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.envelope.data.Card
import com.example.envelope.databinding.DropdownCardItemBinding
import com.example.envelope.utils.extensions.loadUrl

//todo refactor - use abstraction
class CardAdapter(
    myContext: Context,
    cardList: List<Card> = listOf(),
    private val onCardClicked: (number: String, imageUrl: String) -> Unit
) :
    ArrayAdapter<Card>(myContext, 0, cardList) {
    private var paymentCardList: List<Card> = cardList

    override fun getCount(): Int {
        return paymentCardList.size
    }

    override fun getItem(position: Int): Card {
        return paymentCardList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return dropDownCardItemView(position, convertView, parent)
    }

    private fun dropDownCardItemView(position: Int, convertView: View?, parent: ViewGroup): View {
        val card = getItem(position)
        val view =
            DropdownCardItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        view.tvCardNumber.text = card.number
        view.ivCardImage.loadUrl(card.imageUrl)
        view.root.setOnClickListener {
            onCardClicked.invoke(card.number, card.imageUrl)
        }
        return view.root
    }
}