package br.com.motivation.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import br.com.motivation.infra.MotivationConstants
import br.com.motivation.R
import br.com.motivation.infra.SecurityPreferences
import br.com.motivation.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityUserBinding;
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityUserBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.buttonSave.setOnClickListener(this)
        verifyUserName()
    }

    private fun verifyUserName() {
        val name = SecurityPreferences(this).getString(MotivationConstants.KEY.USER_NAME)
        if(name != ""){
            startActivity(Intent(this, UserActivity::class.java))
            finish()
        }
    }

    override fun onClick(v: View) {
        if(v.id == R.id.button_save){
            handleSave()
        }
    }

    private fun handleSave() {
        val name = binding.editYourName.text.toString()
        if(name != ""){
            SecurityPreferences(this).storeString(MotivationConstants.KEY.USER_NAME, name)
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }else{
            Toast.makeText(this, R.string.validation_mandatory_name, Toast.LENGTH_SHORT).show()
        }
    }
}