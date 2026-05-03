package com.example.lifehack

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.lifehack.databinding.ActivityScoreBinding

class ScoreActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScoreBinding

    companion object {
        private const val TAG = "ScoreActivity"
        private const val EXTRA_USER_SCORE = "USER_SCORE"
        private const val EXTRA_TOTAL_QUESTIONS = "TOTAL_QUESTIONS"

        /**
         * Recommended way to start this activity
         */
        fun newIntent(context: Context, score: Int, total: Int): Intent {
            return Intent(context, ScoreActivity::class.java).apply {
                putExtra(EXTRA_USER_SCORE, score)
                putExtra(EXTRA_TOTAL_QUESTIONS, total)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityScoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get values from intent
        val finalScore = intent.getIntExtra(EXTRA_USER_SCORE, 0)
        val totalQuestions = intent.getIntExtra(EXTRA_TOTAL_QUESTIONS, 0)

        Log.d(TAG, "Score received: $finalScore / $totalQuestions")

        // Display score
        binding.txtFinalScore.text = getString(R.string.final_score_format, finalScore, totalQuestions)

        // Personalised feedback
        val feedback = when {
            finalScore == totalQuestions -> "Master Hacker! 🔥"
            finalScore >= totalQuestions / 2 -> "Good job! 👍"
            else -> "Keep practising! 💡"
        }

        binding.txtFinalFeedback.text = feedback

        Log.d(TAG, "Feedback displayed: $feedback")

        // Restart button
        binding.btnRestart.setOnClickListener {
            Log.d(TAG, "Restart button clicked")

            val intent = Intent(this, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }

            startActivity(intent)
            finish()
        }

        // Review button
        binding.btnReview.setOnClickListener {
            Log.d(TAG, "Review button clicked")
            val intent = Intent(this, ReviewActivity::class.java)
            startActivity(intent)
        }
    }
}