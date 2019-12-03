package mobile9workshop.uk.co.cakelist.Util

import android.app.AlertDialog
import android.content.Context
import mobile9workshop.uk.co.cakelist.DataModel.CakeListDataModel

class Util {

    companion object {

        fun showMessageForCakeList(cakeList: List<CakeListDataModel>, pos: Int, context: Context) {
            val builder = AlertDialog.Builder(context)
            builder.setTitle(cakeList[pos].title)
            builder.setMessage(cakeList[pos].desc)
            builder.setPositiveButton("OK") { dialogInterface, i ->

            }
            builder.show()
        }


        fun showMessage(errorMessage : String, context: Context) {
            val builder = AlertDialog.Builder(context)
            builder.setTitle("Error")
            builder.setMessage(errorMessage)
            builder.setPositiveButton("OK") { dialogInterface, i ->

            }
            builder.show()
        }
    }
}