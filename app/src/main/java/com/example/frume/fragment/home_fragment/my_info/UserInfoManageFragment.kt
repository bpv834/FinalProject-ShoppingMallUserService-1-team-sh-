package com.example.frume.fragment.home_fragment.my_info

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.frume.home.HomeActivity
import com.example.frume.R
import com.example.frume.databinding.FragmentUserInfoManageBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class UserInfoManageFragment : Fragment() {
    private var _binding: FragmentUserInfoManageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user_info_manage, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClickButtonUserInfoModify()
        onClickButtonModifyPw()
        onClickToolbar()
        onClickButtonUserInfoManageWithdrawal()  // 탈퇴하기 버튼 클릭 리스너 추가
    }

    // sehoon 정보 수정하기 클릭 메서드
    private fun onClickButtonUserInfoModify() {
        binding.buttonUserInfoManageModifyUserInfo.setOnClickListener {
            val action = UserInfoManageFragmentDirections.actionUserInfoManageToUserInfoModifyFragment()
            findNavController().navigate(action)
        }
    }

    // sehoon 비밀번호 변경 클릭 메서드
    private fun onClickButtonModifyPw() {
        binding.textViewUserInfoManageModifyPW.setOnClickListener {
            val action = UserInfoManageFragmentDirections.actionUserInfoManageToUserPwModify()
            findNavController().navigate(action)
        }
    }

    // sehoon 툴바 클릭 메서드
    private fun onClickToolbar() {
        binding.toolbarUserInfoModify.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    // 탈퇴하기 버튼 클릭 메서드
    private fun onClickButtonUserInfoManageWithdrawal() {
        binding.buttonUserInManageWithdrawal.setOnClickListener {
            // 탈퇴 확인 다이얼로그 표시
            showWithdrawalDialog()
        }
    }

    // 탈퇴 확인 다이얼로그 표시
    private fun showWithdrawalDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("계정 탈퇴")
            .setMessage("정말로 계정을 탈퇴하시겠습니까?")
            .setPositiveButton("확인") { dialog, which ->
                // 확인 클릭 시 계정 탈퇴 처리
                performAccountWithdrawal()
            }
            .setNegativeButton("취소") { dialog, which ->
                // 취소 클릭 시 다이얼로그 닫기
                dialog.dismiss()
            }
            .show()
    }

    // 계정 탈퇴 처리 함수
    private fun performAccountWithdrawal() {
        // 탈퇴 후 로그인 화면으로 이동?
        /*val action = UserInfoManageFragmentDirections.actionUserInfoManageToLoginFragment()
        findNavController().navigate(action)*/

        // 탈퇴 완료 메시지
        Toast.makeText(requireContext(), "계정이 탈퇴되었습니다.", Toast.LENGTH_SHORT).show()
    }
}
