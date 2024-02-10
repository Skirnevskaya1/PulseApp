package net.svichch.geekbrains.android.pressureandpulse.ui.addPage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.basgeekball.awesomevalidation.AwesomeValidation
import com.basgeekball.awesomevalidation.ValidationStyle
import net.svichch.geekbrains.android.pressureandpulse.R
import net.svichch.geekbrains.android.pressureandpulse.data.firestore.Pulse
import net.svichch.geekbrains.android.pressureandpulse.data.firestore.viewmodel.FirestoreViewModel
import net.svichch.geekbrains.android.pressureandpulse.databinding.FragmentAddPulseBinding
import net.svichch.geekbrains.android.pressureandpulse.ui.MainActivity
import java.text.SimpleDateFormat
import java.util.*

class FragmentAddPulse : Fragment() {

    private lateinit var fragment: FragmentAddPulseBinding
    private lateinit var validator: AwesomeValidation
    private lateinit var dbFirestore: FirestoreViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragment = FragmentAddPulseBinding.inflate(inflater, container, false)
        return fragment.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dbFirestore = (requireActivity() as MainActivity).getDbFirestore()
        addButtonListner()
        initEditTextValidator()
        addDateToInputText()
    }

    private fun addDateToInputText() {
         val dateFormat = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())

        fragment.inputAddDateTime.setText(dateFormat.format(Date()).toString())
    }

    private fun addButtonListner() {
        fragment.buttonSave.setOnClickListener {
            if (checkForErrorsAll()) {
                addToBase()
                parentFragmentManager.popBackStack()
            }
        }
    }

    private fun addToBase() {
        val pulse = Pulse()
        pulse.date = Date()
        pulse.lowerPressure = fragment.inputAddLowerPressure.text.toString().toInt()
        pulse.upperPressure = fragment.inputAddUpperPressure.text.toString().toInt()
        pulse.pulse = fragment.inputAddPulse.text.toString().toInt()

        dbFirestore.addPulse(pulse)
    }

    private fun initEditTextValidator() {

        validator = AwesomeValidation(ValidationStyle.TEXT_INPUT_LAYOUT)
        validator.setTextInputLayoutErrorTextAppearance(R.style.errorTextInputLayout)
        val regexNum = "\\d+"
        val regexDataTime = "\\d+.\\d+.\\d\\d\\d\\d \\d+:\\d+"

        validator.addValidation(
            fragment.addDateTime,
            regexDataTime,
            getString(R.string.error_date_time)
        )

        validator.addValidation(
            fragment.addLowerPressure,
            regexNum,
            resources.getString(R.string.error_input_num)
        )

        validator.addValidation(
            fragment.addUpperPressure,
            regexNum,
            resources.getString(R.string.error_input_num)
        )

        validator.addValidation(
            fragment.addPulse,
            regexNum,
            resources.getString(R.string.error_input_num)
        )

    }

    private fun checkForErrorsAll(): Boolean {
        return validator.validate()
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            FragmentAddPulse()
    }
}