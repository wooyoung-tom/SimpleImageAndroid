package tom.dev.simpleimagelisting

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import tom.dev.simpleimagelisting.databinding.ActivityMainBinding
import tom.dev.simpleimagelisting.view.ImageListingFragment

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()

        // Image Listing Fragment Transaction
        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, ImageListingFragment.newInstance())
            .commitNow()
    }

    // this is blank comment
}