package com.hackertronix.mergeadaptersample

import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.MergeAdapter
import androidx.recyclerview.widget.MergeAdapter.Config.DEFAULT
import androidx.recyclerview.widget.MergeAdapter.Config.StableIdMode
import com.hackertronix.mergeadaptersample.model.WhatsNew
import com.hackertronix.mergeadaptersample.model.getMobileBufferoos
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : AppCompatActivity(), WhatsNewAdapter.WhatsNewListener {

    private lateinit var whatsNewAdapter: WhatsNewAdapter
    private lateinit var bufferoosAdapter: BufferoosAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()
        fetchNewFeatureDataFromServer()
        fetchBufferoos()
    }

    private fun fetchBufferoos() {

        val listOfBufferoos = getMobileBufferoos().sortedBy { it.name }
        bufferoosAdapter.bufferoos = listOfBufferoos
        bufferoosAdapter.notifyItemRangeInserted(0, listOfBufferoos.size)
        feedRecyclerView.scrollToPosition(0)
    }

    private fun initRecyclerView() {
        whatsNewAdapter = WhatsNewAdapter(this)
        bufferoosAdapter = BufferoosAdapter()

        feedRecyclerView.adapter = MergeAdapter(whatsNewAdapter, bufferoosAdapter)
    }

    private fun fetchNewFeatureDataFromServer() {

        /**
         * Faking a long running network call.
         * This is a contrived example. Ideally this would be in a repository class
         * */
        Handler().postDelayed(Runnable {
            whatsNewAdapter.whatsNew =
                WhatsNew("Clicking the dismiss button will make this card disappear with an animation!")
            whatsNewAdapter.notifyItemInserted(0)
            feedRecyclerView.scrollToPosition(0)
        }, 2_000L)
    }


    override fun onDismiss() {
        whatsNewAdapter.whatsNew = null
        whatsNewAdapter.notifyItemRemoved(0)
    }
}
