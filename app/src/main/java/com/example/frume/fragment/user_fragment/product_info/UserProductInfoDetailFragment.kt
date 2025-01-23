package com.example.frume.fragment.user_fragment.product_info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.frume.R
import com.example.frume.databinding.FragmentUserProductInfoDetailBinding
import com.example.frume.fragment.user_fragment.product_info.UserProductInfoDescriptionFragment.Companion
import com.example.frume.service.ProductService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class UserProductInfoDetailFragment : Fragment() {
    private var _binding: FragmentUserProductInfoDetailBinding? = null
    private val binding get() = _binding!!
    private var productDocId: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            productDocId = it.getString(ARG_PRODUCT_DOC_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_product_info_detail, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setLayout()
    }

    private fun setLayout() {
        getProductData()
    }


    private fun getProductData() {
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val productList = ProductService.getProductInfo(productDocId!!)
                withContext(Dispatchers.Main) {
                    // UI
                    productList.forEach { item ->
                        with(binding) {
                            textViewProductInfoDetailBreed.text = item.productCategory2
                            textViewProductInfoDetailWeight.text = "${item.productVolume}${item.productUnit}"
                        }
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(requireContext(), "no data", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    companion object {
        private const val ARG_PRODUCT_DOC_ID = "product_doc_id"
        fun newInstance(productDocId: String): UserProductInfoDetailFragment {
            return UserProductInfoDetailFragment().apply {
                // 값 전달 코드 번들 사용
                arguments = Bundle().apply {
                    putString(ARG_PRODUCT_DOC_ID, productDocId)
                }
            }
        }
    }
}