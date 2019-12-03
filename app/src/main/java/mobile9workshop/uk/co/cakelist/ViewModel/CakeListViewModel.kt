package mobile9workshop.uk.co.cakelist.ViewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mobile9workshop.uk.co.cakelist.CommonService.RetrofitService
import mobile9workshop.uk.co.cakelist.DataModel.CakeListDataModel

class CakeListViewModel: ViewModel() {

    private val mService  =  RetrofitService()

    fun getCakeListData(context: Context) : MutableLiveData<List<CakeListDataModel>>? {
        Log.e("getCakeListData","yes")
        return mService.loadCakeListData(context)
    }

}
