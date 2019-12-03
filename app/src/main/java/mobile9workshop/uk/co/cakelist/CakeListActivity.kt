package mobile9workshop.uk.co.cakelist

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_cake_list.*
import mobile9workshop.uk.co.cakelist.DataModel.CakeListDataModel
import mobile9workshop.uk.co.cakelist.Interfaces.ItemClickListener
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

        swipeRefresh.setOnRefreshListener {
            getCakeList()
        }

        getCakeList()


    }


    private fun getCakeList() {
        Log.e("getAndroidVersion","yes")

        val mAndroidViewModel = ViewModelProviders.of(this@CakeListActivity).get(CakeListViewModel::class.java)
        mAndroidViewModel.getCakeListData()?.observe(this, Observer<List<CakeListDataModel>> { cakeList ->
            progressBar.visibility = View.GONE
            swipeRefresh.isRefreshing = false
            Log.e("list",cakeList?.size.toString())
            recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
            recyclerView.adapter = CakeListAdapter(this@CakeListActivity, cakeList.distinct() as ArrayList<CakeListDataModel>, object : ItemClickListener {
                override fun onItemClick(pos: Int) {
                    val builder = AlertDialog.Builder(this@CakeListActivity)
                    builder.setTitle(cakeList[pos].title)
                    builder.setMessage(cakeList[pos].desc)
                    builder.setPositiveButton("OK"){ dialogInterface, i ->

                    }
                    builder.show()
                    //Toast.makeText(applicationContext, "item $pos clicked", Toast.LENGTH_LONG).show()
                }
            })
        })

    }

}
