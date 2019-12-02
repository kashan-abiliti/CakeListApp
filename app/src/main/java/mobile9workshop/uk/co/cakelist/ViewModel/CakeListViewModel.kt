package mobile9workshop.uk.co.cakelist.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import mobile9workshop.uk.co.cakelist.CommonService.RetrofitService
import mobile9workshop.uk.co.cakelist.DataModel.CakeListDataModel

class CakeListViewModel: ViewModel() {

    private val mService  =  RetrofitService()

    fun getCakeListData() : MutableLiveData<List<CakeListDataModel>>? {
        Log.e("getAndroidData","yes")
        return mService.loadCakeListData()
    }

}
