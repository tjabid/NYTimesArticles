package com.sample.nytimesarticles.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sample.nytimesarticles.R

/**
 * Creates an Activity that hosts all of the fragments in the app
 * This activity has different presentations for handset and tablet-size devices.
 * On handsets, the activity presents a list of items, which when touched,
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class MainActivity : AppCompatActivity() {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private var twoPane: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

//        val toolbar = findViewById<Toolbar>(R.id.toolbar)
//        setSupportActionBar(toolbar)
//        toolbar.title = title

//        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()
//        }
//
//        if (findViewById<NestedScrollView>(R.id.item_detail_container) != null) {
//            // The detail container view will be present only in the
//            // large-screen layouts (res/values-w900dp).
//            // If this view is present, then the
//            // activity should be in two-pane mode.
//            twoPane = true
//        }
//
//        setupRecyclerView(findViewById(R.id.item_list))
    }
//
//    private fun setupRecyclerView(recyclerView: RecyclerView) {
//        recyclerView.adapter = SimpleItemRecyclerViewAdapter(this, DummyContent.ITEMS, twoPane)
//    }
//
//    class SimpleItemRecyclerViewAdapter(private val parentActivity: MainActivity,
//                                        private val values: List<DummyContent.DummyItem>,
//                                        private val twoPane: Boolean) :
//            RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {
//
//        private val onClickListener: View.OnClickListener
//
//        init {
//            onClickListener = View.OnClickListener { v ->
//                val item = v.tag as DummyContent.DummyItem
//                val fragment = DetailFragment().apply {
//                    arguments = Bundle().apply {
//                        putString(DetailFragment.ARG_ITEM_ID, item.id)
//                    }
//                }
//                if (twoPane) {
//                    parentActivity.supportFragmentManager
//                            .beginTransaction()
//                            .replace(R.id.item_detail_container, fragment)
//                            .commit()
//                } else {
//                    parentActivity.supportFragmentManager
//                        .beginTransaction()
//                        .replace(R.id.item_detail_container, fragment)
//                        .commit()
//                }
//            }
//        }
//
//        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//            val view = LayoutInflater.from(parent.context)
//                    .inflate(R.layout.item_list_content, parent, false)
//            return ViewHolder(view)
//        }
//
//        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//            val item = values[position]
//            holder.idView.text = item.id
//            holder.contentView.text = item.content
//
//            with(holder.itemView) {
//                tag = item
//                setOnClickListener(onClickListener)
//            }
//        }
//
//        override fun getItemCount() = values.size
//
//        inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//            val idView: TextView = view.findViewById(R.id.id_text)
//            val contentView: TextView = view.findViewById(R.id.content)
//        }
//    }
}