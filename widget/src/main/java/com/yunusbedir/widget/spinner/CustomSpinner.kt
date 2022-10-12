package com.yunusbedir.widget.spinner

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import android.view.inputmethod.EditorInfo.IME_FLAG_NO_FULLSCREEN
import android.widget.AdapterView
import android.widget.AutoCompleteTextView
import androidx.core.widget.doOnTextChanged
import com.google.android.material.textfield.TextInputLayout
import com.yunusbedir.widget.R

class CustomSpinner : TextInputLayout, AdapterView.OnItemSelectedListener,
    AdapterView.OnItemClickListener {

    private var attrs: AttributeSet? = null

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(
        context, attrs
    ) {
        this.attrs = attrs
        getAttributes()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(
        context, attrs, defStyle
    ) {
        this.attrs = attrs
        getAttributes()
    }

    private var autoCompleteTextViewAdapter = CustomArrayAdapter(
        context, R.layout.custom_item_view_spinner, arrayListOf()
    )

    private var autoCompleteTextView: AutoCompleteTextView? = null
    private var onItemSelectedListener: OnItemSelectedListener? = null
    private var onTextChangeListener: OnTextChangeListener? = null

    private var isSpinner = true
        set(value) {
            autoCompleteTextView?.apply {
                this.isFocusable = value.not()
            }
            field = value
        }

    init {
        boxBackgroundMode = BOX_BACKGROUND_OUTLINE
        boxBackgroundColor = Color.WHITE
        endIconMode = END_ICON_DROPDOWN_MENU
        setPadding(0, 0, 0, 0)
        setBoxCornerRadii(10F, 10F, 10F, 10F)
        createAutoCompleteTextView()
        setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) autoCompleteTextView?.showDropDown()
        }
    }

    private fun getAttributes() {
        val a = context.obtainStyledAttributes(
            attrs, R.styleable.CustomSpinner, 0, 0
        )
        isSpinner = a.getBoolean(R.styleable.CustomSpinner_isSpinner, true)
        a.recycle()
    }

    private fun createAutoCompleteTextView() {
        val layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        autoCompleteTextView = AutoCompleteTextView(context)
        autoCompleteTextView?.setPadding(40, 0, 0, 0)
        autoCompleteTextView?.layoutParams = layoutParams
        autoCompleteTextView?.setAdapter(autoCompleteTextViewAdapter)
        autoCompleteTextView?.imeOptions = IME_FLAG_NO_FULLSCREEN
        autoCompleteTextView?.threshold = 1
        autoCompleteTextView?.isSingleLine = true
        autoCompleteTextView?.maxLines = 1
        autoCompleteTextView?.textSize = 10F
        autoCompleteTextView?.onItemSelectedListener = this
        autoCompleteTextView?.onItemClickListener = this
        autoCompleteTextView?.doOnTextChanged { text, start, before, count ->
            onTextChangeListener?.onTextChange(text)
        }
        autoCompleteTextView?.setOnClickListener {
            autoCompleteTextView?.showDropDown()
        }
        autoCompleteTextView?.setSelection(0)
        addView(autoCompleteTextView)
    }

    fun setList(list: List<String>) {
        autoCompleteTextViewAdapter.setList(list)
    }

    fun getList(): List<String> {
        return autoCompleteTextViewAdapter.getList()
    }

    fun addAll(list: List<String>) {
        val tempList = arrayListOf<String>()
        tempList.addAll(getList())
        tempList.addAll(list)
        autoCompleteTextViewAdapter.setList(tempList)
    }

    fun getSelectedItem(): String {
        return autoCompleteTextView?.text.toString()
    }

    fun getSelectedItemPosition(): Int {
        return autoCompleteTextView?.selectionStart ?: 0
    }


    /**
     * setSelectionItem -1 when you want select last item
     */
    fun setSelectionItem(position: Int) {
        if (position == -1) {
            autoCompleteTextView?.setSelection(getList().size - 1)
            return
        }
        if (position < getList().size && position >= 0) {
            try {
                if (position == -1) {
                    autoCompleteTextView?.setText(getList()[getList().size - 1], false)
                    return
                }
                if (position < getList().size && position >= 0) {
                    autoCompleteTextView?.setText(getList()[position], false)
                    onItemSelectedListener?.onItemSelected(this, position)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun setOnItemSelectedListener(onItemSelectedListener: OnItemSelectedListener) {
        this.onItemSelectedListener = onItemSelectedListener
    }

    fun setOnTextChangeListener(onTextChangeListener: OnTextChangeListener) {
        this.onTextChangeListener = onTextChangeListener
    }

    //region Override Methods
    //region override onItemSelectedListener
    override fun onItemSelected(
        parent: AdapterView<*>?, view: View?, position: Int, id: Long
    ) {
        autoCompleteTextView?.threshold = Integer.MAX_VALUE
        onItemSelectedListener?.onItemSelected(this, position)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        onItemSelectedListener?.onItemSelected(this, position)
    }

    fun textClear() {
        autoCompleteTextView?.setText("", false)
    }

    fun setText(text: String) {
        // autoCompleteTextView?.setSelection(getList().indexOf(text))
        autoCompleteTextView?.setText(text, false)
    }

    //endregion override onItemSelectedListener
    //endregion Override Methods
    interface OnItemSelectedListener {
        fun onItemSelected(view: CustomSpinner, position: Int)
        fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long)

    }

    interface OnClickListener {
        fun onClickListener(view: CustomSpinner)
    }

    interface OnTextChangeListener {
        fun onTextChange(text: CharSequence?)
    }
}
