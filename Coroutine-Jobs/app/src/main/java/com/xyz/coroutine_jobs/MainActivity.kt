package com.xyz.coroutine_jobs

import android.os.Bundle
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

/**
 * @author Lloyd D costa
 *
 * This Activity will start a job in the background and update the UI once the job has finished executing
 * Also the job can be cancelled any time
 */
class MainActivity : AppCompatActivity() {

    private lateinit var job: CompletableJob
    private val PROGRESS_MAX = 100
    private val PROGRESS_START = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnStartJob.setOnClickListener {

            if (!::job.isInitialized) {
                initJob()
            }

            progress.StartJobOrCancel(job)
        }
    }

    /**
     * Initialize the job and handle the job cancellation request
     */
    private fun initJob() {
        btnStartJob.text = "Start Job #1"
        updateTextView("")
        job = Job()
        job.invokeOnCompletion { it ->
            it?.message?.let {
                if (it.isNullOrBlank()) {
                    // Unkonwn exception
                    showToast("Unkown error")
                }
                showToast(it)
            }
        }

        progress.max = PROGRESS_MAX
        progress.progress = PROGRESS_START

    }

    /**
     * This is an Progress Bar extension function which starts a job if it has not started yet
     * also it will cancel and reset if the job has already started
     */
    fun ProgressBar.StartJobOrCancel(job: Job) {
        if (this.progress > 0) {
            resetJob()
        } else {
            btnStartJob.text = "Cancel Job#1"
            CoroutineScope(IO + job).launch {
                for (i in PROGRESS_START..PROGRESS_MAX) {
                    delay(40)
                    this@StartJobOrCancel.progress = i
                }
                updateTextView("Job is completed")
                resetJob()
            }

        }
    }

    /**
     * Shows the toast on the main Thread and called from background thread.
     */
    private fun showToast(message: String) {
        CoroutineScope(Main).launch {
            Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
        }
    }

    /**
     * Update the textview in the main thread
     */
    private fun updateTextView(text: String) {
        CoroutineScope(Main).launch {
            tvJobStatus.text = text
        }
    }

    /**
     * This is used to reset the job if its active or already completed
     */
    private fun resetJob() {
        if (job.isActive || job.isCompleted) {
            job.cancel(CancellationException("Resetting job"))
        }
        initJob()
    }

}