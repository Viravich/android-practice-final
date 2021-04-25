package com.example.navigationcomponent1

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Button
import android.widget.ToggleButton
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import kotlinx.android.synthetic.main.fragment_first.*
import kotlinx.android.synthetic.main.fragment_first.view.*


class FirstFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_first, container, false)

        view.button1.setOnClickListener {
            navigationByDirection(view)
        }

        //checked toggle button by if/else statement (1)
        val toggleButton = view.findViewById<ToggleButton>(R.id.toggleButton)
        val button_one = view.findViewById<Button>(R.id.button1)
        //checked toggle button condition (2)
        toggleButton.setOnCheckedChangeListener( { _, pressedToggleButton ->
            if (pressedToggleButton) button_one.setOnClickListener {
                navigationByDirection(view)
            }
            else button_one.setOnClickListener {
                navigationByActionId(view, R.id.action_firstFragment_to_thirdFragment)
            }
        })
        setHasOptionsMenu(true)
        return  view
    }
    //select id of action fragment1 to fragment3
    private fun navigationByActionId(view: View, id: Int) {
        return view.findNavController().navigate(id)
    }
    //select id of action fragment1 to fragment2
    private fun navigationByActionId(view: View) {
        view.findNavController().navigate(R.id.action_firstFragment_to_secondFragment)
    }
    //send message and number from fragment1 to fragment2
    private fun navigationByDirection(view: View) {
        val direction = FirstFragmentDirections.actionFirstFragmentToSecondFragment(
            message = "Viravich",
            number = 100
        )
        view.findNavController().navigate(direction)
    }

    //create option menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
    }
    //add onclick handle option item select
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item,
            requireView().findNavController())
                ||super.onOptionsItemSelected(item)

    }





}