package tom.dev.simpleimagelisting.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.chip.Chip
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tom.dev.simpleimagelisting.R
import tom.dev.simpleimagelisting.databinding.FragmentImageListingBinding
import tom.dev.simpleimagelisting.model.dto.ImageResponseDocument
import tom.dev.simpleimagelisting.viewmodel.ImageListingViewModel

@AndroidEntryPoint
class ImageListingFragment : Fragment() {

    private val viewModel: ImageListingViewModel by viewModels()

    private var _binding: FragmentImageListingBinding? = null
    private val binding get() = _binding!!

    private lateinit var imageListingAdapter: ImageListingAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentImageListingBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()

        setSearchViewWatcher()

        observeImageSearchResult()
    }

    private fun initAdapter() {
        imageListingAdapter = ImageListingAdapter()

        binding.recyclerviewImage.run {
            adapter = imageListingAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }

    private fun setSearchViewWatcher() {
        val textListener = object : androidx.appcompat.widget.SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query.isNullOrBlank()) {
                    binding.textViewImage.visibility = View.VISIBLE
                    binding.recyclerviewImage.visibility = View.GONE
                    binding.chipGroupImage.removeAllViews()
                } else {
                    viewModel.findImage(getString(R.string.kakao_rest_key), query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrBlank()) {
                    binding.textViewImage.visibility = View.VISIBLE
                    binding.recyclerviewImage.visibility = View.GONE
                    binding.chipGroupImage.removeAllViews()
                } else {
                    viewModel.findImage(getString(R.string.kakao_rest_key), newText)
                }
                return true
            }
        }

        binding.searchViewImage.isSubmitButtonEnabled = true
        binding.searchViewImage.setOnQueryTextListener(textListener)
    }

    private fun observeImageSearchResult() {
        viewModel.imageSearchResultLiveData.observe(viewLifecycleOwner) {
            binding.textViewImage.visibility = View.GONE
            binding.recyclerviewImage.visibility = View.VISIBLE

            binding.chipGroupImage.removeAllViews()

            it?.let {
                imageListingAdapter.submitList(it)
            }

            parseCollections(it)
        }

        viewModel.imageSearchFailedLiveData.observe(viewLifecycleOwner) {
            Snackbar.make(requireView(), it, Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun parseCollections(list: List<ImageResponseDocument>) {
        val collectionList = list.groupBy { it.collection }.keys.toMutableList()

        CoroutineScope(Dispatchers.Main).launch {
            // all 필터링 하나 제일 앞에 추가한다.
            collectionList.add(0, "all")

            setChipView(collectionList)

            // Chip Setting 후에 collectionList clear 해준다.
            collectionList.clear()
        }
    }

    private suspend fun setChipView(list: List<String>) {
        list.forEachIndexed { index, collection ->
            val chip = Chip(requireContext()).apply {
                id = index
                text = collection
            }

            binding.chipGroupImage.addView(chip)
        }
    }

    companion object {
        fun newInstance() = ImageListingFragment()
    }
}