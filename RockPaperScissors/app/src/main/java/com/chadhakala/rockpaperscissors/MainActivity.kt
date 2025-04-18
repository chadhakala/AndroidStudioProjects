package com.chadhakala.rockpaperscissors

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.chadhakala.rockpaperscissors.databinding.ActivityMainBinding
import kotlin.random.Random


class MainActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        var playerScore = 0
        var computerScore = 0
        var gameOver = false
        val buttons = listOf(binding.paperButton, binding.rockButton, binding.scissorsButton)

        fun resetGame() {
            playerScore = 0
            computerScore = 0
            gameOver = false
            binding.playerScore.text = playerScore.toString()
            binding.computerScore.text = computerScore.toString()
            binding.rpsWinTextView.text = ""


            var defaultInstr : String = "Pick an object"
            binding.replayButton.visibility = View.INVISIBLE
        }

        fun handleButtonClick(button: Button) {
            if (!gameOver) {

                val random = Random.nextInt(1, 4)
                val computerChoice = when (random) {
                    1 -> binding.rockButton
                    2 -> binding.paperButton
                    3 -> binding.scissorsButton
                    else -> resetGame()
                }
                if (button == computerChoice) {
                    binding.rpsWinTextView.text = "It's a tie!"
                } else if (
                    (button == binding.rockButton && computerChoice == binding.scissorsButton) ||
                    (button == binding.paperButton && computerChoice == binding.rockButton) ||
                    (button == binding.scissorsButton && computerChoice == binding.paperButton)
                ) {
                    binding.rpsWinTextView.text = "nice, that's a point for Player"
                    playerScore += playerScore.toString().toInt()
                    binding.playerScore.text = playerScore.toString()
                    if (playerScore == 10) {
                        gameOver = true
                        binding.replayButton.visibility = View.VISIBLE
                        binding.rpsWinTextView.text = "You win!"
                        binding.instructionsTextView.text = "PLAY AGAIN??"
                        binding.playerScore.text = playerScore.toString()
                        binding.computerScore.text = computerScore.toString()
                        resetGame()
                    }
                } else {
                    binding.rpsWinTextView.text = "Thats a point for Computer"
                    computerScore++
                    binding.computerScore.text = computerScore.toString()
                    if (computerScore == 10) {
                        gameOver = true
                        binding.replayButton.visibility = View.VISIBLE
                        binding.rpsWinTextView.text = "You lose!"
                        binding.instructionsTextView.text = "PLAY AGAIN??"
                        binding.playerScore.text = playerScore.toString()
                        binding.computerScore.text = computerScore.toString()
                        resetGame()
                    }

                }
            }
        }

    }
}
