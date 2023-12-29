package com.example.suitmediatest.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.suitmediatest.adapter.UserAdapter
import com.example.suitmediatest.databinding.ActivityThirdBinding
import com.example.suitmediatest.repository.UserRepository
import com.example.suitmediatest.viewmodel.UserViewModel
import com.example.suitmediatest.viewmodel.UserViewModelFactory

class ThirdActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThirdBinding
    private lateinit var viewModel: UserViewModel
    private lateinit var adapter: UserAdapter
    private var currentPage = 1
    private val pageSize = 10
    private var totalPages = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()
        setAdapter()
        showData()

        binding.refreshUser.setOnRefreshListener {
            loadNextPage()
            binding.refreshUser.isRefreshing = false
        }

        binding.rvUser.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                val childCount = layoutManager.childCount
                val itemCount = layoutManager.itemCount
                val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                if (childCount + firstVisibleItemPosition >= itemCount && firstVisibleItemPosition >= 0) {
                    loadNextPage()
                }
            }
        })

        binding.ivBack.setOnClickListener{
            setResult(Activity.RESULT_OK, Intent())
            finish()
        }
    }

    private fun init() {
        val viewModelFactory = UserViewModelFactory(UserRepository())
        viewModel = ViewModelProvider(this, viewModelFactory)[UserViewModel::class.java]

        binding.rvUser.layoutManager = LinearLayoutManager(this)
        binding.rvUser.setHasFixedSize(true)
    }

    private fun setAdapter() {
        adapter = UserAdapter(this, arrayListOf()) {
            val name = "${it.first_name} " + it.last_name
            Toast.makeText(this, name, Toast.LENGTH_SHORT).show()

            val intent = Intent()
            intent.putExtra("selected_name", name)
            setResult(RESULT_OK, intent)
            finish()
        }
    }

    private fun showData() {
        viewModel.users.observe(this, Observer {
            if (it.isSuccessful) {
                totalPages = it.body()!!.total_pages
                adapter.setData(it.body()!!.users)
                adapter.notifyDataSetChanged()
                binding.rvUser.adapter = adapter
            } else {
                Toast.makeText(this, "failed to get data", Toast.LENGTH_SHORT).show()
            }
        })
        viewModel.getUsers(currentPage, pageSize)
    }

    private fun loadNextPage() {
        if (currentPage < totalPages) {
            currentPage++
            viewModel.getUsers(currentPage, pageSize)
        }
    }
}