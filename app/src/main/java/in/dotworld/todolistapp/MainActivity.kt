package `in`.dotworld.todolistapp

import `in`.dotworld.todolistapp.database.DataBase
import `in`.dotworld.todolistapp.database.Entity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.SparseBooleanArray
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var itemlist = ArrayList<String>()
        var adapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_multiple_choice, itemlist
        )
        add.setOnClickListener {
            if (editText.text.toString() == "") {
                Toast.makeText(this, "please provide description TO DO", Toast.LENGTH_SHORT).show()
            } else {
                val list = Entity(editText.text.toString())
                val d = DataBase.getInstance(this)
                d.doToDo().insertText(list)
                d.doToDo().getText().forEach {
                    Log.d("message", """message - ${it.todo}""")
                    var t =it.todo
                }
                Toast.makeText(this, "added", Toast.LENGTH_SHORT).show()
                itemlist.add(editText.text.toString())
                listView.adapter = adapter
                adapter.notifyDataSetChanged()
                editText.text.clear()
            }
        }

        clear.setOnClickListener {
            itemlist.clear()
            adapter.notifyDataSetChanged()
            val db = DataBase.getInstance(this)
            db.clearAllTables()
        }

        delete.setOnClickListener {
            val position: SparseBooleanArray = listView.checkedItemPositions
            val count = listView.count
            var item = count - 1
            while (item >= 0) {
                if (position.get(item)) {
                    adapter.remove(itemlist.get(item))
                }
                item--
            }
            position.clear()
            adapter.notifyDataSetChanged()
        }
    }
}
