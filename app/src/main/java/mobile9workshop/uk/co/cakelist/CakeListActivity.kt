package mobile9workshop.uk.co.cakelist

import android.app.AlertDialog
import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_cake_list.*
import mobile9workshop.uk.co.cakelist.DataModel.CakeListDataModel
import mobile9workshop.uk.co.cakelist.Interfaces.ItemClickListener
import mobile9workshop.uk.co.cakelist.Util.Util
import mobile9workshop.uk.co.cakelist.ViewModel.CakeListViewModel
import mobile9workshop.uk.co.cakelist.adapters.CakeListAdapter


class CakeListActivity : BaseActivity() {

    private lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_cake_list)

        linearLayoutManager = LinearLayoutManager(this@CakeListActivity)
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.hasFixedSize()
        retryButton.setOnClickListener{
            getCakeList()
        }

        swipeRefresh.setOnRefreshListener {
            getCakeList()
            retryButton.visibility = View.GONE
        }

        if (isNetworkConnected())
            getCakeList()
        else {
            Util.showMessage("Network not found", this@CakeListActivity)
            retryButton.visibility = View.VISIBLE
            progressBar.visibility = View.GONE
        }


    }


    private fun getCakeList() {
        Log.e("get Data","yes")

        val mAndroidViewModel = ViewModelProviders.of(this@CakeListActivity).get(CakeListViewModel::class.java)
        mAndroidViewModel.getCakeListData(this@CakeListActivity)?.observe(this, Observer<List<CakeListDataModel>> { cakeList ->
            setViewVisibility()
            Log.e("list",cakeList?.size.toString())
            recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
            val cakeList = cakeList.sortedWith(compareBy({ it.title })).distinct()
            recyclerView.adapter = CakeListAdapter(this@CakeListActivity, cakeList as ArrayList<CakeListDataModel>, object : ItemClickListener {
                override fun onItemClick(pos: Int) {
                    Util.showMessageForCakeList(cakeList, pos,this@CakeListActivity)
                }
            })
        })

    }

    private fun setViewVisibility(){
        progressBar.visibility = View.GONE
        retryButton.visibility = View.GONE
        swipeRefresh.isRefreshing = false
    }

    private fun isNetworkConnected(): Boolean {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo != null && cm.activeNetworkInfo.isConnected
    }



}
