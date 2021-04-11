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

        setSearchViewWatcher()
    }

    private fun initAdapter() {
        imageListingAdapter = ImageListingAdapter()

        binding.recyclerviewImage.run {
            adapter = imageListingAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    private fun setSearchViewWatcher() {
        val textListener = object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query.isNullOrBlank()) {
                    binding.textViewImage.visibility = View.VISIBLE
                    binding.recyclerviewImage.visibility = View.GONE
                } else {
                    viewModel.findImage(getString(R.string.kakao_rest_key), query)
                        .observe(viewLifecycleOwner) {
                            binding.textViewImage.visibility = View.GONE
                            binding.recyclerviewImage.visibility = View.VISIBLE

                            it?.let {
                                imageListingAdapter.submitList(it)
                            }
                        }
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrBlank()) {
                    binding.textViewImage.visibility = View.VISIBLE
                    binding.recyclerviewImage.visibility = View.GONE
                } else {
                    viewModel.findImage(getString(R.string.kakao_rest_key), newText)
                        .observe(viewLifecycleOwner) {
                            binding.textViewImage.visibility = View.GONE
                            binding.recyclerviewImage.visibility = View.VISIBLE

                            it?.let {
                                imageListingAdapter.submitList(it)
                            }
                        }
                }
                return true
            }
        }
        binding.searchViewImage.isSubmitButtonEnabled = true
        binding.searchViewImage.setOnQueryTextListener(textListener)
    }
}