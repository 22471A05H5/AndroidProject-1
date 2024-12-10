package com.example.myproject

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle

import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainMenuBar : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {
    private lateinit var drawerLayout: DrawerLayout
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu_bar)
        drawerLayout=findViewById<DrawerLayout>(R.id.drawer_layout)

        val toolbar=findViewById<Toolbar>(R.id.toolbarnew)
        setSupportActionBar(toolbar)

        val navigationView=findViewById<NavigationView>(R.id.nav_view_new)
        navigationView.setNavigationItemSelectedListener(this)
        val toggle=androidx.appcompat.app.ActionBarDrawerToggle(
            this,
            drawerLayout,
            toolbar,
            R.string.open_nav,
            R.string.close_nav



        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        if (savedInstanceState==null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_new,HomeFragment()).commit()
            navigationView.setCheckedItem(R.id.item1)
        }
    }
    private fun ActionBarDrawerToggle(mainMenuBar: MainMenuBar,drawerLayout: DrawerLayout) {
        return
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.item1 -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_new,HomeFragment()).commit()
            R.id.item2 -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_new,AboutusFragment()).commit()
            R.id.item3 -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_new,FeedbackFragment()).commit()
            R.id.item4 -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_new,LogoutFragment()).commit()
            R.id.item5 -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_new,SettingsFragment()).commit()
            R.id.item6 -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_new,ReviewFragment()).commit()
            R.id.item7 -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container_new,SettingssFragment()).commit()
            R.id.item8 -> {
                // Launch SettingsActivity when item7 is clicked
                val intent = Intent(this, LoginPage::class.java)
                startActivity(intent)
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

   @SuppressLint("MissingSuperCall")
   override fun onBackPressed(){
       if (drawerLayout.isDrawerOpen(GravityCompat.START)){
           drawerLayout.closeDrawer(GravityCompat.START)
       }
       else{
           onBackPressedDispatcher.onBackPressed()
       }
   }
}
