package com.appat.ecamp.ApplicationModules.Dashboard.Fragments


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.appat.ecamp.ApplicationModules.Dashboard.Adapters.LatestNewsAdapter
import com.appat.ecamp.ApplicationModules.Dashboard.Models.LatestNews
import com.appat.ecamp.ApplicationModules.StudentInformationModule.StudentInformationFormActivity
import com.appat.ecamp.ApplicationModules.UserManagement.ParentLoginActivity
import com.appat.ecamp.ApplicationModules.UserManagement.ParentRegistrationActivity

import com.appat.ecamp.R
import com.appat.ecamp.Utilities.AppPreferences
import org.jetbrains.anko.find

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {

    private lateinit var latestNewsRecyclerView: RecyclerView
    private lateinit var latestNewsAdapter: LatestNewsAdapter
    private var latestNewsArray: MutableList<LatestNews> = ArrayList()
    private lateinit var loginCard: CardView
    private lateinit var registrationCard: CardView
    private lateinit var studentRegistrationCard: CardView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        latestNewsRecyclerView = view.find(R.id.latestNewsRecyclerView)
        loginCard = view.find(R.id.loginCard)
        registrationCard = view.find(R.id.registrationCard)
        studentRegistrationCard= view.find(R.id.studentRegistrationCard)
        latestNewsRecyclerView.layoutManager = LinearLayoutManager(activity!!, RecyclerView.VERTICAL, false)
        getLatestNewsData()

        if(AppPreferences.getIsUserLoggedIn())
        {
            loginCard.visibility = View.GONE
            registrationCard.visibility = View.GONE
            studentRegistrationCard.visibility = View.VISIBLE
            studentRegistrationCard.setOnClickListener {
                gotoStudentRegistrationScreen()
            }
        }
        else {
            loginCard.setOnClickListener {
                gotoLoginScreen()
            }

            registrationCard.setOnClickListener {
                gotoRegistrationScreen()
            }
        }

        return view
    }

    private fun getLatestNewsData()
    {
        val news1 = LatestNews("https://torath.gov.ae/uploads/posts/cb69db4e655684ae33c4a27e1cb1d5f4.JPG", "Musical Dreams Become Reality for Seniors", "J.D. Woods arranges his sheet music on the stand awaiting the director to start rehearsing the band. Woods, 77, is a trumpet player who first began learning his instrument eight years ago. His seat mate, Bill Schwartz, 65, played his trumpet previous to joining the band. Woods, playing second trumpet, and Schwartz, playing first, are preparing for a concert. They are musicians in rehearsal with 60 other senior band members in one of the New Horizons Bands, this one located in Saline, Michigan")
        val news2 = LatestNews("https://torath.gov.ae/uploads/posts/00087eec6093217cd268406dde5e51cf.JPG", "Learning to play an instrument and perform in a group", "Just listening to a Concert Band or Orchestra can be a moving experience, but actually sitting in the midst of one for the first time in many years can be quite startling. Cathy Patience of Longmont, Colorado relates a typical reaction. “As a teenager, band activities were a major part of my life. After not playing my clarinet for 38 years, I joined the Desert Foothills New Horizons Band. I will never forget the feeling I had at my first rehearsal – I felt like a part of me that had been dead came alive again.”")
        val news3 = LatestNews("https://torath.gov.ae/uploads/posts/5e5f44ef6ce5a96a439f7675fc77af2e.JPG", "Just listening to a Concert Band or Orchestra can be a moving experience", "Learning, playing, and performing together is a transforming experience; new friendships are formed with fellow musicians. As Harlene Arnett, a member of the Peterborough New Horizons Band in Canada said, “… the best thing is I’ve found a whole new family of genuine caring friends. There are only my daughter and I still here so it’s good to know there is an external family nearby if we need them")
        latestNewsArray.add(news1)
        latestNewsArray.add(news2)
        latestNewsArray.add(news3)
        latestNewsArray.add(news3)
        latestNewsAdapter = LatestNewsAdapter(latestNewsArray, activity!!)
        latestNewsRecyclerView.adapter = latestNewsAdapter
    }

    private fun gotoLoginScreen()
    {
        val intent = Intent(activity!!, ParentLoginActivity::class.java)
        startActivity(intent)
    }

    private fun gotoRegistrationScreen()
    {
        val intent = Intent(activity!!, ParentRegistrationActivity::class.java)
        startActivity(intent)
    }

    private fun gotoStudentRegistrationScreen()
    {
        val intent = Intent(activity!!, StudentInformationFormActivity::class.java)
        startActivity(intent)
    }
}
