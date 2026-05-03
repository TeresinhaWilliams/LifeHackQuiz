package com.example.lifehack

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.lifehack.databinding.ActivityReviewBinding

class ReviewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReviewBinding

    companion object {
        private const val TAG = "ReviewActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Questions
        val questions = arrayOf(
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

        // Answers
        val answers = arrayOf(
            false, false, false, false, false,
            false, false, false, true, false,
            false, true, false, false, false
        )

        // ✅ NEW: Explanations (THIS WAS MISSING)
        val explanations = arrayOf(
            "Modern phones manage background apps efficiently.",
            "Incognito only hides history locally, not from websites.",
            "Keeping battery between 20–80% is healthier.",
            "Bluetooth uses very little battery today.",
            "Weight gain depends on total calories, not timing.",
            "Water needs vary per person.",
            "Microwaving keeps most nutrients intact.",
            "Camera quality depends on sensors, not just megapixels.",
            "Airplane mode reduces background activity.",
            "Password-protected WiFi can still be unsafe.",
            "Only OLED screens benefit from dark mode.",
            "Removing apps frees resources.",
            "Some natural remedies can be harmful.",
            "Your liver and kidneys detox your body.",
            "Most adults need more sleep."
        )

        Log.d(TAG, "Review screen opened")

        val reviewText = StringBuilder()

        for (i in questions.indices) {

            val correctAnswer = if (answers[i]) "Hack (True)" else "Myth (False)"

            reviewText.append("Q${i + 1}: ${questions[i]}\n")
            reviewText.append("Answer: $correctAnswer\n")
            reviewText.append("Explanation: ${explanations[i]}\n\n")

            Log.d(TAG, "Loaded question ${i + 1}")
        }

        binding.txtReview.text = reviewText.toString()
    }
}