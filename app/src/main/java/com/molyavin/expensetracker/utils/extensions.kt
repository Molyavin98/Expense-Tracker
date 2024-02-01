package com.molyavin.expensetracker.utils

import com.google.android.material.textfield.TextInputLayout
fun TextInputLayout.getTextString(): String? = this.editText?.text?.toString()


