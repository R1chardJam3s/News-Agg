package com.example.newsaggregator

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsaggregator.adapters.ArticleAdapter
import com.example.newsaggregator.adapters.CategoryAdapter
import com.google.android.material.checkbox.MaterialCheckBox
import java.util.ArrayList

/**
 * Method for acquiring preferences
 * @author Richard James
 */
class CatPrefFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_cat_preferences, container, false)

        val prefs = this.activity!!.getSharedPreferences("Preferences", Context.MODE_PRIVATE)

        val busPref = view.findViewById<MaterialCheckBox>(R.id.busi_check)
        busPref.isChecked = prefs.getBoolean("busi",false)

        val entPref = view.findViewById<MaterialCheckBox>(R.id.ent_check)
        entPref.isChecked = prefs.getBoolean("ent",false)

        val helPref = view.findViewById<MaterialCheckBox>(R.id.health_check)
        helPref.isChecked = prefs.getBoolean("hel",false)

        val sciPref = view.findViewById<MaterialCheckBox>(R.id.sci_check)
        sciPref.isChecked = prefs.getBoolean("sci",false)

        val sprPref = view.findViewById<MaterialCheckBox>(R.id.sports_check)
        sprPref.isChecked = prefs.getBoolean("spr",false)

        val techPref = view.findViewById<MaterialCheckBox>(R.id.tech_check)
        techPref.isChecked = prefs.getBoolean("tech",false)

        val saveButton = view.findViewById<Button>(R.id.save_button)
        saveButton.setOnClickListener{
            val editor = prefs.edit()
            editor.putBoolean("busi",busPref.isChecked)
            editor.putBoolean("ent",entPref.isChecked)
            editor.putBoolean("hel",helPref.isChecked)
            editor.putBoolean("sci",sciPref.isChecked)
            editor.putBoolean("spr",sprPref.isChecked)
            editor.putBoolean("tech",techPref.isChecked)
            editor.commit()
            this.activity!!.finish()
        }
        return view
    }

}