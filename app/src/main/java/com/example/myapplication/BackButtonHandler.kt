import android.app.Activity
import android.view.View
import android.widget.Button

class BackButtonHandler(private val activity: Activity) {

    fun setupBackButton(backButton: Button) {
        backButton.setOnClickListener {
            activity.onBackPressed()
        }
    }

    fun hideBackButton(backButton: Button) {
        backButton.visibility = View.GONE
    }
}
