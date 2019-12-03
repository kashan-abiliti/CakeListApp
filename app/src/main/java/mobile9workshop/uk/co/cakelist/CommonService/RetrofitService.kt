package mobile9workshop.uk.co.cakelist.CommonService

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import mobile9workshop.uk.co.cakelist.DataModel.CakeListDataModel
import mobile9workshop.uk.co.cakelist.Interfaces.ApiInterface
import mobile9workshop.uk.co.cakelist.Util.Util
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService {

    val liveUserResponse: MutableLiveData<List<CakeListDataModel>> = MutableLiveData()

    companion object Factory {
        var gson = GsonBuilder().setLenient().create()
        fun create(): ApiInterface {
            Log.e("retrofit","create")

            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .baseUrl("https://gist.githubusercontent.com")
                    .build()

            return retrofit.create(ApiInterface::class.java)
        }
    }

    fun loadCakeListData(context : Context): MutableLiveData<List<CakeListDataModel>>? {

        Log.e("loadCakesData","yes")

        val retrofitCall  = create().getCakeList()

        retrofitCall.enqueue(object : Callback<List<CakeListDataModel>> {
            override fun onFailure(call: Call<List<CakeListDataModel>>, t: Throwable?) {
                Util.showMessage(t?.message.toString(), context)
            }

            override fun onResponse(call: Call<List<CakeListDataModel>>, response: retrofit2.Response<List<CakeListDataModel>>) {

                val list  = response.body()
                for (i in list.orEmpty()){
                    Log.e("on response 1:", i.title)
                }

                liveUserResponse.value = list

                Log.e("hasActiveObservers 1", liveUserResponse.hasActiveObservers().toString()+" check")

                Log.e("on response 2 :", liveUserResponse.toString()+" check")

            }

        })

        return liveUserResponse
    }
}
