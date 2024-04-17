package com.betrybe.trybnb.ui.views.activities

import CreateReservationFragment
import ProfileFragment
import ReservationFragment
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentContainerView
import com.betrybe.trybnb.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private val MenuBottom: BottomNavigationView by lazy {
        findViewById(R.id.navigation_bottom_view)
    }
    private val mainFragmentContainer: FragmentContainerView by lazy {
        findViewById(R.id.main_fragment_container)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MenuBottom.setOnItemSelectedListener { menuItem ->// adiciona a escuta do botao
            when(menuItem.itemId) { // caso o item clicado tenha o id
                R.id.reservation_menu_item -> {
                    supportFragmentManager.beginTransaction() // supFragMan p/ começar uma nova transiçao
                        .replace(mainFragmentContainer.id, ReservationFragment()) // seleciona o que substitui e o fragment novo
                        .commit()
                    true// indica que o evento foi consumido
                }
                R.id.create_reservation_menu_item -> {
                    supportFragmentManager.beginTransaction()
                        .replace(mainFragmentContainer.id, CreateReservationFragment())
                        .commit()
                    true
                }
                R.id.profile_menu_tem -> {
                    supportFragmentManager.beginTransaction()
                        .replace(mainFragmentContainer.id, ProfileFragment())
                        .commit()
                    true
                }
                else -> false// se nenhum caso corresponder retorna false
            }
        }
    }
}
