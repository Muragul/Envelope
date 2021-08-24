package com.example.envelope.ui.payment.detail

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.LayoutRes
import com.example.envelope.R
import com.example.envelope.data.Card
import com.example.envelope.utils.extensions.loadUrl

class CardAdapter(
    private var cardList: List<Card> = listOf(),
    private val myContext: Context,
    @LayoutRes private val layoutRes: Int,
) :
    ArrayAdapter<Card>(myContext, layoutRes, cardList), Filterable {
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
        var v: View? = convertView
        if (v == null) {
            val vi = myContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            v = vi.inflate(layoutRes, null)
        }
        val card: Card? = cardList[position]
        if (card != null) {
            val cardNumber = v?.findViewById(R.id.tv_card_number) as TextView?
            val cardImage = v?.findViewById(R.id.iv_card_image) as ImageView?
            cardNumber?.text = card.number.substring(10)
            cardImage?.loadUrl(card.imageUrl)
        }
        return v!!
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun publishResults(
                charSequence: CharSequence?,
                filterResults: Filter.FilterResults
            ) {
                cardList = filterResults.values as List<Card>
                notifyDataSetChanged()
            }

            override fun performFiltering(charSequence: CharSequence?): Filter.FilterResults {
                val queryString = charSequence?.toString()?.toLowerCase()

                val filterResults = Filter.FilterResults()
                filterResults.values = if (queryString == null || queryString.isEmpty())
                    cardList
                else
                    cardList.filter {
                        it.name.toLowerCase().contains(queryString) ||
                                it.number.toLowerCase().contains(queryString)
                    }
                return filterResults
            }
        }
    }

}