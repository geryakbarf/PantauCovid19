package com.ergnologi.pantaucovid19.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ergnologi.pantaucovid19.R
import com.ergnologi.pantaucovid19.ui.PeriksaMandiriActivity
import kotlinx.android.synthetic.main.fragment_dashboard.*

class PeriksaMandiriFragment : Fragment(), View.OnClickListener {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dashboard, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btnMulai.setOnClickListener(this)
    }

    override fun onClick(p0: View?) {
        startActivity(Intent(context, PeriksaMandiriActivity::class.java))
    }
}
