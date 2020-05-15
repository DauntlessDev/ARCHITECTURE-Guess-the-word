package com.dauntless.architectureguesstheword.screens.game

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.dauntless.architectureguesstheword.R
import com.dauntless.architectureguesstheword.databinding.FragmentGameBinding

/**
 * Fragment where the game is played
 */
class GameFragment : Fragment() {

    private lateinit var viewModel: GameViewModel

    private lateinit var binding: FragmentGameBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_game,
            container,
            false
        )


        Log.i("GameFragment", "Called VMprovider")
        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)
        binding.gameViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.eventGameFinish.observe(viewLifecycleOwner, Observer { hasFinished ->
                if (hasFinished){
                    gameFinished()
                    viewModel.onGameFinished()
                }

        })

        return binding.root


    }
    /**
     * Called when the game is finished
     */
    private fun gameFinished() {
        val action =
            GameFragmentDirections.actionGameToScore( viewModel.score.value ?: 0 )
        findNavController(this).navigate(action)
    }
}
