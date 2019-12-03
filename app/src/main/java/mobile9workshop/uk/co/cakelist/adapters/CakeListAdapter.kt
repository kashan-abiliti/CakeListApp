package mobile9workshop.uk.co.cakelist.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.jakewharton.rxbinding.view.RxView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.items.view.*
import mobile9workshop.uk.co.cakelist.CakeListActivity
import mobile9workshop.uk.co.cakelist.DataModel.CakeListDataModel
import mobile9workshop.uk.co.cakelist.Interfaces.ItemClickListener
import mobile9workshop.uk.co.cakelist.R


class CakeListAdapter(var context: CakeListActivity, var cakeList: ArrayList<CakeListDataModel>, private val itemClick: ItemClickListener): RecyclerView.Adapter<CakeListAdapter.CakeHolder>()  {

    companion object {
        var mItemClickListener : ItemClickListener? = null
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CakeHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.items, parent, false)
        return CakeHolder(view)
    }

    override fun getItemCount(): Int {
        return cakeList.size
    }

    override fun onBindViewHolder(holder:CakeHolder, position: Int) {
        mItemClickListener = itemClick
        holder.title?.text = cakeList[position].desc
        holder.description?.text = cakeList[position].title


        Picasso.get().load(cakeList[position].image).into(holder.imageView);
        setAnimation(holder.mView)

        RxView.clicks(holder.mView).subscribe {
            mItemClickListener!!.onItemClick(position)
        }
    }


    class CakeHolder (view: View) : RecyclerView.ViewHolder(view) {
        val title = view.title
        val description = view.description
        val imageView = view.image
        val mView = view
    }

    private fun setAnimation(viewToAnimate: View) {
        if (viewToAnimate.animation == null) {
            val animation = AnimationUtils.loadAnimation(viewToAnimate.context, android.R.anim.slide_in_left)
            viewToAnimate.animation = animation
        }
    }

}