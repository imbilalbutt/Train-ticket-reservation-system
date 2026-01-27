
INSERT INTO users(
    user_id,
      created_by,
      created_date,
      is_non_locked,
      modified_by,
      modified_date,
      active_status,
      cell_number,
      cnic_number,
      email,
      first_name,
      home_address,
      last_name,
      password,
      reconfirm_password,
      roles,
      username)
Values(1,
       'SQL',
       '2022-04-09T18:00:00.000',
       true,
       'SQL',
       '2022-04-09T18:00:00.000',
       true,
       '+13040000100',
       '800007000001',
       'email@yahoo.com',
       'Bilal Ahmad',
       'Home Address Road',
       'Butt',
--        '$2a$12$9INYZXwgGFjMBNxAw.nFeelD4CuCiHXzECMkTPnKnK3NsLIYzdumW',
--        '$2a$12$9INYZXwgGFjMBNxAw.nFeelD4CuCiHXzECMkTPnKnK3NsLIYzdumW',

--     password@123
    '$2a$12$lzVAKxOZEMyzTqLE.SIacux6UQASzJjWP/VD29Dcx6dy.bovclBgC',
       '$2a$12$lzVAKxOZEMyzTqLE.SIacux6UQASzJjWP/VD29Dcx6dy.bovclBgC',
       'user',
       'imbilalbutt');



INSERT INTO users(
    user_id,
    created_by,
    created_date,
    is_non_locked,
    modified_by,
    modified_date,
    active_status,
    cell_number,
    cnic_number,
    email,
    first_name,
    home_address,
    last_name,
    password,
    reconfirm_password,
    roles,
    username)
Values(2,
       'SQL',
       '2022-04-09T18:00:00.000',
       true,
       'SQL',
       '2022-04-09T18:00:00.000',
       true,
       '+13040000100',
       '800007000001',
       'email2@yahoo.com',
       'Bilal Ahmad',
       'Home Address Road',
       'Butt',
--        '$2a$12$9INYZXwgGFjMBNxAw.nFeelD4CuCiHXzECMkTPnKnK3NsLIYzdumW',
--        '$2a$12$9INYZXwgGFjMBNxAw.nFeelD4CuCiHXzECMkTPnKnK3NsLIYzdumW',

--     password@123
       '$2a$12$lzVAKxOZEMyzTqLE.SIacux6UQASzJjWP/VD29Dcx6dy.bovclBgC',
       '$2a$12$lzVAKxOZEMyzTqLE.SIacux6UQASzJjWP/VD29Dcx6dy.bovclBgC',
       'user',
       'imbilalbutt2');




package com.imbilalbutt.allneedaone.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.imbilalbutt.allneedaone.src.model.ServiceItem;
import com.imbilalbutt.allneedaone.databinding.FragmentHomeBinding;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private ServicesAdapter adapter;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setupRecyclerView();
        loadDummyData();
    }

    private void setupRecyclerView() {
        binding.RecyclerViewCardsListDisplay.setLayoutManager(new LinearLayoutManager(requireContext()));
        adapter = new ServicesAdapter(new ArrayList<>());
        binding.RecyclerViewCardsListDisplay.setAdapter(adapter);
    }

    private void loadDummyData() {
        List<ServiceItem> dummyList = new ArrayList<>();
        dummyList.add(new ServiceItem("1", "Professional Plumbing", "Home Improvement • 2.4 km", "$50/hr", "4.8 (120)", ""));
        dummyList.add(new ServiceItem("2", "Home Cleaning", "Sanitation • 1.2 km", "$30/hr", "4.5 (85)", ""));
        dummyList.add(new ServiceItem("3", "Car Wash & Wax", "Automotive • 3.5 km", "$25/pkg", "4.9 (200)", ""));
        dummyList.add(new ServiceItem("4", "Electrician Expert", "Repairs • 0.8 km", "$60/hr", "4.7 (45)", ""));
        dummyList.add(new ServiceItem("5", "Hair Styling", "Beauty • 1.5 km", "$40/hr", "4.6 (150)", ""));

        // Update adapter with dummy data
        adapter = new ServicesAdapter(dummyList);
        binding.RecyclerViewCardsListDisplay.setAdapter(adapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}