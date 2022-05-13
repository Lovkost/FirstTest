package com.example.firsttest

import android.content.Context
import android.os.Build
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.firsttest.view.details.DetailsActivity
import junit.framework.TestCase
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
class DetailsActivityTest {

    private lateinit var scenario: ActivityScenario<DetailsActivity>
    private lateinit var context: Context

    @Before
    fun setup() {
        scenario = ActivityScenario.launch(DetailsActivity::class.java)
        context = ApplicationProvider.getApplicationContext()
    }

    @Test
    fun activity_AssertNotNull() {
        scenario.onActivity {
            TestCase.assertNotNull(it)
        }
    }

    @Test
    fun activity_IsResumed() {
        TestCase.assertEquals(Lifecycle.State.RESUMED, scenario.state)
    }

    @Test
    fun activityTextView_NotNull() {
        scenario.onActivity {
            val totalCountTextView = it.findViewById<TextView>(R.id.totalCountTextView)
            TestCase.assertNotNull(totalCountTextView)
        }
    }

    @Test
    fun activityTextView_HasText() {
        scenario.onActivity {
            val totalCountTextView = it.findViewById<TextView>(R.id.totalCountTextView)
            TestCase.assertEquals("Number of results: 0", totalCountTextView.text)
        }
    }

    @Test
    fun activityTextView_IsVisible() {
        scenario.onActivity {
            val totalCountTextView = it.findViewById<TextView>(R.id.totalCountTextView)
            TestCase.assertEquals(View.VISIBLE, totalCountTextView.visibility)
        }
    }

    @Test
    fun activityButtons_AreVisible() {
        scenario.onActivity {
            val decrementButton = it.findViewById<Button>(R.id.decrementButton)
            TestCase.assertEquals(View.VISIBLE, decrementButton.visibility)

            val incrementButton = it.findViewById<Button>(R.id.incrementButton)
            TestCase.assertEquals(View.VISIBLE, incrementButton.visibility)
        }
    }

    @Test
    fun activityButtonIncrement_IsWorking() {
        scenario.onActivity {
            val incrementButton = it.findViewById<Button>(R.id.incrementButton)
            val totalCountTextView = it.findViewById<TextView>(R.id.totalCountTextView)
            incrementButton.performClick()

            TestCase.assertEquals("Number of results: 1", totalCountTextView.text)
        }
    }

    @Test
    fun activityButtonDecrement_IsWorking() {
        scenario.onActivity {
            val decrementButton = it.findViewById<Button>(R.id.decrementButton)
            val totalCountTextView = it.findViewById<TextView>(R.id.totalCountTextView)
            decrementButton.performClick()

            TestCase.assertEquals("Number of results: -1", totalCountTextView.text)
        }
    }

    @Test
    fun activityCreateIntent_NotNull() {
        val intent = DetailsActivity.getIntent(context, 0)
        TestCase.assertNotNull(intent)
    }

    @Test
    fun activityCreateIntent_HasExtras() {
        val intent = DetailsActivity.getIntent(context, 0)
        val bundle = intent.extras
        TestCase.assertNotNull(bundle)
    }

    @Test
    fun activityCreateIntent_HasCount() {
        val count = 42
        val intent = DetailsActivity.getIntent(context, count)
        val bundle = intent.extras
        TestCase.assertEquals(count, bundle?.getInt(DetailsActivity.TOTAL_COUNT_EXTRA, 0))
    }

    @After
    fun close() {
        scenario.close()
    }
}