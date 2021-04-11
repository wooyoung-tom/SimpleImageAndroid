package tom.dev.simpleimagelisting.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import tom.dev.simpleimagelisting.R
import tom.dev.simpleimagelisting.databinding.FragmentImageListingBinding
import tom.dev.simpleimagelisting.viewmodel.ImageListingViewModel

@AndroidEntryPoint
class ImageListingFragment : Fragment() {

    private val viewModel: ImageListingViewModel by viewModels()

    private var _binding: FragmentImageListingBinding? = null
    private val binding get() = _binding!!

    private lateinit var imageListingAdapter: ImageListingAdapter

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

        initAdapter()

        observeImageList()
    }

    private fun initAdapter() {
        imageListingAdapter = ImageListingAdapter()

        binding.recyclerviewImage.run {
            adapter = imageListingAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun observeImageList() {
        viewModel.findImage(getString(R.string.kakao_rest_key), "아이유")
            .observe(viewLifecycleOwner) {
                it?.let {
                    imageListingAdapter.submitList(it)
                }
            }
    }
}