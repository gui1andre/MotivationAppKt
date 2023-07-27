package br.com.motivation.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import br.com.motivation.infra.MotivationConstants
import br.com.motivation.R
import br.com.motivation.data.Mock
import br.com.motivation.infra.SecurityPreferences
import br.com.motivation.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding;
    private var categoryId = MotivationConstants.FILTER.All

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setListeners()
        handleFilter(R.id.image_all)
        handleUserName()

    }

    private fun setListeners() {
        binding.buttonNewPharese.setOnClickListener(this)
        binding.imageAll.setOnClickListener(this)
        binding.imageHappy.setOnClickListener(this)
        binding.imageSunny.setOnClickListener(this)
    }

    private fun handleUserName() {
        val name = SecurityPreferences(this).getString(MotivationConstants.KEY.USER_NAME)
        binding.textUserName.text = "Olá, ${name}!"
    }

    override fun onClick(v: View) {
        if(v.id == R.id.button_new_pharese){
            binding.textPhrase.text = Mock().getPhrase(categoryId)
        }else if(v.id in listOf(R.id.image_all, R.id.image_happy, R.id.image_sunny)){
            handleFilter(v.id)
        }
    }

    private fun handleFilter(id: Int) {
        binding.imageAll.setColorFilter(ContextCompat.getColor(this, R.color.black))
        binding.imageHappy.setColorFilter(ContextCompat.getColor(this, R.color.black))
        binding.imageSunny.setColorFilter(ContextCompat.getColor(this, R.color.black))
        when (id) {
            R.id.image_all -> {
                binding.imageAll.setColorFilter(ContextCompat.getColor(this, R.color.white))

                categoryId = MotivationConstants.FILTER.All
            }
            R.id.image_happy -> {
                binding.imageHappy.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationConstants.FILTER.HAPPY
            }
            R.id.image_sunny -> {
                binding.imageSunny.setColorFilter(ContextCompat.getColor(this, R.color.white))
                categoryId = MotivationConstants.FILTER.SUNNY
            }
        }
    }
}