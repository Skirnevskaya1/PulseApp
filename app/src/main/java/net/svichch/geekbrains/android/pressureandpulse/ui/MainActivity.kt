package net.svichch.geekbrains.android.pressureandpulse.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import net.svichch.geekbrains.android.pressureandpulse.R
import net.svichch.geekbrains.android.pressureandpulse.data.firestore.viewmodel.FirestoreViewModel
import net.svichch.geekbrains.android.pressureandpulse.ui.listPulse.FragmentPulseList

class MainActivity : AppCompatActivity() {
    private lateinit var fragment: Fragment
    private lateinit var dbFirestore: FirestoreViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fragment = FragmentPulseList.newInstance()
        navigateTo(fragment)
        dbFirestore = ViewModelProvider(this).get(FirestoreViewModel::class.java)

    }

    fun navigateTo(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction
            .replace(R.id.container, fragment)
            .addToBackStack("pulse")
            .commit()
    }

    // Возрат
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.getItemId()) {
            android.R.id.home -> {
                fragment.parentFragmentManager.popBackStack()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    fun  getDbFirestore(): FirestoreViewModel{
        return dbFirestore
    }
}