package ir.mamhesam.poemskurd.feature.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ir.mamhesam.poemskurd.R
import ir.mamhesam.poemskurd.data.ResponseAllPoems

class AllPoemAdapter(var allPoems: ArrayList<ResponseAllPoems>):
    RecyclerView.Adapter<AllPoemAdapter.AllPoemViewHolder>(), Filterable {

    val mainList = allPoems
    val searchList = ArrayList<ResponseAllPoems>(allPoems)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllPoemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_all_poem,parent,false)
        return AllPoemViewHolder(view)
    }

    override fun onBindViewHolder(holder: AllPoemViewHolder, position: Int) {
        val itemPoem = allPoems[position]
        holder.namePoem.text = itemPoem.name
        holder.yearPoem.text = itemPoem.year
    }

    override fun getItemCount(): Int = allPoems.size

    class AllPoemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val namePoem = itemView.findViewById<TextView>(R.id.txt_name_poem)
        val yearPoem = itemView.findViewById<TextView>(R.id.txt_year_poem)
    }

    override fun getFilter(): Filter {

        return object : Filter(){
            override fun performFiltering(p0: CharSequence?): FilterResults {

                val filteredList = ArrayList<ResponseAllPoems>()

                if (p0?.isBlank()!! or p0.isEmpty()){

                    filteredList.addAll(searchList)
                }else{
                    val filterPattern = p0.toString()
                    searchList.forEach {
                        if (it.name.contains(filterPattern)){
                            filteredList.add(it)
                        }
                    }
                }

                val result = FilterResults()
                result.values = filteredList

                return result
            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                mainList.clear()
                mainList.addAll(p1!!.values as List<ResponseAllPoems>)
                notifyDataSetChanged()
            }


        }

    }
}