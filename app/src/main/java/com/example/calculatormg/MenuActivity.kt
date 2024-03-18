package com.example.calculatormg

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.menu_layout)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun goSimpleCalculator(view: View) {
        var simpleActivity : Intent = Intent(applicationContext,SimpleCalcActivity::class.java)
        startActivity(simpleActivity);
    }
    fun goAdvancedCalculator(view: View) {
        var advancedActivity : Intent = Intent(applicationContext,AdvancedCalcActivity::class.java)
        startActivity(advancedActivity);
    }
    fun goAboutMe(view: View) {
        var aboutMeActivity : Intent = Intent(applicationContext,AboutMeActivity::class.java)
        startActivity(aboutMeActivity);
    }

    fun exitApp(view: View) {
            finishAffinity()
    }
}