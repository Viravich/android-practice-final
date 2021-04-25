package com.example.navigationcomponent1

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fragment_second.view.*


class SecondFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_second, container, false)

        view.button2.setOnClickListener {
            view.findNavController().navigate(R.id.action_secondFragment_to_firstFragment)
        }
        //call use function retrievedArgs
        retrieveArgument()
        //show option menu
        setHasOptionsMenu(true)

        return view
    }

    // retrieve argument from first fragment
    private fun retrieveArgument() {
        arguments?.let { arguments ->
            val args = SecondFragmentArgs.fromBundle(arguments)
            val message = args.message
            val number = args.number
            Toast.makeText(activity, "$message, $number", Toast.LENGTH_SHORT).show()
        }
    }

    //creating our share Intent
    private fun getShareIntent(): Intent {
        val args = SecondFragmentArgs.fromBundle(requireArguments())
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain").putExtra(Intent.EXTRA_TEXT, args.message + ". " + args.number)
        return shareIntent
    }
    //call use function getShareIntent() for start activity
    private fun shareInfo() {
        startActivity(getShareIntent())
    }

    //create option menu in fragment2
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.share_menu, menu)
        if (getShareIntent().resolveActivity(requireActivity().packageManager) == null) {
            menu.findItem(R.id.share).isVisible = false
        }
    }
    //handle menu item select
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.share -> shareInfo()
        }
        return super.onOptionsItemSelected(item)
    }
}