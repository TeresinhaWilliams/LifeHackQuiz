package com.example.lifehack

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.lifehack.databinding.ActivityQuizBinding

class QuizActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuizBinding

    companion object {
        private const val TAG = "QuizActivity"
    }

    // Track current question + score
    private var currentIndex = 0
    private var score = 0

    // Questions
    private val questions = arrayOf(
        "Closing background apps improves your phone battery life",
        "Using incognito mode makes you completely anonymous online",
        "Charging your phone to 100% every time is best for battery health",
        "Turning off Bluetooth always saves significant battery",
        "Eating late at night directly causes weight gain",
        "You should drink 8 glasses of water every day no matter what",
        "Microwaving food destroys all nutrients",
        "More megapixels means a better camera",
        "Airplane mode charges your phone faster",
        "Free WiFi is always safe if it has a password",
        "Using dark mode always saves battery on all phones",
        "Deleting apps you don’t use improves phone performance",
        "Natural remedies are always safer than medicine",
        "You can detox your body with juice cleanses",
        "Sleeping 5 hours is enough if you feel fine"
    )

    // Answers (true = Hack, false = Myth)
    private val answers = arrayOf(
        false, // background apps myth (modern phones manage this)
        false, // incognito myth
        false, // 100% charging not ideal
        false, // minimal impact
        false, // depends on calories
        false, // depends on body
        false, // nutrients mostly remain
        false, // not the only factor
        true,  // airplane mode helps
        false, // password ≠ safe
        false, // only OLD screens benefit
        true,  // helps performance
        false, // not always safer
        false, // body detoxes itself
        false  // most people need 7–9 hrs
    )

    // Correct feedback
    private val correctFeedback = arrayOf(
        "Modern phones manage background apps efficiently.",
        "Incognito only hides history locally, not from websites.",
        "Keeping battery between 20–80% is healthier.",
        " Bluetooth uses very little battery today.",
        " Weight gain depends on total calories, not timing.",
        "Correct! Water needs vary per person.",
        "Microwaving keeps most nutrients intact.",
        "Camera quality depends on sensors, not just megapixels.",
        "Airplane mode reduces background activity.",
        "Password-protected WiFi can still be unsafe.",
        "Only OLED screens benefit from dark mode.",
        " Removing apps frees resources.",
        "Some natural remedies can be harmful.",
        "Your liver and kidneys detox your body.",
        "Most adults need more sleep."
    )

    // Incorrect feedback
    private val incorrectFeedback = arrayOf(
        "Wrong! Closing apps can actually use more battery.",
        "Wrong! Incognito does NOT make you anonymous.",
        "Wrong! Charging to 100% constantly can wear battery faster.",
        "Wrong! Bluetooth has minimal battery impact.",
        "Wrong! Timing alone doesn’t cause weight gain.",
        "Wrong! There’s no fixed rule for everyone.",
        "Wrong! Microwaves don’t destroy all nutrients.",
        "Wrong! Megapixels aren’t everything.",
        "Wrong! Airplane mode actually speeds up charging.",
        "Wrong! Public WiFi can still be risky.",
        "Wrong! Dark mode doesn’t always save battery.",
        "Wrong! Deleting apps can improve performance.",
        "Wrong! Natural doesn’t always mean safe.",
        "Wrong! Juice cleanses don’t detox your body.",
        "Wrong! 5 hours is usually not enough sleep."
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d(TAG, "Quiz started")

        // Load first question
        loadQuestion()

        // Hack button (True)
        binding.btnHack.setOnClickListener {
            Log.d(TAG, "User selected TRUE")
            checkAnswer(true)
        }

        // Myth button (False)
        binding.btnMyth.setOnClickListener {
            Log.d(TAG, "User selected FALSE")
            checkAnswer(false)
        }

        // Next button
        binding.btnNext.setOnClickListener {

            currentIndex++

            Log.d(TAG, "Next clicked. Moving to index: $currentIndex")

            if (currentIndex < questions.size) {
                loadQuestion()
            } else {
                Log.d(TAG, "Quiz finished. Score: $score")

                // Move to ScoreActivity
                val intent = ScoreActivity.newIntent(this, score, questions.size)
                startActivity(intent)
                finish()
            }
        }
    }

    // Load current question
    private fun loadQuestion() {
        binding.txtQuestion.text = questions[currentIndex]

        Log.d(TAG, "Displaying question: ${questions[currentIndex]}")
    }

    // Check if answer is correct
    private fun checkAnswer(userAnswer: Boolean) {

        val correctAnswer = answers[currentIndex]

        if (userAnswer == correctAnswer) {
            score++
            Toast.makeText(this, correctFeedback[currentIndex], Toast.LENGTH_SHORT).show()

            Log.d(TAG, "Correct answer. Score: $score")
        } else {
            Toast.makeText(this, incorrectFeedback[currentIndex], Toast.LENGTH_SHORT).show()

            Log.d(TAG, "Incorrect answer")
        }
    }
}