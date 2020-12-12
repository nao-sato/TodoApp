package com.example.baselearningapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.SimpleAdapter

class MainActivity2 : AppCompatActivity() {

    //リストビューに表示するリストデータ。
    private var _menuList: MutableList<MutableMap<String, Any>>? = null
    private val FROM = arrayOf("name", "price")
    private val TO = intArrayOf(R.id.tvMenuName, R.id.tvMenuPrice)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        _menuList = createTeishokuList()

        val lvMenu = findViewById<ListView>(R.id.lvMenu)
        val adapter = SimpleAdapter(applicationContext, _menuList, R.layout.row, FROM, TO)
        lvMenu.adapter = adapter
        lvMenu.onItemClickListener = ListItemClickListener()

    }

    private inner class ListItemClickListener : AdapterView.OnItemClickListener{
        override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

            val item = parent?.getItemAtPosition(position) as MutableMap<String,Any>

            val menuName = item["name"] as String
            val menuPrice = item["price"] as Int

            val intent = Intent(applicationContext,MenuThankActivity::class.java)

            intent.putExtra("menuName", menuName)
            intent.putExtra("menuPrice", "${menuPrice}円")

            startActivity(intent)

        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_options_menu_list, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menuListOptionTeishoku ->
                _menuList = createTeishokuList()
            R.id.menuListOptionCurry ->
                _menuList = createCurryList()
        }

        val lvMenu = findViewById<ListView>(R.id.lvMenu)
        val adapter = SimpleAdapter(applicationContext,_menuList,R.layout.row,FROM,TO)
        lvMenu.adapter = adapter
        return super.onOptionsItemSelected(item)
    }



    private fun createTeishokuList(): MutableList<MutableMap<String, Any>> {
        val menuList: MutableList<MutableMap<String, Any>> = mutableListOf()
        var menu = mutableMapOf<String, Any>("name" to "から揚げ定食", "price" to 800, "desc" to "若鳥のから揚げにサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)
        menu = mutableMapOf("name" to "ハンバーグ定食", "price" to 850, "desc" to "手ごねハンバーグにサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)
        menu = mutableMapOf("name" to "生姜焼き定食", "price" to 850, "desc" to "すりおろし生姜を使った生姜焼きにサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)
        menu = mutableMapOf("name" to "ステーキ定食", "price" to 1000, "desc" to "国産牛のステーキにサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)
        menu = mutableMapOf("name" to "野菜炒め定食", "price" to 750, "desc" to "季節の野菜炒めにサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)
        menu = mutableMapOf("name" to "とんかつ定食", "price" to 900, "desc" to "ロースとんかつにサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)
        menu = mutableMapOf("name" to "ミンチかつ定食", "price" to 850, "desc" to "手ごねミンチカツにサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)
        menu = mutableMapOf("name" to "チキンカツ定食", "price" to 900, "desc" to "ボリュームたっぷりチキンカツにサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)
        menu = mutableMapOf("name" to "コロッケ定食", "price" to 850, "desc" to "北海道ポテトコロッケにサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)
        menu = mutableMapOf("name" to "焼き魚定食", "price" to 850, "desc" to "鰆の塩焼きにサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)
        menu = mutableMapOf("name" to "焼肉定食", "price" to 950, "desc" to "特性たれの焼肉にサラダ、ご飯とお味噌汁が付きます。")
        menuList.add(menu)
        return menuList
    }


    private fun createCurryList(): MutableList<MutableMap<String, Any>> {
        val menuList: MutableList<MutableMap<String, Any>> = mutableListOf()
        var menu = mutableMapOf<String, Any>("name" to "ビーフカレー", "price" to 520, "desc" to "特選スパイスをきかせた国産ビーフ100%のカレーです。")
        menuList.add(menu)
        menu = mutableMapOf("name" to "ポークカレー", "price" to 420, "desc" to "特選スパイスをきかせた国産ポーク100%のカレーです。")
        menuList.add(menu)
        menu = mutableMapOf("name" to "ハンバーグカレー", "price" to 620, "desc" to "特選スパイスをきかせたカレーに手ごねハンバーグをトッピングです。")
        menuList.add(menu)
        menu = mutableMapOf("name" to "チーズカレー", "price" to 560, "desc" to "特選スパイスをきかせたカレーにとろけるチーズをトッピングです。")
        menuList.add(menu)
        menu = mutableMapOf("name" to "カツカレー", "price" to 760, "desc" to "特選スパイスをきかせたカレーに国産ロースカツをトッピングです。")
        menuList.add(menu)
        menu = mutableMapOf("name" to "ビーフカツカレー", "price" to 880, "desc" to "特選スパイスをきかせたカレーに国産ビーフカツをトッピングです。")
        menuList.add(menu)
        menu = mutableMapOf("name" to "からあげカレー", "price" to 540, "desc" to "特選スパイスをきかせたカレーに若鳥のから揚げをトッピングです。")
        menuList.add(menu)
        return menuList
    }

}
