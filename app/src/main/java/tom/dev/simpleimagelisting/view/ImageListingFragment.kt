package tom.dev.simpleimagelisting.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import tom.dev.simpleimagelisting.databinding.FragmentImageListingBinding
import tom.dev.simpleimagelisting.viewmodel.ImageListingViewModel

@AndroidEntryPoint
class ImageListingFragment : Fragment() {

    private val viewModel: ImageListingViewModel by viewModels()

    private var _binding: FragmentImageListingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentImageListingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}